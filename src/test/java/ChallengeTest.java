import challenge.CartItem;
import org.junit.jupiter.api.Test;

import java.util.*;

import static challenge.Challenge.splitValues;
import static org.junit.jupiter.api.Assertions.*;

public class ChallengeTest {


    @Test
    public void test_emptyCart() {
        List<CartItem> poorCart = Collections.emptyList();
        List<String> emails = getEmails();

        Map<String, Long> result = splitValues(poorCart, emails);

        assertEquals(0, result.get(emails.get(0)));
        assertEquals(0, result.get(emails.get(1)));
        assertEquals(0, result.get(emails.get(2)));
        assertEquals(3, result.keySet().size());
    }

    @Test
    public void test_emptyEmails() {
        List<CartItem> cart = getItems();
        List<String> emails = Collections.emptyList();

        Map<String, Long> result = splitValues(cart, emails);

        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    public void test_perfectDivision() {
        List<CartItem> cart = getItems();
        List<String> emails = getEmails();

        Map<String, Long> result = splitValues(cart, emails);

        assertEquals(3, result.keySet().size());
        assertEquals(32, result.get(emails.get(0)));
        assertEquals(32, result.get(emails.get(1)));
        assertEquals(32, result.get(emails.get(2)));
    }

    @Test
    public void test_DivisionWithLeftovers() {
        List<CartItem> imperfectCart = getItems();
        imperfectCart.get(0).setQuantity(5);

        List<String> emails = getEmails();

        Map<String, Long> result = splitValues(imperfectCart, emails);

        assertEquals(3, result.keySet().size());
        assertTrue(result.containsValue(32L));
        assertTrue(result.containsValue(33L));

        Long sum = result.values().stream().mapToLong(i -> i).sum();
        assertEquals(98, sum);
    }

    @Test
    public void test_SingletonLists() {
        List<CartItem> singleItem = Collections.singletonList(getItems().get(0));
        List<String> singleEmail = Collections.singletonList(getEmails().get(0));

        Map<String, Long> result = splitValues(singleItem, singleEmail);

        assertEquals(1, result.keySet().size());
        assertEquals(8, result.get(singleEmail.get(0)));
    }

    private List<CartItem> getItems() {
        List<CartItem> result = new ArrayList<>();
        CartItem item1 = new CartItem("alfafa", 4, 2L);
        CartItem item2 = new CartItem("tomato", 1, 11L);
        CartItem item3 = new CartItem("stone", 7, 11L);
        result.add(item1);
        result.add(item2);
        result.add(item3);

        return result;
    }

    private List<String> getEmails() {
        List<String> emails = new ArrayList<>();
        emails.add("numberONE@zmail.com");
        emails.add("numer2_@email.com");
        emails.add("stone@stone.com");
        return emails;
    }

}
