package com.firehook.moviestore.util

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by firehook on 12.10.2020.
 * firehook21@gmail.com
 */
class DateFormatter {

    fun format(timeInMillis: Long, format: DateFormat): String {
        return getDateFormat(format).format(Date(timeInMillis))
    }

    private fun getDateFormat(format: DateFormat): SimpleDateFormat {
        return SimpleDateFormat(format.format, Locale.getDefault())
    }
}