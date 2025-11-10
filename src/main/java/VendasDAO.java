import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VendasDAO {

    public void salvar(Venda venda) {
        String sql = "INSERT INTO vendas (cliente_id, valor_total, data) VALUES (?, ?, ?)";

        try (Connection con = ConexaoDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, venda.getClienteId());
            ps.setDouble(2, venda.getValorTotal());
            ps.setTimestamp(3, Timestamp.valueOf(venda.getData()));

            ps.executeUpdate();
            System.out.println("Venda registrada com sucesso!");

            Log.registrar("Venda registrada: Cliente ID " + venda.getClienteId() +
                    " | Valor Total: " + venda.getValorTotal());

        } catch (SQLException e) {
            System.out.println("Erro ao registrar venda: " + e.getMessage());
        }
    }

    public List<Venda> listar() {
        List<Venda> vendas = new ArrayList<>();
        String sql = "SELECT * FROM vendas";

        try (Connection con = ConexaoDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Venda v = new Venda();
                v.setId(rs.getInt("id"));
                v.setClienteId(rs.getInt("cliente_id"));
                v.setValorTotal(rs.getDouble("valor_total"));
                v.setData(rs.getTimestamp("data").toLocalDateTime());
                vendas.add(v);
            }

            Log.registrar("Listagem de vendas realizada");

        } catch (SQLException e) {
            System.out.println("Erro ao listar vendas: " + e.getMessage());
        }
        return vendas;
    }

    public Venda buscarPorId(int id) {
        Venda venda = null;
        String sql = "SELECT * FROM vendas WHERE id = ?";

        try (Connection con = ConexaoDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                venda = new Venda();
                venda.setId(rs.getInt("id"));
                venda.setClienteId(rs.getInt("cliente_id"));
                venda.setValorTotal(rs.getDouble("valor_total"));
                venda.setData(rs.getTimestamp("data").toLocalDateTime());
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar venda: " + e.getMessage());
        }
        return venda;
    }

    public void deletar(int id) {
        String sql = "DELETE FROM vendas WHERE id = ?";

        try (Connection con = ConexaoDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Venda removida com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao deletar venda: " + e.getMessage());
        }
    }
}
