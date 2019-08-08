package models.Ships;

import models.Coordinate;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public abstract class Ship {
    int length ;
    ShipType shipType;
    ShipOrientation shipOrientation;
    HashMap<Coordinate, ShipState> occupiedCoordinates;

    public Ship(){
        occupiedCoordinates = new HashMap<>();
    }

    public void assignCoordinates(Coordinate source){
         int len = length;
         if(shipOrientation.equals(ShipOrientation.HORIZONTAL)){
             for(int i = source.getCol() ; i < source.getCol() + len ; i++){
                 occupiedCoordinates.put(new Coordinate(source.getRow(), i), ShipState.HEALTHY);
             }
         }
         else{
             for(int i = source.getRow() ; i < source.getRow() + len ; i++){
                 occupiedCoordinates.put(new Coordinate(i, source.getCol()), ShipState.HEALTHY);
             }
         }
    }

    public void updateShipCoordinateStateToHit(Coordinate c){

    }

}
