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
    private int p1ScoreSet[];
    private int p2ScoreSet[];
    private int serve;
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
        this.currentSet = 0;                    // The set that is currently being played, used for indexing the p1ScoreSet and p2ScoreSet
        this.p1ScoreSet = new int[winSetNum+2]; // +2 beacuse if its a best of 3 , max amount of sets is 5(3+2)
        this.p2ScoreSet = new int[winSetNum+2];
    }
    
    public Player playMatch()
    {
        // 1% chance of injuries on both players - If a player is injured there will be zero games played but the sets will show who is injured
        Random rand = new Random();
        int injury = 1 + rand.nextInt(100);
        if(injury == 1)
        {
            this.p1.setInjured(true);
            this.p2Sets = this.winSetNum;
            this.p1Sets = 0;
            System.out.println(p1.getName() + " got injured and backed off!");
            return p2;
        }
        else if(injury == 2)
        {
            this.p2.setInjured(true);
            this.p1Sets = this.winSetNum;
            this.p2Sets = 0;
            System.out.println(p2.getName() + " got injured and backed off!");
            return p1;
        }
        
        // Match ending mechanism
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
    
    private void playGame() // Due to a fact that im not printing points in score, im using 0,1,2,3,4(0,15,30,40,win)
    {
        int p1Points = 0;
        int p2Points = 0;
        while(true)
        {
            // Switch block is where the points are achieved
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
                        break;
                    }
                    else
                    {
                        this.p2Gems++;
                        break;
                    }
                }
            }
            
            // Deuce situation
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
                            break;
                        }
                    } 
                    else 
                    {
                        p2Points++;
                        if (p2Points - p1Points >= 2)
                        {
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
        while(true)
        {
            this.playGame();
            this.serve = 1 - this.serve; // After a played game, change the player to serve
            // End set mechanisms, 6/4 or 7/5 on both sides
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
                // Switch block used for winning points
                switch(this.serve)
                {
                    case 0 : // Player 1 to serve
                        if(chanceEvent(p1.servePointChance(p2, this.matchSurface)))
                            p1P++;
                        else
                            p2P++;
                        break;

                    case 1 : // Player 2 to serve
                        if(chanceEvent(p2.servePointChance(p1, this.matchSurface)))
                            p2P++;
                        else
                            p1P++;
                        break;
                }
                this.serve = 1 - this.serve;    // Switch player to serve after each point in tie break
                       
                // 2 points diff and reached a 7 or more - Tir break end mechanism
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
    
    // Print out a match
    @Override
    public String toString()
    {
        return p1.getName() + ":" +  Arrays.toString(this.p1ScoreSet) + "   " + this.p1Sets + "\n" + p2.getName() + ":" + Arrays.toString(this.p2ScoreSet) + "   " + this.p2Sets;
    }
     
    
}
