package sample;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CorService {
    private List<Cor> cores = new ArrayList<>();

    public CorService() {
        this.setListOfColors();
    }

    private void setListOfColors() {
        String rootPath = System.getProperty("user.dir");
        String pathOfColorsTxt =  rootPath + "/cores.txt";

        try {
            String content;
            content = new String(Files.readAllBytes(Paths.get(pathOfColorsTxt)));
            content = content.replace(" ", "");
            content = content.replace("#", "");
            String[] colors = content.split("\n");

            for(String color : colors) {
                String[] dados = color.split(",");

                String colorName = dados[0];
                int r = Integer.parseInt(dados[1].substring(0, 2), 16);
                int g = Integer.parseInt(dados[1].substring(2, 4), 16);
                int b = Integer.parseInt(dados[1].substring(4, 6), 16);

                this.cores.add(new Cor(colorName, r, g, b));
            }
        } catch(Exception e) {
            System.out.println("ERRO: " + e.getMessage());
            System.exit(0);
        }
    }

    public void getColors() {
        this.cores.forEach(cor -> {
            System.out.println(cor.getNome());
        });
    }

    public Cor getCorQueCombina(String rgb) {
        rgb = rgb.replace("#", "");

        int r = (int) Long.parseLong(rgb.substring(0, 2), 16);
        int g = (int) Long.parseLong(rgb.substring(2, 4), 16);
        int b = (int) Long.parseLong(rgb.substring(4, 6), 16);
        Cor corProcurada = new Cor("Cor_procurada", r, g, b);

        // Se saturação for igual a 0 é um range de branco, cinza e preto
        if(corProcurada.getS() == 0) return getColorInRangeOfGray(corProcurada);

        int menorValor = Integer.MAX_VALUE;
        Cor corDesejada = null;

        for (Cor cor : cores) {
            int resultado = Math.abs(
                    Math.abs(r - cor.getR()) +
                            Math.abs(g - cor.getG()) +
                            Math.abs(b - cor.getB())
            );

            // Se a cor exata for encontrada, sair do laço
            if(resultado == 0) {
                corDesejada = cor;
                break;
            }

            // Primeiro laço da interação
            if(menorValor > resultado) {
                menorValor = resultado;
                corDesejada = cor;
            }

        }

        return corDesejada;
    }

    private Cor getColorInRangeOfGray(Cor cor) {
        if(cor.getBr() == 100) {
            return new Cor("Branco", 0xFF, 0xFF, 0xFF);
        }

        if(cor.getBr() <= 40) {
            return new Cor("Preto", 0x00, 0x00, 0x00);
        }

        return new Cor("Cinza", 0xD6, 0xD6, 0xD6);
    }
}
