package jogo;

public class Guerreiro {
    private String nome;
    private String classe;
    private int xp;
    private String imagePath;

    public Guerreiro(String nome, String classe, int xp, String imagePath) {
        this.nome = nome;
        this.classe = classe;
        this.xp = xp;
        this.imagePath = imagePath;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return nome + " (" + classe + ") - XP: " + xp;
    }
}