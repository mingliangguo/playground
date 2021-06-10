package me.mingliang.jdbc

import me.mingliang.config.Config
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*


class SQLServerGenerator(private val cfg: Config) {

    companion object {
        const val DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS"
        val dateTimeFmt = SimpleDateFormat(DATETIME_FORMAT)
        val dateTimeFormatter = DateTimeFormatter.ofPattern(DATETIME_FORMAT)

        init {
            TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
            DriverManager.registerDriver(com.microsoft.sqlserver.jdbc.SQLServerDriver())
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance()

            dateTimeFmt.timeZone = TimeZone.getTimeZone("UTC")
        }
    }

    fun getConn(): Connection {

        return DriverManager.getConnection(cfg.jdbcUrl, cfg.databaseUser, cfg.databasePassword)
    }

    fun populateData() {
        val conn = getConn()

    }

    fun converTimestampToLocalDateTime(timestamp: Timestamp): LocalDateTime {
        val localDateTime = LocalDateTime.of(
            timestamp.year + 1900,
            timestamp.month + 1,
            timestamp.date,
            timestamp.hours,
            timestamp.minutes,
            timestamp.seconds,
            timestamp.nanos
        );
        println("localDateTime value: ${localDateTime.toInstant(ZoneOffset.UTC).toEpochMilli()}")
        return localDateTime
    }

    fun readAsLocalDateTime(rs: ResultSet): LocalDateTime {
        // preferred way to read datetime column from JDBC (since 4.2)
        val utc = rs.getObject("created", LocalDateTime::class.java)
        println(
            "utc local datetime: ${dateTimeFormatter.format(utc)}, value is: ${
                utc.toInstant(ZoneOffset.UTC).toEpochMilli()
            }"
        )
        return utc
    }

    fun fetchData() {
        val conn = getConn()
        conn.use { conn ->
            val pstmt = conn.prepareStatement("SELECT * FROM tblDatetime where id = 6")
            pstmt.use { pstmt ->
                val rs = pstmt.executeQuery()
                rs.use { result ->
                    while (result.next()) {
                        val id = result.getInt("id")
                        val name = result.getString("name")
                        val created = result.getTimestamp("created")
                        println("id: ${id}, name: ${name}, created: ${dateTimeFmt.format(created)}")
                        println("CDC tz: ${dateTimeFmt.format(created)}, value: ${created.time}")
                        readAsLocalDateTime(result)
                        readWithCalendar(result)
                    }
                }
            }
        }
    }

    private fun testLocalDateTime() {
        val localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(System.currentTimeMillis()), ZoneOffset.UTC);

        println("local date time by utc is: ${dateTimeFormatter.format(localDateTime)}")
        val utcTime = LocalDateTime.now(ZoneId.of("UTC"))
        println("local date time from utc zone is: ${dateTimeFormatter.format(utcTime)}")
    }

    private fun readWithCalendar(result: ResultSet) {
        val utcCreated = result.getTimestamp("created", Calendar.getInstance(TimeZone.getTimeZone("UTC")))
        println("Debezium tz: ${dateTimeFmt.format(utcCreated)}, value: ${utcCreated.time}")
    }
}
