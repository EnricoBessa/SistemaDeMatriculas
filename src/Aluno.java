import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Aluno extends Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Disciplina> disciplinasObrigatorias;
    private List<Disciplina> disciplinasOptativas;

    public Aluno(String id, String nome, String senha) {
        super(id, nome, senha);
        this.disciplinasObrigatorias = new ArrayList<>();
        this.disciplinasOptativas = new ArrayList<>();
    }

    public boolean solicitarMatricula(Disciplina disciplina, boolean ehObrigatoria, boolean periodoAberto) {
        if (!periodoAberto) {
            System.out.println("Erro: Período de matrículas está fechado!");
            return false;
        }

        if (ehObrigatoria) {
            if (disciplinasObrigatorias.size() >= 4) {
                System.out.println("Erro: Limite máximo de 4 disciplinas obrigatórias atingido.");
                return false;
            }
            if (disciplina.adicionarAluno(this)) {
                disciplinasObrigatorias.add(disciplina);
                SistemaCobranca.notificarInscricao(this);
                return true;
            }
        } else {
            if (disciplinasOptativas.size() >= 2) {
                System.out.println("Erro: Limite máximo de 2 disciplinas optativas atingido.");
                return false;
            }
            if (disciplina.adicionarAluno(this)) {
                disciplinasOptativas.add(disciplina);
                SistemaCobranca.notificarInscricao(this);
                return true;
            }
        }
        return false;
    }

    public boolean cancelarMatricula(Disciplina disciplina, boolean periodoAberto) {
        if (!periodoAberto) {
            System.out.println("Erro: Fora do período de alteração de matrículas!");
            return false;
        }

        boolean removido = disciplinasObrigatorias.remove(disciplina) || disciplinasOptativas.remove(disciplina);
        if (removido) {
            disciplina.removerAluno(this);
            System.out.println("Matrícula na disciplina " + disciplina.getNome() + " cancelada com sucesso.");
        }
        return removido;
    }

    public List<Disciplina> getDisciplinasObrigatorias() { return disciplinasObrigatorias; }
    public List<Disciplina> getDisciplinasOptativas() { return disciplinasOptativas; }
}