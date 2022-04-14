package lab6;

import org.apache.commons.lang3.tuple.Pair;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

public class Application {
    public static void main(String[] args) {
        try {
            String sourcePath = args[0];
            String destinationPath = args[1];
            String destinationPathGarbage = args[2];
            Path source = Path.of(sourcePath);
            Path destination = Path.of(destinationPath);
            Path destinationGarbage = Path.of(destinationPathGarbage);
            List<Path> files = new ArrayList<>();

            try (Stream<Path> stream = Files.list(source)) {
                files = stream.collect(Collectors.toList());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

            List<Pair<Path, BufferedImage>> originalPairs;

            originalPairs = files.stream().map(path -> {
                BufferedImage image = null;
                try {
                    BufferedImage original = ImageIO.read(path.toFile());
                    image = new BufferedImage(
                            original.getWidth(),
                            original.getHeight(),
                            original.getType()
                    );
                    for (int row = 0; row < original.getHeight(); row++) {
                        for (int col = 0; col < image.getWidth(); col++) {
                            image.setRGB(col, row, original.getRGB(col, row));
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return Pair.of(path, image);
            }).collect(Collectors.toList());

            transform(originalPairs, destination, 12);

            for (int j = 1; j < 14; j+=2) {
                long diff, sum = 0, time;
                for (int i = 0; i < 4; i++) {
                    time = System.currentTimeMillis();
                    transform(originalPairs, destinationGarbage, j);
                    diff = System.currentTimeMillis() - time;
                    sum += diff;
                }
                System.out.println("For " + String.valueOf(j) + " threads average time of execution is " + String.valueOf(sum / 4) + ".");
            }
        } catch (Exception exception){
            exception.printStackTrace();
        }
    }

    public static void transform(List<Pair<Path, BufferedImage>> originalPairs, Path destination, int nThreads){
        ForkJoinPool pool = new ForkJoinPool(nThreads);

        try {
            pool.submit(() -> {
                originalPairs.stream().map(pair -> {
                    BufferedImage image = pair.getRight();
                    for (int row = 0; row < image.getHeight(); row++) {
                        for (int col = 0; col < image.getWidth(); col++) {
                            Color color = new Color(image.getRGB(col, row));

                            int red = (int) (color.getRed() * 0.299);
                            int green = (int) (color.getGreen() * 0.47);
                            int blue = (int) (color.getBlue() * 0.114);

                            Color outColor = new Color(red, green, blue);
                            image.setRGB(col, row, outColor.getRGB());
                        }
                    }
                    String filename = pair.getLeft().getFileName().toString();
                    return Pair.of(Path.of(destination + "/" + filename), image);
                }).forEach(pair -> {
                    try {
                        ImageIO.write(pair.getRight(),"jpg", pair.getLeft().toFile());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }).get();
        } catch (InterruptedException exception) {}
        catch(ExecutionException exception){}
    }
}
