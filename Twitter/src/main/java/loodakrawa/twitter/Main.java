package loodakrawa.twitter;

import twitter4j.*;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class Main
{
    private static final String AUTH_PROPERTIES_PATH = "twitterAuth.properties";
    private static final int DEFAULT_TWEET_COUNT = 5;

    public static void main(String args[]) throws Exception
    {
        if(args.length == 0)
        {
            System.out.println("Usage: username [tweetCount]");
            return;
        }

        String username = args[0];
        int tweetCount = DEFAULT_TWEET_COUNT;

        if(args.length >= 2)
        {
            try
            {
                tweetCount = Integer.parseInt(args[1]);
            }
            catch (NumberFormatException ex)
            {
                //ignore
            }
        }

        List<Status> tweets = LoadTweets(username, tweetCount);
        for (Status s : tweets)
        {
            System.out.println(s.getText());
        }
    }

    private static List<Status> LoadTweets(String username, int count) throws TwitterException
    {
        Configuration config = LoadConfiguration();
        Twitter twitter = new TwitterFactory(config).getInstance();

        Paging paging = new Paging(1, count);
        return twitter.getUserTimeline(username, paging);
    }

    private static Configuration LoadConfiguration()
    {
        Properties props = LoadProperties(AUTH_PROPERTIES_PATH);

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setOAuthConsumerKey(props.getProperty("consumer.key"));
        cb.setOAuthConsumerSecret(props.getProperty("consumer.secret"));
        cb.setOAuthAccessToken(props.getProperty("access.token"));
        cb.setOAuthAccessTokenSecret(props.getProperty("access.token.secret"));

        return cb.build();
    }

    private static Properties LoadProperties(String path)
    {
        Properties properties = new Properties();
        InputStream stream = Main.class.getClassLoader().getResourceAsStream(path);

        try
        {
            properties.load(stream);
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            if(stream != null)
            {
                try
                {
                    stream.close();
                }
                catch (Exception ignorable)
                {
                    //ignore
                }
            }
        }

        return properties;
    }
}
