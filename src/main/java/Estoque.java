import java.util.HashMap;
import java.util.Map;

public class Estoque {
    private Map<Integer, Produto> produtos;

    public Estoque() {
        produtos = new HashMap<>();
    }

    public void adicionarProduto(Produto p) throws ProdutoJaNoEstoqueException{
        if(produtos.containsKey(p.getId()))
            throw new ProdutoJaNoEstoqueException("O produto "+ p.getNome()+
                    " já cadastrado no sistema");

        produtos.put(p.getId(), p);
    }

    public void removerProduto(Produto p) throws ProdutoNaoEncontradoException{
        if(!produtos.containsKey(p.getId()))
            throw new ProdutoNaoEncontradoException("O produto " +p.getNome()+
                    " não foi encontrado no estoque");

        produtos.remove(p.getId());
    }
}
