package util.map;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * 
 *
 */
public class TextMap {

    private final double width;
    private final double height;

    private final String path;

    /**
     * 
     * @param path
     * @throws IOException
     */
    public TextMap(final String path) throws IOException {
        var br = new BufferedReader(new FileReader(path));
        this.height = br.lines().count();
        br.close();
        br = new BufferedReader(new FileReader(path));
        this.width = br.readLine().length();
        br.close();
        this.path = path;
    }

    /**
     * 
     * @return bla
     */
    public double getWidth() {
        return width;
    }

    /**
     * 
     * @return bla
     */
    public double getHeight() {
        return height;
    }

    /**
     * 
     * @return bla
     */
    public File getFile() {
        return new File(path);
    }

}
