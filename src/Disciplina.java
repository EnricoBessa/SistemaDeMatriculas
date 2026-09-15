import java.util.ArrayList;
import java.util.List;

public class Disciplina {
    private String codigo;
    private String nome;
    private Professor professor;
    private List<Aluno> alunosInscritos;
    private boolean ehObrigatoria;
    private boolean ativa;

    public Disciplina(String codigo, String nome, boolean ehObrigatoria) {
        this.codigo = codigo;
        this.nome = nome;
        this.ehObrigatoria = ehObrigatoria;
        this.alunosInscritos = new ArrayList<>();
        this.ativa = false;
    }

    public boolean adicionarAluno(Aluno aluno) {
        // Stub: Validar limite máximo de 60 alunos
        return false;
    }

    public void removerAluno(Aluno aluno) {
        this.alunosInscritos.remove(aluno);
    }

    public void finalizarPeriodoMatricula() {
        // Stub: Validar se tem pelo menos 3 alunos para ativar, senão cancela
    }

    public void definirProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<Aluno> getAlunosInscritos() { return alunosInscritos; }
    public String getNome() { return nome; }
}