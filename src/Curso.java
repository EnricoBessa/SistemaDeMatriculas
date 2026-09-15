import java.util.ArrayList;
import java.util.List;

public class Curso {
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
}