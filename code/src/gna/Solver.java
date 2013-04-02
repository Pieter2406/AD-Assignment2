package gna;

import java.util.ArrayList;

import edu.princeton.cs.algs4.MinPQ;

public class Solver

{
	private int moves;
	private MinPQ<InterState> statePQ ;
	private InterState initState;
	// find a solution to the initial board
	public Solver( Board initial )
	{
		statePQ = new MinPQ<InterState>();
		initState = new InterState(0,null,initial);
		if(isSolvable()){
			while (!isGoalState(initState.getInitBoard())){
				this.stateToPQ();
			}
			moves = initState.getMoves();
		}else{
			moves = -1;
		}
	}
	public void stateToPQ(){
		statePQ.insert(initState);
		initState = statePQ.delMin();
		for(Board brd : initState.getInitBoard()){
			InterState neighborState = new InterState(brd.manhattan(),initState,brd);
			statePQ.insert(neighborState);
		}
	}
	public boolean isGoalState(Board board){
		boolean isGoal = false;
		for(int y = 0; y < board.getHeight(); y++){
			for(int x = 0; x < board.getWidth(); x++){
				if(board.getTiles()[y][x] == board.getWidth()*y+x+1){
					isGoal = true;
				}else if(board.getTiles()[board.getHeight() - 1][board.getWidth() - 1] == 0){
					isGoal = true;
				}
				else{
					return false;
				}
			}
		}
		return isGoal;
	}

	// is the initial board solvable?
	public boolean isSolvable()
	{
		int inversions = 0;
		int bHeight = initState.getInitBoard().getHeight();
		int bWidth = initState.getInitBoard().getWidth();
		int[] serializedBoard = new int[bHeight*bWidth - 1];
		int index = 0;
		int emptySpotIndex = 0;
		for(int y = 0; y < bHeight ; y++){
			for(int x = 0; x < bWidth;x++){
				if(initState.getInitBoard().getTiles()[y][x] == 0){
					emptySpotIndex = y;
				}else{
					serializedBoard[index] = initState.getInitBoard().getTiles()[y][x];
					index ++;
				}

			}
		}

		for(int i = 0 ; i < serializedBoard.length;i++){
			for(int j = i+1; j < serializedBoard.length;j++){
				if(serializedBoard[i] > serializedBoard[j] && j > i){
					inversions++;
					break;	
				}
			}
		}
		//if size is even
		if(bHeight % 2 == 0){
			/*	if inversions + the row index of the empty spot is odd,
			 * 	the puzzle is solvable. */
			if(inversions + emptySpotIndex % 2 != 0)	return true;
			else										return false;
		}else{
			// if size is odd
			// If the amount of inversions is even, the puzzle is solvable. 
			if(inversions % 2 == 0)		return true;
			else						return false;
		}
	}

	// return min number of moves to solve initial board;
	// -1 if no solution
	public int moves()
	{
		return moves;
	}

	// return an Iterable of board positions in solution
	public Iterable<Board> solution()
	{
		ArrayList<Board> boardList = new ArrayList<Board>();
		InterState temp = initState;
		while(temp.getPreviousState() != null){
			boardList.add(temp.getInitBoard());
			temp = temp.getPreviousState();
		}
		return boardList;
	}

}


