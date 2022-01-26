package controller

import domain.Tweet

interface ITweetsController {

  fun getListOfTweetsByPoliceDepartment(policeDepartment: String): List<Tweet>
}