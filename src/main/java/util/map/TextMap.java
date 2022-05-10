package util.map;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TextMap {

    private final double width;
    private final double height;
    
    private final String path;
    
    public TextMap(final String path) throws IOException {
    	var br = new BufferedReader(new FileReader(path));
    	this.height = br.lines().count();
    	br.close();
    	br = new BufferedReader(new FileReader(path));
    	this.width = br.readLine().length();
    	br.close();
    	this.path = path;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public File getFile() {
        return new File(path);
    }  
    
}
