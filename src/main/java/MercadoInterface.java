import exceptions.*;

public interface MercadoInterface {
    void cadastrarCliente(String nome, String cpf, String senha, String enderco, Carrinho carrinho) throws ClienteJaCadastradoException;

    Cliente pesquisarCliente(String cpf) throws ClienteNaoExisteException;

    void removerCliente(String cpf) throws ClienteNaoExisteException;

    void editarCliente(String cpf, String novoNome, String novoEnderco);

    void adicionarAoCarrinho(String cpf, int idProduto);

    void removerDoCarrinho(String cpf, int idProduto);

    void adicionarAoEstoque(TipoProduto tipo, String nome, double preco);

    void removerDoEstoque(int idProduto);

    void fecharPedido(String cpf);
}
