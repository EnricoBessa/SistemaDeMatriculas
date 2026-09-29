import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Professor extends Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Disciplina> disciplinasLecionadas;

    public Professor(String id, String nome, String senha) {
        super(id, nome, senha);
        this.disciplinasLecionadas = new ArrayList<>();
    }

    public List<Aluno> consultarAlunosMatriculados(Disciplina disciplina) {
        if (this.disciplinasLecionadas.contains(disciplina)) {
            return disciplina.getAlunosInscritos();
        }
        System.out.println("Erro: O professor não leciona a disciplina informada.");
        return null;
    }

    public void atribuirDisciplina(Disciplina disciplina) {
        if (!this.disciplinasLecionadas.contains(disciplina)) {
            this.disciplinasLecionadas.add(disciplina);
            disciplina.definirProfessor(this);
        }
    }

    public List<Disciplina> getDisciplinasLecionadas() { return disciplinasLecionadas; }
}