package model

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

class TweetsModel : ITweetsModel {
  companion object {
    val httpClient: HttpClient = HttpClient.newHttpClient()
    const val uri = "https://api.twitter.com/2/users/%s/tweets?max_results=100&end_time=%s&start_time=%s"
    val dateUtil = Date()
  }

  override fun getTweetsByIdLastTwelveHours(userId: String): List<Tweet> {
    val formattedUri = uri.format(
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
      .mapToTweet()
  }

  private fun HttpRequest.toResponse(): HttpResponse<String> {
    return httpClient.send(this, HttpResponse.BodyHandlers.ofString())
  }

  private fun HttpResponse<String>.mapToTweet(): List<Tweet> {
    val gson = GsonBuilder().create()
    val jsonObject = gson.fromJson(this.body(), JsonObject::class.java)
    val elementArray = jsonObject.get("data").asJsonArray
    return gson.fromJson(elementArray, Array<Tweet>::class.java).toList()
  }

}