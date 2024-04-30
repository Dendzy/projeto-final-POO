package guicontrollers;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GerenteController extends JFrame implements ActionListener {
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