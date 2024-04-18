import java.util.HashMap;

public class SistemaMercado implements MercadoInterface{

    private Estoque estoque;
    private HashMap<String, Cliente> clientes;
    private HashMap<Integer, Pedido> pedidos;


    @Override
    public void cadastrarCliente(Cliente cliente) {

    }

    @Override
    public void removerCliente(String cpf) {

    }

    @Override
    public void editarCliente(String cpf) {

    }

    @Override
    public void adicionarAoCarrinho(Cliente cliente) {

    }

    @Override
    public void removerDoCarrinho(Cliente cliente) {

    }

    @Override
    public void adicionarAoEstoque(int idProduto) {

    }

    @Override
    public void removerDoEstoque(int idProduto) {

    }

    @Override
    public void fecharPedido(Cliente cliente) {

    }
}
