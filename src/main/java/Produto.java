public class Produto {
    private TipoProduto tipo;
    private static int id = 0;
    private int produtoId;
    private String nome;
    private double preco;

    public Produto(TipoProduto tipo, String nome, double preco) {
        synchronized(Produto.class) {
            id++;
            this.produtoId = id;
        }
        this.tipo = tipo;
        this.nome = nome;
        this.preco = preco;
    }

    public int getId() {
        return produtoId;
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

    @Override
    public String toString() {
        return "ID: " + this.getId() + "\n"+
                "Tipo: " + this.tipo + "\n" +
                "Nome: "+this.nome+ "\n"+
                "Preço: "+this.preco+"\n";

    }
}