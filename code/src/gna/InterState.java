package gna;

public class InterState {
	private int moves;
	private InterState previousState;
	private Board initBoard;
	
	public InterState(int moves, InterState previousState, Board initBoard){
		this.moves = moves + previousState.getMoves();
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
	
	
}
