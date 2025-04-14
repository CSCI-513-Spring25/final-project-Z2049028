import java.awt.geom.Point2D;
import java.util.Observable;
import java.util.Random;

public class ColumbusShip extends Observable {
    private Point2D coordinate;  
    private Random random;  
    
    public ColumbusShip() {
        random = new Random();
        coordinate = new Point2D.Float(random.nextInt(20), random.nextInt(20));
    }
    
    public int getX() {
        return (int)coordinate.getX();
    }
    
    public int getY() {
        return (int)coordinate.getY();
    }
    
    private void setCoordinate(Point2D point) {
        coordinate = point;
        setChanged();
        notifyObservers();
    }
    
    public void moveEast(Game game) {
        int x = getX();
        int y = getY();
        if(y + 1 < 20 && game.getGrid()[x][y + 1] != 'I') {
            game.updateGrid(x, y, ' ');
            game.updateGrid(x, y + 1, 'C');
            setCoordinate(new Point2D.Float(x, y + 1));
        }
    }
    
    public void moveWest(Game game) {
        int x = getX();
        int y = getY();
        if(y - 1 >= 0 && game.getGrid()[x][y - 1] != 'I') {
            game.updateGrid(x, y, ' ');
            game.updateGrid(x, y - 1, 'C');
            setCoordinate(new Point2D.Float(x, y - 1));
        }
    }
    
    public void moveNorth(Game game) {
        int x = getX();
        int y = getY();
        if(x - 1 >= 0 && game.getGrid()[x - 1][y] != 'I') {
            game.updateGrid(x, y, ' ');
            game.updateGrid(x - 1, y, 'C');
            setCoordinate(new Point2D.Float(x - 1, y));
        }
    }
    
    public void moveSouth(Game game) {
        int x = getX();
        int y = getY();
        if(x + 1 < 20 && game.getGrid()[x + 1][y] != 'I') {
            game.updateGrid(x, y, ' ');
            game.updateGrid(x + 1, y, 'C');
            setCoordinate(new Point2D.Float(x + 1, y));
        }
    }
}