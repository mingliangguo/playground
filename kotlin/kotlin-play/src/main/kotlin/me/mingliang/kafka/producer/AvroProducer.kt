package me.mingliang.kafka.producer

import com.github.javafaker.Faker
import io.confluent.kafka.serializers.KafkaAvroSerializer
import me.mingliang.kafka.data.Student
import mu.KotlinLogging
import org.apache.avro.Schema
import org.apache.avro.generic.GenericRecord
import org.apache.avro.generic.GenericRecordBuilder
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.Producer
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringSerializer
import java.io.File
import java.util.*

class AvroProducer(brokers: String, schemaRegistryUrl: String, keySchemaFile: String, schemaFile: String) {
    private val logger = KotlinLogging.logger {}
    private val producer = createProducer(brokers, schemaRegistryUrl)
    private val schema = (Schema.Parser().parse(File(schemaFile)))
    private val keySchema = (Schema.Parser().parse(File(keySchemaFile)))

    private fun createProducer(brokers: String, schemaRegistryUrl: String): Producer<GenericRecord, GenericRecord> {
        val props = Properties()
        props["bootstrap.servers"] = brokers
        props["key.serializer"] = KafkaAvroSerializer::class.java
        props["value.serializer"] = KafkaAvroSerializer::class.java
        props["schema.registry.url"] = schemaRegistryUrl
        return KafkaProducer(props)
    }

    fun produce(topic: String, rate: Int) {
        val waitDuration = 1000L / rate
        logger.info { "producing $rate records per second to $topic ( 1 every $waitDuration ms" }
        val faker = Faker()
        while (true) {
            val fakeStudent = Student(
                id = System.nanoTime(),
                firstName = faker.name().firstName(),
                lastName = faker.name().lastName()
            )
            logger.info { "generate a record: $fakeStudent" }

            val keyRecord = GenericRecordBuilder(keySchema).apply {
                set("id", fakeStudent.id)
            }.build()
            val record = GenericRecordBuilder(schema).apply {
                set("id", fakeStudent.id)
                set("firstName", fakeStudent.firstName)
                set("lastName", fakeStudent.lastName)
            }.build()

            val future = producer.send(ProducerRecord(topic, keyRecord, record))
            Thread.sleep(waitDuration)
            future.get()
        }
    }
}
