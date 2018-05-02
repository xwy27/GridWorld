import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.FilteredImageSource;
import java.awt.image.RGBImageFilter;

import imagereader.IImageProcessor;

public class MyImageProcessor implements IImageProcessor {
    /**
     * A <code>selector</code> selects the expected GRB chanel<br />
     */
    class RGBChanelSelector extends RGBImageFilter {
        // Red chanel
        private static final int RCHANEL = 0xffff0000;
        // Green chanel
        private static final int GCHANEL = 0xff00ff00;
        // Blue chanel
        private static final int BCHANEL = 0xff0000ff;

        // the expected chanel
        private String chanel;

        /**
         * Construct a preferred RBG chanel processor
         */
        RGBChanelSelector(String selectChanel) {
            chanel = selectChanel;
        }

        public int filterRGB(int x, int y, int rgb) {
            if (chanel.equals("red")) {
                return rgb & RCHANEL;
            } else if (chanel.equals("green")) {
                return rgb & GCHANEL;
            } else if (chanel.equals("blue")) {
                return rgb & BCHANEL;
            }

            int grayChanel = (int) (0.299 * ((rgb & 0x00ff0000) >> 16) + 0.587 * ((rgb & 0x0000ff00) >> 8)
                    + 0.114 * (rgb & 0x000000ff));
            return (rgb & 0xff000000) + (grayChanel << 16) + (grayChanel << 8) + grayChanel;
        }
    }

    public Image showChanelR(Image image) {
        RGBChanelSelector red = new RGBChanelSelector("red");
        return Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), red));
    }

    public Image showChanelG(Image image) {
        RGBChanelSelector green = new RGBChanelSelector("green");
        return Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), green));
    }

    public Image showChanelB(Image image) {
        RGBChanelSelector blue = new RGBChanelSelector("blue");
        return Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), blue));
    }

    public Image showGray(Image image) {
        RGBChanelSelector gray = new RGBChanelSelector("gray");
        return Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), gray));
    }
}