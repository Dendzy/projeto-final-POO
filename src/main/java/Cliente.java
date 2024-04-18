public class Cliente {
    private String nome;
    private String cpf;
    private String endereco;
    private Carrinho carrinho;

    public Cliente(String nome, String cpf, String endereco, Carrinho carrinho) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.carrinho = carrinho;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public Carrinho getCarrinho() {
        return carrinho;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }
}
