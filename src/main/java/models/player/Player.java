package models.player;

import constants.BoardConstants;
import constants.OppositionBoardState;
import models.Coordinate;
import models.Ships.*;

import java.util.*;

public class Player {
    String playerId;
    Random random;
    HashMap<Coordinate, Integer> selfBoard ;
    HashMap<Coordinate, OppositionBoardState> oppositionBoard;
    HashMap<ShipType, Ship> fleet;

    public Player(String identifier){
          playerId = identifier;
          selfBoard = new HashMap<>();
          for(int i = 0 ; i < BoardConstants.boardLen ; i++)
              for(int j = 0 ; j < BoardConstants.boardWidth ; j++){
                    selfBoard.put(new Coordinate(i,j), BoardConstants.selfBoardEmpty) ;
              }
          oppositionBoard = new HashMap<>();
          for(int i = 0 ; i < BoardConstants.boardLen ; i++)
            for(int j = 0 ; j < BoardConstants.boardWidth ; j++){
                oppositionBoard.put(new Coordinate(i,j), OppositionBoardState.DEFAULT);
            }

          fleet  = new HashMap<>();
          random = new Random();
          drawBoard();
    }

    public String getPlayerId(){
        return playerId;
    }

    public boolean buildFleet(Coordinate starting, ShipOrientation orientation, ShipType shipType){
        if(fleet.containsKey(shipType))return false;

        Ship ship = ShipFactory.createShip(shipType, orientation);
        ship.assignCoordinates(starting);
        for(Map.Entry<Coordinate, ShipState> e : ship.getOccupiedCoordinates().entrySet()){
            Coordinate c  = e.getKey();
            if(c.getRow() < 0 || c.getRow() >= BoardConstants.boardLen || c.getCol() < 0 ||
                    c.getCol() >= BoardConstants.boardWidth){
                return false;
            }
        }
        for(Coordinate c : ship.getOccupiedCoordinates().keySet()){
            if(selfBoard.get(c) != BoardConstants.selfBoardEmpty)return false;
        }
        fleet.put(shipType, ship);
        for(Coordinate c : ship.getOccupiedCoordinates().keySet()){
            selfBoard.put(c, ship.getLength());
        }
        return true;
    }
    /*
    * responds true if hit, else false
    * */
    public boolean respondToHit(Coordinate c){
        if(selfBoard.get(c) > BoardConstants.selfBoardEmpty && selfBoard.get(c) < BoardConstants.selfBoardHit){
            System.out.println("Player " + playerId + " has taken a hit " + c.toString());
            selfBoard.put(c, BoardConstants.selfBoardHit);
            for (Map.Entry<ShipType, Ship> e : fleet.entrySet()) {
                Ship s = e.getValue();
                if (s.getOccupiedCoordinates().containsKey(c)) {
                    s.updateShipState(c);
                    if(s.isSunk()){
                        System.out.println("Ship is sunk "+ s.getShipType());
                    }
                    break;
                }
            }
            return true;
        }
        else if(selfBoard.get(c) == BoardConstants.selfBoardEmpty){
            System.out.println("Player " + playerId + " has taken a miss " + c.toString());
            selfBoard.put(c, BoardConstants.selfBoardMiss); // miss on self board
            return false;
        }
        else{
            System.out.println("Attempting to hit the same place which is previously hit or missed");
            return false;
        }
    }

    public void updateOppositionBoard(Coordinate c, OppositionBoardState state){
        oppositionBoard.put(c, state);
    }

    public void drawBoard(){
        for(int i = 0 ; i < ShipType.values().length ;i++){
            int row = random.nextInt(BoardConstants.boardLen);
            int col = random.nextInt(BoardConstants.boardWidth);
            ShipOrientation orientation = ShipOrientation.values()[random.nextInt(2)];
            while(!buildFleet(new Coordinate(row, col), orientation, ShipType.values()[i] )){
                 row = random.nextInt(BoardConstants.boardLen);
                 col = random.nextInt(BoardConstants.boardWidth);
                 orientation = ShipOrientation.values()[random.nextInt(2)];
            }
        }
    }

    public boolean checkIfLost(){
        boolean allShipsSunk = true;
        for(Ship ship : fleet.values()){
            if(!ship.isSunk()){
                allShipsSunk = false;
                break;
            }
        }
        return allShipsSunk;
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

    public void printOppositionBoard() {
        System.out.println("Printing Oppostion Board for Player " + playerId);
        for(int i = 0 ; i < BoardConstants.boardLen ; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append("row ").append( (char)(i + 'A')).append(" : ");
            for (int j = 0; j < BoardConstants.boardWidth; j++) {
                sb.append(j + "(").append(oppositionBoard.get(new Coordinate(i,j))).append("), ");
            }
            System.out.println(sb.toString());
        }
        System.out.println();
    }

    public void printSelfBoard() {
        System.out.println("Printing Self Board for Player " + playerId);
        for(int i = 0 ; i < BoardConstants.boardLen ; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append("Row ").append( (char)(i + 'A')).append(" : ");
            for (int j = 0; j < BoardConstants.boardWidth; j++) {
                sb.append(j + "(").append(selfBoard.get(new Coordinate(i,j))).append("), ");
            }
            System.out.println(sb.toString());
        }
        System.out.println();
    }
}
