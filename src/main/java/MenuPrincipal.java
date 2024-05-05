import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuPrincipal extends JFrame implements ActionListener {
    private JButton clienteButton, gerenteButton;
    private JLabel clienteOuGerente;
    private MercadoInterface sistema;
    private GerenteController gerenteController;

    public MenuPrincipal() {

        this.sistema = new SistemaMercado();

        setTitle("Menu Principal");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        panel.setBackground(new Color(255, 255, 255));

        ImageIcon clienteIcon = new ImageIcon("cliente_icon.png");
        ImageIcon gerenteIcon = new ImageIcon("gerente_icon.png");

        clienteOuGerente = new JLabel("Escolha sua opção de acesso");
        clienteButton = new JButton("Cliente", clienteIcon);
        gerenteButton = new JButton("Gerente", gerenteIcon);

        Dimension buttonSize = new Dimension(150, 50);
        clienteButton.setPreferredSize(buttonSize);
        gerenteButton.setPreferredSize(buttonSize);

        panel.add(clienteOuGerente, gbc);
        gbc.gridy++;
        panel.add(clienteButton, gbc);
        gbc.gridy++;
        panel.add(gerenteButton, gbc);

        add(panel);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (int) ((screenSize.getWidth() - getWidth()) / 2);
        int centerY = (int) ((screenSize.getHeight() - getHeight()) / 2);
        setLocation(centerX, centerY);

        clienteButton.addActionListener(new ClienteController(this, sistema));
        gerenteController = new GerenteController(this,sistema);
        gerenteButton.addActionListener(e -> gerenteController.mostrar());
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu cliente = new JMenu("Cliente");
        JMenu gerente = new JMenu("Gerente");
        cliente.addActionListener(e -> new ClienteController(this, sistema));
        menuBar.add(cliente);
        menuBar.add(gerente);

        setVisible(true);
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clienteButton) {
            ClienteController clienteMenu = new ClienteController(this, sistema);
        }
    }


    public static void main(String[] args) {
        MenuPrincipal menu = new MenuPrincipal();
        menu.setVisible(true);
    }
}
