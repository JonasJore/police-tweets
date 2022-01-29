import controller.TweetsController
import http.TwitterTimelineAdapter
import model.TweetsModel
import util.Strings
import util.TweetDisplayFormatter

fun main(args: Array<String>) {
  val argIndex = 0
  val policeDeptArg = args[argIndex]

  val adapter = TwitterTimelineAdapter()
  val model = TweetsModel(adapter)
  val controller = TweetsController(model)

  val tweetsByPoliceDept = controller.getListOfTweetsByPoliceDepartment(policeDeptArg)
  tweetsByPoliceDept.forEach { tweet ->
    TweetDisplayFormatter().printTweet(tweet)
  }

  println(Strings.amountOfTweetsFetched.format(tweetsByPoliceDept.size))
}
