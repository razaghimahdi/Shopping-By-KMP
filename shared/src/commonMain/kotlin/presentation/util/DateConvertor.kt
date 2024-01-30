package presentation.util

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant


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

        return when (val daysAgo = duration.inWholeDays.toInt()) {
            0 -> "Today"
            1 -> "Yesterday"
            in 2..Int.MAX_VALUE -> "$daysAgo days ago"
            else -> "In the future"
        }
    } catch (e: Exception) {
        e.printStackTrace()
        return ""
    }
}



