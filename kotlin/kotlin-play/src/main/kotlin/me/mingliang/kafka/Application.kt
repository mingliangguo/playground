package me.mingliang.kafka

import me.mingliang.kafka.data.Student
import com.github.javafaker.Faker
import me.mingliang.kafka.producer.AvroProducer
import me.mingliang.kafka.producer.PlainProducer
import mu.KotlinLogging

class Application {
    private val faker: Faker = Faker()
    private val logger = KotlinLogging.logger {}
    fun createStudent(): Student {
        logger.info { "create student object ..." }
        return Student(
            id = System.nanoTime(),
            firstName = faker.name().firstName(),
            lastName = faker.name().lastName()
        )
    }
    fun startProducer() {
        PlainProducer(brokers = "skywalker:9092")
            .produce(kafkaTopic, 5)
    }
    fun startAvroProducer() {
        val schemaPath = "src/main/resources/student.avsc"
        val keySchemaPath = "src/main/resources/student-key.avsc"
        AvroProducer(brokers = "skywalker:9092", schemaRegistryUrl = "http://skywalker:8081", keySchemaPath, schemaPath)
            .produce(kafkaAvroTopic, 5)
    }
}
fun main() {
    val app = Application()
    val emp = app.createStudent()
//    app.startProducer()
    app.startAvroProducer()
    println(emp)
}
