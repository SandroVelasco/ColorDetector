package sample;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CorService {
    private Cor corDesejada;

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

        Long r = Long.parseLong(rgb.substring(0, 2), 16);
        Long g = Long.parseLong(rgb.substring(2, 4), 16);
        Long b = Long.parseLong(rgb.substring(4, 6), 16);

        Long menorValor = Long.MAX_VALUE;
        Cor corDesejada = new Cor("Indefinido", 0x00, 0x00, 0x00);
        for (Cor cor : cores) {
            Long resultado = Math.abs(
                    Math.abs(r - cor.getR()) +
                            Math.abs(g - cor.getG()) +
                            Math.abs(b - cor.getB())
            );

            if(resultado < menorValor) {
                menorValor = resultado;
                corDesejada = cor;
            }
        }

        return corDesejada;
    }
}
