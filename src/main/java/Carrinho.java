import java.util.ArrayList;
import java.util.List;

public class Carrinho {
    private List<Produto> produtos;

    public Carrinho(){
        produtos = new ArrayList<>();
    }

    public void adicionar(Produto p){
        produtos.add(p);
    }

    public void remover(Produto p) {
        produtos.remove(p);
    }

    public List<Produto> getProdutos() {
        return produtos;
    }
}
