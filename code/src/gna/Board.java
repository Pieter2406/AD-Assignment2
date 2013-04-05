package gna;

import java.util.ArrayList;
import java.util.Iterator;


public class Board implements Iterable<Board>
{
	private int[][] tiles;
	private int width;
	private int height;
	private int emptySpotX;
	private int emptySpotY;
	// construct a board from an N-by-N array of tiles
	public Board( int[][] tiles )
	{
		this.tiles = tiles;
		width = tiles[0].length;
		height = tiles.length;
		for(int y = 0; y < height; y++){
			for (int x = 0; x < width; x++){
				if(tiles[y][x] == 0){
					emptySpotX = x;
					emptySpotY = y;
					break;
				}
			}
		}
	}

	// return number of blocks out of place
	public int hamming()
	{
		int outOfPlace = 0;
		for(int y = 0; y < height;y++){
			for(int x = 0; x < width;x++){
				if(tiles[y][x] != width*y+x+1 && tiles[y][x] != 0){
					outOfPlace++;
				}
			}
		}
		return outOfPlace;
	}

	// return sum of Manhattan distances between blocks and goal
	public int manhattan()
	{
		int rightX = 0;
		int rightY = 0;
		int sum = 0;
		for(int y = 0; y < height;y++){
			for (int x = 0; x < width;x++){
				if(tiles[y][x] != 0){
					rightY = (tiles[y][x] - 1) / width;
					rightX = (tiles[y][x] - 1) % height;
					sum += Math.abs(rightX - x) + Math.abs(rightY - y);
				}
			}
		}
		return sum;
	}

	// does this board position equal o
	public boolean equals(Object o)
	{
		if (o == null){
			return false;
		}
		Board other = (Board) o;
		boolean isEqual = true;
		for(int y = 0; y < height;y++){
			for(int x = 0; x < width;x++){
				if(this.tiles[y][x] != other.tiles[y][x]){
					isEqual = false;
					break;
				}
			}
		}
		return isEqual;
	}

	// return an Iterable of all neighboring board positions
	public Iterable<Board> neighbors()
	{
		ArrayList<Board> boardList = new ArrayList<Board>();
		
		if(isSwappableRight()){
			int[][] tempTiles = new int[this.height][this.width];
			arrayCopy2D(tiles,tempTiles);
			Board newBoard = new Board(tempTiles);
			newBoard.swapRight();
			boardList.add(newBoard);			
		}
		if(isSwappableLeft()){
			int[][] tempTiles = new int[this.height][this.width];
			arrayCopy2D(tiles,tempTiles);
			Board newBoard = new Board(tempTiles);
			newBoard.swapLeft();
			boardList.add(newBoard);
		}
		if(isSwappableDown()){
			int[][] tempTiles = new int[this.height][this.width];
			arrayCopy2D(tiles,tempTiles);
			Board newBoard = new Board(tempTiles);
			newBoard.swapDown();
			boardList.add(newBoard);
		}
		if(isSwappableUp()){
			int[][] tempTiles = new int[this.height][this.width];
			arrayCopy2D(tiles,tempTiles);
			Board newBoard = new Board(tempTiles);
			newBoard.swapUp();
			boardList.add(newBoard);
		}
		return boardList;
	}
	
	
	// return a string representation of the board
	public String toString()
	{
		String output = "";
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				output += (tiles[y][x] + " ");
			}
			output += "\n";
		}
		return output;
	}

	/**************************************************************************
	 * 							SWAPPABLE CHECKERS							  *
	 **************************************************************************/

	private boolean isSwappableRight(){ //Check if empty spot is swappable right.
		if(emptySpotX + 1 >= width){
			return false;
		}else{
			return true;
		}
	}
	private boolean isSwappableLeft(){ //Check if empty spot is swappable left.
		if(emptySpotX - 1 < 0){
			return false;
		}else{
			return true;
		}
	}
	private boolean isSwappableDown(){ //Check if empty spot is swappable down.
		if(emptySpotY + 1 >= height){
			return false;
		}else{
			return true;
		}
	}
	private boolean isSwappableUp(){ //Check if empty spot is swappable up.
		if(emptySpotY - 1 < 0){
			return false;
		}else{
			return true;
		}
	}

	/**************************************************************************
	 *								SWAPPERS								  *
	 **************************************************************************/
	private void swapRight(){
		newExchange(emptySpotX,emptySpotY,emptySpotX + 1, emptySpotY);
		emptySpotX++;
	}
	private void swapLeft(){
		newExchange(emptySpotX,emptySpotY,emptySpotX - 1, emptySpotY);
		emptySpotX--;
	}
	private void swapDown(){
		newExchange(emptySpotX,emptySpotY,emptySpotX, emptySpotY + 1);
		emptySpotY++;
	}
	private void swapUp(){
		newExchange(emptySpotX,emptySpotY,emptySpotX, emptySpotY - 1);
		emptySpotY--;
	}
	/*************************************************************************
	 *								HELPERMETHODS							 *
	 *************************************************************************/
	private void newExchange(int x1, int y1, int x2, int y2){
		int temp = tiles[y1][x1];
		tiles[y1][x1] = tiles[y2][x2];
		tiles[y2][x2] = temp;
	}

	@Override
	public Iterator<Board> iterator() {
		Iterator<Board> it = this.neighbors().iterator();
		return it;
	}
	//Make a copy of a two dimensional array, using the System.arraycopy method.
	public static void arrayCopy2D(int[][] array1,int[][] array2){
		for (int i = 0; i < array1.length; i++) {
		    System.arraycopy(array1[i], 0, array2[i], 0, array1[0].length);
		}
	}
	
	/*************************************************************************
	 *							Getters & Setters							 *
	 *************************************************************************/
	
	/**
	 * @return the tiles
	 */
	public int[][] getTiles() {
		return tiles;
	}
	
	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * @return the emptySpotX
	 */
	public int getEmptySpotX() {
		return emptySpotX;
	}
	
	/**
	 * @return the emptySpotY
	 */
	public int getEmptySpotY() {
		return emptySpotY;
	}
	
	/**
	 * @param tiles the tiles to set
	 */
	public void setTiles(int[][] tiles) {
		this.tiles = tiles;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	
	/**
	 * @param emptySpotX the emptySpotX to set
	 */
	public void setEmptySpotX(int emptySpotX) {
		this.emptySpotX = emptySpotX;
	}

	/**
	 * @param emptySpotY the emptySpotY to set
	 */
	public void setEmptySpotY(int emptySpotY) {
		this.emptySpotY = emptySpotY;
	}

	
}

