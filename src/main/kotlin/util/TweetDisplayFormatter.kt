package util

import domain.Tweet

class TweetDisplayFormatter {
  private fun formatTweet(tweet: Tweet): String {
    return Strings.formattedTweetTemplate.format(tweet.id, tweet.text)
  }

  fun printTweet(tweet: Tweet) {
    println(formatTweet(tweet))
  }
}