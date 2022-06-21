package util;

import java.util.Date;

/**
 * The class that contains all data from a video game user.
 */
public class UserData {

    private final String name;
    private double points;
    private Date lastGame;

    /**
     * The UserData constructor.
     * 
     * @param name the user name
     */
    public UserData(final String name) {
        this.name = name;
    }

    /**
     * Gets the total points possessed by the user.
     * @return points
     */
    public double getPoints() {
        return points;
    }

    /**
     * Gets the date of the last game played.
     * @return Date
     */
    public Date getLastGame() {
        return lastGame;
    }
    
    public void setLastGame(final Date lastGame) {
        this.lastGame = lastGame;
    }
    
    /**
     * Gets the user name.
     * @return String
     */
    public String getName() {
        return this.name;
    }
    /**
     * {@inheritDoc}
     */
    public String toString() {
        return this.name + ": " + this.points + " points, " + this.lastGame + " last game";
    }
}
