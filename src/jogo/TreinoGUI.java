package jogo;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class TreinoGUI extends JFrame implements ActionListener {
    private Guerreiro guerreiro;
    private JButton btnTreinar;
    private JProgressBar barraProgesso;
    private JLabel lblStatus;
    private Timer timer;
    private int progresso = 0;
    private final int TEMPO_TREINO = 5;

    public TreinoGUI(Guerreiro guerreiro) {
        super("Treino de " + guerreiro.getNome());
        this.guerreiro = guerreiro;

        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initUI();
    }

    private void initUI() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        JPanel centerPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        
        
        lblStatus = new JLabel("XP atual: " + guerreiro.getXp(), SwingConstants.CENTER);
        btnTreinar = new JButton(" Treinar ");
        btnTreinar.addActionListener(this);

        barraProgesso = new JProgressBar(0, 100);
        barraProgesso.setValue(0);
        barraProgesso.setStringPainted(true);

        centerPanel.add(lblStatus);
        centerPanel.add(barraProgesso);
        centerPanel.add(btnTreinar);

        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(centerPanel, BorderLayout.CENTER);

        add(panel);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (timer != null && timer.isRunning()) {
            JOptionPane.showMessageDialog(this, "O treino já está em andamento!");
            return;
        }

        btnTreinar.setEnabled(false);
        lblStatus.setText("Treinando...");

        progresso = 0;
        barraProgesso.setValue(0);

        int delay = (TEMPO_TREINO * 1000) / 100;
        timer = new Timer(delay, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                progresso ++;
                barraProgesso.setValue(progresso);

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
        int ganho = rand.nextInt(3) + 2;

        guerreiro.setXp(guerreiro.getXp() + ganho);
        lblStatus.setText("Treino concluído! XP atual: " + guerreiro.getXp());
        btnTreinar.setEnabled(true);

        JOptionPane.showMessageDialog(this, "Treino finalizado!\n" + guerreiro.getNome() + "Ganhou +" + ganho + "XP!");
    }





}
