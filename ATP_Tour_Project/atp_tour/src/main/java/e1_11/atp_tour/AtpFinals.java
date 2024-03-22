
package e1_11.atp_tour;

import java.util.ArrayList;
import java.util.Collections;


public class AtpFinals extends Tournament{
    // Atributes
    private ArrayList<Player> groupA;  // 4 players in each group
    private ArrayList<Player> groupB;
    private ArrayList<Player> semiFinalists;
    private ArrayList<Player> finalists;
    
    // METHODS
    
    // Constructor
    public AtpFinals(String tourName, String tourType , String tourSurface, ArrayList<Player> newGroupA , ArrayList<Player> newGroupB)
    {
        super(tourName,tourType,tourSurface); // Call constructor of tournament
        this.groupA = newGroupA;    
        this.groupB = newGroupB;
        this.semiFinalists = new ArrayList<>();
        this.finalists = new ArrayList<>();
    }
    
    @Override
    public void play()
    {
        // Group A matches
        for(int i = 0 ; i < 3 ; i++)
        {
            for(int j = i+1 ; j < 4 ; j++)
            {
                Player p1 = this.groupA.get(i);
                Player p2 = this.groupA.get(j);
                Match gA = new Match(p1,p2,2,this.tourSurface);
                Player g_winner = gA.playMatch();
                if(g_winner == p1)
                    p1.setAtpPoints(p1.getAtpPoints() + 200);
                else
                    p2.setAtpPoints(p2.getAtpPoints() + 200);
            }
        }
        
        // Group B matches
        for(int i = 0 ; i < 3 ; i++)
        {
            for(int j = i+1 ; j < 4 ; j++)
            {
                Player p1 = this.groupB.get(i);
                Player p2 = this.groupB.get(j);
                Match gA = new Match(p1,p2,2,this.tourSurface);
                Player g_winner = gA.playMatch();
                if(g_winner == p1)
                    p1.setAtpPoints(p1.getAtpPoints() + 200);
                else
                    p2.setAtpPoints(p2.getAtpPoints() + 200);
            }
        } 
        
        // Ranking after group stage
        Collections.sort(this.groupA);
        Collections.sort(this.groupB);
        
        // Semifinals match 1
        Player sp1 = this.groupA.get(0);
        this.semiFinalists.add(sp1); // Not neccesary i guess
        Player sp2 = this.groupB.get(1);
        this.semiFinalists.add(sp2); // Not neccesary i guess
        Match sf1 = new Match(sp1,sp2,2,this.tourSurface);
        Player sf1_w = sf1.playMatch();
        if(sf1_w == sp1)
        {
            sp1.setAtpPoints(sp1.getAtpPoints() + 400);
        }
        else
        {
            sp1.setAtpPoints(sp1.getAtpPoints() + 400);
        }
        this.finalists.add(sf1_w);
        
        // Semifinals match 2
        Player sp3 = this.groupA.get(1);
        this.semiFinalists.add(sp3);
        Player sp4 = this.groupB.get(0);
        this.semiFinalists.add(sp4);
        Match sf2 = new Match(sp3,sp4,2,this.tourSurface);
        Player sf2_w = sf2.playMatch();
        if(sf2_w == sp3)
        {
            sp3.setAtpPoints(sp3.getAtpPoints() + 400);
        }
        else
        {
            sp4.setAtpPoints(sp4.getAtpPoints() + 400);
        }
        this.finalists.add(sf2_w);
        
        // Finals 
        Player f1 = this.finalists.get(0);
        Player f2 = this.finalists.get(1);
        Match finals = new Match(f1,f2,2,this.tourSurface);
        Player champion = finals.playMatch();
        if(champion == f1)
        {
            f1.setAtpPoints(f1.getAtpPoints() + 500);
        }
        else
        {
            f2.setAtpPoints(f2.getAtpPoints() + 500);
        }
        
        
    }       
    
}
