package http

import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import domain.Tweet
import token.TwitterAuthToken
import util.Date
import util.policeSectionContainer
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class TwitterTimelineAdapter : ITwitterTimelineAdapter {
  companion object {
    val httpClient: HttpClient = HttpClient.newHttpClient()
    val dateUtil = Date()
    const val twitterTimelineUri = TwitterApiUris.twitterTimelineApiEndpoint
  }

  override fun getTweetsByIdLastTwelveHours(userId: String): List<Tweet> {
    val formattedUri = twitterTimelineUri.format(
      policeSectionContainer[userId],
      dateUtil.getInstantNow(),
      dateUtil.getInstantTwelveHoursAgo()
    )
    return HttpRequest.newBuilder()
      .uri(URI.create(formattedUri))
      .version(HttpClient.Version.HTTP_2)
      .header(
        "Authorization",
        "Bearer ${TwitterAuthToken.BEARER_TOKEN}"
      )
      .header("accept", "application/json")
      .GET()
      .build()
      .toResponse()
      .mapToTwitterTimeline()
  }

  private fun HttpRequest.toResponse(): HttpResponse<String> {
    return httpClient.send(this, HttpResponse.BodyHandlers.ofString())
  }

  private fun HttpResponse<String>.mapToTwitterTimeline(): List<Tweet> {
    val gson = GsonBuilder().create()
    val jsonObject = gson.fromJson(this.body(), JsonObject::class.java)
    val elementArray = jsonObject.get("data").asJsonArray
    return gson.fromJson(elementArray, Array<Tweet>::class.java).toList()
  }
}
