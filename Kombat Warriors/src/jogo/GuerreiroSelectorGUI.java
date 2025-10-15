package jogo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class GuerreiroSelectorGUI extends JFrame implements ActionListener {

    private JTextField nome1Field, nome2Field;
    private JButton btnSel1, btnSel2;
    private JTextArea displayArea;
    private HashMap<String, Guerreiro> banco;
    private Guerreiro selecionado1, selecionado2;

    public GuerreiroSelectorGUI() {
        super("Selecionar Guerreiros");
        setSize(600, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        banco = new HashMap<>();
        initBanco();
        initUI();
    }

    private void initBanco() {
        banco.put("Arthos", new Guerreiro("Arthos, o Bravo", "Humano", 1200, ""));
        banco.put("Bruna", new Guerreiro("Bruna, a Lâmina", "Elfa", 950, ""));
        banco.put("Thorg", new Guerreiro("Thorg, o Gigante", "Orc", 2000, ""));
        banco.put("Lia", new Guerreiro("Lia, a Veloz", "Humana", 700, ""));
    }

    private void initUI() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel topPanel = new JPanel(new GridLayout(2, 1, 8, 8));

        JPanel p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p1.add(new JLabel("Guerreiro 1:"));
        nome1Field = new JTextField(15);
        p1.add(nome1Field);
        btnSel1 = new JButton("Selecionar 1");
        btnSel1.addActionListener(this);
        p1.add(btnSel1);

        JPanel p2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p2.add(new JLabel("Guerreiro 2:"));
        nome2Field = new JTextField(15);
        p2.add(nome2Field);
        btnSel2 = new JButton("Selecionar 2");
        btnSel2.addActionListener(this);
        p2.add(btnSel2);

        topPanel.add(p1);
        topPanel.add(p2);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(displayArea), BorderLayout.CENTER);

        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == btnSel1) {
            selecionado1 = buscarOuCriar(nome1Field.getText().trim());
        } else if (src == btnSel2) {
            selecionado2 = buscarOuCriar(nome2Field.getText().trim());
        }
        atualizarDisplay();
    }

    private Guerreiro buscarOuCriar(String nome) {
        if (nome.isEmpty()) return null;
        Guerreiro g = banco.get(nome);
        if (g == null) {
            g = new Guerreiro(nome, "Desconhecido", 1, ""); //Inicializa XP sempre pra um.
            banco.put(nome, g);
        }
        return g;
    }

    private void atualizarDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append("== SELEÇÃO ATUAL ==\n\n");
        sb.append("Guerreiro 1: ").append(selecionado1 != null ? selecionado1 : "(não selecionado)").append("\n\n");
        sb.append("Guerreiro 2: ").append(selecionado2 != null ? selecionado2 : "(não selecionado)").append("\n");
        displayArea.setText(sb.toString());
    }

    public void adicionarGuerreiroAoBanco(Guerreiro g) {
        banco.put(g.getNome(), g);
    }

    public Guerreiro getSelecionado1() { return selecionado1; }
    public Guerreiro getSelecionado2() { return selecionado2; }
}