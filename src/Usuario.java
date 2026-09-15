public abstract class Usuario {
    protected String id;
    protected String nome;
    protected String senha;

    public Usuario(String id, String nome, String senha) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
    }

    public boolean autenticar(String senhaInformada) {
        return this.senha.equals(senhaInformada);
    }

    public String getId() { return id; }
    public String getNome() { return nome; }
}