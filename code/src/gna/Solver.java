package gna;

import edu.princeton.cs.algs4.MinPQ;

public class Solver
{
	private MinPQ<InterState> statePQ = new MinPQ<InterState>();
	// find a solution to the initial board
	public Solver( Board initial )
	{
		InterState initState = new InterState(0,null,initial);
		this.stateToPQ(initState);
		while (!isGoalState(initState.getInitBoard())){
			
		}
		
	}
	public void stateToPQ(InterState initState){
		statePQ.insert(initState);
		statePQ.delMin();
		for(Board brd : initState.getInitBoard()){
			InterState newState = new InterState(brd.manhattan(),initState,brd);
			statePQ.insert(newState);
		}
	}
	public boolean isGoalState(Board board){
		boolean isGoal = false;
		for(int x = 0; x < board.getWidth(); x++){
			for(int y = 0; y < board.getHeight() - 1; y++){
				if(board.getTiles()[y][x] == board.getWidth()*x+y+1){
					isGoal = true;
				}else{
					isGoal = false;
					break;
				}
			}
		}
		return isGoal;
	}
	
	// is the initial board solvable?
	public boolean isSolvable()
	{
		return false;
	}
	
	// return min number of moves to solve initial board;
	// -1 if no solution
	public int moves()
	{
		return 0;
	}
	
	// return an Iterable of board positions in solution
	public Iterable<Board> solution()
	{
		return null;
	}
}


