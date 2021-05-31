package me.mingliang.kafka.producer

import com.github.javafaker.Faker
import me.mingliang.kafka.data.Student
import me.mingliang.kafka.jsonMapper
import mu.KotlinLogging
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.Producer
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringSerializer
import java.util.*

class PlainProducer(brokers: String) {
    private val logger = KotlinLogging.logger {}
    private val producer = createProducer(brokers)

    private fun createProducer(brokers: String): Producer<String, String> {
        val props = Properties()
        props["bootstrap.servers"] = brokers
        props["key.serializer"] = StringSerializer::class.java
        props["value.serializer"] = StringSerializer::class.java
        return KafkaProducer(props)
    }

    fun produce(topic: String, rate: Int) {
        val waitDuration = 1000L / rate
        logger.info { "producing $rate records per second to $topic ( 1 every $waitDuration ms" }
        val faker = Faker()
        while (true) {
            val fakeStudent = Student(
                firstName = faker.name().firstName(),
                lastName = faker.name().lastName()
            )
            logger.info { "generate a record: $fakeStudent" }

            val future = producer.send(ProducerRecord(topic, jsonMapper.writeValueAsString(fakeStudent)))
            Thread.sleep(waitDuration)
            future.get()
        }
    }
}
