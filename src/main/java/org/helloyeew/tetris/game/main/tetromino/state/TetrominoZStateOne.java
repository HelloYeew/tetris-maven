package org.helloyeew.tetris.game.main.tetromino.state;

import org.helloyeew.tetris.game.main.tetromino.Tetromino;

/**
 * First state of the Z tetromino (diagram 1).
 */
public class TetrominoZStateOne implements TetrominoState, TetrominoZState {
    /**
     * Rotates the Tetromino.
     * @param tetromino the Tetromino to rotate
     */
    @Override
    public void rotate(Tetromino tetromino) {
        tetromino.setState(new TetrominoZStateTwo());
        tetromino.generateBlock();
    }
}
