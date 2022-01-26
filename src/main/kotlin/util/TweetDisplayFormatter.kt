package util

import domain.Tweet

class TweetDisplayFormatter {
  private fun formatTweet(number: Int, tweet: Tweet): String {
    return """
      ------------------------------
      ---         TWEET          ---
      ------------------------------
      ID: ${tweet.id}
      TWEET_NUMBER: $number
      TWEET: ${tweet.text}
    """
  }

  fun printTweet(number: Int, tweet: Tweet) {
    println(formatTweet(number, tweet))
  }
}