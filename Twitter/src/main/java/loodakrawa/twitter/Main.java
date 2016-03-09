package loodakrawa.twitter;

import twitter4j.*;
import twitter4j.api.UsersResources;
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

    /**
     * Application entry point.
     *
     * Accepts a Twitter username and an optional tweet count and displays basic info about the associated Twitter
     * account and the last N tweets where N is the passed tweet count value.
     *
     * @param args command line parameters.
     * @throws Exception
     */
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

        Configuration config = LoadConfiguration();
        Twitter twitter = new TwitterFactory(config).getInstance();

        UsersResources us = twitter.users();
        User user = us.showUser(username);

        System.out.println("Username: " + username);
        System.out.println("Tweets: " + user.getStatusesCount());
        System.out.println("Following: " + user.getFriendsCount());
        System.out.println("Followers: " + user.getFollowersCount());
        System.out.println("Likes: " + user.getFavouritesCount());

        System.out.println("Last " + tweetCount + " tweets:");
        Paging paging = new Paging(1, tweetCount);
        List<Status> tweets = twitter.getUserTimeline(username, paging);
        for (Status s : tweets)
        {
            System.out.println(s.getText());
        }
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
