import exceptions.*;

import java.util.HashMap;

public class SistemaMercado implements MercadoInterface {

    private Estoque estoque;
    private HashMap<String, Cliente> clientes;
    private HashMap<Integer, Pedido> pedidos;
    private HashMap<String, Carrinho> carrinhos;

    public SistemaMercado() {
        estoque = new Estoque();
        clientes = new HashMap<>();
        pedidos = new HashMap<>();
        carrinhos = new HashMap<>();
    }

    @Override
    public void cadastrarCliente(String nome, String cpf, String senha, String enderco, Carrinho carrinho) throws ClienteJaCadastradoException {
        if (clientes.containsKey(cpf))
            throw new ClienteJaCadastradoException("O cliente " + nome + " já está cadastrado");
        Cliente novoCliente = new Cliente(nome, cpf, senha, enderco, carrinho);
        clientes.put(cpf, novoCliente);
    }

    @Override
    public Cliente pesquisarCliente(String cpf) throws ClienteNaoExisteException {
        if (!clientes.containsKey(cpf)) throw new ClienteNaoExisteException("Cliente não existe!");
        return clientes.get(cpf);
    }

    @Override
    public void removerCliente(String cpf) throws ClienteNaoExisteException {
        if(!clientes.containsKey(cpf)) throw new ClienteNaoExisteException("Cliente não existe!");
        clientes.remove(cpf);
    }

    @Override
    public void editarCliente(String cpf, String novoNome, String novoEnderco) throws ClienteNaoExisteException{
        if(!clientes.containsKey(cpf)) throw new ClienteNaoExisteException("Cliente não existe!");
        Cliente clienteParaEditar = clientes.get(cpf);
        clienteParaEditar.setNome(novoNome);
        clienteParaEditar.setEndereco(novoEnderco);
    }

    @Override
    public void adicionarAoCarrinho(String cpf, int idProduto) {
        try {
            Produto p = estoque.pegarProduto(idProduto);
            Carrinho carrinho = new Carrinho();
            if (carrinhos.containsKey(cpf)) carrinho = carrinhos.get(cpf);
            carrinho.adicionar(p);
            carrinhos.put(cpf, carrinho);
        } catch (ProdutoNaoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void removerDoCarrinho(String cpf, int idProduto) {
        try {
            Produto p = estoque.pegarProduto(idProduto);
            Carrinho carrinho = new Carrinho();
            if (carrinhos.containsKey(cpf)) carrinho = carrinhos.get(cpf);
            carrinho.remover(p);
            carrinhos.put(cpf, carrinho);
        } catch (ProdutoNaoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Carrinho verCarrinhoDoCliente(String cpf) {
        return this.carrinhos.get(cpf);
    }

    @Override
    public int adicionarAoEstoque(TipoProduto tipo, String nome, double preco) {
        Produto novoProduto = new Produto(tipo, nome, preco);
        estoque.adicionarProduto(novoProduto);
        return novoProduto.getId();
    }

    @Override
    public void removerDoEstoque(int idProduto) {
       try {
           Produto p = estoque.pegarProduto(idProduto);
           estoque.removerProduto(p);
       }catch(ProdutoNaoEncontradoException e){
           System.err.println(e.getMessage());
       }

    }

    @Override
    public void fecharPedido(String cpf) {

    }

    @Override
    public boolean produtoExisteNoEstoque(String nomeProduto) {
        return estoque.existeProdutoNoEstoque(nomeProduto);
    }

    public Estoque getEstoque() {
        return estoque;
    }
}
