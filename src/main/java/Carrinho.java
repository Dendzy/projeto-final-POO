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

    public List<Produto> getProdutos() {
        return produtos;
    }
}
