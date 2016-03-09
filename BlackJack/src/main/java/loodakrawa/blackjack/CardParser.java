package loodakrawa.blackjack;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Static class with utility methods for parsing Card related values.
 */
public final class CardParser
{
    private static Map<String, Suit> suits = new HashMap<>();

    static
    {
        for (Suit cs : Suit.values()) suits.put(cs.toString(), cs);
        suits.put("S", Suit.SPADES);
        suits.put("C", Suit.CLUBS);
        suits.put("H", Suit.HEARTS);
        suits.put("D", Suit.DIAMONDS);
    }

    private static Map<String, Rank> ranks = new HashMap<>();

    static
    {
        Rank[] allValues = Rank.values();
        for (Rank cv : allValues) ranks.put(cv.toString(), cv);
        for (int i = 2; i <=10; ++i) ranks.put(String.valueOf(i), allValues[i-2]);
        ranks.put("J", Rank.JACK);
        ranks.put("Q", Rank.QUEEN);
        ranks.put("K", Rank.KING);
        ranks.put("A", Rank.ACE);
    }

    /**
     * Parses the Suit
     *
     * @param input a String containing the Suit representation to be parsed.
     * @return the Suit value represented by the argument.
     * @throws IllegalArgumentException if the string does not contain a parsable Suit.
     */
    public static Suit parseSuit(String input)
    {
        Objects.requireNonNull(input);
        Suit suit = suits.get(input.toUpperCase());
        if(suit == null) throw new IllegalArgumentException("input string is not in the correct format");
        return suit;
    }

    /**
     * Parses the Rank
     *
     * @param input a String containing the Rank representation to be parsed.
     * @return the Rank value represented by the argument.
     * @throws IllegalArgumentException  if the string does not contain a parsable Rank.
     */
    public static Rank parseRank(String input)
    {
        Objects.requireNonNull(input);
        Rank rank = ranks.get(input.toUpperCase());
        if(rank == null) throw new IllegalArgumentException("input string is not in the correct format");
        return rank;
    }

    /**
     * Parses the Card
     *
     * @param input a String containing the Card representation to be parsed.
     * @return the Card value represented by the argument.
     * @throws IllegalArgumentException  if the string does not contain a parsable Card.
     */
    public static Card parse(String input)
    {
        Objects.requireNonNull(input);
        int length = input.length();
        if (length < 2 || length > 3) throw new IllegalArgumentException("Input string has an invalid format");
        int suitIndex = length == 2 ? 1 : 2;

        String valueStr = input.substring(0, suitIndex);
        String suitStr = input.substring(suitIndex);

        Rank rank = parseRank(valueStr);
        Suit suit = parseSuit(suitStr);

        return new Card(rank, suit);
    }

    private CardParser()
    {
    }
}
