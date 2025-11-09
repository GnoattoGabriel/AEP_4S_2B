import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public void salvar(Produto produto){
        String sql = "INSERT INTO produtos (nome, preco, quantidade) VALUES (?, ?, ?)";

        try (Connection con = ConexaoDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)
        ){
            ps.setString(1, produto.getNome());
            ps.setDouble(2, produto.getPreco());
            ps.setInt(3, produto.getQuantidade());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao salvar produto!");
        }
    }

    public List<Produto> listar(){
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
        } catch (SQLException e) {
            System.out.println("Erro ao listar produtos!");
        }
        return produtos;
    }

    public Produto buscarPorId(int id){
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
        } catch (SQLException e) {
            System.out.println("Erro ao buscar produto!");
        }
        return null;
    }

    public void atualizar(Produto produto){
        String sql = "UPDATE produtos SET nome = ?, preco = ?, quantidade = ? WHERE id = ?";

        try (Connection con = ConexaoDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, produto.getNome());
            ps.setDouble(2, produto.getPreco());
            ps.setInt(3, produto.getQuantidade());
            ps.setInt(4, produto.getId());
            ps.executeUpdate();
        }  catch (SQLException e) {
            System.out.println("Erro ao atualizar produto!");
        }
    }

    public void deletar (int id){
        String sql = "DELETE FROM produtos WHERE id = ?";

        try (Connection con = ConexaoDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)
        ){
            ps.setInt(1, id);
            ps.executeUpdate();
        }  catch (SQLException e) {
            System.out.println("Erro ao deletar produto!");
        }
    }
}
