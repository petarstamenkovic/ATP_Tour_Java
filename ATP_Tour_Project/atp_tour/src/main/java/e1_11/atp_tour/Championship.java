
package e1_11.atp_tour;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


public class Championship {
    // Atributes
    private ArrayList<Player> players;
    private ArrayList<Tournament> tournaments;
    
    // Methods
    // Standard constructor
    public Championship(ArrayList<Player> players, ArrayList<Tournament> tournaments) {
        this.players = players;
        this.tournaments = tournaments;
    }
    
    // Method that updates a list by ATP Points
    public void updateAtpPoints()
    {
        Collections.sort(players);
    }
    
    // After sorting a list by ATP points, just fix the ATP Ranks
    public void updateAtpRank()
    {
        for(int i = 0 ; i < this.players.size() ; i++)
        {
            this.players.get(i).setAtpRank(i+1);
        }
    }
    
    public void recoverPlayers()
    {
        for(int i = 0 ; i< players.size() ; i++)
        {
            if(players.get(i).isInjured())
                players.get(i).setInjured(true);
        }
    }
    
    public void loadFiles() throws FileNotFoundException, IOException
    {
        // Loading players
        File fp = new File("./players.txt");
        if(fp.exists())
        {
            BufferedReader in = new BufferedReader(new FileReader(fp));
            String line;

            while((line = in.readLine()) != null)
            {
                    String [] token = line.split(",");
                    Player player = new Player(Integer.parseInt(token[0]),token[1],token[2],token[3],Integer.parseInt(token[4]));
                    players.add(player);
            }     
        }
        else 
        {
            System.out.println("File does not exist!");
        }
        
        // Loading tournaments
        File fp2 = new File("./tournaments.txt");
        if(fp2.exists())
        {
            BufferedReader in = new BufferedReader(new FileReader(fp2));
            String line2;

            while((line2 = in.readLine()) != null)
            {
                    String [] token = line2.split(",");
                    Tournament tournament = new SeasonTournament(token[0],token[1],token[2],players); // Polimorfizam
                    tournaments.add(tournament);
            }   
        }
        else 
        {
            System.out.println("File does not exist!");
        }    
    }
    
    @Override
    public String toString()
    {
        return "Ranking after season tournaments : " + "\n" + players;
    }
        
    
}
