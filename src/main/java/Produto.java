public class Produto {
    private TipoProduto tipo;
    private int id = 0;
    private String nome;
    private double preco;

    public Produto(TipoProduto tipo, String nome, double preco) {
        this.tipo = tipo;
        this.id++;
        this.nome = nome;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public TipoProduto getTipo() {
        return tipo;
    }

    public void setTipo(TipoProduto tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}