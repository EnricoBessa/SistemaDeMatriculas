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
        // Stub: Lógica de negócio da Sprint 3 validará os limites de 4 obrigatórias e 2 optativas
        return false;
    }

    public boolean cancelarMatricula(Disciplina disciplina, boolean periodoAberto) {
        // Stub: Lógica para remover das listas e atualizar vagas da disciplina
        return false;
    }

    public List<Disciplina> getDisciplinasObrigatorias() { return disciplinasObrigatorias; }
    public List<Disciplina> getDisciplinasOptativas() { return disciplinasOptativas; }
}