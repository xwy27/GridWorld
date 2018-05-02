import imagereader.Runner;

/**
 * A <code>runner</code> runs the image processing program<br />
 */
public final class ImageReaderRunner {
    private ImageReaderRunner() {}

    public static void main(String[] args) {
        MyImageIO imageioer = new MyImageIO();
        MyImageProcessor processor = new MyImageProcessor();
        Runner.run(imageioer, processor);
    }
}