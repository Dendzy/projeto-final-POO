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
            System.out.println(e.getMessage());
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
            System.out.println(e.getMessage());
        }
    }

    @Test
    void testaAdicionarAoCarrinho() {
        try{
            sistema.cadastrarCliente("Luiz", "054", "catupiry", "58102", new Carrinho());
            sistema.adicionarAoEstoque(TipoProduto.AUDIO,"fone",20);
            sistema.adicionarAoEstoque(TipoProduto.CONSOLE,"ps4",5000);
            sistema.adicionarAoCarrinho("054",1);


        }catch(ClienteJaCadastradoException e){
            System.out.println(e.getMessage());
        }
    }
}
