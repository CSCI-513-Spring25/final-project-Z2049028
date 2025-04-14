import java.util.Observer;
interface PirateShip extends Observer{
    void movePirateShip(char[][] oceanGrid,ColumbusShip ship);
}
