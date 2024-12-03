import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MirrorImageSequential {

    public static void main(String[] args) throws IOException {

        File inputFile = new File(
                "C://Users//oswal//OneDrive//Escritorio//Code//Materias2024//Paralela//TIPAN_OSWALDO_NRC2192_EXAMEN_PRACTICO//imgs//7.jpg");
        BufferedImage image = ImageIO.read(inputFile);

        int width = image.getWidth();
        int height = image.getHeight();
        long startTime = System.nanoTime();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width / 2; x++) {
                int temp = image.getRGB(x, y);
                image.setRGB(x, y, image.getRGB(width - 1 - x, y));
                image.setRGB(width - 1 - x, y, temp);
            }
        }

        long endTime = System.nanoTime();
        File outputFile = new File(
                "C://Users//oswal//OneDrive//Escritorio//Code//Materias2024//Paralela//TIPAN_OSWALDO_NRC2192_EXAMEN_PRACTICO//imgs_revert//7.jpg");
        ImageIO.write(image, "jpg", outputFile);
        System.out.println("Proceso secuencial completado en: " + (endTime - startTime) / 1000 + "us");
    }
}