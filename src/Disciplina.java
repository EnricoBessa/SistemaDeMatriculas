import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Disciplina implements Serializable {

    private static final long serialVersionUID = 1L;

    private String codigo;
    private String nome;
    private Professor professor;
    private List<Aluno> alunosInscritos;
    private boolean ehObrigatoria;
    private StatusDisciplina status;

    public Disciplina(String codigo, String nome, boolean ehObrigatoria) {
        this.codigo = codigo;
        this.nome = nome;
        this.ehObrigatoria = ehObrigatoria;
        this.alunosInscritos = new ArrayList<>();
        this.status = StatusDisciplina.AGUARDANDO;
    }

    public boolean adicionarAluno(Aluno aluno) {
        if (this.alunosInscritos.size() < 60
                && this.status != StatusDisciplina.CANCELADA
                && !this.alunosInscritos.contains(aluno)) {

            this.alunosInscritos.add(aluno);
            return true;
        }

        return false;
    }


    public void removerAluno(Aluno aluno) {
        this.alunosInscritos.remove(aluno);
    }

    public void finalizarPeriodoMatricula() {
        if (this.alunosInscritos.size() >= 3) {
            this.status = StatusDisciplina.ATIVA;
        } else {
            this.status = StatusDisciplina.CANCELADA;
        }
    }

    public void definirProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<Aluno> getAlunosInscritos() {
        return alunosInscritos;
    }

    public String getNome() {
        return nome;
    }

    public StatusDisciplina getStatus() {
        return status;
    }
}
