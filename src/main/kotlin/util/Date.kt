package util

import java.time.Instant
import java.time.temporal.ChronoUnit

const val TWELWE_HOURS_IN_MS: Long = 43_200

class Date {

  fun getInstantTwelveHoursAgo(): String {
    val format = Instant.now().truncatedTo(ChronoUnit.SECONDS).minusSeconds(TWELWE_HOURS_IN_MS)
    return format.toString()
  }

  fun getInstantNow(): String {
    val formatOfInstant = Instant.now().truncatedTo(ChronoUnit.SECONDS)
    return formatOfInstant.toString()
  }
}