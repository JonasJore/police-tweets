package controller

import domain.Tweet
import model.TweetsModel

class TweetsController(private val tweetsModel: TweetsModel) : ITweetsController {

  override fun getListOfTweetsByPoliceDepartment(policeDepartment: String): List<Tweet> {
    return tweetsModel.getTweetsByIdLastTwelveHours(policeDepartment)
  }
}