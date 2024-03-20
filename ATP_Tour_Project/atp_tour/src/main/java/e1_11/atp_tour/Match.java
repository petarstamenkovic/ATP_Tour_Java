package e1_11.atp_tour;

import java.util.Arrays;
import java.util.Random;

public class Match {
    // Atributes
    private Player p1;
    private Player p2;
    private int winSetNum; 
    private String matchSurface;
    private int p1Sets;
    private int p1Gems;
    private int p2Sets;
    private int p2Gems;
    private int p1ScoreSet[]; // These arrays are used for storing info and printing out match points
    private int p2ScoreSet[];
    protected int serve;
    private int currentSet;
    
    // Methods
    // Constructor
    public Match(Player p1, Player p2, int winSetNum, String matchSurface) {
        this.p1 = p1;
        this.p2 = p2;
        this.winSetNum = winSetNum;
        this.matchSurface = matchSurface;
        this.p1Gems = 0;
        this.p2Gems = 0;
        this.p1Sets = 0;
        this.p2Sets = 0;
        this.currentSet = 0;
        this.p1ScoreSet = new int[winSetNum+2]; // This is put +1 purely to escape the out of bounds error
        this.p2ScoreSet = new int[winSetNum+2];
    }
    
    public Player playMatch()
    {
        
        // 1% chance of injuries on both players
        Random rand = new Random();
        int injury = 1 + rand.nextInt(100);
        if(injury == 1)
        {
            this.p1.setInjured(true);
            return p2;
        }
        else if(injury == 2)
        {
            this.p2.setInjured(true);
            return p1;
        }
        
        while(this.p1Sets < this.winSetNum || this.p2Sets < this.winSetNum)
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
    
    private void playGame()
    {
        int p1Points = 0;
        int p2Points = 0;
        while(true)
        {
            switch(this.serve)
            {
                case 0: // Player 1 serves
                   if(chanceEvent(p1.servePointChance(p2, this.matchSurface)))
                       p1Points++;
                   else 
                       p2Points++;
                   break;
                   
                case 1 : // Player 2 serves
                    if(chanceEvent(p2.servePointChance(p1, this.matchSurface)))
                        p2Points++;
                    else 
                        p1Points++;
                    break;
            }

            // Clean win without deuce
            if(p1Points >= 4 || p2Points >= 4)
            {
                if(Math.abs(p1Points - p2Points) >= 2)
                {
                    if(p1Points > p2Points)
                    {
                        this.p1Gems++;
                        //p1Points = 0;
                        //p2Points = 0;
                        break;
                    }
                    else
                    {
                        this.p2Gems++;
                        //p1Points = 0;
                        //p2Points = 0;
                        break;
                    }
                }
            }
            
            // Deuce
            if (p1Points == 3 && p2Points == 3) 
            {
                while (true) 
                {
                    if (chanceEvent(p1.servePointChance(p2, this.matchSurface))) 
                    {
                        p1Points++;
                        if (p1Points - p2Points >= 2) 
                        {
                            this.p1Gems++;
                            //p1Points = 0;
                            //p2Points = 0;
                            break;
                        }
                    } 
                    else 
                    {
                        p2Points++;
                        if (p2Points - p1Points >= 2)
                        {
                            //p1Points = 0;
                            //p2Points = 0;
                            this.p2Gems++;
                            break;
                        }
                    }
                }
                break;
            }
        }
    }
    private void playSet()
    {
        this.serve = 0;
        while(this.p1Gems < 7 && this.p2Gems < 7)
        {
            this.playGame();
            this.serve = 1 - this.serve;
            if(this.p1Gems == 6 && this.p2Gems <= 4)
            {
                this.p1ScoreSet[this.currentSet] = this.p1Gems;
                this.p2ScoreSet[this.currentSet] = this.p2Gems;
                this.currentSet++;
                this.p1Sets++;
                this.p1Gems = 0;
                this.p2Gems = 0;
                break;
            }
            else if(this.p1Gems == 7 && this.p2Gems == 5)
            {
                this.p1ScoreSet[this.currentSet] = this.p1Gems;
                this.p2ScoreSet[this.currentSet] = this.p2Gems;
                this.currentSet++;
                this.p1Sets++;
                this.p1Gems = 0;
                this.p2Gems = 0;
                break;
            }
            else if(this.p2Gems == 6 && this.p1Gems <= 4)
            {
                this.p1ScoreSet[this.currentSet] = this.p1Gems;
                this.p2ScoreSet[this.currentSet] = this.p2Gems;
                this.currentSet++;
                this.p2Sets++; 
                this.p1Gems = 0;
                this.p2Gems = 0;
                break;
            }
            else if(this.p2Gems == 7 && this.p1Gems == 5)
            {
                this.p1ScoreSet[this.currentSet] = this.p1Gems;
                this.p2ScoreSet[this.currentSet] = this.p2Gems;
                this.currentSet++;
                this.p2Sets++;
                this.p1Gems = 0;
                this.p2Gems = 0;
                break;
            }
            else if(this.p1Gems == 6 && this.p2Gems == 6)
                playTieBreak();
              
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
                    if(chanceEvent(p1.servePointChance(p2, this.matchSurface)))
                        p1P++;
                    else
                        p2P++;
                        
                    // 2 points diff and reached a 7
                    if(Math.abs(p1P - p2P) >= 2 && (p1P >= 7 || p2P >= 7))
                    {
                        if(p1P > p2P)
                        {
                            this.p1ScoreSet[this.currentSet] = 7;
                            this.p2ScoreSet[this.currentSet] = 6;
                            this.currentSet++;
                            this.p1Sets++;
                            this.p1Gems = 0;
                            this.p2Gems = 0;
                            break;
                        }
                        else 
                        {
                            this.p1ScoreSet[this.currentSet] = 6;
                            this.p2ScoreSet[this.currentSet] = 7;
                            this.currentSet++;
                            this.p2Sets++;
                            this.p1Gems = 0;
                            this.p2Gems = 0;
                            break;
                        }
                    }
                    break;
                    
                case 1 : // Player 2 to serve
                    if(chanceEvent(p2.servePointChance(p1, this.matchSurface)))
                        p2P++;
                    else
                        p1P++;
                        
                    // 2 points diff and reached a 7
                    if(Math.abs(p1P - p2P) >= 2 && (p1P == 7 || p2P == 7))
                    {
                        if(p1P > p2P)
                        {
                            this.p1ScoreSet[this.currentSet] = 7;
                            this.p2ScoreSet[this.currentSet] = 6;
                            this.currentSet++;
                            this.p1Sets++;
                            this.p1Gems = 0;
                            this.p2Gems = 0;
                            break;
                        }
                        else
                        {
                            this.p1ScoreSet[this.currentSet] = 6;
                            this.p2ScoreSet[this.currentSet] = 7;
                            this.currentSet++;
                            this.p2Sets++;
                            this.p1Gems = 0;
                            this.p2Gems = 0;
                            break;
                        }
                    }
                    break;
                    
            }
                this.serve = 1 - this.serve; 
        }
    }
   
    // Use this to declare if a player won a point, probability should be p1.servePointChance
    private boolean chanceEvent(int probability)
    {
        Random rand = new Random();
        int chance = 1 + rand.nextInt(100);
        if (chance <= probability) 
            return true;
        else 
            return false;
        
    }
    
    @Override
    public String toString()
    {
        return p1.getName() + ":" +  Arrays.toString(this.p1ScoreSet) + "   " + this.p1Sets + "\n" + p2.getName() + ":" + Arrays.toString(this.p2ScoreSet) + "   " + this.p2Sets;
    }
     
    
}
