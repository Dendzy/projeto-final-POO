import javax.swing.*;
import java.awt.event.*;

class ClienteController extends JFrame implements ActionListener {
    private JButton buscaButton, adicionarCarrinhoButton, removerCarrinhoButton;

    public ClienteController() {
        setTitle("Menu do Cliente");
        setSize(300, 150);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        buscaButton = new JButton("Buscar Produto");
        adicionarCarrinhoButton = new JButton("Adicionar ao Carrinho");
        removerCarrinhoButton = new JButton("Remover do Carrinho");

        buscaButton.addActionListener(this);
        adicionarCarrinhoButton.addActionListener(this);
        removerCarrinhoButton.addActionListener(this);

        panel.add(buscaButton);
        panel.add(adicionarCarrinhoButton);
        panel.add(removerCarrinhoButton);

        add(panel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        // Implemente as ações correspondentes para cada botão do menu do cliente
    }
}

class GerenteController extends JFrame implements ActionListener {
    private JButton buscaProdutoButton, adicionarProdutoButton, removerProdutoButton;
    private JButton buscaClienteButton, adicionarClienteButton, removerClienteButton;

    public GerenteController() {
        setTitle("Menu do Gerente");
        setSize(400, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        buscaProdutoButton = new JButton("Buscar Produto");
        adicionarProdutoButton = new JButton("Adicionar Produto");
        removerProdutoButton = new JButton("Remover Produto");
        buscaClienteButton = new JButton("Buscar Cliente");
        adicionarClienteButton = new JButton("Adicionar Cliente");
        removerClienteButton = new JButton("Remover Cliente");

        buscaProdutoButton.addActionListener(this);
        adicionarProdutoButton.addActionListener(this);
        removerProdutoButton.addActionListener(this);
        buscaClienteButton.addActionListener(this);
        adicionarClienteButton.addActionListener(this);
        removerClienteButton.addActionListener(this);

        panel.add(buscaProdutoButton);
        panel.add(adicionarProdutoButton);
        panel.add(removerProdutoButton);
        panel.add(buscaClienteButton);
        panel.add(adicionarClienteButton);
        panel.add(removerClienteButton);

        add(panel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        // Implemente as ações correspondentes para cada botão do menu do gerente
    }
}
