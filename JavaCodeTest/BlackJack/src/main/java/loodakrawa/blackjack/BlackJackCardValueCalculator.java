package loodakrawa.blackjack;

import java.util.Arrays;
import java.util.List;

/**
 * Implementation of the CardValueCalculator for BlackJack
 */
public class BlackJackCardValueCalculator implements CardValueCalculator
{
    private static final List<Rank> ranks = Arrays.asList(Rank.values());

    @Override
    public int getCardValue(Card card)
    {
        Rank rank = card.getRank();
        switch(card.getRank())
        {
            case JACK: return 10;
            case QUEEN: return 10;
            case KING: return 10;
            case ACE: return 11;
            default: return ranks.indexOf(rank) + 2;
        }
    }
}
