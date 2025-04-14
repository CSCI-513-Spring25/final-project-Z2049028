import java.util.Arrays;
import java.util.Random;

public class Game {
    private static char[][] grid = new char[20][20];  
    private ColumbusShip ship;  
    private Random random;
    PirateFactory slowPirateFactory;
    PirateFactory fasPirateFactory;
    
    public void updateGrid(int x, int y, char value) {
        grid[x][y] = value;        
    }
    
    public Game() {         
        ship = new ColumbusShip();
        slowPirateFactory = new SlowPirateShipFactory();
        fasPirateFactory = new FastPirateShipFactory(); 
        random = new Random();
        initializeGrid();
    }
    
    public char[][] initializeGrid() {
        grid = new char[20][20];
        for(int i = 0; i < 20; i++) {
            Arrays.fill(grid[i], ' ');
        }         
        grid[ship.getX()][ship.getY()] = 'C';             
        addIslands();
        addPirateShips();
        return grid;
    }
    
    public static char[][] getGrid() {        
        return grid;
    }
    
    public ColumbusShip getColumbusShip() {
        return ship;
    }
    
    public Game play(int keyEvent) {
        switch(keyEvent) {
            case 37: ship.moveWest(this); break;
            case 38: ship.moveNorth(this); break;
            case 39: ship.moveEast(this); break;
            case 40: ship.moveSouth(this); break;
        }
        return this;
    }
    
    public void addPirateShips() {
        int piratesCount = 5;       
        while(piratesCount > 0) {
            int x = random.nextInt(20);
            int y = random.nextInt(20);
            if(grid[x][y] == ' ') {
                grid[x][y] = 'P';       
                PirateShip pirate = slowPirateFactory.createPirateShip(x, y);
                ship.addObserver(pirate);                   
                piratesCount--;
            }
        } 
        piratesCount = 5;
        while(piratesCount > 0) {
            int x = random.nextInt(20);
            int y = random.nextInt(20);
            if(grid[x][y] == ' ') {
                grid[x][y] = 'P';       
                PirateShip pirate = fasPirateFactory.createPirateShip(x, y);  
                ship.addObserver(pirate);                   
                piratesCount--;
            }
        }         
    }
    
    public void addIslands() {
        int islandCount = 8;
        while(islandCount > 0) {
            int x = random.nextInt(20);
            int y = random.nextInt(20);
            if(grid[x][y] == ' ') {
                grid[x][y] = 'I';
                islandCount--;
            }
        }
    }
}