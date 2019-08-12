package models.Ships;

public class ShipFactory {
    public static Ship createShip(ShipType shipType, ShipOrientation shipOrientation){
        if(ShipType.BATTLESHIP.equals(shipType)){
            return new BattleShip(shipOrientation);
        }
        else if(ShipType.CARRIER.equals(shipType)){
            return new Carrier(shipOrientation);
        }
        else if(ShipType.CRUISER.equals(shipType)){
            return new Cruiser(shipOrientation);
        }
        else if(ShipType.DESTROYER.equals(shipType)){
            return new Destroyer(shipOrientation);
        }
        else if(ShipType.SUBMARINE.equals(shipType)){
            return new Submarine(shipOrientation);
        }
        else{
            throw new RuntimeException("Invalid shipType provided");
        }
    }
}
