package challenge;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class containing the method implementation for Stone's Elixir challenge.
 * Testing is done with JUnit on the ChallengeTest class. It can be run on IntelliJ IDEA or with
 * Maven.
 */
public class Challenge {

    /**
     * Splits the total price of the cart between each provided email
     * @param cart      the cart to be shared
     * @param emails    the list of emails that will share the value
     * @return  a Map containing emails as keys and corresponding price share as value
     */
    public static Map<String, Long> splitValues(List<CartItem> cart, List<String> emails) {
        if (emails.isEmpty()) {
            // If there are no emails, there's no need to do anything else
            return Collections.emptyMap();
        }

        Map<String, Long> splitResult = new HashMap<>();

        // Gets the total of the cart, or 0 if it is empty
        Long totalPrice = 0L;
        if (!cart.isEmpty()) {
            totalPrice = cart.stream()
                    .mapToLong(CartItem::getTotalPrice)
                    .sum();
        }

        Integer numPersons = emails.size();
        final Long leftoverMoney = totalPrice % numPersons;

        // If the division between emails is not equal, properly distributes the leftovers
        if (leftoverMoney != 0) {
            Long count = leftoverMoney;
            Long pricePerPerson = (totalPrice - leftoverMoney) / numPersons;

            for (String email : emails) {
                if (count > 0) {
                    splitResult.put(email, pricePerPerson + 1);
                    count--;
                } else {
                    splitResult.put(email, pricePerPerson);
                }
            }

        } else {
            Long pricePerPerson = totalPrice / numPersons;
            emails.forEach(email -> splitResult.put(email, pricePerPerson));
        }

        return splitResult;
    }

}
