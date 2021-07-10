package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;

import java.awt.*;

public class Controller {

    CorService corService = new CorService();

    @FXML
    private Rectangle rectangle;

    @FXML
    private TextField nome;

    @FXML
    private TextField hexCode;

    @FXML
    private TextField matched;

    @FXML
    private TextField redText;

    @FXML
    private TextField greenText;

    @FXML
    private TextField blueText;

    @FXML
    public void initialize() {
        this.atualizarValores();
    }

    private void atualizarValores() {
        Thread t1 = new Thread(() -> {
            while (true) {
                try {
                    int mouseX = this.getMouseX();
                    int mouseY = this.getMouseY();

                    Robot r = new Robot();
                    Color color = r.getPixelColor(mouseX, mouseY);
                    String colorR = String.format("%02X", color.getRed());
                    String colorG = String.format("%02X", color.getGreen());
                    String colorB = String.format("%02X", color.getBlue());
                    String rgb = "#" + colorR + colorG + colorB;
                    Cor cor = corService.getCorQueCombina(rgb);

                    Platform.runLater(() -> {
                        this.setNome(cor.getNome());
                        this.setHexCode(rgb);
                        this.setRectangleColor(color);
                        this.setMatched(cor);
                        this.setRedGreenAndBlueTexts(rgb);
                    });

                    Thread.sleep(100);
                } catch (Exception e) {}
            }
        });

        t1.setDaemon(true);
        t1.start();
    }

    private int getMouseX() {
        return MouseInfo.getPointerInfo().getLocation().x;
    }

    private int getMouseY() {
        return MouseInfo.getPointerInfo().getLocation().y;
    }

    private void setNome(String colorName) {
        nome.setText(colorName);
    }

    private void setHexCode(String rgb) {
        hexCode.setText(rgb);
    }

    private void setRectangleColor(Color color) {
        rectangle.setFill(javafx.scene.paint.Color.rgb(color.getRed(), color.getGreen(), color.getBlue()));
    }

    private void setMatched(Cor cor) {
        int r = cor.getR();
        int g = cor.getG();
        int b = cor.getB();

        String rgb = "#" + String.format("%02X", r) + String.format("%02X", g) + String.format("%02X", b);

        matched.setText(rgb);
    }

    private void setRedGreenAndBlueTexts(String rgbStr) {
        int[] rgb = this.corService.getRGB(rgbStr);

        redText.setText("" + rgb[0]);
        greenText.setText("" + rgb[1]);
        blueText.setText("" + rgb[2]);
    }
}
