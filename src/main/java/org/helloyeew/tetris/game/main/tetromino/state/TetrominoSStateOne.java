package org.helloyeew.tetris.game.main.tetromino.state;

import org.helloyeew.tetris.game.main.tetromino.Tetromino;

/**
 * First state of the S tetromino (diagram 1).
 */
public class TetrominoSStateOne implements TetrominoState, TetrominoSState {
    /**
     * Rotates the Tetromino.
     * @param tetromino the Tetromino to rotate
     */
    @Override
    public void rotate(Tetromino tetromino) {
        tetromino.setState(new TetrominoSStateTwo());
        tetromino.generateBlock();
    }
}
