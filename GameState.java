import java.util.Arrays;

public class GameState {
    private final Cell[] cells;
    
    public GameState(Game game) {
        this.cells = getCells(game);
    }
    
    public static GameState forGame(Game game) {
        return new GameState(game);
    }
    
    private static Cell[] getCells(Game game) {
        Cell[] cells = new Cell[400];
        char[][] grid = game.getGrid();
        
        for (int x = 0; x < 20; x++) {
            for (int y = 0; y < 20; y++) {
                char value = grid[x][y];
                String text = value == '\u0000' ? "" : String.valueOf(value);
                cells[20 * x + y] = new Cell(x, y, text);
            }
        }
        return cells;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"cells\":[");
        for (int i = 0; i < cells.length; i++) {
            sb.append(cells[i].toString());
            if (i < cells.length - 1) sb.append(",");
        }
        sb.append("]}");
        return sb.toString();
    }
}

class Cell {
    private final int x;
    private final int y;
    private final String text;
    
    public Cell(int x, int y, String text) {
        this.x = x;
        this.y = y;
        this.text = text;
    }
    
    @Override
    public String toString() {
        return String.format(
            "{\"x\":%d,\"y\":%d,\"text\":\"%s\"}",
            x, y, text
        );
    }
}