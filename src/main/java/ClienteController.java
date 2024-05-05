import exceptions.ClienteJaCadastradoException;
import exceptions.ClienteNaoExisteException;

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

        // Define o layout do painel principal para BorderLayout
        JPanel panelPrincipal = new JPanel(new BorderLayout());

        // Painel para os botões dos clientes
        JPanel panelClientes = new JPanel(new GridLayout(clientes.size(), 1));


        // Adiciona os botões dos clientes ao painelClientes
        atualizaPerfis(panelClientes);

        // Painel para o botão de cadastro
        JPanel panelCadastro = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton botaoCadastrarNovoCliente = new JButton("Cadastre-se");
        botaoCadastrarNovoCliente.setPreferredSize(new Dimension(150, 50));
        botaoCadastrarNovoCliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exibeFormulario(panelClientes);
            }
        });

        JButton botaoVoltarMenuPrincipal = new JButton("Voltar");
        botaoVoltarMenuPrincipal.setPreferredSize(new Dimension(150, 50));
        botaoVoltarMenuPrincipal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sistema.salvarTodosOsDados();
                janelaPrincipal.dispose();

                // Abre novamente a janela do menu principal
                MenuPrincipal menuPrincipal = new MenuPrincipal();
                menuPrincipal.setVisible(true);
            }
        });

        panelCadastro.add(botaoVoltarMenuPrincipal);
        panelCadastro.add(botaoCadastrarNovoCliente);

        // Adiciona os painéis ao painel principal
        panelPrincipal.add(panelClientes, BorderLayout.CENTER);
        panelPrincipal.add(panelCadastro, BorderLayout.SOUTH);

        // Adiciona o painel principal ao frame
        janelaPrincipal.add(panelPrincipal);

        // Redimensiona e redesenha o frame
        janelaPrincipal.revalidate();
        janelaPrincipal.repaint();
    }

    private void abrirLoginCliente(Cliente cliente) {
        JFrame frameLogin = new JFrame("Login Cliente");
        frameLogin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameLogin.setSize(300, 150);
        frameLogin.setLocationRelativeTo(janelaPrincipal);

        JPanel panel = new JPanel(new BorderLayout());

        // Label e campo de texto para o nome do cliente
        JLabel labelNomeCliente = new JLabel("Nome do Cliente: " + cliente.getNome());
        panel.add(labelNomeCliente, BorderLayout.NORTH);

        // Painel para o campo de senha
        JPanel panelSenha = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel labelSenha = new JLabel("Senha:");
        JPasswordField campoSenha = new JPasswordField(15);
        panelSenha.add(labelSenha);
        panelSenha.add(campoSenha);
        panel.add(panelSenha, BorderLayout.CENTER);

        // Botão de login
        JButton botaoLogin = new JButton("Login");
        botaoLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String senhaDigitada = new String(campoSenha.getPassword());
                if (cliente.getSenha().equals(senhaDigitada)) {
                    JOptionPane.showMessageDialog(frameLogin, "Senha válida! Ação executada.");
                    frameLogin.dispose();
                    try{
                        new LojaController(janelaPrincipal,sistema,cliente.getCpf());
                    }catch (ClienteNaoExisteException erro){
                        System.err.println(erro.getMessage());
                    }

                    // Adicione aqui a ação que deseja executar após a validação da senha
                } else {
                    JOptionPane.showMessageDialog(frameLogin, "Senha inválida. Tente novamente.");
                }
            }
        });
        panel.add(botaoLogin, BorderLayout.SOUTH);

        frameLogin.add(panel);
        frameLogin.setVisible(true);
    }

    private void atualizaPerfis(JPanel painel) {
        // Limpa o painel
        painel.removeAll();

        // Cria um novo painel para centralizar os botões
        JPanel panelCentralizado = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 5, 5, 5); // Adiciona um espaçamento entre os botões

        // Obtém a lista de clientes do sistema
        clientes = sistema.getClientes().values();

        // Adiciona os botões dos clientes ao painel centralizado
        for (Cliente cliente : clientes) {
            // Cria um botão para o cliente atual
            JButton button = new JButton(cliente.getNome());
            button.setPreferredSize(new Dimension(150, 50));

            // Adiciona um ActionListener para o botão
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    abrirLoginCliente(cliente);
                }
            });

            // Adiciona o botão ao painel centralizado
            panelCentralizado.add(button, gbc);
            gbc.gridx++; // Incrementa o índice da coluna para adicionar o próximo botão ao lado
        }

        // Adiciona o painel centralizado ao centro do painel principal
        painel.add(panelCentralizado);

        // Redimensiona e redesenha o painel
        painel.revalidate();
        painel.repaint();
    }

    private void exibeFormulario(JPanel panelClientes) {
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
                    sistema.cadastrarCliente(nome, cpf, senha, endereco, new Carrinho());
                    JOptionPane.showMessageDialog(frame, "Cliente cadastrado com sucesso!");
                    frame.dispose();
                    atualizaPerfis(panelClientes); // Atualiza os perfis de clientes após o cadastro
                } catch (ClienteJaCadastradoException erro) {
                    JOptionPane.showMessageDialog(frame, erro.getMessage());
                }
            }
        });

        // Adicionando botão de cadastro ao painel
        gbc.gridx = 1;
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