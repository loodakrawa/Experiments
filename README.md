# JavaCodeTest

### Task
###### Problem 1:
Write a program which accepts two inputs, representing two playing cards out of a standard 52 card deck. Add these two cards
together to produce a blackjack score, and print the score on the screen for the output.

Cards will be identified by the input, the first part representing the face value from 2-10, plus A, K, Q, J. The second part represents the suit S, C, D, H.

The blackjack score is the face value of the two cards added together, with cards 2-10 being the numeric face value, and A is worth 11, and K, Q, J are each worth 10.

###### Problem 2: 
Write a program that accepts a twitter username as input, and returns the last 5 tweets, plus number of Tweets, Following, and Followers for that twitter username.

### Solution
Both solutions are set up as a Gradle project.
* [Install Gradle] (https://docs.gradle.org/current/userguide/installation.html).
* Open terminal in the project root folder (Twitter or BlackJack)
* Run "gradle assemble"
* Navigate to ProjectRoot\build\libs
* Run "java -jar ProjectName.jar [input params]"

###### BlackJack
To Run: java -jar BlackJack.jar [list of cards]
e.g. java -jar BlackJack.jar 5s ad

###### Twitter
To Run: java -jar Twitter.jar username [tweetNumber]
e.g. java -jar Twitter.jar cnn 5

This solution uses the [Twitter4J library](http://twitter4j.org/en/index.html) as recommended on the [official documentation] (https://dev.twitter.com/overview/api/twitter-libraries#java)

Twitter's REST APIs only allow access to authenticated users/apps. Because of that, this test application requires specific secret user credentials which are not included with the source code. Please sign it at https://apps.twitter.com/, create an APP and fill the [properties file](https://github.com/loodakrawa/JavaCodeTest/blob/master/Twitter/src/main/resources/twitterAuth.properties) with the generated credentials.
More about auth in the [official documentation](https://dev.twitter.com/oauth).

Made a change

Made a second change