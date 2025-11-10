import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public void inserir(Cliente cliente) {
        String sql = "INSERT INTO clientes (nome, email, telefone) VALUES (?, ?, ?)";
        try (Connection con = ConexaoDB.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)
        ){

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getTelefone());
            stmt.executeUpdate();

            Log.registrar("Cliente cadastrado: " + cliente.getNome() + " - " + cliente.getEmail());

        } catch (SQLException e) {
            System.out.println("Erro ao inserir cliente: " + e.getMessage());
        }
    }

    public List<Cliente> listar() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        try (Connection con = ConexaoDB.conectar();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)
        ){

            while (rs.next()) {
                Cliente c = new Cliente(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("telefone")
                );
                clientes.add(c);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar clientes: " + e.getMessage());
        }
        return clientes;
    }

    public Cliente buscarPorId(int id) {
        String sql = "SELECT * FROM clientes WHERE id = ?";
        try (Connection con = ConexaoDB.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)
        ){

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Cliente(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("telefone")
                );
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar cliente: " + e.getMessage());
        }
        return null;
    }

    public void atualizar(Cliente cliente) {
        String sql = "UPDATE clientes SET nome=?, email=?, telefone=? WHERE id=?";
        try (Connection con = ConexaoDB.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)
        ){

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getTelefone());
            stmt.setInt(4, cliente.getId());
            stmt.executeUpdate();

            Log.registrar("Cliente atualizado: ID " + cliente.getId() + " - " + cliente.getNome());

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar cliente: " + e.getMessage());
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM clientes WHERE id = ?";
        try (Connection con = ConexaoDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)
        ){

            ps.setInt(1, id);
            ps.executeUpdate();

            Log.registrar("Cliente deletado: ID " + id);

        } catch (SQLException e) {
            System.out.println("Erro ao excluir cliente: " + e.getMessage());
        }
    }
}
