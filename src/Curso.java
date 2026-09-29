import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Curso implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nome;
    private int totalCreditos;
    private List<Disciplina> gradeCurricular;

    public Curso(String nome, int totalCreditos) {
        this.nome = nome;
        this.totalCreditos = totalCreditos;
        this.gradeCurricular = new ArrayList<>();
    }

    public void adicionarDisciplina(Disciplina d) {
        this.gradeCurricular.add(d);
    }

    public List<Disciplina> getGradeCurricular() {
        return gradeCurricular;
    }

    public String getNome() {
        return nome;
    }
}