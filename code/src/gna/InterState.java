package gna;

public class InterState implements Comparable<InterState> {
	private int moves;
	private InterState previousState;
	private Board initBoard;
	
	public InterState(int moves, InterState previousState, Board initBoard){
		if (previousState != null){
			this.moves = moves + previousState.getMoves();
		}else{
			this.moves = moves;
		}
		this.previousState = previousState;
		this.initBoard = initBoard;
	}
	/**
	 * @return the previousState
	 */
	public InterState getPreviousState() {
		return previousState;
	}
	/**
	 * @param previousState the previousState to set
	 */
	public void setPreviousState(InterState previousState) {
		this.previousState = previousState;
	}
	/**
	 * @return the initBoard
	 */
	public Board getInitBoard() {
		return initBoard;
	}
	/**
	 * @return the moves
	 */
	public int getMoves() {
		return moves;
	}
	/**
	 * @param initBoard the initBoard to set
	 */
	public void setInitBoard(Board initBoard) {
		this.initBoard = initBoard;
	}
	/**
	 * @param moves the moves to set
	 */
	public void setMoves(int moves) {
		this.moves = moves;
	}
	@Override
	public int compareTo(InterState other) {
		int diff = this.moves - other.moves;
		if(diff < 0){
			return -1;
		}else if(diff > 0){
			return 1;
		}else{
			return 0;
		}
	}
	
	
}
