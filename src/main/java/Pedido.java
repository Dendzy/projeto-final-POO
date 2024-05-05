import java.io.Serializable;
import java.util.List;

public class Pedido implements Serializable {
    private int codigoPedido;
    private static int idPedido = 0;
    Cliente cliente;
    private List<Produto> produtos;
    private double valorTotal;


    public Pedido(Cliente cliente){
        synchronized(Produto.class) {
            idPedido++;
            this.codigoPedido = idPedido;
        }
        this.cliente = cliente;
        this.produtos = cliente.getCarrinho().getProdutos();
        this.valorTotal = cliente.getCarrinho().calculaValorTotal();
    }

    public int getCodigoPedido() {
        return codigoPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public String toString(){
        StringBuilder produtos = new StringBuilder();
        for(Produto p: this.produtos){
            produtos.append(p.getNome()).append(" ").append(p.getPreco());
        }

        return "\nCódigo do pedido: "+ this.codigoPedido+
                "\nCliente: "+ this.cliente.getNome()+
                "\n"+produtos+
                "\nValor total: "+this.valorTotal;
    }
}
