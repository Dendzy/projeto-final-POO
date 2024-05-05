import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Estoque {
    private Map<Integer, Produto> produtos;

    public Estoque() {
        produtos = new HashMap<>();
    }

    public void adicionarProduto(Produto p){
        produtos.put(p.getId(), p);
    }

    public boolean removerProduto(Produto p) throws ProdutoNaoEncontradoException{
       if(!existeProdutoNoEstoque(p.getNome()))
            return false;
        produtos.remove(p.getId());
        return true;
    }

    public Produto pegarProduto(int idProduto) throws ProdutoNaoEncontradoException{
        if(!produtos.containsKey(idProduto)) throw new ProdutoNaoEncontradoException("Produto de id "+idProduto+" não existe no Estoque!");
        return produtos.get(idProduto);
    }

    public boolean existeProdutoNoEstoque(String nomeProduto) {
       for(Produto p: this.produtos.values()){
           if(p.getNome().equals(nomeProduto)){
               return true;
           }
       }
       return false;
    }

    public Map<Integer, Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(Map<Integer, Produto> produtos) {
        this.produtos = produtos;
    }
}
