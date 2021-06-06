package me.mingliang.jdbc

import java.sql.Connection
import java.sql.Date
import java.sql.DriverManager
import java.text.SimpleDateFormat
import java.util.*


class SQLServerGenerator {
    val DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS"

    companion object {
        init {
            DriverManager.registerDriver(com.microsoft.sqlserver.jdbc.SQLServerDriver())
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance()
        }
    }

    fun getConn(): Connection {
        val dbURL = "jdbc:sqlserver://skywalker;databaseName=mydb"
        val user = "sa"
        val password = "P@ssw0rd"
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
                        val d = result.getTimestamp("created")
                        val dateTimeFmt = SimpleDateFormat(DATETIME_FORMAT)
                        dateTimeFmt.timeZone = TimeZone.getTimeZone("UTC")

                        println("id: ${id}, name: ${name}, created: ${dateTimeFmt.format(d)}")
                    }
                }
            }
        }
   }
}
