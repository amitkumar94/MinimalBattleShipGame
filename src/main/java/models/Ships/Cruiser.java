package models.Ships;

public class Cruiser extends Ship {

    public Cruiser(ShipOrientation orientation){
        super();
        length = 3;
        shipType = ShipType.CRUISER;
        shipOrientation = orientation;
    }
}
