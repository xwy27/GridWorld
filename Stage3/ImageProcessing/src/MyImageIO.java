import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.MemoryImageSource;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;

import javax.imageio.ImageIO;

import imagereader.IImageIO;

public class MyImageIO implements IImageIO {
    // bmp head byte length
    private static final int HEAD_BYTE = 14;
    // bmp info byte length
    private static final int INFO_BYTE = 40;
    // bmp multiple color
    private static final int MULTICOLOR = 24;
    // bmp gray color
    private static final int GRAY = 8;
    // bmp bit count
    private int bCount;

    public Image myRead(String filePath) throws IOException {
        FileInputStream file = new FileInputStream(filePath);
        
        byte bHead[] = new byte[HEAD_BYTE];
        byte bInfo[] = new byte[INFO_BYTE];
        byte rgbByte[];
        int bOffset, bWidth, bHeight, bImageSize, bPixelSize;
        int bImageRGB[];
        int emptyByte;
        
        Image image = null;

        try {
            //read head
            file.read(bHead, 0, HEAD_BYTE);
            //read info
            file.read(bInfo, 0, INFO_BYTE);

            bOffset = bytestoInt(bHead, 13, Number.FOUR);
            bWidth = bytestoInt(bInfo, 7, Number.FOUR);
            bHeight = bytestoInt(bInfo, 11, Number.FOUR);
            bCount = bytestoInt(bInfo, 15, Number.TWO);
            bImageSize = bytestoInt(bInfo, 23, Number.FOUR);

            int factor = (bCount == MULTICOLOR ? Number.THREE : Number.ONE);
            
            emptyByte = (bImageSize / bHeight) - bWidth * factor;
            if (emptyByte == Number.FOUR) {
                emptyByte = 0;
            }

            bPixelSize = (bWidth + emptyByte) * factor * bHeight;
            
            if (emptyByte != 0) {
                rgbByte = new byte[bPixelSize];
            } else {
                rgbByte = new byte[bImageSize];
            }

            //read bitmap rgb datas
            file.read(rgbByte, 0, bPixelSize);
            bImageRGB = new int[bWidth * bHeight];
            
            int index = (bCount == MULTICOLOR ? 0 : bOffset);
            if (bCount == MULTICOLOR) {
                for (int i = 0; i < bHeight; ++i) {
                    for (int j = 0; j < bWidth; ++j) {
                        bImageRGB[bWidth * (bHeight - i - 1) + j] =
                            (255 & 0xff) << 24 | bytestoInt(rgbByte, index + 2, 3);
                        index += factor;
                    }
                    index += emptyByte;
                }
            }

            if (bCount == GRAY) {
                for (int i = 0; i < bHeight; ++i) {
                    for (int j = 0; j < bWidth; ++j) {
                        if (index >= bPixelSize) {
                            index = 0;
                        }
                        int temp = ((int)rgbByte[index] & 0xff);
                        bImageRGB[bWidth * (bHeight - i - 1) + j] =
                            (255 & 0xff) << 24 | temp << 16 | temp << 8 | temp;
                        index += factor;
                    }
                    index += emptyByte;
                }
            }
            
            image = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(bWidth, bHeight, bImageRGB, 0, bWidth));

            file.close();
        } catch(Exception e) {}

        return image;
    }

    /**
     * Convert the specific datas in a byte array to integer
     * @param arr the byte array
     * @param start the start index
     * @param length the length of the data
     * @return the converted integer
     */
    private int bytestoInt(byte[] arr, int start, int length) {
        int temp = 0;
        for (int i = length - 1; i >= 0; --i) {
            temp |= (int) (arr[start-i] & 0xff) << (8 * (length - i - 1));
        }
        return temp;
    }

    public Image myWrite(Image image, String fileName) throws IOException {
        try {
            int height = image.getHeight(null);
            int width = image.getWidth(null);
            int fileType;

            if (bCount == MULTICOLOR) {
                fileType = BufferedImage.TYPE_3BYTE_BGR;
            } else {
                fileType = BufferedImage.TYPE_BYTE_GRAY;
            }

            BufferedImage bmp = new BufferedImage(width, height, fileType);
            bmp.getGraphics().drawImage(image, 0, 0, null);

            File file = new File(fileName + ".bmp");
            ImageIO.write(bmp, "bmp", file);
        } catch(Exception e) {}

        return image;
    }
}
