package jogo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class CadastrarGuerreiro extends JFrame implements ActionListener {

    Random random = new Random();
    private JTextField nomeField, classeField, imgField;
    private JButton btnSalvar, btnCancelar;
    private GuerreiroSelectorGUI selectorGUI;

    public CadastrarGuerreiro(GuerreiroSelectorGUI selectorGUI) {
        super("Cadastrar Guerreiro");
        this.selectorGUI = selectorGUI;
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initUI();
    }

    private void initUI() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Nome:"));
        nomeField = new JTextField();
        panel.add(nomeField);

        panel.add(new JLabel("Classe:"));
        classeField = new JTextField();
        panel.add(classeField);

        
        panel.add(new JLabel("Caminho da Imagem:"));
        imgField = new JTextField();
        panel.add(imgField);

        btnSalvar = new JButton("Salvar");
        btnCancelar = new JButton("Cancelar");
        btnSalvar.addActionListener(this);
        btnCancelar.addActionListener(this);

        panel.add(btnSalvar);
        panel.add(btnCancelar);
        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == btnSalvar) {
            try {
                String nome = nomeField.getText().trim();
                String classe = classeField.getText().trim();
                //int xp = Integer.parseInt(xpField.getText().trim());
                String img = imgField.getText().trim();

                Guerreiro novo = new Guerreiro(nome, classe, 1, img);
                JOptionPane.showMessageDialog(this, "Guerreiro cadastrado com sucesso!");
                if (selectorGUI != null) selectorGUI.adicionarGuerreiroAoBanco(novo);
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else if (src == btnCancelar) {
            dispose();
        }
    }
}