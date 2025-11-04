package jogo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.Collection;

public class TreinoGUI extends JFrame implements ActionListener {
    private JComboBox<String> comboGuerreiros;
    private JButton btnTreinar;
    private JProgressBar barraProgresso;
    private JLabel lblStatus;
    private Timer timer;
    private int progresso = 0;
    private final int TEMPO_TREINO = 5;

    private Guerreiro guerreiroSelecionado;
    private GuerreiroSelectorGUI selectorGUI;

    public TreinoGUI(GuerreiroSelectorGUI selectorGUI) {
        super("Treino de Guerreiro");
        this.selectorGUI = selectorGUI;

        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initUI();
    }

    private void initUI() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        JPanel centerPanel = new JPanel(new GridLayout(4, 1, 5, 5));

        comboGuerreiros = new JComboBox<>();
        preencherComboBox();

        comboGuerreiros.addActionListener(e -> atualizarGuerreiroSelecionado());

        lblStatus = new JLabel("Selecione um guerreiro para treinar.", SwingConstants.CENTER);

        btnTreinar = new JButton("Treinar");
        btnTreinar.addActionListener(this);

        barraProgresso = new JProgressBar(0, 100);
        barraProgresso.setValue(0);
        barraProgresso.setStringPainted(true);

        centerPanel.add(new JLabel("Escolha o guerreiro:", SwingConstants.CENTER));
        centerPanel.add(comboGuerreiros);
        centerPanel.add(barraProgresso);
        centerPanel.add(btnTreinar);

        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(lblStatus, BorderLayout.NORTH);
        panel.add(centerPanel, BorderLayout.CENTER);
        add(panel);

        if (comboGuerreiros.getItemCount() > 0)
            comboGuerreiros.setSelectedIndex(0);
    }

    private void preencherComboBox() {
        comboGuerreiros.removeAllItems();
        Collection<Guerreiro> guerreiros = selectorGUI.getTodosGuerreiros();
        for (Guerreiro g : guerreiros) {
            comboGuerreiros.addItem(g.getNome());
        }
    }

    private void atualizarGuerreiroSelecionado() {
        String nome = (String) comboGuerreiros.getSelectedItem();
        if (nome != null && !nome.isEmpty()) {
            guerreiroSelecionado = selectorGUI.getTodosGuerreiros()
                                              .stream()
                                              .filter(g -> g.getNome().equals(nome))
                                              .findFirst()
                                              .orElse(null);
            if (guerreiroSelecionado != null) {
                lblStatus.setText("XP atual: " + guerreiroSelecionado.getXp());
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (guerreiroSelecionado == null) {
            JOptionPane.showMessageDialog(this, "Selecione um guerreiro antes de treinar!");
            return;
        }

        if (timer != null && timer.isRunning()) {
            JOptionPane.showMessageDialog(this, "O treino já está em andamento!");
            return;
        }

        btnTreinar.setEnabled(false);
        comboGuerreiros.setEnabled(false);
        lblStatus.setText("Treinando " + guerreiroSelecionado.getNome() + "...");

        progresso = 0;
        barraProgresso.setValue(0);

        int delay = (TEMPO_TREINO * 1000) / 100;
        timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                progresso++;
                barraProgresso.setValue(progresso);

                if (progresso >= 100) {
                    timer.stop();
                    terminarTreino();
                }
            }
        });
        timer.start();
    }

    private void terminarTreino() {
        Random rand = new Random();
        int ganho = rand.nextInt(3) + 2; // Ganha entre 2 e 4 XP

        guerreiroSelecionado.setXp(guerreiroSelecionado.getXp() + ganho);
        lblStatus.setText("Treino concluído! XP atual: " + guerreiroSelecionado.getXp());
        btnTreinar.setEnabled(true);
        comboGuerreiros.setEnabled(true);

        JOptionPane.showMessageDialog(this,
            "Treino finalizado!\n" + guerreiroSelecionado.getNome() + " ganhou +" + ganho + " XP!");
    }
}



