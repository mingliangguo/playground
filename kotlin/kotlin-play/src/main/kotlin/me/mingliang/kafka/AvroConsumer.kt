package me.mingliang.kafka

import io.confluent.kafka.serializers.KafkaAvroDeserializer
import io.github.cdimascio.dotenv.Dotenv
import io.github.cdimascio.dotenv.dotenv
import org.apache.kafka.clients.consumer.Consumer
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.clients.consumer.ConsumerRecords
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.TopicPartition
import java.time.Duration
import java.util.*

class AvroConsumer(private val dotenv: Dotenv) {

    fun createConsumer(): Consumer<String, String> {
        val props = Properties()

        props["bootstrap.servers"] = dotenv["kafka.brokers"]
        props["schema.registry.url"] = dotenv["schema.registry.url"]
        props["group.id"] = dotenv["group.id"]
        props["enable.auto.commit"] = "false"
        props["key.deserializer"] = KafkaAvroDeserializer::class.java
        props["value.deserializer"] = KafkaAvroDeserializer::class.java
        return KafkaConsumer(props)
    }

    fun Consumer<String, String>.consumeMessagesFromOffset(topic: String, partition: Int, offset: Long) {
        val topicPartition = TopicPartition(topic, partition)
        assign(listOf(topicPartition))
        seek(topicPartition, offset)
        processMessages()
    }
    fun Consumer<String, String>.consumeMessages(topic: String) {
        subscribe(listOf(topic))
        processMessages()
    }

    private fun Consumer<String, String>.processMessages() {
        while (true) {
            val messages: ConsumerRecords<String, String> = poll(Duration.ofMillis(5000))
            if (!messages.isEmpty) {
                for (message: ConsumerRecord<String, String> in messages) {
                    println("${message.topic()}-${message.partition()}, offset: ${message.offset()}, key: ${message.key()}, value: ${message.value()}")
                }
            } else {
                println("No messages to read and poll timeout reached")
            }
        }
    }

    fun consume(topic: String, partition: Int, offset: Long) {
        val consumer = createConsumer()
        // consumer.consumeMessages(topic)
        consumer.consumeMessagesFromOffset(topic, partition, offset)
    }
}

fun main() {
    val consumer = AvroConsumer(dotenv = dotenv())
    consumer.consume("avroStudent", partition = 0, offset = 0)
}
