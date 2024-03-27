
package e1_11.atp_tour;

import java.io.BufferedReader;
import java.util.ArrayList;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
public class Atp_tour {

    public static void main(String[] args) throws IOException { // NetBeans added this as precaution 
        
 
        int numOfSeasonTournaments;
        String nameOfTournament;
        ArrayList<Player> players = new ArrayList<>();
        ArrayList<Tournament> tournaments = new ArrayList<>();
        HashMap<String,Tournament> hash_tournaments = new HashMap<>();
        Set<String> playedTournaments = new HashSet<>();
        
        Championship champ = new Championship(players,tournaments);
        champ.loadFiles();
        
        //////// HASH MAP - Copy from tournament list in hash map and make the name of tournament a key /////////
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
        ////////////////////////////////////////////////////////////////////////////////////////////////////////
         
        Scanner sc = new Scanner(System.in);
        
        do{
            System.out.print("Enter the number of season tournaments to be played: ");    
            if(sc.hasNextInt()) // Check if an input is integer
            {
                numOfSeasonTournaments = sc.nextInt();
            }
            else    // Removes whats in the buffer as i understood and -1 simply continues the loop
            {
                System.out.println("Number of tournaments must be an integer value.");
                sc.next();
                numOfSeasonTournaments = -1;
            }
        }
        while(numOfSeasonTournaments < 4 || numOfSeasonTournaments > 13);
        System.out.println("In current season we will have " + numOfSeasonTournaments + " season tournaments.");
        
        sc.nextLine(); // Allow the input for the name of the tournament
        
        // Season tournaments begining
        int i = 0;
        while(i < numOfSeasonTournaments)  // Use while instead of for loop beacuse of already playable option
        {
            System.out.println("Enter the name of the tournament.");
            nameOfTournament = sc.nextLine();
            if(playedTournaments.contains(nameOfTournament))
            {
                System.out.println(nameOfTournament + " was already played!");
            }
            else
            {
                Tournament curent_tournament = hash_tournaments.get(nameOfTournament); // Fetch the required tournament object by its name (key)
                if(curent_tournament == null) // Null comes out if you type the name wrong
                {
                    System.out.println("Invalid tournament name");
                    continue;
                }
                SeasonTournament st = new SeasonTournament(nameOfTournament,curent_tournament.tourSurface, curent_tournament.tourType,players); // Here the playable will always be set to true, so thats why i use set for this purpose!
                st.play();
                playedTournaments.add(nameOfTournament);
                champ.updateAtpPoints();    // Update ATP list after every tournament
                champ.recoverPlayers();     // Recover injured players before the next tournament
                i++;     
            }
                
        }
        
        champ.updateAtpRank(); // Update the rankings (exact ranks of the players) after the seaoson tournaments   
        //sc.close();
        System.out.println("Rankings after season tournaments : " + "\n" + players);
        
        // Putting players into groups
        ArrayList<Player> newGroupA = new ArrayList<>();
        ArrayList<Player> newGroupB = new ArrayList<>();
        
        for(int j = 0 ; j < 8 ; j++) 
        {
            if(j%2 == 0)
                newGroupB.add(players.get(j));
            else
                newGroupA.add(players.get(j));
        }
        
        System.in.read();  // Wait for any press before ATP Finals
        sc.close();        // Close keyboard input, no more inputs required
        
        System.out.println("ATP Finals tournament begins...." + "\n");
        System.out.println("====================================");
        
        // ATP Finals begins
        AtpFinals atpFinals = new AtpFinals("ATP Finals","hard","ATP FINALS",newGroupA,newGroupB);
        atpFinals.play();
        // Here we dont have to print out score and matches so here we just print atp finals draw
        System.out.println("Group A : " + "\n" + newGroupA);
        System.out.println("====================================");
        System.out.println("Group B : " + "\n" + newGroupB);
        System.out.println("====================================");
        System.out.println("Semifinalists : " + "\n" + atpFinals.getSemiFinalists());
        System.out.println("====================================");
        System.out.println("Finalists : " + "\n" + atpFinals.getFinalists());
        System.out.println("====================================");
        System.out.println("Champion of ATP Finals tournaments is : " + atpFinals.getChampion());
        System.out.println("====================================");
        
        champ.updateAtpPoints();
        champ.updateAtpRank();
        
        System.out.println("Final ranking is : " + "\n" + players + "\n");
        System.out.println("====================================");
        System.out.println("Seasonal winner is : " + players.get(0));
       
    }
}
