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
    protected int serve;
    
    // Methods
    // Constructor
    public Match(Player p1, Player p2, int winSetNum) {
        this.p1 = p1;
        this.p2 = p2;
        this.winSetNum = winSetNum;
        this.p1Gems = 0;
        this.p2Gems = 0;
        this.p1Sets = 0;
        this.p2Sets = 0;
    }
    
    public Player playMatch() // So far assuming that this returns the winner
    {
        while(this.p1Sets <= this.winSetNum || this.p2Sets <= this.winSetNum)
        {
            this.playSet();
            if(this.p1Sets == this.winSetNum)
            {
                return p1;
            }
            else if(this.p2Sets == this.winSetNum)
            {
                return p2;
            }
        }   
        return null;
    }
    
    private void playGame() // Da li moze ovako resenje za servis?
    {
        int p1P = 0;
        int p2P = 0;
        int p1Points = 0;
        int p2Points = 0;
        while(p1P < 4 || p2P < 4)
        {
            switch(this.serve)
            {
                case 0: // Player 1 serves
                   if(chanceEvent(p1.servePointChance(p2, surface)))
                       p1P++;
                   else 
                       p2P++;
                  
                  // Deuce on a player one serve
                  if(p1P == 3 && p2P == 3)
                  {
                      boolean adv1 = false ;
                      boolean adv2 = false;
                      
                      while(true)
                      {
                        if(chanceEvent(p1.servePointChance(p2, surface)))
                        {
                            if(adv2)
                                adv2 = false;
                            else if(adv1)
                            {   
                                this.p1Gems++;
                                break; // Player 1 won a game
                            }
                            else 
                                adv1 = true;
                        }
                        else 
                        {
                            if(adv1)               
                                adv1 = false;                        
                            else if(adv2)
                            {   
                                this.p2Gems++;
                                break; // Player 2 won a game
                            }
                            else
                                adv2 = true;            
                        }
                       
                      }
                  }
                  break; // Consider this one
                  
                case 1 : // Player 2 serves
                    if(chanceEvent(p2.servePointChance(p1, surface)))
                        p2P++;
                    else 
                        p1P++;
                    
                  // Deuce on a player two serve
                  if(p1P == 3 && p2P == 3)
                  {
                      boolean adv1 = false ;
                      boolean adv2 = false;
                      
                      while(true)
                      {
                        if(chanceEvent(p2.servePointChance(p1, surface)))
                        {
                            if(adv1)
                            {
                                adv1 = false;
                            }
                            else if(adv2)
                            {
                                this.p2Gems++;
                                p1P = 0;
                                p2P = 0;
                                break; // Player 2 won a game
                            }
                            else 
                            {
                                adv2 = true;
                            }
                        }
                        else 
                        {
                            if(adv2)
                            {
                                adv2 = false;
                            }
                            else if(adv1)
                            {
                                this.p1Gems++;
                                p1P = 0;
                                p2P = 0;
                                break; // Player 1 won a game
                            }
                            else
                            {
                                adv1 = true;
                            }
                                    
                        }
                       
                      }
                  }
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
            
            if(p1P == 4 && p2P != 4)
            {
                this.p1Gems++;
                p1P = 0;
                p2P = 0;
                break;
            }
            else if(p1P != 4 && p2P == 4)
            {
                this.p2Gems++;
                p1P = 0;
                p2P = 0;
                break;
            }   
        }
    }
    
    private void playSet()
    {
        this.serve = 0;
        while(this.p1Gems <= 7 && this.p2Gems <= 7)
        {
            this.playGame();
            this.serve = 1 - this.serve;
            if(this.p1Gems == 6 && this.p2Gems <= 4)
            {
                this.p1Sets++;
                this.p1Gems = 0;
                this.p2Gems = 0;
                break;
            }
            else if(this.p1Gems == 7 && this.p2Gems == 5)
            {
                this.p1Sets++;
                this.p1Gems = 0;
                this.p2Gems = 0;
                break;
            }
            else if(this.p2Gems == 6 && this.p1Gems <= 4)
            {
                this.p2Sets++; 
                this.p1Gems = 0;
                this.p2Gems = 0;
                break;
            }
            else if(this.p2Gems == 7 && this.p1Gems == 5)
            {
                this.p2Sets++;
                this.p1Gems = 0;
                this.p2Gems = 0;
                break;
            }
            else if(this.p1Gems == 6 && this.p2Gems == 6)
                playTieBreak();
                break;
        }  
    }
    
    private void playTieBreak()
    {
        int p1P = 0;
        int p2P = 0;
        this.serve = 0;
        while(true)
        {
            switch(this.serve)
            {
                case 0 : // Player 1 to serve
                    if(chanceEvent(p1.servePointChance(p2, surface)))
                        p1P++;
                    else
                        p2P++;
                        
                    // 2 points diff and reached a 7
                    if(Math.abs(p1P - p2P) >= 2 && (p1P == 7 || p2P == 7))
                        if(p1P > p2P)
                        {
                            this.p1Sets++;
                            break;
                        }
                        else 
                        {
                            this.p2Sets++;
                            break;
                        }
                    
                case 1 : // Player 2 to serve
                    if(chanceEvent(p2.servePointChance(p1, surface)))
                        p2P++;
                    else
                        p1P++;
                        
                    // 2 points diff and reached a 7
                    if(Math.abs(p1P - p2P) >= 2 && (p1P == 7 || p2P == 7))
                        if(p1P > p2P)
                        {
                            this.p1Sets++;
                            break;
                        }
                        else
                        {
                            this.p2Sets++;
                            break;
                        }
                    
                    this.serve = 1 - this.serve; 
            }
        }
    }
   
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
