package http

interface ITwitterTimelineAdapter {
  fun getTweetsByIdLastTwelveHours(userId: String): List<Any>
}
