package model.map;

public class TextMap {

    private final double width;
    private final double height;
    
    private final String path;
    
    public TextMap(final double width, final double height, final String path) {
	this.width = width;
	this.height = height;
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
