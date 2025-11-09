import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public void salvar(Produto produto) throws SQLException {
        String sql = "INSERT INTO produtos (nome, preco, quantidade) VALUES (?, ?, ?)";

        try (Connection con = ConexaoDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)
        ){
            ps.setString(1, produto.getNome());
            ps.setDouble(2, produto.getPreco());
            ps.setInt(3, produto.getQuantidade());
            ps.executeUpdate();

        }
    }

    public List<Produto> listar() throws SQLException {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produtos";
        try (Connection con = ConexaoDB.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)
        ){
            while (rs.next()){
                produtos.add(new Produto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDouble("preco"),
                        rs.getInt("quantidade")
                ));
            }
        }
        return produtos;
    }

    public Produto buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM produtos WHERE id = ?";

        try (Connection con = ConexaoDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)
        ){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Produto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDouble("preco"),
                        rs.getInt("quantidade")
                );
            }
        }
        return null;
    }

    public void atualizar(Produto produto) throws SQLException {
        String sql = "UPDATE produtos SET nome = ?, preco = ?, quantidade = ? WHERE id = ?";

        try (Connection con = ConexaoDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, produto.getNome());
            ps.setDouble(2, produto.getPreco());
            ps.setInt(3, produto.getQuantidade());
            ps.setInt(4, produto.getId());
            ps.executeUpdate();
        }
    }

    public void deletar (int id) throws SQLException {
        String sql = "DELETE FROM produtos WHERE id = ?";

        try (Connection con = ConexaoDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)
        ){
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
