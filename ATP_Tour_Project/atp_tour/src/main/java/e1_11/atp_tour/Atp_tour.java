
package e1_11.atp_tour;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Atp_tour {

    public static void main(String[] args) {
        
        Player p1 = new Player("Novak Djokovic" , "mentality" , "hard" , 1 , 0 , false);
        Player p2 = new Player("Danil Medvedev" , "serve" , "hard" , 4 , 0 , false);
        
        System.out.println("If Novak serves chance for winning a point is : " + p1.servePointChance(p2,"hard"));
        System.out.println("If Danil serves chance for winning a point is : " + p2.servePointChance(p1,"hard"));
        
        
    }
}
