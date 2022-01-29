package model

import domain.Tweet

interface ITweetsModel {
  fun getTwitterTimeLine(userId: String): List<Tweet>

}