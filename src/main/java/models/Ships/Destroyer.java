package models.Ships;

public class Destroyer extends Ship {
     public Destroyer(ShipOrientation orientation){
         super();
         length=2;
         shipOrientation = orientation;
         shipType = ShipType.DESTROYER;
     }
}
