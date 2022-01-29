package controller

import domain.Tweet
import model.TweetsModel
import util.policeSectionContainer
import java.lang.RuntimeException

class PoliceDepartementNotFoundException(exceptionMessage: String) : RuntimeException(exceptionMessage)

class TweetsController(private val tweetsModel: TweetsModel) : ITweetsController {

  override fun getListOfTweetsByPoliceDepartment(policeDepartment: String): List<Tweet> {
    if (policeSectionContainer.containsKey(policeDepartment)) {
      return tweetsModel.getTwitterTimeLine(policeDepartment)
    } else {
      throw PoliceDepartementNotFoundException("Police department not found")
    }
  }
}
