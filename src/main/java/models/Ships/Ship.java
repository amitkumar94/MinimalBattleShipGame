package models.Ships;

import models.Coordinate;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public abstract class Ship {
    int length ;
    ShipType shipType;
    ShipOrientation shipOrientation;
    ShipState shipState;
    HashMap<Coordinate, ShipState> occupiedCoordinates;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public ShipType getShipType() {
        return shipType;
    }

    public void setShipType(ShipType shipType) {
        this.shipType = shipType;
    }

    public ShipOrientation getShipOrientation() {
        return shipOrientation;
    }

    public void setShipOrientation(ShipOrientation shipOrientation) {
        this.shipOrientation = shipOrientation;
    }

    public HashMap<Coordinate, ShipState> getOccupiedCoordinates() {
        return occupiedCoordinates;
    }

    public void setOccupiedCoordinates(HashMap<Coordinate, ShipState> occupiedCoordinates) {
        this.occupiedCoordinates = occupiedCoordinates;
    }

    public Ship(){
        occupiedCoordinates = new HashMap<>();
        shipState  = ShipState.AFLOAT;
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

    public void updateShipState(Coordinate c){
        occupiedCoordinates.put(c, ShipState.HIT);
        if(isSunk()){
            shipState = ShipState.SUNK;
        }
    }

    public boolean isSunk(){
        boolean shouldSunk = true;
        for(Map.Entry<Coordinate, ShipState> e : occupiedCoordinates.entrySet()){
            if(e.getValue().equals(ShipState.HEALTHY)){
                shouldSunk = false;
                break;
            }
        }
        return shouldSunk;
    }

}
