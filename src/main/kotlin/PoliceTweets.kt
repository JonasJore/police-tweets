import controller.TweetsController
import model.TweetsModel
import util.TweetDisplayFormatter

fun main(args: Array<String>) {
  val argIndex = 0
  val policeDeptArg = args[argIndex]

  val model = TweetsModel()
  val controller = TweetsController(model)

  val tweetsByPoliceDept = controller.getListOfTweetsByPoliceDepartment(policeDeptArg)
  tweetsByPoliceDept.forEachIndexed { index, tweet ->
    TweetDisplayFormatter().printTweet(index + 1, tweet)
  }
}
