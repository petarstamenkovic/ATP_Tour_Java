
package e1_11.atp_tour;

import java.util.ArrayList;


public class SeasonTournament extends Tournament {
    // Atributes
    private ArrayList<Player> roundOf16;
    private ArrayList<Player> quaterFinalists;
    private ArrayList<Player> semiFinalists;
    private ArrayList<Player> finalists;
    
    // Methods
    public SeasonTournament(String tourName, String tourType , String tourSurface, ArrayList<Player> roundOf16)
    {
        super(tourName,tourType,tourSurface); // Call constructor of tournament
        this.roundOf16 = roundOf16;     // Does this copies a reference as original?
    }
    
    public void play(){} // Simulate quater, semi and finals, create a copy of smaller arrays
}
