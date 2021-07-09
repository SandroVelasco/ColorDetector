package sample;

import java.util.List;

public class Cor {

    private String nome;
    private int r;
    private int g;
    private int b;

    public Cor(String nome, int r, int g, int b) {
        this.nome = nome;
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public String getNome() {
        return nome;
    }

    public int getR() {
        return r;
    }

    public int getG() {
        return g;
    }

    public int getB() {
        return b;
    }

    @Override
    public String toString() {
        return this.getNome();
    }
}
