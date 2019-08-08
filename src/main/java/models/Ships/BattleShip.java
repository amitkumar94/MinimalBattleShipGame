package models.Ships;

public class BattleShip extends Ship {
    public BattleShip(ShipOrientation orientation){
        super();
        length = 4;
        shipType = ShipType.BATTLESHIP;
        shipOrientation = orientation;
    }
}
