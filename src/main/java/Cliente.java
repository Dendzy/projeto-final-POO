import java.io.Serializable;

public class Cliente implements Serializable {
    private String nome;
    private String cpf;
    private String senha;
    private String endereco;
    private Carrinho carrinho;

    public Cliente(String nome, String cpf, String senha, String endereco, Carrinho carrinho) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.endereco = endereco;
        this.carrinho = carrinho;
    }

    public void adicionarAoCarrinho(Produto produto){
        carrinho.adicionar(produto);
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
