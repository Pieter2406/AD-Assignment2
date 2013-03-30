package gna;

import java.util.ArrayList;
import java.util.Iterator;


public class Board implements Comparable<Board>, Iterable<Board>
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
				if(tiles[y][x] != width*x+y+1 && tiles[y][x] != 0){
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
					rightX = tiles[y][x] / width;
					rightY = (tiles[y][x] % height) - 1;
					sum += Math.abs(rightX - x) + Math.abs(rightY - y);
				}
			}
		}
		return sum;
	}

	// does this board position equal o
	public boolean equals(Object o)
	{
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
		Board newBoard = new Board(tiles);
		ArrayList<Board> boardList = new ArrayList<Board>();
		if(isSwappableRight()){
			newBoard.swapRight();
			boardList.add(newBoard);
			newBoard = new Board(tiles);
		}
		if(isSwappableLeft()){
			newBoard.swapLeft();
			boardList.add(newBoard);
			newBoard = new Board(tiles);
		}
		if(isSwappableDown()){
			newBoard.swapDown();
			boardList.add(newBoard);
			newBoard = new Board(tiles);
		}
		if(isSwappableUp()){
			newBoard.swapUp();
			boardList.add(newBoard);
			newBoard = new Board(tiles);
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
	}
	private void swapLeft(){
		newExchange(emptySpotX,emptySpotY,emptySpotX - 1, emptySpotY);
	}
	private void swapDown(){
		newExchange(emptySpotX,emptySpotY,emptySpotX, emptySpotY + 1);
	}
	private void swapUp(){
		newExchange(emptySpotX,emptySpotY,emptySpotX, emptySpotY - 1);
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
	public int compareTo(Board arg0) {
		int diff = this.manhattan() - arg0.manhattan();
		if(diff < 0){
			return -1;
		}else if(diff > 0){
			return 1;
		}else{
			return 0;
		}
	}

	@Override
	public Iterator<Board> iterator() {
		Iterator<Board> it = this.neighbors().iterator();
		return it;
	}

	/**
	 * @return the tiles
	 */
	public int[][] getTiles() {
		return tiles;
	}

	/**
	 * @param tiles the tiles to set
	 */
	public void setTiles(int[][] tiles) {
		this.tiles = tiles;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}
}

