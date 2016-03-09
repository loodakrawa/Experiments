package loodakrawa.blackjack;

/**
 * Classes implementing this interface calculate the value of the card for the specific game.
 */
public interface CardValueCalculator
{
    /**
     * Method for calculating the value of the card in argument.
     * @param card the Card
     * @return int value of the Card argument
     */
    int getCardValue(Card card);
}
