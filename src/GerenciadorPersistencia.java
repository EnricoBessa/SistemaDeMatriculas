import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorPersistencia {
    private static final String ARQUIVO_DADOS = "dados_sistema.dat";

    public static void salvarDados(List<Usuario> usuarios, List<Curso> cursos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_DADOS))) {
            oos.writeObject(usuarios);
            oos.writeObject(cursos);
            System.out.println("Dados salvos com sucesso em arquivo!");
        } catch (IOException e) {
            System.err.println("Erro ao salvar dados: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Object> carregarDados() {
        List<Object> dados = new ArrayList<>();
        File arquivo = new File(ARQUIVO_DADOS);

        if (!arquivo.exists()) {
            return dados; // Retorna lista vazia se for a primeira execução
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO_DADOS))) {
            List<Usuario> usuarios = (List<Usuario>) ois.readObject();
            List<Curso> cursos = (List<Curso>) ois.readObject();
            dados.add(usuarios);
            dados.add(cursos);
            System.out.println("Dados do sistema carregados do arquivo!");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar dados salvos. Iniciando novo estado: " + e.getMessage());
        }

        return dados;
    }
}