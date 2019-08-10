package models.Ships;

public class Submarine extends Ship {
    public Submarine(ShipOrientation orientation){
        super();
        length = 3;
        shipOrientation = orientation;
        shipType = ShipType.SUBMARINE;
    }
}
