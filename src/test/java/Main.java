import static org.junit.jupiter.api.Assertions.assertTrue;

import org.helloyeew.tetris.game.client.GameLocalClient;
import org.junit.jupiter.api.Test;

class MainTest {

    public GameLocalClient gameClient = new GameLocalClient();

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
        gameClient.observable.delayedTick = 5;
        gameClient.start();
        assertTrue(gameClient.observable.getRunning());
        // Wait for 1 second
        sleep(1000);
        // Check that the game is over
        assertTrue(gameClient.playfieldPlayer1.isGameOver() || gameClient.playfieldPlayer2.isGameOver());
    }
}