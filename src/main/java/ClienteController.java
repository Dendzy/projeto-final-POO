import exceptions.ClienteJaCadastradoException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Collection;

public class ClienteController implements ActionListener {
    private JFrame janelaPrincipal;
    private MercadoInterface sistema;
    private Collection<Cliente> clientes;

    public ClienteController(JFrame janelaPrincipal, MercadoInterface sistema) {
        this.janelaPrincipal = janelaPrincipal;
        this.sistema = sistema;
        this.clientes = sistema.getClientes().values();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        janelaPrincipal.getContentPane().removeAll();

        // Atualiza a lista de clientes
        clientes = sistema.getClientes().values();

        // Define o layout do painel para uma grade
        JPanel panel = new JPanel(new GridLayout(clientes.size() + 1, 1)); // +1 para incluir o botão de cadastro

        JButton botaoCadastrarNovoCliente = new JButton("Cadastre-se");
        botaoCadastrarNovoCliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exibeFormulario(panel);

            }
        });

        panel.add(botaoCadastrarNovoCliente);

        // Itera sobre a lista de clientes
        for (Cliente cliente : clientes) {
            // Cria um botão para o cliente atual
            JButton button = new JButton(cliente.getNome());

            // Adiciona um ActionListener para o botão
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Lida com a ação quando o botão é clicado
                    // Por exemplo, abra o perfil do cliente em uma nova janela
                    abrirPerfilCliente(cliente);

                }
            });

            // Adiciona o botão ao painel
            panel.add(button);
        }

        // Adiciona o painel ao frame
        janelaPrincipal.add(panel);

        // Redimensiona e redesenha o frame
        janelaPrincipal.revalidate();
        janelaPrincipal.repaint();
    }

    private void abrirPerfilCliente(Cliente cliente) {
        // Implemente a lógica para abrir o perfil do cliente
        // por exemplo: abra uma nova janela com os detalhes do cliente
        // Você pode passar o objeto cliente para a nova janela
    }

    private void atualizaPerfis(JPanel painel){
        clientes = sistema.getClientes().values();
        painel.removeAll();
        for (Cliente cliente : clientes) {
            // Cria um botão para o cliente atual
            JButton button = new JButton(cliente.getNome());

            // Adiciona um ActionListener para o botão
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    abrirPerfilCliente(cliente);
                }
            });

            // Adiciona o botão ao painel
            painel.add(button);
        }
    }

    private void exibeFormulario(JPanel painelDeClientes) {
        JFrame frame = new JFrame("Formulário de Cadastro");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Painel para o formulário
        JPanel panel = new JPanel(new BorderLayout());

        // Painel para os rótulos e campos de texto
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Componentes
        JLabel lblNome = new JLabel("Nome:");
        JTextField txtNome = new JTextField(20);
        JLabel lblCPF = new JLabel("CPF:");
        JTextField txtCPF = new JTextField(20);
        JLabel lblEndereco = new JLabel("Endereço:");
        JTextField txtEndereco = new JTextField(20);
        JLabel lblSenha = new JLabel("Senha:");
        JPasswordField txtSenha = new JPasswordField(20);

        // Adicionando componentes ao painel do formulário
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(lblNome, gbc);
        gbc.gridx = 1;
        formPanel.add(txtNome, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(lblCPF, gbc);
        gbc.gridx = 1;
        formPanel.add(txtCPF, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(lblEndereco, gbc);
        gbc.gridx = 1;
        formPanel.add(txtEndereco, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(lblSenha, gbc);
        gbc.gridx = 1;
        formPanel.add(txtSenha, gbc);

        // Botão de cadastro
        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = txtNome.getText();
                String cpf = txtCPF.getText();
                String endereco = txtEndereco.getText();
                String senha = new String(txtSenha.getPassword());

                try {
                    sistema.cadastrarCliente(nome, cpf, endereco, senha, new Carrinho());
                    JOptionPane.showMessageDialog(frame,"Cliente cadastrado com sucesso!");
                    frame.dispose();
                    atualizaPerfis(painelDeClientes);
                } catch (ClienteJaCadastradoException erro) {
                    JOptionPane.showMessageDialog(frame, erro.getMessage());
                }
            }
        });

        // Adicionando botão de cadastro ao painel
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        formPanel.add(btnCadastrar, gbc);

        // Adicionando painel do formulário ao painel principal
        panel.add(formPanel, BorderLayout.CENTER);

        // Adicionando painel principal ao frame
        frame.add(panel);
        frame.pack(); // Ajusta o tamanho do frame de acordo com o conteúdo
        frame.setLocationRelativeTo(null); // Centraliza o frame na tela
        frame.setVisible(true);
    }
}