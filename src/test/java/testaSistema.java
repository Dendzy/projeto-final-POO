import exceptions.ClienteJaCadastradoException;
import exceptions.ClienteNaoExisteException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class testaSistema {

    SistemaMercado sistema;


    @BeforeEach
    void setUp() {
        sistema = new SistemaMercado();

    }

    @Test
    void testaCadastrarCliente() {
        assertThrows(ClienteNaoExisteException.class, () -> sistema.pesquisarCliente("054"));
        try {
            sistema.cadastrarCliente("Luiz", "054", "catupiry", "58102", new Carrinho());
            Cliente c = sistema.pesquisarCliente("054");
            assertEquals("Luiz", c.getNome());
            assertThrows(ClienteJaCadastradoException.class, () ->
                    sistema.cadastrarCliente("", "054", "catupiry", "", new Carrinho()));
        } catch (ClienteJaCadastradoException | ClienteNaoExisteException e) {
            fail("Nenhuma dessas exceções deveria ser lançada!");
        }
    }

    @Test
    void testaRemoverCliente() {
        try {
            sistema.cadastrarCliente("Yago", "122", "dandy", "200", new Carrinho());
            assertThrows(ClienteNaoExisteException.class, () -> sistema.removerCliente("023"));
            assertDoesNotThrow(() -> {
                sistema.removerCliente("122");
            });
        } catch (ClienteJaCadastradoException e) {
            fail("Essa exceção não deveria ser lançada!");
        }
    }

    @Test
    void testaEditarCliente() {
        try {
            sistema.cadastrarCliente("Jose", "2025", "yago", "200", new Carrinho());
            sistema.editarCliente("2025","ana","barao");
            Cliente c = sistema.pesquisarCliente("2025");
            assertFalse(c.getNome().equals("Jose"));
            assertFalse(c.getEndereco().equals("200"));
            assertTrue(c.getNome().equals("ana"));
            assertTrue(c.getEndereco().equals("barao"));
            assertThrows(ClienteNaoExisteException.class, () -> sistema.editarCliente("12586","a","b"));
        } catch (ClienteNaoExisteException | ClienteJaCadastradoException e) {
            fail("Nenhuma dessas exceções deveria ser lançada!");
        }
    }

    @Test
    void testaAdicionarAoEstoque() {
        try {
            sistema.cadastrarCliente("Roberto", "354", "arroz", "82749", new Carrinho());
            sistema.adicionarAoEstoque(TipoProduto.AUDIO, "microfone", 100);
            assertTrue(sistema.produtoExisteNoEstoque("microfone"));
            sistema.adicionarAoEstoque(TipoProduto.CONSOLE, "xbox", 3000);
            assertTrue(sistema.produtoExisteNoEstoque("xbox"));
        } catch (ClienteJaCadastradoException e) {
            fail("Essa exceção não deveria ser lançada!");
        }
    }

    @Test
    void testaRemoverDoEstoque(){
        try {
            sistema.cadastrarCliente("Louise", "082", "Bela", "127632", new Carrinho());
            int p1ID = sistema.adicionarAoEstoque(TipoProduto.GAMER, "monitor", 999);
            int p2ID = sistema.adicionarAoEstoque(TipoProduto.CONSOLE, "nintendo switch", 2200);
            Estoque estoque = sistema.getEstoque();
            for(Produto p: estoque.getProdutos().values()){
                System.out.println(p);
            }
            sistema.removerDoEstoque(p1ID);
            assertFalse(sistema.produtoExisteNoEstoque("monitor"));
            sistema.removerDoEstoque(p2ID);
            for(Produto p: estoque.getProdutos().values()){
                System.out.println(p);
            }
            assertFalse(sistema.produtoExisteNoEstoque("nintendo switch"));
        } catch (ClienteJaCadastradoException e) {
            fail("Essa exceção não deveria ser lançada!");
        }

    }

    @Test
    void testaAdicionarAoCarrinho() {
        try {
            sistema.cadastrarCliente("Luiz", "054", "catupiry", "58102", new Carrinho());
            int p1ID = sistema.adicionarAoEstoque(TipoProduto.AUDIO, "fone", 20);
            int p2ID = sistema.adicionarAoEstoque(TipoProduto.CONSOLE, "ps4", 5000);
            sistema.adicionarAoCarrinho("054", p1ID);
            sistema.adicionarAoCarrinho("054", p2ID);
            assertEquals("fone", sistema.verCarrinhoDoCliente("054").getProdutos().get(0).getNome());
            assertEquals("ps4", sistema.verCarrinhoDoCliente("054").getProdutos().get(1).getNome());
            Estoque estoque = sistema.getEstoque();
            for(Produto p: estoque.getProdutos().values()){
                System.out.println(p);
            }
        } catch (ClienteJaCadastradoException e) {
            fail("Essa exceção não deveria ser lançada!");
        }
    }

    @Test
    void testaRemoverDoCarrinho() {
        try {
            sistema.cadastrarCliente("Luiz", "054", "catupiry", "58102", new Carrinho());
            int p1ID = sistema.adicionarAoEstoque(TipoProduto.AUDIO, "fone", 20);
            sistema.removerDoCarrinho("054",p1ID);
            assertTrue(sistema.verCarrinhoDoCliente("054").getProdutos().size() == 0);
        } catch (ClienteJaCadastradoException e) {
            fail("Essa exceção não deveria ser lançada!");
        }
    }
}
