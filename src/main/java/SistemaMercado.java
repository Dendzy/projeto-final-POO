import exceptions.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SistemaMercado implements MercadoInterface {

    private Estoque estoque;
    private HashMap<String, Cliente> clientes;
    private HashMap<Integer, Pedido> pedidos;
    private HashMap<String, Carrinho> carrinhos;
    private GerenciadorDeDados gerenciadorDeClientes = new GerenciadorDeDados();
    private GerenciadorDeDados gerenciadorDeEstoque = new GerenciadorDeDados();
    private GerenciadorDeDados gerenciadorDeCarrinhos = new GerenciadorDeDados();
    private GerenciadorDeDados gerenciadorDePedidos = new GerenciadorDeDados();

    public SistemaMercado() {
        estoque = new Estoque();
        clientes = new HashMap<>();
        pedidos = new HashMap<>();
        carrinhos = new HashMap<>();
        recuperarDadosEstoque();
        recuperarDadosClientes();
        recuperarPedidos();
        recuperarCarrinhos();
    }

    @Override
    public void cadastrarCliente(String nome, String cpf, String senha, String enderco, Carrinho carrinho) throws ClienteJaCadastradoException {
        if (clientes.containsKey(cpf))
            throw new ClienteJaCadastradoException("O cliente " + nome + " já está cadastrado");
        Cliente novoCliente = new Cliente(nome, cpf, senha, enderco, carrinho);
        clientes.put(cpf, novoCliente);
        carrinhos.put(cpf,carrinho);
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
    public boolean existeCliente(String cpf) {
        if(clientes.containsKey(cpf)) return true;
        return false;
    }

    @Override
    public void adicionarAoCarrinho(String cpf, int idProduto) {
        try {
            Produto p = estoque.pegarProduto(idProduto);
            Carrinho carrinho;
            if (carrinhos.containsKey(cpf)) {
                carrinho = carrinhos.get(cpf);
            } else {
                // Se não houver um carrinho, cria um novo
                carrinho = new Carrinho();
            }
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
    public boolean removerDoEstoque(int idProduto) {
        try {
            Produto p = estoque.pegarProduto(idProduto);
            return estoque.removerProduto(p);
        } catch(ProdutoNaoEncontradoException e){
            System.err.println(e.getMessage());
        }
        return false;
    }

    public int contaProdutosIguaisNoEstoque(String nomeProduto){
        return estoque.contaProdutosIguaisNoEstoque(nomeProduto);
    }

    @Override
    public Pedido fecharPedido(String cpf) {
        Cliente c = this.clientes.get(cpf);
        Pedido pedido = new Pedido(c);
        for(Produto p: c.getCarrinho().getProdutos()){
            removerDoEstoque(p.getId());
            removerDoCarrinho(cpf,p.getId());
        }
        return pedido;
    }

    public void recuperarPedidos(){
        try{
            this.pedidos = gerenciadorDePedidos.recuperarPedidos();
        }catch(IOException e){
            System.err.println("Não foi possível recuperar os pedidos");
        }
    }

    public void recuperarDadosEstoque(){
        try{
            estoque.setProdutos(gerenciadorDeEstoque.recuperarEstoque());
        }catch(IOException e){
            System.err.println("Não foi possível recuperar os dados do Estoque");
        }
    }

    public void recuperarDadosClientes(){
        try{
           this.clientes = gerenciadorDeClientes.recuperarClientes();
        }catch(IOException e){
            System.err.println("Não foi possível recuperar os dados de Clientes");
        }
    }

    public void recuperarCarrinhos(){
        try{
            this.carrinhos = gerenciadorDeCarrinhos.recuperarCarrinhos();
        }catch (IOException e){
            System.err.println("Não foi possível reuperar os Carrinhos");
        }
    }

    public void salvarDadosCarrinhos(){
        try{
            gerenciadorDeCarrinhos.salvarCarrinhos(this.carrinhos);
        }catch (IOException e){
            System.err.println("Não foi possível salvar os Carrinhos");
        }
    }

    public void salvarDadosEstoque(){
        try{
            gerenciadorDeEstoque.salvarEstoque(estoque.getProdutos());
        }catch (IOException e){
            System.err.println("Não foi possível salvar os dados do Estoque");
        }
    }

    public void salvarDadosClientes(){
        try{
            gerenciadorDeClientes.salvarClientes(this.clientes);
        }catch(IOException e){
            System.err.println("Não foi possível salvar os dados de Clientes");
        }
    }

    public void salvarPedidos(){
        try{
            gerenciadorDePedidos.salvarPedidos(this.pedidos);
        }catch(IOException e){

        }
    }

    @Override
    public void salvarTodosOsDados(){
        salvarDadosCarrinhos();
        salvarDadosEstoque();
        salvarDadosClientes();
        salvarPedidos();
    }

    @Override
    public boolean produtoExisteNoEstoque(String nomeProduto) {
        return estoque.existeProdutoNoEstoque(nomeProduto);
    }

    public Estoque getEstoque() {
        return estoque;
    }

    public HashMap<String, Cliente> getClientes() {
        return clientes;
    }

    @Override
    public Map<Integer, Produto> getProdutos() {
        return estoque.getProdutos();
    }

}
