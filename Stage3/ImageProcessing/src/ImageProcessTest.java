import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.FileInputStream;

import java.awt.image.BufferedImage;
import java.awt.Image;

import javax.imageio.ImageIO;

public class ImageProcessTest {
    private MyImageIO myImage; //bitmap I/O
    private String picture1; //traget bitmap path
    private String goalPath; //goal bitmap path

    /**
     * Initialization
     */
    @Before
    public void setUp() throws Exception {
        myImage = new MyImageIO();
        picture1 = "/home/xwy27/Desktop/GridWorld/Stage3/ImageProcessing/target/1.bmp";
        goalPath = "/home/xwy27/Desktop/GridWorld/Stage3/ImageProcessing/goal/";
    }
    
    /**
     * Test red chanel convert
     */
    @Test
    public void testRed() throws IOException {
        Image image = myImage.myRead(picture1);
        
        MyImageProcessor processor = new MyImageProcessor();
        Image red = processor.showChanelR(image);
        
        FileInputStream testFile = new FileInputStream(goalPath + "1_red_goal.bmp");
        BufferedImage testImage = ImageIO.read(testFile);
        
        int w = red.getWidth(null);
        int d = red.getHeight(null);
        BufferedImage processImage = new BufferedImage(w, d, BufferedImage.TYPE_INT_BGR);
        processImage.getGraphics().drawImage(red, 0, 0, w, d, null);
        
        for (int i = 0; i < testImage.getWidth(null); i++) {
            for (int j = 0; j < testImage.getHeight(null); j++) {
                assertEquals(testImage.getRGB(i, j), processImage.getRGB(i, j));
            }
        }
        
        assertEquals(red.getWidth(null), testImage.getWidth(null));
        assertEquals(red.getHeight(null), testImage.getHeight(null));
    }
    
    /**
     * Test green chanel convert
     */
    @Test
    public void testGreen() throws IOException {
        Image image = myImage.myRead(picture1);
        
        MyImageProcessor processor = new MyImageProcessor();
        Image green = processor.showChanelG(image);
        
        FileInputStream testFile = new FileInputStream(goalPath + "1_green_goal.bmp");
        BufferedImage testImage = ImageIO.read(testFile);
        
        int w = green.getWidth(null);
        int d = green.getHeight(null);
        BufferedImage processImage = new BufferedImage(w, d, BufferedImage.TYPE_INT_BGR);
        processImage.getGraphics().drawImage(green, 0, 0, w, d, null);
        
        for (int i = 0; i < testImage.getWidth(null); i++) {
            for (int j = 0; j < testImage.getHeight(null); j++) {
                assertEquals(testImage.getRGB(i, j), processImage.getRGB(i, j));
            }
        }
        
        assertEquals(green.getWidth(null), testImage.getWidth(null));
        assertEquals(green.getHeight(null), testImage.getHeight(null));
    }
    
    /**
     * Test blue chanel convert
     */
    @Test
    public void testBlue() throws IOException {
        Image image = myImage.myRead(picture1);

        MyImageProcessor processor = new MyImageProcessor();
        Image blue = processor.showChanelB(image);

        FileInputStream testFile = new FileInputStream(goalPath + "1_blue_goal.bmp");
        BufferedImage testImage = ImageIO.read(testFile);

        int w = blue.getWidth(null);
        int d = blue.getHeight(null);
        BufferedImage processImage = new BufferedImage(w, d, BufferedImage.TYPE_INT_BGR);
        processImage.getGraphics().drawImage(blue, 0, 0, w, d, null);

        for (int i = 0; i < testImage.getWidth(null); i++) {
            for (int j = 0; j < testImage.getHeight(null); j++) {
                assertEquals(testImage.getRGB(i, j), processImage.getRGB(i, j));
            }
        }

        assertEquals(blue.getWidth(null), testImage.getWidth(null));
        assertEquals(blue.getHeight(null), testImage.getHeight(null));
    }

    /**
     * Test gray chanel convert
     */
    @Test
    public void testGray() throws IOException {
        Image image = myImage.myRead(picture1);

        MyImageProcessor processor = new MyImageProcessor();
        Image gray = processor.showGray(image);

        FileInputStream testFile = new FileInputStream(goalPath + "1_gray_goal.bmp");
        BufferedImage testImage = ImageIO.read(testFile);

        int w = gray.getWidth(null);
        int d = gray.getHeight(null);
        BufferedImage processImage = new BufferedImage(w, d, BufferedImage.TYPE_INT_BGR);
        processImage.getGraphics().drawImage(gray, 0, 0, w, d, null);

        for (int i = 0; i < testImage.getWidth(null); i++) {
            for (int j = 0; j < testImage.getHeight(null); j++) {
                assertEquals(testImage.getRGB(i, j), processImage.getRGB(i, j));
            }
        }

        assertEquals(gray.getWidth(null), testImage.getWidth(null));
        assertEquals(gray.getHeight(null), testImage.getHeight(null));
    }
}