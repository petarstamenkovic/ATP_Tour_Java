
package e1_11.atp_tour;

import java.io.BufferedReader;
import java.util.ArrayList;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
public class Atp_tour {

    public static void main(String[] args) throws IOException {
        
 
        int numOfSeasonTournaments;
        String nameOfTournament;
        ArrayList<Player> players = new ArrayList<>();
        ArrayList<Tournament> tournaments = new ArrayList<>();
        HashMap<String,Tournament> hash_tournaments = new HashMap<>();
        Set<String> playedTournaments = new HashSet<>();
        
        Championship champ = new Championship(players,tournaments);
        champ.loadFiles();
        
        hash_tournaments.put("Australian Open", tournaments.get(0));
        hash_tournaments.put("Indian Wells Masters", tournaments.get(1));
        hash_tournaments.put("Miami Open", tournaments.get(2));
        hash_tournaments.put("Monte-Carlo Masters", tournaments.get(3));
        hash_tournaments.put("Madrid Open", tournaments.get(4));
        hash_tournaments.put("Italian Open", tournaments.get(5));
        hash_tournaments.put("French Open", tournaments.get(6));
        hash_tournaments.put("Wimbledon", tournaments.get(7));
        hash_tournaments.put("Canadian Open", tournaments.get(8));
        hash_tournaments.put("Cincinnati Open", tournaments.get(9));
        hash_tournaments.put("US Open", tournaments.get(10));
        hash_tournaments.put("Shanghai Masters", tournaments.get(11));
        hash_tournaments.put("Paris Masters", tournaments.get(12));
    
        
        Scanner sc = new Scanner(System.in);
        
        do{
            System.out.print("Enter the number of season tournaments to be played: ");    
            if(sc.hasNextInt())
            {
                numOfSeasonTournaments = sc.nextInt();
            }
            else
            {
                System.out.println("Number of tournaments must be an integer value.");
                sc.next();
                numOfSeasonTournaments = -1;
            }
        }
        while(numOfSeasonTournaments < 4 || numOfSeasonTournaments > 13);
        System.out.println("Odigrace se " + numOfSeasonTournaments + " sezonskih turnira.");
        
        sc.nextLine();
        
        // Season tournaments begining
        int i = 0;
        while(i < numOfSeasonTournaments)
        {
            System.out.println("Enter the name of the tournament.");
            nameOfTournament = sc.nextLine();
            if(playedTournaments.contains(nameOfTournament))
            {
                System.out.println(nameOfTournament + " was already played!");
            }
            else
            {
                Tournament curent_tournament = hash_tournaments.get(nameOfTournament);
                SeasonTournament st = new SeasonTournament(nameOfTournament,curent_tournament.tourSurface, curent_tournament.tourType,players); // Here the playable will always be set to true!
                st.play();
                playedTournaments.add(nameOfTournament);
                champ.updateAtpRanks();
                champ.recoverPlayers();
                i++;
                
            }
                
        }
            
        sc.close();
        System.out.println("Rankings after season tournaments : " + "\n" + players);
        
        
        /*
        for(int i = 0; i<= 7 ; i++)
        {
            Match m1 = new Match(players.get(i),players.get(i+1),3,"hard");
            m1.playMatch();
            System.out.println("""
                               Match stats -> 
                               """ + m1);
        }
        */
        
    }
}
