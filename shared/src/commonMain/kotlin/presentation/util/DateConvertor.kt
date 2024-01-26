package presentation.util

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.toInstant


fun String.convertDate() :String{
    try {
        // Validate that the input date string is not empty
        if (this.isEmpty()) {
            return ""
        }

        // Parse the original date string
        val originalInstant: Instant = Instant.parse(this)

        // Calculate the time difference
        val currentInstant: Instant = Clock.System.now()
        val duration = currentInstant - originalInstant

        // Format the time difference in "X days ago" format
        val daysAgo = duration.inWholeDays.toInt()

        return when {
            daysAgo == 0 -> "Today"
            daysAgo == 1 -> "Yesterday"
            daysAgo in 2..Int.MAX_VALUE -> "$daysAgo days ago"
            else -> "In the future"
        }
    } catch (e: Exception) {
        e.printStackTrace()
        return ""
    }
}



fun parseDateTime(originalDateString: String): Instant? {
    val regex = """(\d{4})-(\d{2})-(\d{2})T(\d{2}):(\d{2}):(\d{2}).(\d+)Z""".toRegex()
    val matchResult = regex.matchEntire(originalDateString)

    return if (matchResult != null) {
        val (year, month, day, hour, minute, second, fraction) = matchResult.destructured
        val epochMillis = "${year}-${month}-${day}T${hour}:${minute}:${second}.${fraction}Z"
            .toInstant()
            .toEpochMilliseconds()

        Instant.fromEpochMilliseconds(epochMillis)
    } else {
        null
    }
}