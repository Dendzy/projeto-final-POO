import exceptions.ClienteJaCadastradoException;
import exceptions.ClienteNaoExisteException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;


public class LojaController implements ActionListener {

    private JFrame janelaPrincipal;
    private MercadoInterface sistema;
    private Cliente cliente;
    private JPanel panelCarrinho;

    public LojaController(JFrame janelaPrincipal, MercadoInterface sistema, String cpf) throws ClienteNaoExisteException {
        this.janelaPrincipal = janelaPrincipal;
        this.sistema = sistema;
        this.cliente = sistema.pesquisarCliente(cpf);


        janelaPrincipal.getContentPane().removeAll();

        // Configurações da janela principal
        janelaPrincipal.setTitle("Loja");
        janelaPrincipal.getContentPane().removeAll();
        janelaPrincipal.setSize(800, 600);
        janelaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Painel principal da loja
        JPanel panelPrincipal = new JPanel(new BorderLayout());

        // Painel para exibir os produtos em um scroll pane
        JPanel panelProdutos = new JPanel(new GridLayout(0, 1));
        JScrollPane scrollPane = new JScrollPane(panelProdutos);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Painel para exibir os produtos no carrinho do cliente
        panelCarrinho = new JPanel();
        panelCarrinho.setLayout(new BoxLayout(panelCarrinho, BoxLayout.Y_AXIS)); // Layout de caixa vertical
        panelCarrinho.setBorder(BorderFactory.createTitledBorder("Produtos no Carrinho")); // Borda com título
        Dimension preferredSize = new Dimension(200, panelCarrinho.getPreferredSize().height);
        panelCarrinho.setPreferredSize(preferredSize);

        for (Produto produto : cliente.getCarrinho().getProdutos()) {
            JLabel labelNomeProduto = new JLabel(produto.getNome());
            JLabel labelValorProduto = new JLabel("R$" + String.valueOf(produto.getPreco()));
            panelCarrinho.add(labelNomeProduto);
            panelCarrinho.add(labelValorProduto);
        }

        panelPrincipal.add(panelCarrinho, BorderLayout.EAST);
        atualizarPainelCarrinho();

        // Mapa para manter o controle das quantidades disponíveis
        Map<String, Integer> quantidadeDisponivelMap = new HashMap<>();

        // Inicialize o mapa com as quantidades disponíveis para cada produto
        for (Produto p : sistema.getProdutos().values()) {
            quantidadeDisponivelMap.put(p.getNome(), sistema.contaProdutosIguaisNoEstoque(p.getNome()));
        }

        Set<String> nomesAdicionados = new HashSet<>();

        // Adiciona os produtos ao painel de produtos
        for (Produto p : sistema.getProdutos().values()) {

            System.out.println(p);
            if (!nomesAdicionados.contains(p.getNome())) {
                JPanel panelProduto = new JPanel(new FlowLayout(FlowLayout.LEFT));
                JLabel labelNome = new JLabel(p.getNome());
                JLabel labelValorProduto = new JLabel("R$" + String.valueOf(p.getPreco()));
                JButton buttonAdicionar = new JButton("Adicionar ao Carrinho");
                JButton buttonDescricao = new JButton("Ver Descrição");

                buttonAdicionar.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        int quantidadeJaNoCarrinhoDoCliente = cliente.getCarrinho().contaProdutosIguaisNoCarrinho(p.getNome());
                        int quantidadeDisponivel = quantidadeDisponivelMap.get(p.getNome()) - quantidadeJaNoCarrinhoDoCliente;
                        if(quantidadeDisponivel > 0){
                            sistema.adicionarAoCarrinho(cpf, p.getId());
                            if (cliente.getCarrinho().contemProduto(p.getId())) {
                                JOptionPane.showMessageDialog(janelaPrincipal, "Produto adicionado ao carrinho!");
                                atualizarPainelCarrinho();

                            } else {
                                JOptionPane.showMessageDialog(janelaPrincipal, "Produto NÃO adicionado ao carrinho!");
                            }
                        }else {
                            JOptionPane.showMessageDialog(janelaPrincipal, "Produto não adicionado ao carrinho!" +
                                    " Não há mais "+p.getNome()+"'s no estoque.");
                        }
                    }
                });

                buttonDescricao.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.showMessageDialog(janelaPrincipal,p);
                    }
                });
                panelProduto.add(labelNome);
                panelProduto.add(labelValorProduto);
                panelProduto.add(buttonAdicionar);
                panelProduto.add(buttonDescricao);
                panelProdutos.add(panelProduto);

                // Adiciona o nome do produto ao conjunto
                nomesAdicionados.add(p.getNome());
            }
        }

        // Botão para finalizar a compra
        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton buttonFinalizarCompra = new JButton("Finalizar Compra");
        buttonFinalizarCompra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        JButton botaoVoltarMenuPrincipal = new JButton("Voltar");
        botaoVoltarMenuPrincipal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sistema.salvarTodosOsDados();
                janelaPrincipal.dispose();

                MenuPrincipal menuPrincipal = new MenuPrincipal();
                menuPrincipal.setVisible(true);
            }
        });

        panelBotoes.add(botaoVoltarMenuPrincipal);
        panelBotoes.add(buttonFinalizarCompra);

        // Adiciona os componentes ao painel principal
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);
        panelPrincipal.add(panelBotoes, BorderLayout.SOUTH);

        // Adiciona o painel principal à janela
        janelaPrincipal.add(panelPrincipal);

        // Exibe a janela
        janelaPrincipal.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void atualizarPainelCarrinho() {
        // Remove todos os componentes do painel do carrinho
        panelCarrinho.removeAll();

        // Adiciona rótulos para cada produto no carrinho
        for (Produto produto : cliente.getCarrinho().getProdutos()) {
            JLabel labelNomeProduto = new JLabel(produto.getNome());
            JLabel labelValorProduto = new JLabel("R$" + String.valueOf(produto.getPreco()));
            panelCarrinho.add(labelNomeProduto);
            panelCarrinho.add(labelValorProduto);
        }

        // Atualiza e redesenha o painel do carrinho
        panelCarrinho.revalidate();
        panelCarrinho.repaint();
    }
}
