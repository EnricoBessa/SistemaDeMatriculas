import java.util.ArrayList;
import java.util.List;

public class Aluno extends Usuario {
    private List<Disciplina> disciplinasObrigatorias;
    private List<Disciplina> disciplinasOptativas;

    public Aluno(String id, String nome, String senha) {
        super(id, nome, senha);
        this.disciplinasObrigatorias = new ArrayList<>();
        this.disciplinasOptativas = new ArrayList<>();
    }

    public boolean solicitarMatricula(Disciplina disciplina, boolean ehObrigatoria, boolean periodoAberto) {
        // Stub: Valida os limites de 4 obrigatórias e 2 optativas
        if (!periodoAberto) return false;

        if (ehObrigatoria && disciplinasObrigatorias.size() < 4) {
            disciplinasObrigatorias.add(disciplina);
            disciplina.adicionarAluno(this);
            SistemaCobranca.notificarInscricao(this); // Alinhado com a dependência da UML
            return true;
        } else if (!ehObrigatoria && disciplinasOptativas.size() < 2) {
            disciplinasOptativas.add(disciplina);
            disciplina.adicionarAluno(this);
            SistemaCobranca.notificarInscricao(this); // Alinhado com a dependência da UML
            return true;
        }
        return false;
    }

    public boolean cancelarMatricula(Disciplina disciplina, boolean periodoAberto) {
        if (!periodoAberto) return false;
        
        boolean removido = disciplinasObrigatorias.remove(disciplina) || disciplinasOptativas.remove(disciplina);
        if (removido) {
            disciplina.removerAluno(this);
        }
        return removido;
    }

    public List<Disciplina> getDisciplinasObrigatorias() { return disciplinasObrigatorias; }
    public List<Disciplina> getDisciplinasOptativas() { return disciplinasOptativas; }
}