import exceptions.*;

import java.util.HashMap;

public class SistemaMercado implements MercadoInterface {

    private Estoque estoque;
    private HashMap<String, Cliente> clientes;
    private HashMap<Integer, Pedido> pedidos;
    private HashMap<String, Carrinho> carrinhos;

    public SistemaMercado(){
        estoque = new Estoque();
        clientes = new HashMap<>();
        pedidos = new HashMap<>();
        carrinhos = new HashMap<>();
    }

    @Override
    public void cadastrarCliente(String nome, String cpf, String senha, String enderco, Carrinho carrinho)throws ClienteJaCadastradoException {
        if(clientes.containsKey(cpf)) throw new ClienteJaCadastradoException("O cliente "+nome+" já está cadastrado");
        Cliente novoCliente = new Cliente(nome, cpf, senha ,enderco, carrinho);
        clientes.put(cpf, novoCliente);
    }

    @Override
    public Cliente pesquisarCliente(String cpf) throws ClienteNaoExisteException {
        if(clientes.containsKey(cpf)) return clientes.get(cpf);
        throw new ClienteNaoExisteException("Cliente não existe!");
    }

    @Override
    public void removerCliente(String cpf) throws ClienteNaoExisteException{
        if(clientes.containsKey(cpf)) {
            clientes.remove(cpf);
            return;
        }
        throw new ClienteNaoExisteException("Cliente não existe!");
    }

    @Override
    public void editarCliente(String cpf, String novoNome, String novoEnderco) {

    }

    @Override
    public void adicionarAoCarrinho(String cpf, int idProduto) {
        try {
            Produto p = estoque.pegarProduto(idProduto);
            Carrinho carrinho = new Carrinho();
            if(carrinhos.containsKey(cpf)) carrinho = carrinhos.get(cpf);
            carrinho.adicionar(p);
            carrinhos.put(cpf,carrinho);
        } catch (ProdutoNaoEncontradoException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void removerDoCarrinho(String cpf, int idProduto) {

    }

    @Override
    public void adicionarAoEstoque(TipoProduto tipo, String nome, double preco) {
        Produto novoProduto = new Produto(tipo, nome, preco);
        estoque.adicionarProduto(novoProduto);
    }

    @Override
    public void removerDoEstoque(int idProduto) {

    }

    @Override
    public void fecharPedido(String cpf) {

    }
}
