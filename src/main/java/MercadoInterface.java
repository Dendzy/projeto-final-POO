public interface MercadoInterface {
    void cadastrarCliente(String nome, String cpf, String enderco, Carrinho carrinho);

    void removerCliente(String cpf);

    void editarCliente(String cpf, String novoNome, String novoEnderco);

    void adicionarAoCarrinho(String cpf, int idProduto);

    void removerDoCarrinho(String cpf, int idProduto);

    void adicionarAoEstoque(int idProduto);

    void removerDoEstoque(int idProduto);

    void fecharPedido(String cpf);
}
