import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.helloyeew.tetris.game.client.GameLocalClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MainTest {

    public GameLocalClient gameClient = new GameLocalClient();

    @BeforeEach
    void setUp() {
        gameClient.isTestEnvironment = true;
    }

    /**
     * Sleep the thread for a certain amount of time.
     * @param ms The amount of time to sleep in milliseconds.
     */
    void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test that the game is running when the game is started.
     */
    @Test
    void gameUpdateThreadTest() {
        gameClient.start();
        assertTrue(gameClient.observable.getRunning());
        // Wait for 1 second
        sleep(500);
        // Check that the tick is updated
        assertTrue(gameClient.observable.getTick() > 0);
    }

    /**
     * Test that the game can be over when some conditions are met.
     */
    @Test
    void gameEndTest() {
        // Make the game faster by setting the delayed tick to a very small value
        gameClient.observable.delayedTick = 2;
        gameClient.start();
        // Wait for 1 second
        sleep(500);
        // Check that the game is over
        assertTrue(gameClient.playfieldPlayer1.isGameOver() || gameClient.playfieldPlayer2.isGameOver());
    }

    /**
     * Test that the block not gone out of the playfield.
     */
    @Test
    void moveTest() {
        gameClient.observable.delayedTick = 100;
        gameClient.start();
        sleep(500);
        for (int i = 0; i < 50; i++) {
            gameClient.playfieldPlayer1.moveLeft();
        }
        sleep(20);
        // Check that the piee is moved to the right
        assertEquals(gameClient.playfieldPlayer1.getCurrentTetromino().getPositions().get(0).x, 0);
    }
}