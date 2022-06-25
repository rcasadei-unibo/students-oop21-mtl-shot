package util.map;

/**
 * 
 *
 */
public final class MapConstants {

    private static final int TILESIZE = 64;

    private MapConstants() { }

    /**
     * Returns the TILESIZE constant for converting screen pixels into Game Units.
     * @return the TILESIZE constant.
     */
    public static int getTilesize() {
        return TILESIZE;
    }

}
