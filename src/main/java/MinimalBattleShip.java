import game.Game;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class MinimalBattleShip {
    public static void main(String args[]){
            HashMap<Integer, Game> gameRepository = new HashMap<>();
            HashMap<String, HashSet<Integer>> playerGames = new HashMap<>();
            Scanner sc = new Scanner(System.in);
            while(true){
                String commands[] = sc.nextLine().split(" ");
                if(commands[0].equalsIgnoreCase("newgame")){

                }

            }

    }
}
