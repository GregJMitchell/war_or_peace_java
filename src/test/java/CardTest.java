import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {
    @Test
    void setsAttrs() {
        Card card = new Card("Hearts", "Queen", 12);

        assertEquals(12, card.rank);
        assertSame("Hearts", card.suit);
        assertSame("Queen", card.value);
    }
}