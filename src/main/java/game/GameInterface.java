package game;

import models.Coordinate;
import models.player.Player;

public interface GameInterface {

    // fetches whose turn is it next
    public Player nextTurn();

    // plays next turn on the game
    public void takeTurn(Coordinate c);

    // prints the current status of the boards of the game
    public void printGameState();

}
