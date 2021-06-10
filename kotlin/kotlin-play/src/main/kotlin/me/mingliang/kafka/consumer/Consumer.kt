package me.mingliang.kafka.consumer

import io.confluent.kafka.serializers.KafkaAvroDeserializer
import io.github.cdimascio.dotenv.dotenv
import me.mingliang.config.Config
import org.apache.avro.generic.GenericRecord
import org.apache.kafka.clients.consumer.Consumer
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.clients.consumer.ConsumerRecords
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.TopicPartition
import java.time.Duration
import java.util.*

class Consumer(private val cfg: Config) {

    fun createConsumer(): Consumer<GenericRecord, GenericRecord> {
        val props = Properties()

        props["bootstrap.servers"] = cfg.kafkaBroker
        props["schema.registry.url"] = cfg.schemaRegistryUrl
        props["group.id"] = cfg.consumerGroupId
        props["enable.auto.commit"] = "false"
        props["key.deserializer"] = KafkaAvroDeserializer::class.java
        props["value.deserializer"] = KafkaAvroDeserializer::class.java
        return KafkaConsumer(props)
    }

    private fun Consumer<*, *>.consumeMessagesFromOffset(topic: String, partition: Int, offset: Long) {
        val topicPartition = TopicPartition(topic, partition)
        assign(listOf(topicPartition))
        seek(topicPartition, offset)
        processMessages()
    }
    private fun Consumer<*, *>.consumeMessages(topic: String) {
        subscribe(listOf(topic))
        processMessages()
    }

    private fun Consumer<*, *>.processMessages() {
        var count = 0
        while (true) {
            val messages: ConsumerRecords<*, *> = poll(Duration.ofSeconds(5))
            if (!messages.isEmpty) {
                for (message: ConsumerRecord<*, *> in messages) {
                    println("==> offset: ${message.offset()},\t key: ${message.key()},\t value: ${message.value()}")
                }
                count += messages.count()
                if (cfg.messageCount != -1L && count >= cfg.messageCount) {
                    println("Read total $count messages and exit ...")
                    break;
                }
            } else {
                println("Read total $count messages. No messages to read and poll timeout reached")
            }
        }
    }

    fun consume(topic: String, partition: Int, offset: Long) {
        val consumer = createConsumer()
        // consumer.consumeMessages(topic)
        consumer.consumeMessagesFromOffset(topic, partition, offset)
    }
}
