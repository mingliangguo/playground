package me.mingliang.config

data class Config(
    val kafkaBroker: String,
    val schemaRegistryUrl: String,
    val consumerGroupId: String,
    val kafkaTopic: String,
    val topicPartition: Int,
    val partitionOffset: Long,
    val messageCount: Long,
    val jdbcUrl: String,
    val databaseUser: String,
    val databasePassword: String
)
