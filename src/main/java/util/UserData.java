package util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The class that contains all data from a video game user.
 */
public class UserData {

    private final String name;
    private int points;
    private int lpLeft;
    private Date lastGame = new Date();
    private final long startTime = new Date().getTime();
    private final SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
    /**
     * Defines the amount of points the player gets for defeating an Enemy.
     */
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

    /**
     * Increases the player's points by POINTS_PER_ENEMY.
     */
    public void increasePoints() {
        this.points += POINTS_PER_ENEMY;
    }
    /**
     * Returns the player's health points.
     * @return the player's remaining health
     */
    public int getLpLeft() {
        return this.lpLeft;
    }

    /**
     * Sets the player's health points.
     * @param lp
     */
    public void setLpLeft(final int lp) {
        this.lpLeft = lp;
    }

    /**
     * Gets the date of the last game played.
     * @return Date
     */
    public Date getLastGame() {
        return lastGame;
    }

    /**
     * Sets the date of the last game played.
     * @param lastGame
     */
    public void setLastGame(final Date lastGame) {
        this.lastGame = lastGame;
    }

    /**
     * Returns time elapsed from starting a new game.
     * @return time elapsed from new game.
     */
    public String getTime() {
        return sdf.format(new Date(new Date().getTime() - startTime));
    }

    /**
     * Gets the user's name.
     * @return the user's name.
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
