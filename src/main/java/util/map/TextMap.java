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
        System.out.println(this.path);
        System.out.println(ClassLoader.getSystemResource(path).getPath());
        File f = new File(ClassLoader.getSystemResource(path).getFile());
        System.out.println(f.exists());
        return f;
    }
    
    public String getPath() {
        return this.path;
    }

}
