package com.example.structure.common.extend

import java.text.SimpleDateFormat
import java.util.*

interface TimestampHelper {

    fun with(timeZoneId: String)

    fun with(timeZone: TimeZone)

    fun reset()

    fun currentTimeStamp(): Long

    fun currentCalendar(): Calendar

    // 2021-03-19T06:00:00.000Z
    fun parseLocalTimestamp(str: String?): Long?

    fun isDiffDate(time: Long, with: Long): Boolean

    fun isSameDate(from: Long, to: Long): Boolean

    fun isToday(time: Long): Boolean

    fun isTomorrow(time: Long): Boolean

    fun toCalendar(time: Long): Calendar

    fun isSameHour(time: Long, with: Long): Boolean

    fun isSameMinute(time: Long, with: Long): Boolean

    fun getGmtTime(): String

    fun getGmtTimeWithoutZ(): String
}

class TimestampHelperImpl : TimestampHelper {

    companion object {
        const val GTM_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
        const val GTM_FORMAT_WITHOUT_Z = "yyyy-MM-dd'T'HH:mm:ss.SS"
        val GMT_TIME_ZONE: TimeZone = TimeZone.getTimeZone("GMT")
    }

    private var timeZone = TimeZone.getDefault()

    override fun with(timeZoneId: String) {
        TimeZone.setDefault(TimeZone.getTimeZone(timeZoneId))
    }

    override fun with(timeZone: TimeZone) {
        TimeZone.setDefault(timeZone)
    }

    override fun reset() {
        TimeZone.setDefault(timeZone)
    }

    override fun currentTimeStamp(): Long {
        return System.currentTimeMillis()
    }

    override fun currentCalendar(): Calendar {
        return Calendar.getInstance()
    }

    // 2021-03-19T06:00:00.000Z
    override fun parseLocalTimestamp(str: String?): Long? {
        str ?: return null

        val sdf = SimpleDateFormat(GTM_FORMAT, Locale.getDefault())
        sdf.timeZone = GMT_TIME_ZONE
        return sdf.parse(str)?.time
    }

    override fun isDiffDate(time: Long, with: Long): Boolean {
        return !isSameDate(time, with)
    }

    override fun isSameDate(from: Long, to: Long): Boolean {
        val fromCal = toCalendar(from)
        val toCal = toCalendar(to)

        return fromCal.get(Calendar.YEAR) == toCal.get(Calendar.YEAR) &&
            fromCal.get(Calendar.MONTH) == toCal.get(Calendar.MONTH) &&
            fromCal.get(Calendar.DAY_OF_MONTH) == toCal.get(Calendar.DAY_OF_MONTH)
    }

    override fun isToday(time: Long): Boolean {
        val fromCal = toCalendar(time)
        val toCal = currentCalendar()

        return fromCal.get(Calendar.YEAR) == toCal.get(Calendar.YEAR) &&
            fromCal.get(Calendar.MONTH) == toCal.get(Calendar.MONTH) &&
            fromCal.get(Calendar.DAY_OF_MONTH) == toCal.get(Calendar.DAY_OF_MONTH)
    }

    override fun isTomorrow(time: Long): Boolean {
        val fromCal = toCalendar(time)
        val toCal = currentCalendar()
        toCal.roll(Calendar.DATE, 1)

        return fromCal.get(Calendar.YEAR) == toCal.get(Calendar.YEAR) &&
            fromCal.get(Calendar.MONTH) == toCal.get(Calendar.MONTH) &&
            fromCal.get(Calendar.DAY_OF_MONTH) == toCal.get(Calendar.DAY_OF_MONTH)
    }

    override fun toCalendar(time: Long): Calendar {
        return Calendar.getInstance().apply {
            timeInMillis = time
        }
    }

    override fun isSameHour(time: Long, with: Long): Boolean {
        val timeCal = toCalendar(time)
        val withCal = toCalendar(with)

        return timeCal.get(Calendar.YEAR) == withCal.get(Calendar.YEAR) &&
            timeCal.get(Calendar.MONTH) == withCal.get(Calendar.MONTH) &&
            timeCal.get(Calendar.DAY_OF_MONTH) == withCal.get(Calendar.DAY_OF_MONTH) &&
            timeCal.get(Calendar.HOUR_OF_DAY) == withCal.get(Calendar.HOUR_OF_DAY)
    }

    override fun isSameMinute(time: Long, with: Long): Boolean {
        val timeCal = toCalendar(time)
        val withCal = toCalendar(with)

        return timeCal.get(Calendar.YEAR) == withCal.get(Calendar.YEAR) &&
            timeCal.get(Calendar.MONTH) == withCal.get(Calendar.MONTH) &&
            timeCal.get(Calendar.DAY_OF_MONTH) == withCal.get(Calendar.DAY_OF_MONTH) &&
            timeCal.get(Calendar.HOUR_OF_DAY) == withCal.get(Calendar.HOUR_OF_DAY) &&
            timeCal.get(Calendar.MINUTE) == withCal.get(Calendar.MINUTE)
    }

    override fun getGmtTime(): String {
        val dateFormatGmt = SimpleDateFormat(GTM_FORMAT, Locale.getDefault())
        dateFormatGmt.timeZone = TimeZone.getTimeZone("GMT")
        return dateFormatGmt.format(Date())
    }

    override fun getGmtTimeWithoutZ(): String {
        val dateFormatGmt = SimpleDateFormat(GTM_FORMAT_WITHOUT_Z, Locale.getDefault())
        dateFormatGmt.timeZone = TimeZone.getTimeZone("GMT")
        return dateFormatGmt.format(Date())
    }
}
