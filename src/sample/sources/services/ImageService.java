package sample.sources.services;

import com.sun.istack.internal.NotNull;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

import static sample.sources.constants.ImageHandlerConstants.ALFA_OFFSET;
import static sample.sources.constants.ImageHandlerConstants.PIXEL_MASK;
import static sample.sources.constants.ImageHandlerConstants.ALFA_SHIFT;
import static sample.sources.constants.ImageHandlerConstants.GREEN_SHIFT;
import static sample.sources.constants.ImageHandlerConstants.RED_SHIFT;


/**
 * Created by Alexadner Dadukin on 13.09.2016.
 */

public class ImageService {

    public interface OnImageHandleListener {
        void onVectorize(int[] vector);
        void onError();
    }

    private OnImageHandleListener listener;

    private ImageService(@NotNull OnImageHandleListener listener) {
        this.listener = listener;
    }

    public static ImageService getService(@NotNull OnImageHandleListener listener) {
        return new ImageService(listener);
    }

    public void getImageVector(final File file) {
        Thread thread = new Thread(() -> {
            try {
                BufferedImage image = ImageIO.read(file);
                int[] result = convertWithoutUsing(image);
                listener.onVectorize(result);
            } catch (IOException io) {
                io.printStackTrace();
                listener.onError();
            }
        });
        thread.start();
    }

    private int[] convertWithoutUsing(BufferedImage image) {
        final byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        final int width = image.getWidth();
        final int height = image.getHeight();
        final boolean hasAlphaChannel = hasAlfaChannel(image);

        int[] result = new int[height * width];

        if (hasAlphaChannel) {
            final int pixelLength = 4;
            for (int pixel = 0, counter = 0; pixel < pixels.length; pixel += pixelLength) {
                int argb = 0;

                argb += (((int) pixels[pixel] & PIXEL_MASK) << ALFA_SHIFT);
                argb += ((int) pixels[pixel + 1] & PIXEL_MASK);
                argb += (((int) pixels[pixel + 2] & PIXEL_MASK) << GREEN_SHIFT);
                argb += (((int) pixels[pixel + 3] & PIXEL_MASK) << RED_SHIFT);

                result[counter] = argb;
                counter++;
            }
        } else {
            final int pixelLength = 3;
            for (int pixel = 0, counter = 0; pixel < pixels.length; pixel += pixelLength) {
                int argb = 0;

                argb += ALFA_OFFSET;
                argb += ((int) pixels[pixel] & PIXEL_MASK);
                argb += (((int) pixels[pixel + 1] & PIXEL_MASK) << GREEN_SHIFT);
                argb += (((int) pixels[pixel + 2] & PIXEL_MASK) << RED_SHIFT);

                result[counter] = argb;
                counter++;
            }
        }

        return result;
    }

    private boolean hasAlfaChannel(BufferedImage image) {
        return image.getAlphaRaster() != null;
    }
}
