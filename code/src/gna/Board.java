package gna;

import java.util.Iterator;

public class Board
{
	private int[][] tiles;
	private int width;
	private int height;
	// construct a board from an N-by-N array of tiles
	public Board( int[][] tiles )
	{
		this.tiles = tiles;
		width = tiles[0].length;
		height = tiles.length;
	}

	// return number of blocks out of place
	public int hamming()
	{
		int outOfPlace = 0;
		for(int i = 0; i < height;i++){
			for(int j = 0; j < width;j++){
				if(tiles[i][j] != width*j+i+1 && tiles[i][j] != 0){
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
		for(int i = 0; i < height;i++){
			for (int j = 0; j < width;j++){
				if(tiles[i][j] != 0){
					rightX = tiles[i][j] / width;
					rightY = (tiles[i][j] % height) - 1;
					sum += Math.abs(rightX - j) + Math.abs(rightY - i);
				}
			}
		}
		return sum;
	}

	// does this board position equal y
	public boolean equals(Object y)
	{
		return false;
	}

	// return an Iterable of all neighboring board positions
	public Iterable<Board> neighbors()
	{
		return null;
	}

	// return a string representation of the board
	public String toString()
	{
		return "<empty>";
	}
}

