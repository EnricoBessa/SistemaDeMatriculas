import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Usuario> usuarios = new ArrayList<>();
    private static List<Curso> cursos = new ArrayList<>();
    private static boolean periodoMatriculaAberto = true;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        carregarEstadoInicial();

        System.out.println("==================================================");
        System.out.println("   SISTEMA DE MATRÍCULAS UNIVERSITÁRIO - PUC MINAS");
        System.out.println("==================================================");

        Usuario usuarioLogado = realizarLogin();

        if (usuarioLogado != null) {
            System.out.println("\nBem-vindo(a), " + usuarioLogado.getNome() + "!");
            if (usuarioLogado instanceof Secretaria) {
                menuSecretaria((Secretaria) usuarioLogado);
            } else if (usuarioLogado instanceof Aluno) {
                menuAluno((Aluno) usuarioLogado);
            } else if (usuarioLogado instanceof Professor) {
                menuProfessor((Professor) usuarioLogado);
            }
        }

        GerenciadorPersistencia.salvarDados(usuarios, cursos);
        System.out.println("Sessão finalizada com sucesso!");
    }

    private static Usuario realizarLogin() {
        System.out.print("ID do Usuário: ");
        String id = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        for (Usuario u : usuarios) {
            if (u.getId().equals(id) && u.autenticar(senha)) {
                return u;
            }
        }
        System.out.println("Credenciais inválidas!");
        return null;
    }

    private static void menuSecretaria(Secretaria sec) {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- MENU SECRETARIA ---");
            System.out.println("1. Alterar Período de Matrículas (Atual: " + (periodoMatriculaAberto ? "ABERTO" : "FECHADO") + ")");
            System.out.println("2. Finalizar Período e Validar Quórum (Mín. 3 alunos)");
            System.out.println("3. Listar Disciplinas e Alunos Matriculados");
            System.out.println("0. Sair");
            System.out.print("Opção: ");
            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcao = -1;
            }

            switch (opcao) {
                case 1 -> {
                    periodoMatriculaAberto = !periodoMatriculaAberto;
                    System.out.println("Status do período alterado para: " + (periodoMatriculaAberto ? "ABERTO" : "FECHADO"));
                }
                case 2 -> {
                    for (Curso c : cursos) {
                        for (Disciplina d : c.getGradeCurricular()) {
                            d.finalizarPeriodoMatricula();
                        }
                    }
                    System.out.println("Processamento concluído! Disciplinas com menos de 3 alunos foram CANCELADAS e as demais foram ATIVADAS.");
                }
                case 3 -> listarDisciplinasGerais();
                case 0 -> System.out.println("Saindo do menu da secretaria...");
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private static void menuAluno(Aluno aluno) {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- MENU ALUNO ---");
            System.out.println("1. Realizar Matrícula em Disciplina");
            System.out.println("2. Cancelar Matrícula");
            System.out.println("3. Ver Minhas Matrículas");
            System.out.println("0. Sair");
            System.out.print("Opção: ");
            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcao = -1;
            }

            switch (opcao) {
                case 1 -> {
                    Disciplina d = selecionarDisciplina();
                    if (d != null) {
                        System.out.print("É disciplina obrigatória? (s/n): ");
                        boolean ehObrigatoria = scanner.nextLine().equalsIgnoreCase("s");
                        aluno.solicitarMatricula(d, ehObrigatoria, periodoMatriculaAberto);
                    }
                }
                case 2 -> {
                    Disciplina d = selecionarDisciplina();
                    if (d != null) {
                        aluno.cancelarMatricula(d, periodoMatriculaAberto);
                    }
                }
                case 3 -> {
                    System.out.println("\n--- SUAS MATRÍCULAS ---");
                    System.out.println("Obrigatórias (" + aluno.getDisciplinasObrigatorias().size() + "/4):");
                    aluno.getDisciplinasObrigatorias().forEach(d -> System.out.println(" - " + d.getNome() + " [" + d.getStatus() + "]"));
                    System.out.println("Optativas (" + aluno.getDisciplinasOptativas().size() + "/2):");
                    aluno.getDisciplinasOptativas().forEach(d -> System.out.println(" - " + d.getNome() + " [" + d.getStatus() + "]"));
                }
                case 0 -> System.out.println("Saindo do menu do aluno...");
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private static void menuProfessor(Professor prof) {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- MENU PROFESSOR ---");
            System.out.println("1. Consultar Alunos por Disciplina");
            System.out.println("0. Sair");
            System.out.print("Opção: ");
            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcao = -1;
            }

            if (opcao == 1) {
                if (prof.getDisciplinasLecionadas().isEmpty()) {
                    System.out.println("Você não possui disciplinas vinculadas.");
                } else {
                    for (Disciplina d : prof.getDisciplinasLecionadas()) {
                        System.out.println("\nDisciplina: " + d.getNome() + " (Status: " + d.getStatus() + ")");
                        List<Aluno> alunos = prof.consultarAlunosMatriculados(d);
                        if (alunos != null && !alunos.isEmpty()) {
                            alunos.forEach(a -> System.out.println("  - Aluno: " + a.getNome() + " (ID: " + a.getId() + ")"));
                        } else {
                            System.out.println("  - Nenhum aluno matriculado até o momento.");
                        }
                    }
                }
            }
        }
    }

    private static Disciplina selecionarDisciplina() {
        List<Disciplina> todas = new ArrayList<>();
        int index = 1;
        System.out.println("\nDisciplinas Disponíveis:");
        for (Curso c : cursos) {
            for (Disciplina d : c.getGradeCurricular()) {
                todas.add(d);
                System.out.println(index + ". " + d.getNome() + " (Vagas ocupadas: " + d.getAlunosInscritos().size() + "/60)");
                index++;
            }
        }

        if (todas.isEmpty()) {
            System.out.println("Nenhuma disciplina cadastrada.");
            return null;
        }

        System.out.print("Escolha o número da disciplina: ");
        try {
            int escolha = Integer.parseInt(scanner.nextLine());
            if (escolha > 0 && escolha <= todas.size()) {
                return todas.get(escolha - 1);
            }
        } catch (NumberFormatException e) {
            // Entrada inválida
        }
        System.out.println("Seleção inválida!");
        return null;
    }

    private static void listarDisciplinasGerais() {
        System.out.println("\n--- LISTAGEM GERAL DE CURSOS E DISCIPLINAS ---");
        for (Curso c : cursos) {
            System.out.println("Curso: " + c.getNome());
            for (Disciplina d : c.getGradeCurricular()) {
                System.out.println("  - Disciplina: " + d.getNome() + " | Status: " + d.getStatus() + " | Inscritos: " + d.getAlunosInscritos().size());
            }
        }
    }

    @SuppressWarnings("unchecked")
    private static void carregarEstadoInicial() {
        List<Object> dados = GerenciadorPersistencia.carregarDados();
        if (!dados.isEmpty()) {
            usuarios = (List<Usuario>) dados.get(0);
            cursos = (List<Curso>) dados.get(1);
        } else {
            Secretaria sec = new Secretaria("SEC01", "Secretaria Central", "admin123");
            Aluno al1 = new Aluno("A1", "Enrico Bessa", "123");
            Aluno al2 = new Aluno("A2", "João Rajão", "123");
            Aluno al3 = new Aluno("A3", "Leonardo Augusto", "123");
            Professor pr1 = new Professor("P1", "Profa. Milena", "123");

            Curso engSoft = new Curso("Engenharia de Software", 3000);
            Disciplina projSoft = new Disciplina("ES01", "Projeto de Software", true);
            Disciplina engProc = new Disciplina("ES02", "Engenharia de Processos", true);

            engSoft.adicionarDisciplina(projSoft);
            engSoft.adicionarDisciplina(engProc);

            pr1.atribuirDisciplina(projSoft);

            usuarios.add(sec);
            usuarios.add(al1);
            usuarios.add(al2);
            usuarios.add(al3);
            usuarios.add(pr1);
            cursos.add(engSoft);
        }
    }
}