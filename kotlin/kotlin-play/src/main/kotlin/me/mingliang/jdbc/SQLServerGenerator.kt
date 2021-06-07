package me.mingliang.jdbc

import java.sql.Connection
import java.sql.DriverManager
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*


class SQLServerGenerator {

    companion object {
        const val dbURL = "jdbc:sqlserver://skywalker;databaseName=mydb"
        const val user = "sa"
        const val password = "P@ssw0rd"
        const val DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS"
        val dateTimeFmt = SimpleDateFormat(DATETIME_FORMAT)
        var format = DateTimeFormatter.ofPattern(DATETIME_FORMAT)
        init {
            DriverManager.registerDriver(com.microsoft.sqlserver.jdbc.SQLServerDriver())
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance()

            dateTimeFmt.timeZone = TimeZone.getTimeZone("UTC")
        }
    }

    fun getConn(): Connection {
        return DriverManager.getConnection(dbURL, user, password)
    }

    fun populateData() {
    }
    fun fetchData() {
        val conn = getConn()
        conn.use { conn ->
            val pstmt = conn.prepareStatement("SELECT * FROM tblDatetime")
            pstmt.use { pstmt ->
                val result = pstmt.executeQuery()
                result.use { result ->
                    while (result.next()) {
                        val id = result.getInt("id")
                        val name = result.getString("name")
//                        val created = result.getTimestamp("created")
                        val created = result.getTimestamp("created", Calendar.getInstance(TimeZone.getTimeZone("UTC")))

                        println("id: ${id}, name: ${name}, created: ${dateTimeFmt.format(created)}")
                    }
                }
            }
        }
        val localDate = LocalDateTime.now();
        println("local date time is: ${format.format(localDate.atZone(ZoneId.of("UTC")))}")
        val utcTime = LocalDateTime.now(ZoneId.of("UTC"))
        println("local date time is: ${format.format(utcTime)}")
   }
}
