package Map;
import java.awt.Point;
import java.util.ArrayList;
/**
 * 
 */

/**
 * @author Saklani Pankaj
 *
 */

public class Map {
	
	private final Cell[][] grid;
	
	//KIV add Robot once Created
	public Map()
	{
		grid = new Cell[MapConstants.MAP_HEIGHT][MapConstants.MAP_WIDTH];
		
		//Init Cells on the grid;
		for(int row=0; row < MapConstants.MAP_HEIGHT; row++) {
			for(int col=0; col < MapConstants.MAP_WIDTH; col++) {
				grid[row][col] = new Cell(new Point(col,row));
				
				//Init Virtual wall
				if(row==0||col==0||row==MapConstants.MAP_HEIGHT-1||col==MapConstants.MAP_WIDTH-1) {
					grid[row][col].setVirtualWall(true);
				}
				
			}
		}
		
	}
	
	//Returns the Cell
	public Cell getCell(int row, int col) {
		return grid[row][col];
	}

	// Returns the Cell based on Point
	public Cell getCell(Point pos) {
		return grid[pos.y][pos.x];
	}
	
	//Check if the row and col is within the map boundary
	public boolean checkValidCell(int row, int col) {
		return row>=0&&col>=0&&row<MapConstants.MAP_HEIGHT&&col<MapConstants.MAP_WIDTH;
	}
	
	
	//Check if valid to move there cannot move to virtual wall
	public boolean checkValidMove(int row, int col) {
		return checkValidCell(row, col) && !getCell(row,col).isVirtualWall() && !getCell(row,col).isObstacle();
	}
	
	
	//Reset Map
	public void resetMap() {
		for(int row = 0; row < MapConstants.MAP_HEIGHT; row++) {
			for(int col = 0; col < MapConstants.MAP_WIDTH; col++) {
				grid[row][col].setExplored(false);
				grid[row][col].setObstacle(false);
				grid[row][col].setPath(false);
				grid[row][col].setVirtualWall(false);
			}
		}
	}
	
	//Returns the Percentage Explored
	public double exploredPercentage() {
		double total = MapConstants.MAP_HEIGHT*MapConstants.MAP_WIDTH;
		double explored = 0;
		
		for (int row = 0; row < MapConstants.MAP_HEIGHT; row++) {
			for (int col = 0; col < MapConstants.MAP_WIDTH; col++) {
				if(grid[row][col].isExplored())
					explored++;
			}
		}
		return explored/total;
	}
	
	
	public ArrayList<Cell> getNeighbours(Cell c){
		ArrayList<Cell> neighbours = new ArrayList<Cell>();
	
		//UP
		if(checkValidCell(c.getPos().x, c.getPos().y+1) && checkValidMove(c.getPos().x, c.getPos().y+1)) {
			neighbours.add(getCell(c.getPos().y+1, c.getPos().x));
		}
		//DOWN
		if(checkValidCell(c.getPos().x, c.getPos().y-1) && checkValidMove(c.getPos().x, c.getPos().y-1)) {
			neighbours.add(getCell(c.getPos().y-1, c.getPos().x));
		}
		
		//RIGHT
		if(checkValidCell(c.getPos().x+1, c.getPos().y) && checkValidMove(c.getPos().x+1, c.getPos().y)) {
			neighbours.add(getCell(c.getPos().y+1, c.getPos().x));
		}
		
		//LEFT
		if(checkValidCell(c.getPos().x-1, c.getPos().y) && checkValidMove(c.getPos().x-1, c.getPos().y)) {
			neighbours.add(getCell(c.getPos().y+1, c.getPos().x));
		}
		
		return neighbours;
	}
}
