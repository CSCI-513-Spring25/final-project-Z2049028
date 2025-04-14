import java.util.Observable;
import java.util.Observer;
import java.awt.geom.Point2D;

class SlowPirateShip implements PirateShip{
    private Point2D pirateLocation;
    public SlowPirateShip(int x,int y){
        pirateLocation=new Point2D.Float(x,y);
    }
    @Override
    public void update(Observable ship,Object args){
        if(ship instanceof ColumbusShip){
            ColumbusShip columbusShip=(ColumbusShip)ship;
            movePirateShip(Game.getGrid(),columbusShip);
        }
    }  
    public void movePirateShip(char[][] oceanGrid,ColumbusShip ship){
        int px = (int)pirateLocation.getX();
		int py = (int)pirateLocation.getY();
		int cx = (int)ship.getX();
		int cy = (int)ship.getY();
		oceanGrid[px][py]=Character.MIN_VALUE;
		//If Columbus Ship is below the Pirate ship
		if(px-cx<0 && px+1<10&&px+1>=0&& oceanGrid[px+1][py]!='I'&&oceanGrid[px+1][py]!='P'&&oceanGrid[px+1][py]!='C')
			px++;
		// If Columbus Ship is above Pirate ship
		else if(px-cx>0 && px-1<10&&px-1>=0&&oceanGrid[px-1][py]!='I'&&oceanGrid[px-1][py]!='P'&&oceanGrid[px-1][py]!='C')
		    px--;
		//If Columbus ship is towards right side of the Pirate ship
		if(py-cy<0 && py+1<10&&py+1>=0&& oceanGrid[px][py+1]!='I'&&oceanGrid[px][py+1]!='P'&&oceanGrid[px][py+1]!='C')
		    py++;		
		//If Columbus ship is towards left side of the Pirate Ship
		else if(py-cy>0 && py-1<10&&py-1>=0&&oceanGrid[px][py-1]!='I'&&oceanGrid[px][py-1]!='P'&&oceanGrid[px][py-1]!='C')
		    py--;
        pirateLocation.setLocation(px, py);	
		oceanGrid[(int)pirateLocation.getX()][(int)pirateLocation.getY()]='P';
    }
}
