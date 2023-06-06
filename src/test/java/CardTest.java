import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Test.*;

class CardTest {
    @Test
    void setsAttrs() {
        Card card = new Card("Hearts", "Queen", 12);

        assertTrue(card.rank == 12);
        assertTrue(card.suit == "Hearts");
        assertTrue(card.value == "Queen");
    }
}