import java.util.ArrayList;
import java.util.List;

public class Professor extends Usuario {
    private List<Disciplina> disciplinasLecionadas;

    public Professor(String id, String nome, String senha) {
        super(id, nome, senha);
        this.disciplinasLecionadas = new ArrayList<>();
    }

    public List<Aluno> consultarAlunosMatriculados(Disciplina disciplina) {
        // Stub: Retorna a lista de alunos de uma disciplina se o professor lecionar ela
        return null;
    }

    public void atribuirDisciplina(Disciplina disciplina) {
        this.disciplinasLecionadas.add(disciplina);
    }
}