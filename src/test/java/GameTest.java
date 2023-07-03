import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    Game game = new Game();

    @BeforeEach
    void setUp() {
    }

    @Test
    void runsGame() {
        game.start();
    }
}