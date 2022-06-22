package util;

import java.util.Date;

/**
 * The class that contains all data from a video game user.
 */
public class UserData {

    private final String name;
    private int points;
    private int lpLeft;
    private Date lastGame;
    
    public static final int POINTS_PER_ENEMY = 10;

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
    public int getPoints() {
        return points;
    }

    public void increasePoints() {
        this.points += POINTS_PER_ENEMY;
    }
    
    public int getLpLeft() {
        return this.lpLeft;
    }
    
    public void setLpLeft(int lp) {
        this.lpLeft = lp;
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
