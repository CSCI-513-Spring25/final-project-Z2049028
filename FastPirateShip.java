import java.awt.geom.Point2D;
import java.util.Observable;
import java.util.Observer;

public class FastPirateShip implements PirateShip, Observer {
    private Point2D location;
    
    public FastPirateShip(int x, int y) {
        location = new Point2D.Float(x, y);
    }
    
    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof ColumbusShip) {
            movePirateShip(Game.getGrid(), (ColumbusShip)o);
        }
    }
    
    @Override
    public void movePirateShip(char[][] grid, ColumbusShip ship) {
        int px = (int)location.getX();
        int py = (int)location.getY();
        grid[px][py] = ' ';
        
        // Move randomly but towards CC
        int cx = ship.getX();
        int cy = ship.getY();
        
        if(Math.random() > 0.5) {
            if(px < cx && px + 1 < 20 && grid[px+1][py] == ' ') px++;
            else if(px > cx && px - 1 >= 0 && grid[px-1][py] == ' ') px--;
        } else {
            if(py < cy && py + 1 < 20 && grid[px][py+1] == ' ') py++;
            else if(py > cy && py - 1 >= 0 && grid[px][py-1] == ' ') py--;
        }
        
        location.setLocation(px, py);
        grid[px][py] = 'P';
    }
}