package me.mingliang

import io.github.cdimascio.dotenv.dotenv
import me.mingliang.jdbc.SQLServerGenerator
import me.mingliang.kafka.AvroConsumer

fun sqlTest() {
    val dotenv = dotenv()
    val sql = SQLServerGenerator(dotenv)
    sql.fetchData()
}

fun consumeKafka() {
    val dotenv = dotenv()
    val consumer = AvroConsumer(dotenv)
    consumer.consume(dotenv["topic"], dotenv["partition"].toInt(), dotenv["offset"].toLong())
}
fun main() {
    sqlTest()
    consumeKafka()
}
