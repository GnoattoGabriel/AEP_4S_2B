public class Funcionario extends Usuario{

    public Funcionario(String nome, String login, String senha) {
        super(nome, login, senha);
    }

    @Override
    public void exibirMenu() {
        System.out.println("Menu do Funcionario: [1] Produtos [2] Vendas");
    }
}
