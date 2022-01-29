package model

import domain.Tweet
import http.TwitterTimelineAdapter

class TweetsModel(private val twitterTimelineAdapter: TwitterTimelineAdapter) : ITweetsModel {

  override fun getTwitterTimeLine(userId: String): List<Tweet> {
    return twitterTimelineAdapter.getTweetsByIdLastTwelveHours(userId)
  }
}
