import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Carrinho implements Serializable {
    private List<Produto> produtos;

    public Carrinho(){
        produtos = new ArrayList<>();
    }

    public boolean contemProduto(int idProduto){
        for(Produto p: this.produtos){
            if(p.getId() == idProduto) return true;
        }
        return false;
    }

    public void adicionar(Produto p){
        produtos.add(p);
    }

    public int contaProdutosIguaisNoCarrinho(String nomeProduto){
        int cont = 0;
        for(Produto p: this.produtos){
            if(p.getNome().equals(nomeProduto)) cont++;
        }
        return cont;
    }

    public double calculaValorTotal(){
        double valorTotal = 0;
        for(Produto p: this.produtos){
            valorTotal += p.getPreco();
        }
        return valorTotal;
    }

    public void remover(Produto p) {
        produtos.remove(p);
    }

    public List<Produto> getProdutos() {
        return produtos;
    }
}
