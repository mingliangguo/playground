package me.mingliang

import io.github.cdimascio.dotenv.dotenv
import me.mingliang.jdbc.SQLServerGenerator
import me.mingliang.kafka.consumer.Consumer
import me.mingliang.config.Config

fun sqlTest(cfg: Config) {
    val sql = SQLServerGenerator(cfg)
    sql.fetchData()
}

fun consumeKafka(cfg: Config) {
    val consumer = Consumer(cfg)
    consumer.consume(cfg.kafkaTopic, cfg.topicPartition, cfg.partitionOffset)
}
fun loadConfig(): Config {
    val dotenv = dotenv()
    return Config(kafkaBroker = dotenv["kafka.brokers"],
        schemaRegistryUrl = dotenv["schema.registry.url"],
        consumerGroupId = dotenv["group.id"],
        kafkaTopic = dotenv["topic"],
        topicPartition = dotenv["partition"].toInt(),
        partitionOffset = dotenv["offset"].toLong(),
        messageCount = dotenv["message_count"].toLong(),
        jdbcUrl = dotenv["jdbc_url"],
        databaseUser = dotenv["db_user"],
        databasePassword = dotenv["db_password"]
    )
}
fun main() {
    val cfg = loadConfig()
    consumeKafka(cfg)
}
