import java.util.ArrayList;
import java.util.List;

public class Disciplina {
    private String codigo;
    private String nome;
    private Professor professor;
    private List<Aluno> alunosInscritos;
    private boolean ehObrigatoria;
    private StatusDisciplina status; // Alinhado com o diagrama UML

    public Disciplina(String codigo, String nome, boolean ehObrigatoria) {
        this.codigo = codigo;
        this.nome = nome;
        this.ehObrigatoria = ehObrigatoria;
        this.alunosInscritos = new ArrayList<>();
        this.status = StatusDisciplina.AGUARDANDO;
    }

    public boolean adicionarAluno(Aluno aluno) {
        // Stub: Validar limite máximo de 60 alunos e status diferente de CANCELADA
        if (this.alunosInscritos.size() < 60 && this.status != StatusDisciplina.CANCELADA) {
            this.alunosInscritos.add(aluno);
            return true;
        }
        return false;
    }

    public void removerAluno(Aluno aluno) {
        this.alunosInscritos.remove(aluno);
    }

    public void finalizarPeriodoMatricula() {
        // Stub: Validar se tem pelo menos 3 alunos para ativar, senão cancela
        if (this.alunosInscritos.size() >= 3) {
            this.status = StatusDisciplina.ATIVA;
        } else {
            this.status = StatusDisciplina.CANCELADA;
        }
    }

    public void definirProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<Aluno> getAlunosInscritos() { return alunosInscritos; }
    public String getNome() { return nome; }
    public StatusDisciplina getStatus() { return status; }
}