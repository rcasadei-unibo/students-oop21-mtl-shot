package model.map;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TextMap {

    private final double width;
    private final double height;
    
    private final String path;
    
    public TextMap(final String path) throws IOException {
    	this.height = new BufferedReader(new FileReader(path)).lines().count();
    	this.width = new BufferedReader(new FileReader(path)).readLine().length();
    	this.path = path;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public String getPath() {
        return path;
    }  
    
}
