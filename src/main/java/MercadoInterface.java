import exceptions.*;

public interface MercadoInterface {
    void cadastrarCliente(String nome, String cpf, String senha, String enderco, Carrinho carrinho) throws ClienteJaCadastradoException;

    Cliente pesquisarCliente(String cpf) throws ClienteNaoExisteException;

    void removerCliente(String cpf) throws ClienteNaoExisteException;

    void editarCliente(String cpf, String novoNome, String novoEnderco) throws ClienteNaoExisteException;

    void adicionarAoCarrinho(String cpf, int idProduto);

    void removerDoCarrinho(String cpf, int idProduto);

    Carrinho verCarrinhoDoCliente(String cpf);

    int adicionarAoEstoque(TipoProduto tipo, String nome, double preco);

    void removerDoEstoque(int idProduto);

    boolean produtoExisteNoEstoque(String nomeProduto);

    void fecharPedido(String cpf);
}
