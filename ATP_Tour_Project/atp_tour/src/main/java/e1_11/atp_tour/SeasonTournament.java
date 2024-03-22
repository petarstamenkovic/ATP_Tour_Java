
package e1_11.atp_tour;

import java.util.ArrayList;
import java.util.Collections;


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
        this.quaterFinalists = new ArrayList<>();
        this.semiFinalists = new ArrayList<>();
        this.finalists = new ArrayList<>();
    }
    
    @Override
    public void play()
    {
        int numSet = 0;
        int numOfMatches = 0;
        switch(this.tourType)
        {
            case "Grand Slam" : 
                numSet = 3;
                break;
                
            case "Masters1000" : 
                numSet = 2;
                break;
        }
        
        Collections.shuffle(roundOf16);
        
        // Round of 16
        numOfMatches = this.roundOf16.size()/2;
        for(int i = 0 ; i<numOfMatches ; i++)
        {
            Player p1 = this.roundOf16.get(i*2);
            Player p2 = this.roundOf16.get(i*2+1);
            Match rm = new Match(p1,p2,numSet,this.tourSurface);
            Player r_winner = rm.playMatch();
            System.out.println("\n" + "Round of 16 match number " + (i+1) + ": "  + "\n" + rm);
            if(r_winner == p1)
            {
                this.quaterFinalists.add(p1);
                if(numSet == 2)
                    p2.setAtpPoints(p2.getAtpPoints() + 100);
                if(numSet == 3)
                    p2.setAtpPoints(p2.getAtpPoints() + 180);
            }
            else 
            {
                this.quaterFinalists.add(p2);
                if(numSet == 2)
                    p1.setAtpPoints(p1.getAtpPoints() + 100);
                if(numSet == 3)
                    p1.setAtpPoints(p1.getAtpPoints() + 180);
            }
        }
        
        // Quaterfinals
        numOfMatches = this.quaterFinalists.size()/2;
        for(int i = 0 ; i < numOfMatches ; i++)
        {
            Player p1 = this.quaterFinalists.get(i*2);
            Player p2 = this.quaterFinalists.get(i*2+1);
            Match qm = new Match(p1,p2,numSet,this.tourSurface);
            Player q_winner = qm.playMatch();
            System.out.println("\n" + "Quaterfinals match number " + (i+1) + ": "  + "\n" + qm);
            if(q_winner == p1)
            {
                this.semiFinalists.add(p1);
                if(numSet == 2)
                    p2.setAtpPoints(p2.getAtpPoints() + 200);
                if(numSet == 3)
                    p2.setAtpPoints(p2.getAtpPoints() + 360);
            }
            else
            {
                this.semiFinalists.add(p2);
                if(numSet == 2)
                    p1.setAtpPoints(p1.getAtpPoints() + 200);
                if(numSet == 3)
                    p1.setAtpPoints(p1.getAtpPoints() + 360);
            }   
        }
        
        // Semifinals
        numOfMatches = this.semiFinalists.size()/2;
        for(int i = 0 ; i < numOfMatches ; i++)
        {
            Player p1 = this.semiFinalists.get(i*2);
            Player p2 = this.semiFinalists.get(i*2+1);
            Match sm = new Match(p1,p2,numSet,this.tourSurface);
            Player s_winner = sm.playMatch();
            System.out.println("\n" + "Semifinals match number " + (i+1) + ": "  + "\n" + sm);
            if(s_winner == p1)
            {
                this.finalists.add(p1);
                if(numSet == 2)
                    p2.setAtpPoints(p2.getAtpPoints() + 400);
                if(numSet == 3)
                    p2.setAtpPoints(p2.getAtpPoints() + 720);
            }
            else
            {
                this.finalists.add(p2);
                if(numSet == 2)
                    p1.setAtpPoints(p1.getAtpPoints() + 400);
                if(numSet == 3)
                    p1.setAtpPoints(p1.getAtpPoints() + 720);
            }   
        }
        
        // Finals
        Player p1 = this.finalists.get(0);
        Player p2 = this.finalists.get(1);
        Match fm = new Match(p1,p2,numSet,this.tourSurface);
        Player champion = fm.playMatch();
        System.out.println("\n" + "Final match : " + "\n" + fm);
        
        if(champion == p1)
        {
            if(numSet == 2)
            {
                p1.setAtpPoints(p1.getAtpPoints() + 1000);
                p2.setAtpPoints(p2.getAtpPoints() + 650);
            }
            else if(numSet == 3)
            {
                p1.setAtpPoints(p1.getAtpPoints() + 2000);
                p2.setAtpPoints(p2.getAtpPoints() + 1200);
            }
        }
        else 
        {
            if(numSet == 2)
            {
                p2.setAtpPoints(p1.getAtpPoints() + 1000);
                p1.setAtpPoints(p2.getAtpPoints() + 650);
            }
            else if(numSet == 3)
            {
                p2.setAtpPoints(p1.getAtpPoints() + 2000);
                p1.setAtpPoints(p2.getAtpPoints() + 1200);
            }        
        }
        
        
    } // Simulate quater, semi and finals, create a copy of smaller arrays
}
