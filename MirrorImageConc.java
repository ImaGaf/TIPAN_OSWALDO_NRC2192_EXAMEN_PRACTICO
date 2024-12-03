import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class MirrorImageConc {

    public static void main(String[] args) throws Exception {
        File inputFile = new File(
                "C://Users//oswal//OneDrive//Escritorio//Code//Materias2024//Paralela//TIPAN_OSWALDO_NRC2192_EXAMEN_PRACTICO//imgs//7.jpg");
        BufferedImage image = ImageIO.read(inputFile);

        int width = image.getWidth();
        int height = image.getHeight();
        int numThreads = 4;
        Thread[] threads = new Thread[numThreads];

        long startTime = System.nanoTime();

        int rowsPerThread = height / numThreads;

        for (int i = 0; i < numThreads; i++) {
            int startRow = i * rowsPerThread;
            int endRow = (i == numThreads - 1) ? height : startRow + rowsPerThread;

            threads[i] = new Thread(new MirrorTask(image, startRow, endRow, width));
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        long endTime = System.nanoTime();

        File outputFile = new File(
                "C://Users//oswal//OneDrive//Escritorio//Code//Materias2024//Paralela//TIPAN_OSWALDO_NRC2192_EXAMEN_PRACTICO//imgs_revert//7.jpg");
        ImageIO.write(image, "jpg", outputFile);

        System.out.println("Proceso concurrente completado en " + (endTime - startTime) / 1000 + " us");
    }
}

class MirrorTask implements Runnable {
    private final BufferedImage image;
    private final int startRow;
    private final int endRow;
    private final int width;

    public MirrorTask(BufferedImage image, int startRow, int endRow, int width) {
        this.image = image;
        this.startRow = startRow;
        this.endRow = endRow;
        this.width = width;
    }

    @Override
    public void run() {
        for (int y = startRow; y < endRow; y++) {
            for (int x = 0; x < width / 2; x++) {
                int temp = image.getRGB(x, y);
                image.setRGB(x, y, image.getRGB(width - 1 - x, y));
                image.setRGB(width - 1 - x, y, temp);
            }
        }
    }
}
