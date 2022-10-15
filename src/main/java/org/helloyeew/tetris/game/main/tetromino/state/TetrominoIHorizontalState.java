package org.helloyeew.tetris.game.main.tetromino.state;

import org.helloyeew.tetris.game.main.tetromino.Tetromino;

/**
 * First state of the I tetromino (horizontal).
 */
public class TetrominoIHorizontalState implements TetrominoState, TetrominoIState {
    /**
     * Rotates the Tetromino.
     * @param tetromino the Tetromino to rotate
     */
    @Override
    public void rotate(Tetromino tetromino) {
        tetromino.setState(new TetrominoIVerticalState());
        tetromino.generateBlock();
    }
}
