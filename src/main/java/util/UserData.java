package util;

import java.util.Date;

/**
 * TODO: Filippo Gurioli.
 */
public class UserData {

    private final String name;
    private double points;
    private Date lastGame;

    /**
     * TODO: Filippo Gurioli.
     * @param name
     */
    public UserData(final String name) {
        this.name = name;
    }

    /**
     * TODO: Filippo Gurioli.
     * @return double
     */
    public double getPoints() {
        return points;
    }

    /**
     * TODO: Filippo Gurioli.
     * @param points
     */
    public void setPoints(final double points) {
        this.points = points;
    }

    /**
     * TODO: Filippo Gurioli.
     * @return Date
     */
    public Date getLastGame() {
        return lastGame;
    }

    /**
     * TODO: Filippo Gurioli.
     * @param lastGame
     */
    public void setLastGame(final Date lastGame) {
        this.lastGame = lastGame;
    }

    /**
     * TODO: Filippo Gurioli.
     * @return String
     */
    public String getName() {
        return this.name;
    }
}
