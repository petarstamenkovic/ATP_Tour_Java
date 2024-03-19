
package e1_11.atp_tour;

import java.util.ArrayList;


public abstract class Tournament {
    // Atributes
    protected String tourName;
    protected String tourType;
    protected String tourSurface;
    protected boolean playable;
    protected int numOfSets;
    protected ArrayList<Player> contestants;
    
    // Methods

    public Tournament(String tourName, String tourType, String tourSurface, boolean playable, int numOfSets, ArrayList<Player> contestants) {
        this.tourName = tourName;
        this.tourType = tourType;
        this.tourSurface = tourSurface;
        this.playable = playable;
        this.numOfSets = numOfSets;
        this.contestants = contestants;
    }
    
    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public String getTourType() {
        return tourType;
    }

    public void setTourType(String tourType) {
        this.tourType = tourType;
    }

    public String getTourSurface() {
        return tourSurface;
    }

    public void setTourSurface(String tourSurface) {
        this.tourSurface = tourSurface;
    }

    public boolean isPlayable() {
        return playable;
    }

    public void setPlayable(boolean playable) {
        this.playable = playable;
    }

    public int getNumOfSets() {
        return numOfSets;
    }

    public void setNumOfSets(int numOfSets) {
        this.numOfSets = numOfSets;
    }
    
    
    abstract public void play();
    
    
}
