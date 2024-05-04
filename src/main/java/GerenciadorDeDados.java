import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class GerenciadorDeDados {
    private static final String ARQUIVO_CLIENTES = "clientes.dat";
    private static final String ARQUIVO_ESTOQUE = "estoque.dat";
    private static final String ARQUIVO_CARRINHOS = "carrinhos.dat";
    private static final String ARQUIVO_PEDIDOS = "pedidos.dat";

    public HashMap<String, Cliente> recuperarClientes() throws IOException {
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream(ARQUIVO_CLIENTES));
            return (HashMap<String, Cliente>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Não foi possível recuperar clientes!");
            throw new IOException("Não foi possível recuperar os dados do arquivo " + ARQUIVO_CLIENTES);
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }

    public HashMap<Integer, Produto> recuperarEstoque() throws IOException {
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream(ARQUIVO_ESTOQUE));
            return (HashMap<Integer, Produto>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Não foi possível recuperar estoque!");
            throw new IOException("Não foi possível recuperar os dados do arquivo " + ARQUIVO_ESTOQUE);
        }finally {
            if (in != null) {
                in.close();
            }
        }
    }

    public HashMap<String, Carrinho> recuperarCarrinhos() throws IOException {
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream(ARQUIVO_CARRINHOS));
            return (HashMap<String, Carrinho>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Não foi possível recuperar carrinhos!");
            throw new IOException("Não foi possível recuperar os dados do arquivo " + ARQUIVO_CARRINHOS);
        }finally {
            if (in != null) {
                in.close();
            }
        }
    }

    public HashMap<Integer, Pedido> recuperarPedidos() throws IOException {
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream(ARQUIVO_PEDIDOS));
            return (HashMap<Integer, Pedido>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Não foi possível recuperar pedidos!");
            throw new IOException("Não foi possível recuperar os dados do arquivo " + ARQUIVO_PEDIDOS);
        }finally {
            if (in != null) {
                in.close();
            }
        }
    }

    public void salvarClientes(Map<String, Cliente> clientes) throws IOException {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream(ARQUIVO_CLIENTES));
            out.writeObject(clientes);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Erro ao salvar clientes no arquivo " + ARQUIVO_CLIENTES);
        }
    }

    public void salvarEstoque(Map<Integer, Produto> produtos) throws IOException {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream(ARQUIVO_ESTOQUE));
            out.writeObject(produtos);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Erro ao salvar produtos do estoque no arquivo " + ARQUIVO_ESTOQUE);
        }
    }

    public void salvarCarrinhos(Map<String, Carrinho> carrinhos) throws IOException {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream(ARQUIVO_CARRINHOS));
            out.writeObject(carrinhos);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Erro ao salvar carrinhos no arquivo " + ARQUIVO_CARRINHOS);
        }
    }

    public void salvarPedidos(Map<Integer, Pedido> pedidos) throws IOException {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream(ARQUIVO_PEDIDOS));
            out.writeObject(pedidos);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Erro ao salvar pedidos no arquivo " + ARQUIVO_PEDIDOS);
        }
    }


}
