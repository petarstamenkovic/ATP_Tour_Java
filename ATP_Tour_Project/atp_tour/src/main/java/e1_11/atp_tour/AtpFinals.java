
package e1_11.atp_tour;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class AtpFinals extends Tournament{
    // Atributes
    private ArrayList<Player> groupA;  // 4 players in each group
    private ArrayList<Player> groupB;
    private ArrayList<Player> semiFinalists;
    private ArrayList<Player> finalists;
    private ArrayList<Player> champion; // Additional atribute for printing out a winner
    
    // METHODS
    // Constructor
    public AtpFinals(String tourName, String tourType , String tourSurface, ArrayList<Player> newGroupA , ArrayList<Player> newGroupB)
    {
        super(tourName,tourType,tourSurface); // Call constructor of tournament
        this.groupA = newGroupA;    // These lists i create in simulation and pass them on as parameters
        this.groupB = newGroupB;
        this.semiFinalists = new ArrayList<>();
        this.finalists = new ArrayList<>();
        this.champion = new ArrayList<>();  
    }

    public ArrayList<Player> getGroupA() {
        return groupA;
    }

    public void setGroupA(ArrayList<Player> groupA) {
        this.groupA = groupA;
    }

    public ArrayList<Player> getGroupB() {
        return groupB;
    }

    public void setGroupB(ArrayList<Player> groupB) {
        this.groupB = groupB;
    }

    public ArrayList<Player> getSemiFinalists() {
        return semiFinalists;
    }

    public void setSemiFinalists(ArrayList<Player> semiFinalists) {
        this.semiFinalists = semiFinalists;
    }

    public ArrayList<Player> getFinalists() {
        return finalists;
    }

    public void setFinalists(ArrayList<Player> finalists) {
        this.finalists = finalists;
    }

    public ArrayList<Player> getChampion() {
        return champion;
    }

    public void setChampion(ArrayList<Player> champion) {
        this.champion = champion;
    }
 
    @Override
    public void play()
    {
        // All the players that made it to here, "activate them in group phase" assign everyone zero wins beacuse of the printing
        for(int i = 0 ; i < this.groupA.size() ; i++)
        {
            this.groupA.get(i).setGroupWin(0);
            this.groupB.get(i).setGroupWin(0);
        }
        // Group A matches
        for(int i = 0 ; i < 3 ; i++)    // Double for loop makes round robin matches -> 0 - 1 ,0 - 2 , 0 - 3 , 1 - 2.... (no 1 - 1 or 1 - 0)
        {
            for(int j = i+1 ; j < 4 ; j++)
            {
                Player p1 = this.groupA.get(i);
                Player p2 = this.groupA.get(j);
                Match gA = new Match(p1,p2,2,this.tourSurface);
                Player g_winner = gA.playMatch();
                if(g_winner == p1)
                {
                    p1.setAtpPoints(p1.getAtpPoints() + 200);
                    p1.setGroupWin(p1.getGroupWin()+1);
                }
                else
                {
                    p2.setAtpPoints(p2.getAtpPoints() + 200);
                    p2.setGroupWin(p1.getGroupWin()+1);
                }
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
                {
                    p1.setAtpPoints(p1.getAtpPoints() + 200);
                    p1.setGroupWin(p1.getGroupWin()+1);
                }
                else
                {
                    p2.setAtpPoints(p2.getAtpPoints() + 200);
                    p2.setGroupWin(p1.getGroupWin()+1);
                }
            }
        } 
        
        // Ranking after group stage - sort groups beacuse the semifinals are made from 1st and 2nd in groups - Call the comparator
        groupSort(this.groupA);
        groupSort(this.groupB);
        //Collections.sort(this.groupA);
        //Collections.sort(this.groupB);
        
        
        // Semifinals match 1
        Player sp1 = this.groupA.get(0); // First in group A
        this.semiFinalists.add(sp1); 
        Player sp2 = this.groupB.get(1); // Second in group B
        this.semiFinalists.add(sp2); 
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
        Player sp3 = this.groupA.get(1); // Second in group A
        this.semiFinalists.add(sp3);
        Player sp4 = this.groupB.get(0); // First in group B
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
            this.champion.add(f1);
        }
        else
        {
            f2.setAtpPoints(f2.getAtpPoints() + 500);
            this.champion.add(f2);
        }
   
    }
    
    // Function in this class(groups are sorted in this class) that sorts by groupWins via Comparator
    public static void groupSort(ArrayList<Player> atpFinalsGroup) 
    {
        Collections.sort(atpFinalsGroup, new comparatorPlayer());
    }
}
