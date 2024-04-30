package guicontrollers;

import javax.swing.*;
import java.awt.event.*;

public class ClienteController extends JFrame implements ActionListener {
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


