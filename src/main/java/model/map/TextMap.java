package model.map;

public class TextMap {

    private final int width;
    private final int height;
    
    private final String path;
    
    public TextMap(final int width, final int height, final String path) {
	this.width = width;
	this.height = height;
	this.path = path;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getPath() {
        return path;
    }  
    
}
