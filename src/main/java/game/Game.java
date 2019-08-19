package game;

import constants.OppositionBoardState;
import models.Coordinate;
import models.Ships.Ship;
import models.player.Player;

import java.util.Deque;
import java.util.LinkedList;

public class Game implements GameInterface{
    int gameId;
    Player A ;
    Player B ;
    Deque<Player> turnQueue;
    GameState gameState;
    Player Winner ;         // could be null when game is in RUNNING state.

    public Game(int id, Player a , Player b){
        gameId = id;
        A = a;
        B = b;
        turnQueue = new LinkedList<>();
        Winner = null;
        gameState = GameState.RUNNING;
    }

    public Player nextTurn(){
        return turnQueue.peekFirst();
    }

    public void takeTurn(Coordinate c){
        Player now = turnQueue.removeFirst();
        Player opposite = turnQueue.peekFirst();
        if(opposite.respondToHit(c)){
            now.updateOppositionBoard(c, OppositionBoardState.HIT);
        }
        else{
            now.updateOppositionBoard(c, OppositionBoardState.MISS);
        }
        if(opposite.checkIfLost()){
            gameState = GameState.WON;
            Winner = now;
        }
        turnQueue.addLast(now);
    }

    public void printGameState(){
        A.printSelfBoard();
        A.printOppositionBoard();

        B.printSelfBoard();
        B.printOppositionBoard();
    }

}
