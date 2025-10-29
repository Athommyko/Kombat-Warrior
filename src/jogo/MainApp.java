package jogo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class MainApp extends JFrame implements ActionListener {
    private JButton btnCadastrar, btnSelecionar, btnBatalha, btnTreinar;
    private GuerreiroSelectorGUI selectorGUI;

    public MainApp() {
        super("Batlle Fantasy");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        initUI();
    }

    private void initUI() {
        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        btnCadastrar = new JButton("Cadastrar guerreiro");
        btnSelecionar = new JButton("Selecionar guerreiro");
        btnBatalha = new JButton("Iniciar Luta");
        btnTreinar = new JButton("Treinar Guerreiro");

        btnCadastrar.addActionListener(this);
        btnSelecionar.addActionListener(this);
        btnBatalha.addActionListener(this);
        btnTreinar.addActionListener(this);

        panel.add(btnCadastrar);
        panel.add(btnSelecionar);
        panel.add(btnBatalha);
        panel.add(btnTreinar);

        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == btnCadastrar) abrirCadastro();
        else if (src == btnSelecionar) abrirSelecionador();
        else if (src == btnBatalha) iniciarBatalha();
        else if (src == btnTreinar) iniciarTreino();
    }

    private void abrirCadastro() {
        CadastrarGuerreiro cadastro = new CadastrarGuerreiro(selectorGUI);
        cadastro.setVisible(true);
    }

    private void abrirSelecionador() {
        if (selectorGUI == null) selectorGUI = new GuerreiroSelectorGUI();
        selectorGUI.setVisible(true);
    }

    private void iniciarTreino() {
       if (selectorGUI == null || selectorGUI.getSelecionado1() == null) {
        JOptionPane.showMessageDialog(this, "Selecione um guerreiro para treinar!");
        return;
       }

       TreinoGUI treino = new TreinoGUI(selectorGUI.getSelecionado1());
       treino.setVisible(true);
    }

    private void iniciarBatalha() {
        if (selectorGUI == null || selectorGUI.getSelecionado1() == null || selectorGUI.getSelecionado2() == null) {
            JOptionPane.showMessageDialog(this, "Selecione dois guerreiros antes de iniciar a batalha!");
            return;
        }

        Guerreiro g1 = selectorGUI.getSelecionado1();
        Guerreiro g2 = selectorGUI.getSelecionado2();
        Guerreiro vencedor = (g1.getXp() >= g2.getXp()) ? g1 : g2;

        Random rand = new Random();

        int dadoG1 = g1.getXp()*(rand.nextInt(3) + 1); // sorteia de 1 a 6 para g1
        int dadoG2 = g2.getXp()*(rand.nextInt(3) + 1); // sorteia de 1 a 6 para g2
        System.out.println("Jogador 1: " + dadoG1 + " ; Jogador 2: " + dadoG2);

        if (dadoG1 > dadoG2) {
            vencedor = g1;
            //Verifica vencedor e adiciona +2 Xp quando ganha
            vencedor.setXp(vencedor.getXp()+2);
        } else if (dadoG2 > dadoG1) {
            vencedor = g2;
            //Verifica vencedor e adiciona +2 Xp quando ganha
            vencedor.setXp(vencedor.getXp()+2);
        } else {
            // Empate: cada guerreiro ganha +1 Xp
            g1.setXp(g1.getXp()+1);
            g2.setXp(g2.getXp()+1);
        }

        JOptionPane.showMessageDialog(this,
                "Batalha entre:\n" + g1.getNome() + " e " + g2.getNome() +
                "\n\nVencedor: " + vencedor.getNome() + "!");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainApp().setVisible(true));
    }
}