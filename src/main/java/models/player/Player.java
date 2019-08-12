package models.player;

import constants.BoardConstants;
import constants.OppositionBoardState;
import models.Coordinate;
import models.Ships.Ship;
import models.Ships.ShipFactory;
import models.Ships.ShipOrientation;
import models.Ships.ShipType;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Player {
    String playerId;
    HashMap<Coordinate, Integer> selfBoard ;
    HashMap<Coordinate, OppositionBoardState> oppositionBoard;
    HashMap<ShipType, Ship> fleet;

    public Player(String identifier){
          playerId = identifier;
          selfBoard = new HashMap<>();
          for(int i = 0 ; i < BoardConstants.boardLen ; i++)
              for(int j = 0 ; j < BoardConstants.boardWidth ; j++){
                    selfBoard.put(new Coordinate(i,j), 0) ;
              }
          oppositionBoard = new HashMap<>();
          for(int i = 0 ; i < BoardConstants.boardLen ; i++)
            for(int j = 0 ; j < BoardConstants.boardWidth ; j++){
                oppositionBoard.put(new Coordinate(i,j), OppositionBoardState.DEFAULT);
            }

          fleet  = new HashMap<>();
    }

    public boolean buildFleet(Coordinate starting, ShipOrientation orientation, ShipType shipType){
        if(fleet.containsKey(shipType))return false;
        Ship ship = ShipFactory.createShip(shipType, orientation);
        ship.assignCoordinates(starting);

        for(Coordinate c : ship.getOccupiedCoordinates().keySet()){
            if(selfBoard.containsKey(c))return false;
        }
        fleet.put(shipType, ship);
        for(Coordinate c : ship.getOccupiedCoordinates().keySet()){
            selfBoard.put(c, ship.getLength());
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(playerId, player.playerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerId);
    }
}
