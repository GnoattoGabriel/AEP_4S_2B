import java.time.LocalDateTime;

public class Venda {
    private int id;
    private int clienteId;
    private double valorTotal;
    private LocalDateTime data;

    public Venda() {}

    public Venda(int clienteId, double valorTotal, LocalDateTime data) {
        this.clienteId = clienteId;
        this.valorTotal = valorTotal;
        this.data = data;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getClienteId() { return clienteId; }
    public void setClienteId(int clienteId) { this.clienteId = clienteId; }

    public double getValorTotal() { return valorTotal; }
    public void setValorTotal(double valorTotal) { this.valorTotal = valorTotal; }

    public LocalDateTime getData() { return data; }
    public void setData(LocalDateTime data) { this.data = data; }

    @Override
    public String toString() {
        return "ID: " + id +
                " | Cliente ID: " + clienteId +
                " | Valor Total: R$ " + String.format("%.2f", valorTotal) +
                " | Data: " + data;
    }
}

