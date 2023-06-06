import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {
    @Test
    void setsAttrs() {
        Card card = new Card("Hearts", "Queen", 12);

        assertEquals(12, card.rank);
        assertTrue(card.suit == "Hearts");
        assertTrue(card.value == "Queen");
    }
}