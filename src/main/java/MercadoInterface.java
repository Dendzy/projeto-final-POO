public interface MercadoInterface {
    void cadastrarCliente(Cliente cliente);

    void removerCliente(String cpf);

    void editarCliente(String cpf);

    void adicionarAoCarrinho(Cliente cliente);

    void removerDoCarrinho(Cliente cliente);

    void adicionarAoEstoque(int idProduto);

    void removerDoEstoque(int idProduto);

    void fecharPedido(Cliente cliente);
}
