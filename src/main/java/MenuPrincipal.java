import guicontrollers.ClienteController;
import guicontrollers.GerenteController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuPrincipal extends JFrame implements ActionListener {
    private JButton clienteButton, gerenteButton;

    public MenuPrincipal() {
        setTitle("Menu Principal");
        setSize(800, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel(null);



        panel.setBackground(new Color(255,255 , 255));


        ImageIcon clienteIcon = new ImageIcon("cliente_icon.png");
        ImageIcon gerenteIcon = new ImageIcon("gerente_icon.png");


        clienteButton = new JButton("Cliente", clienteIcon);
        gerenteButton = new JButton("Gerente", gerenteIcon);


        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (int) ((screenSize.getWidth() - getWidth()) / 2);
        int centerY = (int) ((screenSize.getHeight() - getHeight()) / 2);


        int buttonWidth = 150;
        int buttonHeight = 100;
        int buttonX = centerX - (buttonWidth / 2);
        int buttonY = centerY - (buttonHeight / 2);


        clienteButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        gerenteButton.setBounds(buttonX, buttonY + 120, buttonWidth, buttonHeight); // Separando os botões verticalmente


        panel.add(clienteButton);
        panel.add(gerenteButton);


        add(panel);


        clienteButton.addActionListener(this);
        gerenteButton.addActionListener(this);
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu cliente = new JMenu("Cliente");
        JMenu gerente = new JMenu("Gerente");
        cliente.addActionListener(e -> new ClienteController());
        gerente.addActionListener(e -> new GerenteController());
        menuBar.add(cliente);
        menuBar.add(gerente);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clienteButton) {
            ClienteController clienteMenu = new ClienteController();
        } else if (e.getSource() == gerenteButton) {
            GerenteController gerenteMenu = new GerenteController();
        }
    }

    public static void main(String[] args) {
        new MenuPrincipal();
    }
}



