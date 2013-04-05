package gna;

import java.util.ArrayList;
import java.util.PriorityQueue;


public class Solver

{
	private int moves;
	private PriorityQueue<InterState> statePQ ;
	private InterState initState;
	// find a solution to the initial board
	public Solver( Board initial )
	{
		statePQ = new PriorityQueue<InterState>();
		initState = new InterState(0,null,initial);
		statePQ.add(initState);
		if(isSolvable()){
			while (!isGoalState(initState.getInitBoard())){
				this.stateToPQ();
			}
			moves = initState.getMoves();
		}else{
			moves = -1;
		}
	}
	//Function to add neighboring boards to the priority queue
	public void stateToPQ(){
		initState = statePQ.remove();
		for(Board brd : initState.getInitBoard()){
			int priority = brd.hamming();
			if(initState.getPreviousState() == null){
				InterState neighborState = new InterState(priority,initState,brd);
				statePQ.add(neighborState);
			}
			else if(!brd.equals(initState.getPreviousState().getInitBoard())){
				InterState neighborState = new InterState(priority,initState,brd);
				statePQ.add(neighborState);
			}

		}
	}
	//Function to check whether the given board is the goalstate
	public boolean isGoalState(Board board){
		if(board.getTiles()[board.getHeight() - 1][board.getWidth() - 1] == 0){
			for(int y = 0; y < board.getHeight(); y++){
				for(int x = 0; x < board.getWidth(); x++){
					if(y == board.getHeight() - 1 && x == board.getWidth() - 1){
						break;
					}else{
						if(board.getTiles()[y][x] != board.getWidth()*y+x+1){
							return false;
						}
					}
				}
			}
			return true;
		}else{
			return false;
		}

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
		//Put the two dimensional array in a one dimensional array without the empty spot.
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
		//Calculate inversions
		for(int i = 0 ; i < serializedBoard.length;i++){
			for(int j = i+1; j < serializedBoard.length;j++){
				if(serializedBoard[i] > serializedBoard[j]){
					inversions++;
				}
			}
		}
		//if size is even
		if(bHeight % 2 == 0){
			/*	if inversions + the row index of the empty spot is odd,
			 * 	the puzzle is solvable. */
			if((inversions + emptySpotIndex) % 2 != 0){
				return true;
			}else{
				return false;
			}
		}else{
			// if size is odd
			// If the amount of inversions is even, the puzzle is solvable. 
			if(inversions % 2 == 0){
				return true;
			}else{
				return false;
			}
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
			boardList.add(0,temp.getInitBoard());
			temp = temp.getPreviousState();
		}
		return boardList;
	}

}


