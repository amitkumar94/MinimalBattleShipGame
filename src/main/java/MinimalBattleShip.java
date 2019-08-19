import constants.BoardConstants;
import constants.StandardInputCommands;
import game.Game;
import game.GameState;
import models.Coordinate;
import models.player.Player;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class MinimalBattleShip {
    public static void main(String args[]){
//            for(int game = 1; game < 3; game++)
//            for(int i = 0 ; i < 10 ; i++)
//             for(int j = 0 ; j < 10; j++)
//             for(int m = 0  ;  m < 3; m++)
//             {
//                System.out.println("GAME " + game + " FIRE " + i + " " + j);
//             }
            HashMap<Integer, Game> gameRepository = new HashMap<>();
            HashMap<String, HashSet<Integer>> playerGames = new HashMap<>();
            HashSet<String> registeredPlayers  = new HashSet<>();
            int gameCounter = 1;
            Scanner sc = new Scanner(System.in);
            while(true){
                String command  = sc.nextLine();
                System.out.println("Current command : " + command);
                String commands[] = command.split(" ");
                if(commands.length == 3){
                    if(commands[0].equalsIgnoreCase("NEWGAME")){
                         String playerOneName = commands[1];
                         String playerTwoName = commands[2] ;
                         registeredPlayers.add(playerOneName);
                         registeredPlayers.add(playerTwoName);
                         Game newGame = new Game(gameCounter, new Player(playerOneName), new Player(playerTwoName));
                         gameRepository.put(gameCounter, newGame);
                         playerGames.putIfAbsent(playerOneName, new HashSet<>());
                         playerGames.putIfAbsent(playerTwoName, new HashSet<>());
                         playerGames.get(playerOneName).add(gameCounter);
                         playerGames.get(playerTwoName).add(gameCounter);
                         System.out.println("Succesfully created new game with players " + playerOneName + ", "
                                 + playerTwoName + " game id:" + gameCounter);
                         gameCounter++;
                    }
                    else{
                        System.out.println("Invalid command, correctcommand NEWGAME playerOne playerTwo");
                    }
                }
                else if(commands.length == 2){
                    if(commands[0].equalsIgnoreCase("PRINTGAME")){
                        int gameId = Integer.valueOf(commands[1]);
                        if(gameRepository.containsKey(gameId)){
                            gameRepository.get(gameId).printGameState();
                        }
                        else{
                            System.out.println("Not a valid gameId");
                        }
                    }
                    else{
                        System.out.println("Invalid command, correct command PRINTGAME validGameId");
                    }
                }
                else if(commands.length == 5){
                    if(commands[0].equalsIgnoreCase("GAME")){
                        int gameId = Integer.valueOf(commands[1]);
                        if(gameRepository.containsKey(gameId)){
                            if(commands[2].equalsIgnoreCase("FIRE")){
                                int row = Integer.parseInt(commands[3]);
                                int col = Integer.parseInt(commands[4]);
                                if(row >= 0 && row < BoardConstants.boardLen && col >= 0 && col < BoardConstants.boardWidth){
                                    Game game = gameRepository.get(gameId);
                                    if(game.getGameState().equals(GameState.RUNNING)){
                                        System.out.println("Taking a turn for game id " + gameId + " for player : " +
                                                            game.nextTurn().getPlayerId());
                                        game.takeTurn(new Coordinate(row, col));
                                    }
                                    else{
                                        System.out.println("Game is already finished with id " + gameId + " won by " + game.getWinner().getPlayerId());
                                    }
                                }
                                else{
                                    System.out.println("Not valid coordinate given for attack in gameId: " + gameId +
                                     " command " + commands[2]);
                                }
                            }
                            else{
                                System.out.println("Not a valid command issued to game id : " + gameId);
                            }
                        }
                        else{
                            System.out.println("Not a valid gameId");
                        }
                    }
                    else{
                        System.out.println("Not a valid command");
                    }

                }
                else{
                    System.out.println("Unrecognizable Commands , valid commands" + StandardInputCommands.values());
                }

            }

    }


    public void createPlayers(HashSet<String> players){
         players.add("AMIT");
         players.add("SUMIT");
         players.add("ANJU");
    }
}

/*
NEWGAME A B
NEWGAME A C
NEWGAME C D
PRINTGAME 1
GAME 1 A FIRE 0 3


*/


/*
Sample game
NEWGAME A B
NEWGAME B C
GAME 1 FIRE 0 0
GAME 1 FIRE 0 1
GAME 1 FIRE 0 2
GAME 1 FIRE 0 3
GAME 1 FIRE 0 4
GAME 1 FIRE 0 5
GAME 1 FIRE 0 6
GAME 1 FIRE 0 7
GAME 1 FIRE 0 8
GAME 1 FIRE 0 9
GAME 1 FIRE 1 0
GAME 1 FIRE 1 1
GAME 1 FIRE 1 2
GAME 1 FIRE 1 3
GAME 1 FIRE 1 4
GAME 1 FIRE 1 5
GAME 1 FIRE 1 6
GAME 1 FIRE 1 7
GAME 1 FIRE 1 8
GAME 1 FIRE 1 9
GAME 1 FIRE 2 0
GAME 1 FIRE 2 1
GAME 1 FIRE 2 2
GAME 1 FIRE 2 3
GAME 1 FIRE 2 4
GAME 1 FIRE 2 5
GAME 1 FIRE 2 6
GAME 1 FIRE 2 7
GAME 1 FIRE 2 8
GAME 1 FIRE 2 9
GAME 1 FIRE 3 0
GAME 1 FIRE 3 1
GAME 1 FIRE 3 2
GAME 1 FIRE 3 3
GAME 1 FIRE 3 4
GAME 1 FIRE 3 5
GAME 1 FIRE 3 6
GAME 1 FIRE 3 7
GAME 1 FIRE 3 8
GAME 1 FIRE 3 9
GAME 1 FIRE 4 0
GAME 1 FIRE 4 1
GAME 1 FIRE 4 2
GAME 1 FIRE 4 3
GAME 1 FIRE 4 4
GAME 1 FIRE 4 5
GAME 1 FIRE 4 6
GAME 1 FIRE 4 7
GAME 1 FIRE 4 8
GAME 1 FIRE 4 9
GAME 1 FIRE 5 0
GAME 1 FIRE 5 1
GAME 1 FIRE 5 2
GAME 1 FIRE 5 3
GAME 1 FIRE 5 4
GAME 1 FIRE 5 5
GAME 1 FIRE 5 6
GAME 1 FIRE 5 7
GAME 1 FIRE 5 8
GAME 1 FIRE 5 9
GAME 1 FIRE 6 0
GAME 1 FIRE 6 1
GAME 1 FIRE 6 2
GAME 1 FIRE 6 3
GAME 1 FIRE 6 4
GAME 1 FIRE 6 5
GAME 1 FIRE 6 6
GAME 1 FIRE 6 7
GAME 1 FIRE 6 8
GAME 1 FIRE 6 9
GAME 1 FIRE 7 0
GAME 1 FIRE 7 1
GAME 1 FIRE 7 2
GAME 1 FIRE 7 3
GAME 1 FIRE 7 4
GAME 1 FIRE 7 5
GAME 1 FIRE 7 6
GAME 1 FIRE 7 7
GAME 1 FIRE 7 8
GAME 1 FIRE 7 9
GAME 1 FIRE 8 0
GAME 1 FIRE 8 1
GAME 1 FIRE 8 2
GAME 1 FIRE 8 3
GAME 1 FIRE 8 4
GAME 1 FIRE 8 5
GAME 1 FIRE 8 6
GAME 1 FIRE 8 7
GAME 1 FIRE 8 8
GAME 1 FIRE 8 9
GAME 1 FIRE 9 0
GAME 1 FIRE 9 1
GAME 1 FIRE 9 2
GAME 1 FIRE 9 3
GAME 1 FIRE 9 4
GAME 1 FIRE 9 5
GAME 1 FIRE 9 6
GAME 1 FIRE 9 7
GAME 1 FIRE 9 8
GAME 1 FIRE 9 9
GAME 2 FIRE 0 0
GAME 2 FIRE 0 1
GAME 2 FIRE 0 2
GAME 2 FIRE 0 3
GAME 2 FIRE 0 4
GAME 2 FIRE 0 5
GAME 2 FIRE 0 6
GAME 2 FIRE 0 7
GAME 2 FIRE 0 8
GAME 2 FIRE 0 9
GAME 2 FIRE 1 0
GAME 2 FIRE 1 1
GAME 2 FIRE 1 2
GAME 2 FIRE 1 3
GAME 2 FIRE 1 4
GAME 2 FIRE 1 5
GAME 2 FIRE 1 6
GAME 2 FIRE 1 7
GAME 2 FIRE 1 8
GAME 2 FIRE 1 9
GAME 2 FIRE 2 0
GAME 2 FIRE 2 1
GAME 2 FIRE 2 2
GAME 2 FIRE 2 3
GAME 2 FIRE 2 4
GAME 2 FIRE 2 5
GAME 2 FIRE 2 6
GAME 2 FIRE 2 7
GAME 2 FIRE 2 8
GAME 2 FIRE 2 9
GAME 2 FIRE 3 0
GAME 2 FIRE 3 1
GAME 2 FIRE 3 2
GAME 2 FIRE 3 3
GAME 2 FIRE 3 4
GAME 2 FIRE 3 5
GAME 2 FIRE 3 6
GAME 2 FIRE 3 7
GAME 2 FIRE 3 8
GAME 2 FIRE 3 9
GAME 2 FIRE 4 0
GAME 2 FIRE 4 1
GAME 2 FIRE 4 2
GAME 2 FIRE 4 3
GAME 2 FIRE 4 4
GAME 2 FIRE 4 5
GAME 2 FIRE 4 6
GAME 2 FIRE 4 7
GAME 2 FIRE 4 8
GAME 2 FIRE 4 9
GAME 2 FIRE 5 0
GAME 2 FIRE 5 1
GAME 2 FIRE 5 2
GAME 2 FIRE 5 3
GAME 2 FIRE 5 4
GAME 2 FIRE 5 5
GAME 2 FIRE 5 6
GAME 2 FIRE 5 7
GAME 2 FIRE 5 8
GAME 2 FIRE 5 9
GAME 2 FIRE 6 0
GAME 2 FIRE 6 1
GAME 2 FIRE 6 2
GAME 2 FIRE 6 3
GAME 2 FIRE 6 4
GAME 2 FIRE 6 5
GAME 2 FIRE 6 6
GAME 2 FIRE 6 7
GAME 2 FIRE 6 8
GAME 2 FIRE 6 9
GAME 2 FIRE 7 0
GAME 2 FIRE 7 1
GAME 2 FIRE 7 2
GAME 2 FIRE 7 3
GAME 2 FIRE 7 4
GAME 2 FIRE 7 5
GAME 2 FIRE 7 6
GAME 2 FIRE 7 7
GAME 2 FIRE 7 8
GAME 2 FIRE 7 9
GAME 2 FIRE 8 0
GAME 2 FIRE 8 1
GAME 2 FIRE 8 2
GAME 2 FIRE 8 3
GAME 2 FIRE 8 4
GAME 2 FIRE 8 5
GAME 2 FIRE 8 6
GAME 2 FIRE 8 7
GAME 2 FIRE 8 8
GAME 2 FIRE 8 9
GAME 2 FIRE 9 0
GAME 2 FIRE 9 1
GAME 2 FIRE 9 2
GAME 2 FIRE 9 3
GAME 2 FIRE 9 4
GAME 2 FIRE 9 5
GAME 2 FIRE 9 6
GAME 2 FIRE 9 7
GAME 2 FIRE 9 8
GAME 2 FIRE 9 9
*/

/*
GAME 1 FIRE 0 0
GAME 1 FIRE 0 0
GAME 1 FIRE 0 0
GAME 1 FIRE 0 1
GAME 1 FIRE 0 1
GAME 1 FIRE 0 1
GAME 1 FIRE 0 2
GAME 1 FIRE 0 2
GAME 1 FIRE 0 2
GAME 1 FIRE 0 3
GAME 1 FIRE 0 3
GAME 1 FIRE 0 3
GAME 1 FIRE 0 4
GAME 1 FIRE 0 4
GAME 1 FIRE 0 4
GAME 1 FIRE 0 5
GAME 1 FIRE 0 5
GAME 1 FIRE 0 5
GAME 1 FIRE 0 6
GAME 1 FIRE 0 6
GAME 1 FIRE 0 6
GAME 1 FIRE 0 7
GAME 1 FIRE 0 7
GAME 1 FIRE 0 7
GAME 1 FIRE 0 8
GAME 1 FIRE 0 8
GAME 1 FIRE 0 8
GAME 1 FIRE 0 9
GAME 1 FIRE 0 9
GAME 1 FIRE 0 9
GAME 1 FIRE 1 0
GAME 1 FIRE 1 0
GAME 1 FIRE 1 0
GAME 1 FIRE 1 1
GAME 1 FIRE 1 1
GAME 1 FIRE 1 1
GAME 1 FIRE 1 2
GAME 1 FIRE 1 2
GAME 1 FIRE 1 2
GAME 1 FIRE 1 3
GAME 1 FIRE 1 3
GAME 1 FIRE 1 3
GAME 1 FIRE 1 4
GAME 1 FIRE 1 4
GAME 1 FIRE 1 4
GAME 1 FIRE 1 5
GAME 1 FIRE 1 5
GAME 1 FIRE 1 5
GAME 1 FIRE 1 6
GAME 1 FIRE 1 6
GAME 1 FIRE 1 6
GAME 1 FIRE 1 7
GAME 1 FIRE 1 7
GAME 1 FIRE 1 7
GAME 1 FIRE 1 8
GAME 1 FIRE 1 8
GAME 1 FIRE 1 8
GAME 1 FIRE 1 9
GAME 1 FIRE 1 9
GAME 1 FIRE 1 9
GAME 1 FIRE 2 0
GAME 1 FIRE 2 0
GAME 1 FIRE 2 0
GAME 1 FIRE 2 1
GAME 1 FIRE 2 1
GAME 1 FIRE 2 1
GAME 1 FIRE 2 2
GAME 1 FIRE 2 2
GAME 1 FIRE 2 2
GAME 1 FIRE 2 3
GAME 1 FIRE 2 3
GAME 1 FIRE 2 3
GAME 1 FIRE 2 4
GAME 1 FIRE 2 4
GAME 1 FIRE 2 4
GAME 1 FIRE 2 5
GAME 1 FIRE 2 5
GAME 1 FIRE 2 5
GAME 1 FIRE 2 6
GAME 1 FIRE 2 6
GAME 1 FIRE 2 6
GAME 1 FIRE 2 7
GAME 1 FIRE 2 7
GAME 1 FIRE 2 7
GAME 1 FIRE 2 8
GAME 1 FIRE 2 8
GAME 1 FIRE 2 8
GAME 1 FIRE 2 9
GAME 1 FIRE 2 9
GAME 1 FIRE 2 9
GAME 1 FIRE 3 0
GAME 1 FIRE 3 0
GAME 1 FIRE 3 0
GAME 1 FIRE 3 1
GAME 1 FIRE 3 1
GAME 1 FIRE 3 1
GAME 1 FIRE 3 2
GAME 1 FIRE 3 2
GAME 1 FIRE 3 2
GAME 1 FIRE 3 3
GAME 1 FIRE 3 3
GAME 1 FIRE 3 3
GAME 1 FIRE 3 4
GAME 1 FIRE 3 4
GAME 1 FIRE 3 4
GAME 1 FIRE 3 5
GAME 1 FIRE 3 5
GAME 1 FIRE 3 5
GAME 1 FIRE 3 6
GAME 1 FIRE 3 6
GAME 1 FIRE 3 6
GAME 1 FIRE 3 7
GAME 1 FIRE 3 7
GAME 1 FIRE 3 7
GAME 1 FIRE 3 8
GAME 1 FIRE 3 8
GAME 1 FIRE 3 8
GAME 1 FIRE 3 9
GAME 1 FIRE 3 9
GAME 1 FIRE 3 9
GAME 1 FIRE 4 0
GAME 1 FIRE 4 0
GAME 1 FIRE 4 0
GAME 1 FIRE 4 1
GAME 1 FIRE 4 1
GAME 1 FIRE 4 1
GAME 1 FIRE 4 2
GAME 1 FIRE 4 2
GAME 1 FIRE 4 2
GAME 1 FIRE 4 3
GAME 1 FIRE 4 3
GAME 1 FIRE 4 3
GAME 1 FIRE 4 4
GAME 1 FIRE 4 4
GAME 1 FIRE 4 4
GAME 1 FIRE 4 5
GAME 1 FIRE 4 5
GAME 1 FIRE 4 5
GAME 1 FIRE 4 6
GAME 1 FIRE 4 6
GAME 1 FIRE 4 6
GAME 1 FIRE 4 7
GAME 1 FIRE 4 7
GAME 1 FIRE 4 7
GAME 1 FIRE 4 8
GAME 1 FIRE 4 8
GAME 1 FIRE 4 8
GAME 1 FIRE 4 9
GAME 1 FIRE 4 9
GAME 1 FIRE 4 9
GAME 1 FIRE 5 0
GAME 1 FIRE 5 0
GAME 1 FIRE 5 0
GAME 1 FIRE 5 1
GAME 1 FIRE 5 1
GAME 1 FIRE 5 1
GAME 1 FIRE 5 2
GAME 1 FIRE 5 2
GAME 1 FIRE 5 2
GAME 1 FIRE 5 3
GAME 1 FIRE 5 3
GAME 1 FIRE 5 3
GAME 1 FIRE 5 4
GAME 1 FIRE 5 4
GAME 1 FIRE 5 4
GAME 1 FIRE 5 5
GAME 1 FIRE 5 5
GAME 1 FIRE 5 5
GAME 1 FIRE 5 6
GAME 1 FIRE 5 6
GAME 1 FIRE 5 6
GAME 1 FIRE 5 7
GAME 1 FIRE 5 7
GAME 1 FIRE 5 7
GAME 1 FIRE 5 8
GAME 1 FIRE 5 8
GAME 1 FIRE 5 8
GAME 1 FIRE 5 9
GAME 1 FIRE 5 9
GAME 1 FIRE 5 9
GAME 1 FIRE 6 0
GAME 1 FIRE 6 0
GAME 1 FIRE 6 0
GAME 1 FIRE 6 1
GAME 1 FIRE 6 1
GAME 1 FIRE 6 1
GAME 1 FIRE 6 2
GAME 1 FIRE 6 2
GAME 1 FIRE 6 2
GAME 1 FIRE 6 3
GAME 1 FIRE 6 3
GAME 1 FIRE 6 3
GAME 1 FIRE 6 4
GAME 1 FIRE 6 4
GAME 1 FIRE 6 4
GAME 1 FIRE 6 5
GAME 1 FIRE 6 5
GAME 1 FIRE 6 5
GAME 1 FIRE 6 6
GAME 1 FIRE 6 6
GAME 1 FIRE 6 6
GAME 1 FIRE 6 7
GAME 1 FIRE 6 7
GAME 1 FIRE 6 7
GAME 1 FIRE 6 8
GAME 1 FIRE 6 8
GAME 1 FIRE 6 8
GAME 1 FIRE 6 9
GAME 1 FIRE 6 9
GAME 1 FIRE 6 9
GAME 1 FIRE 7 0
GAME 1 FIRE 7 0
GAME 1 FIRE 7 0
GAME 1 FIRE 7 1
GAME 1 FIRE 7 1
GAME 1 FIRE 7 1
GAME 1 FIRE 7 2
GAME 1 FIRE 7 2
GAME 1 FIRE 7 2
GAME 1 FIRE 7 3
GAME 1 FIRE 7 3
GAME 1 FIRE 7 3
GAME 1 FIRE 7 4
GAME 1 FIRE 7 4
GAME 1 FIRE 7 4
GAME 1 FIRE 7 5
GAME 1 FIRE 7 5
GAME 1 FIRE 7 5
GAME 1 FIRE 7 6
GAME 1 FIRE 7 6
GAME 1 FIRE 7 6
GAME 1 FIRE 7 7
GAME 1 FIRE 7 7
GAME 1 FIRE 7 7
GAME 1 FIRE 7 8
GAME 1 FIRE 7 8
GAME 1 FIRE 7 8
GAME 1 FIRE 7 9
GAME 1 FIRE 7 9
GAME 1 FIRE 7 9
GAME 1 FIRE 8 0
GAME 1 FIRE 8 0
GAME 1 FIRE 8 0
GAME 1 FIRE 8 1
GAME 1 FIRE 8 1
GAME 1 FIRE 8 1
GAME 1 FIRE 8 2
GAME 1 FIRE 8 2
GAME 1 FIRE 8 2
GAME 1 FIRE 8 3
GAME 1 FIRE 8 3
GAME 1 FIRE 8 3
GAME 1 FIRE 8 4
GAME 1 FIRE 8 4
GAME 1 FIRE 8 4
GAME 1 FIRE 8 5
GAME 1 FIRE 8 5
GAME 1 FIRE 8 5
GAME 1 FIRE 8 6
GAME 1 FIRE 8 6
GAME 1 FIRE 8 6
GAME 1 FIRE 8 7
GAME 1 FIRE 8 7
GAME 1 FIRE 8 7
GAME 1 FIRE 8 8
GAME 1 FIRE 8 8
GAME 1 FIRE 8 8
GAME 1 FIRE 8 9
GAME 1 FIRE 8 9
GAME 1 FIRE 8 9
GAME 1 FIRE 9 0
GAME 1 FIRE 9 0
GAME 1 FIRE 9 0
GAME 1 FIRE 9 1
GAME 1 FIRE 9 1
GAME 1 FIRE 9 1
GAME 1 FIRE 9 2
GAME 1 FIRE 9 2
GAME 1 FIRE 9 2
GAME 1 FIRE 9 3
GAME 1 FIRE 9 3
GAME 1 FIRE 9 3
GAME 1 FIRE 9 4
GAME 1 FIRE 9 4
GAME 1 FIRE 9 4
GAME 1 FIRE 9 5
GAME 1 FIRE 9 5
GAME 1 FIRE 9 5
GAME 1 FIRE 9 6
GAME 1 FIRE 9 6
GAME 1 FIRE 9 6
GAME 1 FIRE 9 7
GAME 1 FIRE 9 7
GAME 1 FIRE 9 7
GAME 1 FIRE 9 8
GAME 1 FIRE 9 8
GAME 1 FIRE 9 8
GAME 1 FIRE 9 9
GAME 1 FIRE 9 9
GAME 1 FIRE 9 9
GAME 2 FIRE 0 0
GAME 2 FIRE 0 0
GAME 2 FIRE 0 0
GAME 2 FIRE 0 1
GAME 2 FIRE 0 1
GAME 2 FIRE 0 1
GAME 2 FIRE 0 2
GAME 2 FIRE 0 2
GAME 2 FIRE 0 2
GAME 2 FIRE 0 3
GAME 2 FIRE 0 3
GAME 2 FIRE 0 3
GAME 2 FIRE 0 4
GAME 2 FIRE 0 4
GAME 2 FIRE 0 4
GAME 2 FIRE 0 5
GAME 2 FIRE 0 5
GAME 2 FIRE 0 5
GAME 2 FIRE 0 6
GAME 2 FIRE 0 6
GAME 2 FIRE 0 6
GAME 2 FIRE 0 7
GAME 2 FIRE 0 7
GAME 2 FIRE 0 7
GAME 2 FIRE 0 8
GAME 2 FIRE 0 8
GAME 2 FIRE 0 8
GAME 2 FIRE 0 9
GAME 2 FIRE 0 9
GAME 2 FIRE 0 9
GAME 2 FIRE 1 0
GAME 2 FIRE 1 0
GAME 2 FIRE 1 0
GAME 2 FIRE 1 1
GAME 2 FIRE 1 1
GAME 2 FIRE 1 1
GAME 2 FIRE 1 2
GAME 2 FIRE 1 2
GAME 2 FIRE 1 2
GAME 2 FIRE 1 3
GAME 2 FIRE 1 3
GAME 2 FIRE 1 3
GAME 2 FIRE 1 4
GAME 2 FIRE 1 4
GAME 2 FIRE 1 4
GAME 2 FIRE 1 5
GAME 2 FIRE 1 5
GAME 2 FIRE 1 5
GAME 2 FIRE 1 6
GAME 2 FIRE 1 6
GAME 2 FIRE 1 6
GAME 2 FIRE 1 7
GAME 2 FIRE 1 7
GAME 2 FIRE 1 7
GAME 2 FIRE 1 8
GAME 2 FIRE 1 8
GAME 2 FIRE 1 8
GAME 2 FIRE 1 9
GAME 2 FIRE 1 9
GAME 2 FIRE 1 9
GAME 2 FIRE 2 0
GAME 2 FIRE 2 0
GAME 2 FIRE 2 0
GAME 2 FIRE 2 1
GAME 2 FIRE 2 1
GAME 2 FIRE 2 1
GAME 2 FIRE 2 2
GAME 2 FIRE 2 2
GAME 2 FIRE 2 2
GAME 2 FIRE 2 3
GAME 2 FIRE 2 3
GAME 2 FIRE 2 3
GAME 2 FIRE 2 4
GAME 2 FIRE 2 4
GAME 2 FIRE 2 4
GAME 2 FIRE 2 5
GAME 2 FIRE 2 5
GAME 2 FIRE 2 5
GAME 2 FIRE 2 6
GAME 2 FIRE 2 6
GAME 2 FIRE 2 6
GAME 2 FIRE 2 7
GAME 2 FIRE 2 7
GAME 2 FIRE 2 7
GAME 2 FIRE 2 8
GAME 2 FIRE 2 8
GAME 2 FIRE 2 8
GAME 2 FIRE 2 9
GAME 2 FIRE 2 9
GAME 2 FIRE 2 9
GAME 2 FIRE 3 0
GAME 2 FIRE 3 0
GAME 2 FIRE 3 0
GAME 2 FIRE 3 1
GAME 2 FIRE 3 1
GAME 2 FIRE 3 1
GAME 2 FIRE 3 2
GAME 2 FIRE 3 2
GAME 2 FIRE 3 2
GAME 2 FIRE 3 3
GAME 2 FIRE 3 3
GAME 2 FIRE 3 3
GAME 2 FIRE 3 4
GAME 2 FIRE 3 4
GAME 2 FIRE 3 4
GAME 2 FIRE 3 5
GAME 2 FIRE 3 5
GAME 2 FIRE 3 5
GAME 2 FIRE 3 6
GAME 2 FIRE 3 6
GAME 2 FIRE 3 6
GAME 2 FIRE 3 7
GAME 2 FIRE 3 7
GAME 2 FIRE 3 7
GAME 2 FIRE 3 8
GAME 2 FIRE 3 8
GAME 2 FIRE 3 8
GAME 2 FIRE 3 9
GAME 2 FIRE 3 9
GAME 2 FIRE 3 9
GAME 2 FIRE 4 0
GAME 2 FIRE 4 0
GAME 2 FIRE 4 0
GAME 2 FIRE 4 1
GAME 2 FIRE 4 1
GAME 2 FIRE 4 1
GAME 2 FIRE 4 2
GAME 2 FIRE 4 2
GAME 2 FIRE 4 2
GAME 2 FIRE 4 3
GAME 2 FIRE 4 3
GAME 2 FIRE 4 3
GAME 2 FIRE 4 4
GAME 2 FIRE 4 4
GAME 2 FIRE 4 4
GAME 2 FIRE 4 5
GAME 2 FIRE 4 5
GAME 2 FIRE 4 5
GAME 2 FIRE 4 6
GAME 2 FIRE 4 6
GAME 2 FIRE 4 6
GAME 2 FIRE 4 7
GAME 2 FIRE 4 7
GAME 2 FIRE 4 7
GAME 2 FIRE 4 8
GAME 2 FIRE 4 8
GAME 2 FIRE 4 8
GAME 2 FIRE 4 9
GAME 2 FIRE 4 9
GAME 2 FIRE 4 9
GAME 2 FIRE 5 0
GAME 2 FIRE 5 0
GAME 2 FIRE 5 0
GAME 2 FIRE 5 1
GAME 2 FIRE 5 1
GAME 2 FIRE 5 1
GAME 2 FIRE 5 2
GAME 2 FIRE 5 2
GAME 2 FIRE 5 2
GAME 2 FIRE 5 3
GAME 2 FIRE 5 3
GAME 2 FIRE 5 3
GAME 2 FIRE 5 4
GAME 2 FIRE 5 4
GAME 2 FIRE 5 4
GAME 2 FIRE 5 5
GAME 2 FIRE 5 5
GAME 2 FIRE 5 5
GAME 2 FIRE 5 6
GAME 2 FIRE 5 6
GAME 2 FIRE 5 6
GAME 2 FIRE 5 7
GAME 2 FIRE 5 7
GAME 2 FIRE 5 7
GAME 2 FIRE 5 8
GAME 2 FIRE 5 8
GAME 2 FIRE 5 8
GAME 2 FIRE 5 9
GAME 2 FIRE 5 9
GAME 2 FIRE 5 9
GAME 2 FIRE 6 0
GAME 2 FIRE 6 0
GAME 2 FIRE 6 0
GAME 2 FIRE 6 1
GAME 2 FIRE 6 1
GAME 2 FIRE 6 1
GAME 2 FIRE 6 2
GAME 2 FIRE 6 2
GAME 2 FIRE 6 2
GAME 2 FIRE 6 3
GAME 2 FIRE 6 3
GAME 2 FIRE 6 3
GAME 2 FIRE 6 4
GAME 2 FIRE 6 4
GAME 2 FIRE 6 4
GAME 2 FIRE 6 5
GAME 2 FIRE 6 5
GAME 2 FIRE 6 5
GAME 2 FIRE 6 6
GAME 2 FIRE 6 6
GAME 2 FIRE 6 6
GAME 2 FIRE 6 7
GAME 2 FIRE 6 7
GAME 2 FIRE 6 7
GAME 2 FIRE 6 8
GAME 2 FIRE 6 8
GAME 2 FIRE 6 8
GAME 2 FIRE 6 9
GAME 2 FIRE 6 9
GAME 2 FIRE 6 9
GAME 2 FIRE 7 0
GAME 2 FIRE 7 0
GAME 2 FIRE 7 0
GAME 2 FIRE 7 1
GAME 2 FIRE 7 1
GAME 2 FIRE 7 1
GAME 2 FIRE 7 2
GAME 2 FIRE 7 2
GAME 2 FIRE 7 2
GAME 2 FIRE 7 3
GAME 2 FIRE 7 3
GAME 2 FIRE 7 3
GAME 2 FIRE 7 4
GAME 2 FIRE 7 4
GAME 2 FIRE 7 4
GAME 2 FIRE 7 5
GAME 2 FIRE 7 5
GAME 2 FIRE 7 5
GAME 2 FIRE 7 6
GAME 2 FIRE 7 6
GAME 2 FIRE 7 6
GAME 2 FIRE 7 7
GAME 2 FIRE 7 7
GAME 2 FIRE 7 7
GAME 2 FIRE 7 8
GAME 2 FIRE 7 8
GAME 2 FIRE 7 8
GAME 2 FIRE 7 9
GAME 2 FIRE 7 9
GAME 2 FIRE 7 9
GAME 2 FIRE 8 0
GAME 2 FIRE 8 0
GAME 2 FIRE 8 0
GAME 2 FIRE 8 1
GAME 2 FIRE 8 1
GAME 2 FIRE 8 1
GAME 2 FIRE 8 2
GAME 2 FIRE 8 2
GAME 2 FIRE 8 2
GAME 2 FIRE 8 3
GAME 2 FIRE 8 3
GAME 2 FIRE 8 3
GAME 2 FIRE 8 4
GAME 2 FIRE 8 4
GAME 2 FIRE 8 4
GAME 2 FIRE 8 5
GAME 2 FIRE 8 5
GAME 2 FIRE 8 5
GAME 2 FIRE 8 6
GAME 2 FIRE 8 6
GAME 2 FIRE 8 6
GAME 2 FIRE 8 7
GAME 2 FIRE 8 7
GAME 2 FIRE 8 7
GAME 2 FIRE 8 8
GAME 2 FIRE 8 8
GAME 2 FIRE 8 8
GAME 2 FIRE 8 9
GAME 2 FIRE 8 9
GAME 2 FIRE 8 9
GAME 2 FIRE 9 0
GAME 2 FIRE 9 0
GAME 2 FIRE 9 0
GAME 2 FIRE 9 1
GAME 2 FIRE 9 1
GAME 2 FIRE 9 1
GAME 2 FIRE 9 2
GAME 2 FIRE 9 2
GAME 2 FIRE 9 2
GAME 2 FIRE 9 3
GAME 2 FIRE 9 3
GAME 2 FIRE 9 3
GAME 2 FIRE 9 4
GAME 2 FIRE 9 4
GAME 2 FIRE 9 4
GAME 2 FIRE 9 5
GAME 2 FIRE 9 5
GAME 2 FIRE 9 5
GAME 2 FIRE 9 6
GAME 2 FIRE 9 6
GAME 2 FIRE 9 6
GAME 2 FIRE 9 7
GAME 2 FIRE 9 7
GAME 2 FIRE 9 7
GAME 2 FIRE 9 8
GAME 2 FIRE 9 8
GAME 2 FIRE 9 8
GAME 2 FIRE 9 9
GAME 2 FIRE 9 9
GAME 2 FIRE 9 9

 */