package models.Ships;

public class Carrier extends Ship {

    public Carrier(ShipOrientation orientation){
        super();
        length = 5;
        shipType = ShipType.CARRIER;
        shipOrientation = orientation;
    }

}
