package model

interface ITweetsModel {
  fun getTweetsByIdLastTwelveHours(userId: String): List<Any>
}