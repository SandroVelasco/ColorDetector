package sample;

import java.awt.*;
import java.util.List;

import static java.awt.Color.RGBtoHSB;

public class Cor {

    private String nome;
    private int r, g, b;
    private int h, s, br;

    public Cor(String nome, int r, int g, int b) {
        this.nome = nome;
        this.r = r;
        this.g = g;
        this.b = b;

        this.setHSB();
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

    public int getH() {
        return h;
    }

    public int getS() {
        return s;
    }

    public int getBr() {
        return br;
    }

    public void setHSB() {
        float[] hsb = Color.RGBtoHSB(this.r, this.g, this.b, null);

        for(int i = 0; i < hsb.length; i++) {
            hsb[i] = hsb[i] * 100;
            hsb[i] = (float) Math.floor(hsb[i]);
        }

        this.h = (int) hsb[0];
        this.s = (int) hsb[1];
        this.br = (int) hsb[2];
    }

    @Override
    public String toString() {
        return this.getNome();
    }
}
