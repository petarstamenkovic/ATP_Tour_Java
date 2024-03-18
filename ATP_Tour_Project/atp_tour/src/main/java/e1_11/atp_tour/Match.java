package e1_11.atp_tour;

import java.util.Random;

public class Match {
    // Atributes
    private Player p1;
    private Player p2;
    private int winSetNum; 
    private int p1Sets;
    private int p1Gems;
    private int p2Sets;
    private int p2Gems;
    private int p1ScoreSet[]; // These arrays are used for storing info and printing out match points
    private int p2ScoreSet[];
    // Rng field?
    
    // Methods
    // Constructor
    public Match(Player p1, Player p2, int winSetNum) {
        this.p1 = p1;
        this.p2 = p2;
        this.winSetNum = winSetNum;
    }
    
    public Player playMatch() // So far assuming that this returns the winner
    {
        
    }
    
    private void playGame(int serve) // Da li moze ovako resenje za servis?
    {
        int p1P = 0;
        int p2P = 0;
        int p1Points = 0;
        int p2Points = 0;
        while(p1P < 4 || p2P < 4)
        {
            switch(serve)
            {
                case 0: // Player 1 serves
                   if(chanceEvent(p1.servePointChance(p2, surface)))
                       p1P++;
                   else 
                       p2P++;
                   break;
                
                case 1 : // Player 2 serves
                    if(chanceEvent(p2.servePointChance(p1, surface)))
                        p2P++;
                    else 
                        p1P++;
                    break;
            }
            
                        switch(p1P)
            {
                case 0 -> p1Points = 0; 
                
                case 1 -> p1Points = 15;
                
                case 2 -> p1Points = 30;
                
                case 3 -> p1Points = 40;
            }
            
            switch(p2P)
            {
                case 0 -> p2Points = 0; 
                
                case 1 -> p2Points = 15;
                
                case 2 -> p2Points = 30;
                
                case 3 -> p2Points = 40;
            }
            
            if(p1P == 4)
                this.p1Gems++;
            else if(p2P == 4)
                this.p2Gems++;
                
        }
    }
    
    private void playSet()
    {}
    
    private void playTieBreak()
    {}
   
    // Use this to declare if a player won a point, probability should be p1.servePointChance
    private boolean chanceEvent(int probability)
    {
        Random rand = new Random();
        int chance = rand.nextInt(101);
        if (chance <= probability) 
            return true;
        else 
            return false;
        
    }
    
    
    
    
    
    
    
}
