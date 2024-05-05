import exceptions.ClienteNaoExisteException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class GerenteController extends JFrame{
    private JButton buscaProdutoButton, adicionarProdutoButton, removerProdutoButton;
    private JButton buscaClienteButton, adicionarClienteButton, removerClienteButton;
    JFrame janelaPrincipal;
    MercadoInterface sistema;
    boolean opcaoValida;

    public GerenteController(JFrame janelaPrincipal, MercadoInterface sistema) {
        this.janelaPrincipal = janelaPrincipal;
        this.sistema = sistema;

    }

    public void mostrar(){
        janelaPrincipal.setVisible(false);
        setVisible(true);
        inicializar();
    }

    private void inicializar() {
        setTitle("Menu do Gerente");
        setSize(400, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        adicionarProdutoButton = new JButton("Adicionar Produto ao Estoque");
        buscaProdutoButton = new JButton("Verificar Produto no Estoque");
        removerProdutoButton = new JButton("Remover Produto");
        buscaClienteButton = new JButton("Buscar Cliente");
        adicionarClienteButton = new JButton("Adicionar Cliente");
        removerClienteButton = new JButton("Remover Cliente");
        panel.add(adicionarProdutoButton);
        panel.add(buscaProdutoButton);
        panel.add(removerProdutoButton);
        panel.add(buscaClienteButton);
        panel.add(adicionarClienteButton);
        panel.add(removerClienteButton);

        add(panel);
        setVisible(true);

        buscaProdutoButton.addActionListener(e -> {
            String nomeDoProduto = JOptionPane.showInputDialog("Digite o nome do Produto:");
            if(sistema.produtoExisteNoEstoque(nomeDoProduto))
                JOptionPane.showMessageDialog(null,"Produto Existe no Estoque!");
            else JOptionPane.showMessageDialog(null,"Produto não existe no Estoque!");
        });

        adicionarProdutoButton.addActionListener(e -> {
            janelaProduto janela = new janelaProduto(janelaPrincipal, sistema);
        });

        removerProdutoButton.addActionListener(e -> {
            opcaoValida = false;
            while(!opcaoValida) {
                try {
                    int idProduto = Integer.parseInt(JOptionPane.showInputDialog("Digite o id do Produto:"));
                    if(sistema.removerDoEstoque(idProduto))
                        JOptionPane.showMessageDialog(null,"Produto removido com Sucesso!");
                    else JOptionPane.showMessageDialog(null,"Produto não existe no Estoque!");
                    opcaoValida = true;
                } catch (NumberFormatException n) {
                    JOptionPane.showMessageDialog(null,"Digite um id em número inteiro!");
                }
            }
        });

        removerClienteButton.addActionListener(e -> {
            String cpf = JOptionPane.showInputDialog("Digite o cpf do Cliente:");
            try {
                sistema.removerCliente(cpf);
                JOptionPane.showMessageDialog(null,"Cliente removido com sucesso!");
            } catch (ClienteNaoExisteException ex) {
                JOptionPane.showMessageDialog(null,"Cliente não Existe!");
            }
        });

        buscaClienteButton.addActionListener(e -> {
            String cpf = JOptionPane.showInputDialog(null,"Digite o cpf do Cliente:");
            if(sistema.existeCliente(cpf))
                JOptionPane.showMessageDialog(null,"Cliente Existe no Sistema!");
            else JOptionPane.showMessageDialog(null,"Cliente Não Existe no Sistema!");
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setVisible(false);
                janelaPrincipal.setVisible(true);
            }
        });
    }

    public class janelaProduto extends JFrame {
        private JButton cadastrar,cancelar;
        private JFrame janelaPrincipal;
        private MercadoInterface sistema;
        private static final Map<String, TipoProduto> mapaCategoria = new HashMap<>();

        public janelaProduto(JFrame janelaPrincipal, MercadoInterface sistema) {
            this.janelaPrincipal = janelaPrincipal;
            this.sistema = sistema;
            mostrarJanela();
        }

        protected void mostrarJanela() {
            setTitle("Cadastro de Remédio");
            setSize(430,370);
            setLayout(new GridLayout(1,2));
            setResizable(false);

            JPanel info = new JPanel(new GridLayout(7,1));
            JPanel painelBotoes = new JPanel();
            painelBotoes.setLayout(new FlowLayout());
            JPanel painelNome = new JPanel(new FlowLayout());
            JPanel painelPreco = new JPanel(new FlowLayout());
            JPanel painelCategoria = new JPanel(new FlowLayout());

            JLabel nome = new JLabel("Nome do Produto: ");
            JTextField nomeField = new JTextField(30);
            painelNome.add(nome);
            painelNome.add(nomeField);

            JLabel preco = new JLabel("Preço do produto: ");
            JTextField precoField = new JTextField(30);
            precoField.setText("0");
            painelPreco.add(preco);
            painelPreco.add(precoField);

            JLabel categoria = new JLabel("Tipo do Produto: ");
            JComboBox<String> categoriaEscolha = new JComboBox<>(new String[]{"Eletrodoméstico", "Tecnologia",
                    "Energia", "Gamer", "Periférico", "Áudio", "Console"});
            painelCategoria.add(categoria);
            painelCategoria.add(categoriaEscolha);

            cadastrar = new JButton("Cadastrar");
            cancelar = new JButton("Cancelar");

            painelBotoes.add(cadastrar);
            painelBotoes.add(cancelar);
            info.add(painelNome);
            info.add(painelPreco);
            info.add(painelCategoria);
            info.add(painelBotoes,CENTER_ALIGNMENT);

            add(info);

            cadastrar.addActionListener(e -> {
                if(nomeField.getText().equals("") || precoField.getText().equals("") ||
                        nomeField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Por favor preencha todos os " +
                            "campos válidos!");
                    return;
                }
                String nomeProduto = nomeField.getText();
                double precoUser;
                try {
                    if(precoField.getText().isEmpty()) {
                        precoUser = 0.0;
                    } else {
                        precoUser = Double.parseDouble(precoField.getText());
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, insira um preço válido.",
                            "Entrada Inválida", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int quantidadeUser;
                TipoProduto categoriaSelecionada = mapaCategoria.get((String) categoriaEscolha.getSelectedItem());
                sistema.adicionarAoEstoque(categoriaSelecionada,nomeProduto,precoUser);
            });
            cancelar.addActionListener(e -> cancelar());
            this.setVisible(true);
        }
        static {
            mapaCategoria.put("Eletrodoméstico", TipoProduto.ELETRODOMESTICO);
            mapaCategoria.put("Tecnologia", TipoProduto.TECNOLOGIA);
            mapaCategoria.put("Energia", TipoProduto.ENERGIA);
            mapaCategoria.put("Gamer", TipoProduto.GAMER);
            mapaCategoria.put("Periférico", TipoProduto.PERIFERICO);
            mapaCategoria.put("Áudio", TipoProduto.AUDIO);
            mapaCategoria.put("Console", TipoProduto.CONSOLE);

        }

        public void cancelar() {
            setVisible(false);
            janelaPrincipal.setVisible(true);
        }
    }
}