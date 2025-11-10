import java.util.Scanner;
import java.util.List;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProdutoDAO produtoDAO = new ProdutoDAO();
        ClienteDAO clienteDAO = new ClienteDAO();
        VendasDAO vendasDAO = new VendasDAO();

        while (true) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1 - Gerenciar Produtos");
            System.out.println("2 - Gerenciar Clientes");
            System.out.println("3 - Gerenciar Vendas");
            System.out.println("4 - Sair");
            System.out.print("Escolha: ");
            int menuPrincipal = scanner.nextInt();
            scanner.nextLine();

            switch (menuPrincipal) {
                case 1:
                    menuProdutos(scanner, produtoDAO);
                    break;

                case 2:
                    menuClientes(scanner, clienteDAO);
                    break;

                case 3:
                    menuVendas(scanner, vendasDAO, clienteDAO);
                    break;

                case 4:
                    System.out.println("Encerrando...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    // ----------------- MENU PRODUTOS -----------------
    private static void menuProdutos(Scanner scanner, ProdutoDAO produtoDAO) {
        while (true) {
            System.out.println("\n=== MENU PRODUTOS ===");
            System.out.println("1 - Cadastrar produto");
            System.out.println("2 - Listar produtos");
            System.out.println("3 - Atualizar produto");
            System.out.println("4 - Deletar produto");
            System.out.println("5 - Buscar por ID");
            System.out.println("6 - Voltar");
            System.out.print("Escolha: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (opcao) {
                    case 1:
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();
                        System.out.print("Preço: ");
                        double preco = scanner.nextDouble();
                        System.out.print("Quantidade: ");
                        int qtd = scanner.nextInt();
                        Produto novo = new Produto(nome, preco, qtd);
                        produtoDAO.salvar(novo);
                        System.out.println("Produto cadastrado com sucesso!");
                        break;

                    case 2:
                        System.out.println("\n=== LISTA DE PRODUTOS ===");
                        for (Produto p : produtoDAO.listar()) {
                            System.out.println(p);
                        }
                        break;

                    case 3:
                        System.out.print("ID do produto a atualizar: ");
                        int idAtualizar = scanner.nextInt();
                        scanner.nextLine();
                        Produto pAtualizar = produtoDAO.buscarPorId(idAtualizar);
                        if (pAtualizar != null) {
                            System.out.print("Novo nome (" + pAtualizar.getNome() + "): ");
                            String novoNome = scanner.nextLine();
                            if (!novoNome.isEmpty()) pAtualizar.setNome(novoNome);

                            System.out.print("Novo preço (" + pAtualizar.getPreco() + "): ");
                            String precoStr = scanner.nextLine();
                            if (!precoStr.isEmpty()) pAtualizar.setPreco(Double.parseDouble(precoStr));

                            System.out.print("Nova quantidade (" + pAtualizar.getQuantidade() + "): ");
                            String qtdStr = scanner.nextLine();
                            if (!qtdStr.isEmpty()) pAtualizar.setQuantidade(Integer.parseInt(qtdStr));

                            produtoDAO.atualizar(pAtualizar);
                            System.out.println("Produto atualizado com sucesso!");
                        } else {
                            System.out.println("Produto não encontrado!");
                        }
                        break;

                    case 4:
                        System.out.print("ID do produto a deletar: ");
                        int idDel = scanner.nextInt();
                        produtoDAO.deletar(idDel);
                        System.out.println("Produto deletado com sucesso!");
                        break;

                    case 5:
                        System.out.print("Digite o ID: ");
                        int idBuscar = scanner.nextInt();
                        Produto p = produtoDAO.buscarPorId(idBuscar);
                        if (p != null) System.out.println(p);
                        else System.out.println("Produto não encontrado!");
                        break;

                    case 6:
                        return;

                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
                scanner.nextLine();
            }
        }
    }

    // ----------------- MENU CLIENTES -----------------
    private static void menuClientes(Scanner scanner, ClienteDAO clienteDAO) {
        while (true) {
            System.out.println("\n=== MENU CLIENTES ===");
            System.out.println("1 - Cadastrar cliente");
            System.out.println("2 - Listar clientes");
            System.out.println("3 - Atualizar cliente");
            System.out.println("4 - Deletar cliente");
            System.out.println("5 - Buscar por ID");
            System.out.println("6 - Voltar");
            System.out.print("Escolha: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (opcao) {
                    case 1:
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();
                        System.out.print("Email: ");
                        String email = scanner.nextLine();
                        System.out.print("Telefone: ");
                        String telefone = scanner.nextLine();
                        Cliente novo = new Cliente(nome, email, telefone);
                        clienteDAO.inserir(novo);
                        System.out.println("Cliente cadastrado com sucesso!");
                        break;

                    case 2:
                        System.out.println("\n=== LISTA DE CLIENTES ===");
                        List<Cliente> clientes = clienteDAO.listar();
                        for (Cliente c : clientes) {
                            System.out.println(c);
                        }
                        break;

                    case 3:
                        System.out.print("ID do cliente a atualizar: ");
                        int idAtualizar = scanner.nextInt();
                        scanner.nextLine();
                        Cliente cAtualizar = clienteDAO.buscarPorId(idAtualizar);
                        if (cAtualizar != null) {
                            System.out.print("Novo nome (" + cAtualizar.getNome() + "): ");
                            String novoNome = scanner.nextLine();
                            if (!novoNome.isEmpty()) cAtualizar.setNome(novoNome);

                            System.out.print("Novo email (" + cAtualizar.getEmail() + "): ");
                            String novoEmail = scanner.nextLine();
                            if (!novoEmail.isEmpty()) cAtualizar.setEmail(novoEmail);

                            System.out.print("Novo telefone (" + cAtualizar.getTelefone() + "): ");
                            String novoTel = scanner.nextLine();
                            if (!novoTel.isEmpty()) cAtualizar.setTelefone(novoTel);

                            clienteDAO.atualizar(cAtualizar);
                            System.out.println("Cliente atualizado com sucesso!");
                        } else {
                            System.out.println("Cliente não encontrado!");
                        }
                        break;

                    case 4:
                        System.out.print("ID do cliente a deletar: ");
                        int idDel = scanner.nextInt();
                        clienteDAO.excluir(idDel);
                        System.out.println("Cliente deletado com sucesso!");
                        break;

                    case 5:
                        System.out.print("Digite o ID: ");
                        int idBuscar = scanner.nextInt();
                        Cliente c = clienteDAO.buscarPorId(idBuscar);
                        if (c != null) System.out.println(c);
                        else System.out.println("Cliente não encontrado!");
                        break;

                    case 6:
                        return;

                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
                scanner.nextLine();
            }
        }
    }

    // ----------------- MENU VENDAS -----------------
    private static void menuVendas(Scanner scanner, VendasDAO vendaDAO, ClienteDAO clienteDAO) {
        while (true) {
            System.out.println("\n=== MENU VENDAS ===");
            System.out.println("1 - Registrar venda");
            System.out.println("2 - Listar vendas");
            System.out.println("3 - Buscar por ID");
            System.out.println("4 - Deletar venda");
            System.out.println("5 - Voltar");
            System.out.print("Escolha: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (opcao) {
                    case 1:
                        System.out.print("ID do cliente: ");
                        int clienteId = scanner.nextInt();
                        scanner.nextLine();
                        Cliente cliente = clienteDAO.buscarPorId(clienteId);

                        if (cliente == null) {
                            System.out.println("Cliente não encontrado!");
                            break;
                        }

                        System.out.print("Valor total da venda: ");
                        double valor = scanner.nextDouble();
                        scanner.nextLine();

                        Venda novaVenda = new Venda(clienteId, valor, LocalDateTime.now());
                        vendaDAO.salvar(novaVenda);
                        System.out.println("Venda registrada com sucesso!");
                        break;

                    case 2:
                        System.out.println("\n=== LISTA DE VENDAS ===");
                        List<Venda> vendas = vendaDAO.listar();
                        for (Venda v : vendas) {
                            System.out.println(v);
                        }
                        break;

                    case 3:
                        System.out.print("Digite o ID da venda: ");
                        int idBuscar = scanner.nextInt();
                        Venda v = vendaDAO.buscarPorId(idBuscar);
                        if (v != null) System.out.println(v);
                        else System.out.println("Venda não encontrada!");
                        break;

                    case 4:
                        System.out.print("ID da venda a deletar: ");
                        int idDel = scanner.nextInt();
                        vendaDAO.deletar(idDel);
                        System.out.println("Venda deletada com sucesso!");
                        break;

                    case 5:
                        return;

                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
                scanner.nextLine();
            }
        }
    }
}
