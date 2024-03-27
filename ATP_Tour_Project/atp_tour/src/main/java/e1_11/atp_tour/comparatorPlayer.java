package e1_11.atp_tour;

import java.util.Comparator;

// This class is exclusivly for sorting players in their group phase
public class comparatorPlayer implements Comparator<Player>{
    @Override
    public int compare(Player p1, Player p2)
    {
        return Integer.compare(p2.getGroupWin(), p1.getGroupWin()); // switch p2 and p1 for different order
    }
    
}
