
package e1_11.atp_tour;

import java.io.BufferedReader;
import java.util.ArrayList;

import java.io.IOException;
public class Atp_tour {

    public static void main(String[] args) throws IOException {
        
 
        ArrayList<Player> players = new ArrayList<>();
        ArrayList<Tournament> tournaments = new ArrayList<>();
        
        Championship champ = new Championship(players,tournaments);
        champ.loadFiles();
        
        for(int i = 0; i<= 7 ; i++)
        {
            Match m1 = new Match(players.get(i),players.get(i+1),3,"hard");
            m1.playMatch();
            System.out.println("""
                               Match stats -> 
                               """ + m1);
        }
        //Player p1 = new Player("Novak Djokovic" , "mentality" , "hard" , 1 , 0);
        //Player p2 = new Player("Danil Medvedev" , "serve" , "hard" , 4 , 0);
        //System.out.println("If Novak serves chance for winning a point is : " + p1.servePointChance(p2,"hard"));
        //System.out.println("If Danil serves chance for winning a point is : " + p2.servePointChance(p1,"hard"));
        
    }
}
