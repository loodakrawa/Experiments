package loodakrawa.blackjack;

public class Main
{
    /**
     * Application entry point.
     *
     * Accepts an series of command line parameters representing poker cards, parses them and adds card values together
     * using BlackJack rules.
     *
     * @param args command line parameters.
     */
    public static void main(String[] args)
    {
        int sum = 0;
        CardValueCalculator calculator = new BlackJackCardValueCalculator();

        for(String arg : args)
        {
            try
            {
                Card card = CardParser.parse(arg);
                System.out.println(arg + " : " + card);
                sum += calculator.getCardValue(card);
            }
            catch(IllegalArgumentException ex)
            {
                System.out.println(arg + " is not a valid card input. Ignoring.");
            }

        }

        System.out.println("Sum: " + sum);
    }
}
