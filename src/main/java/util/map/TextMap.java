package util.map;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

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
        this.path = path;
        var br = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream(path)));
        this.height = br.lines().count();
        br.close();
        br = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream(path)));
        this.width = br.readLine().length();
        br.close();
    }

    /**
     * Returns the TextMap's width.
     * @return the width.
     */
    public double getWidth() {
        return width;
    }

    /**
     * Returns the TextMap's height.
     * @return the height.
     */
    public double getHeight() {
        return height;
    }

    /**
     * Returns the TextMap's source file.
     * @return the file.
     */
    public File getFile() {
        System.out.println(this.path);
        System.out.println(ClassLoader.getSystemResource(path).getPath());
        File f = new File(ClassLoader.getSystemResource(path).getFile());
        System.out.println(f.exists());
        return f;
    }
    /**
     * Returns the TextMap's File's path.
     * @return the file's path.
     */
    public String getPath() {
        return this.path;
    }

}
