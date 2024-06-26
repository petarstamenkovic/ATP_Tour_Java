package e1_11.atp_tour;


public class Player implements Comparable<Player>{
    // Atributes
    private String name;
    private String ability;
    private String preferedSurface;
    private int atpRank;
    private int atpPoints;
    private boolean injured;
    private int groupWin;
    
    // Methods
    // Setters and getters for all atributes
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public String getPreferedSurface() {
        return preferedSurface;
    }

    public void setPreferedSurface(String preferedSurface) {
        this.preferedSurface = preferedSurface;
    }

    public int getAtpRank() {
        return atpRank;
    }

    public void setAtpRank(int atpRank) {
        this.atpRank = atpRank;
    }

    public int getAtpPoints() {
        return atpPoints;
    }

    public void setAtpPoints(int atpPoints) {
        this.atpPoints = atpPoints;
    }

    public boolean isInjured() {
        return injured;
    }

    public void setInjured(boolean injured) {
        this.injured = injured;
    }

    public int getGroupWin() {
        return groupWin;
    }

    public void setGroupWin(int groupWin) {
        this.groupWin = groupWin;
    }

    
    // Constructor with parameters
    public Player(int atpRank, String name, String ability, String preferedSurface, int atpPoints) {
        this.name = name;
        this.ability = ability;
        this.preferedSurface = preferedSurface;
        this.atpRank = atpRank;
        this.atpPoints = atpPoints;
        this.groupWin = -1;
    }
    
    // Method that calucaltes the chance of winning a point on serve
    public int servePointChance(Player opponent, String surface)
    {
        int winChance = 50;
        int rank_diff;
        
        // Check players adut on his serve
        switch(this.ability)
        {       
            case "forehand" -> winChance = winChance + 10;
                
            case "serve" -> winChance = winChance + 15;
                
            case "mentality" -> winChance = winChance + 5;
        }
        
        // Check opponents information that affect this players serve
        switch(opponent.ability)
        {
            case "backhand" -> winChance = winChance - 8;
            
            case "serve" -> winChance = winChance - 5;
            
            case "mentality" -> winChance = winChance - 10;
        }
        
        // Check surface compatability
        if(this.preferedSurface.equals(surface))
            winChance = winChance + 5;
        
        // Points for rank difference
        if(this.atpRank < opponent.atpRank)
        {
            rank_diff = opponent.atpRank - this.atpRank;
            winChance = winChance + rank_diff;
        }
        else 
        {
            rank_diff = this.atpRank - opponent.atpRank;
            winChance = winChance - rank_diff;
        }
         
        return winChance;
    }
    
    // Overriden method for an adapted player information printing
    @Override
    public String toString()
    {
        if(this.groupWin>=0)
            return this.name + ", " + "ATP Rank: " + this.atpRank + ":" + "ATP Points : " + this.atpPoints + " Group wins : " + this.groupWin + "\n";
        else
            return this.name + ", " + "ATP Rank: " + this.atpRank + ":" + "ATP Points : " + this.atpPoints + "\n";
    }
    
    
    // Sorting players by their atpPoints using Comparable interface
    @Override
    public int compareTo(Player p2)
    {
        if (this.atpPoints > p2.atpPoints) 
                return -1;
        else 
                return 1;
    
    }
    

    
}
