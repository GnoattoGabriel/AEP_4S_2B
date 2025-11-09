public class Admin extends Usuario{

    public Admin(String nome, String login, String senha) {
        super(nome, login, senha);
    }

    @Override
    public void exibirMenu() {
        System.out.println("Menu do Administrador: [1] Produtos [2] Vendas [3] Usuários");
    }
}
