import java.util.HashMap;

public class SistemaMercado implements MercadoInterface{

    private Estoque estoque;
    private HashMap<String, Cliente> clientes;
    private HashMap<Integer, Pedido> pedidos;


    @Override
    public void cadastrarCliente(String nome, String cpf, String enderco, Carrinho carrinho) {

    }

    @Override
    public void removerCliente(String cpf) {

    }

    @Override
    public void editarCliente(String cpf, String novoNome, String novoEnderco) {

    }

    @Override
    public void adicionarAoCarrinho(String cpf, int idProduto) {

    }

    @Override
    public void removerDoCarrinho(String cpf, int idProduto) {

    }

    @Override
    public void adicionarAoEstoque(int idProduto) {

    }

    @Override
    public void removerDoEstoque(int idProduto) {

    }

    @Override
    public void fecharPedido(String cpf) {

    }
}
