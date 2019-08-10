package models.player;

import constants.OppositionBoardState;
import models.Coordinate;

import java.util.HashMap;

public class Player {
    String playerId;
    HashMap<Coordinate, Integer> selfBoard ;
    HashMap<Coordinate, OppositionBoardState> oppositionBoard;

}
