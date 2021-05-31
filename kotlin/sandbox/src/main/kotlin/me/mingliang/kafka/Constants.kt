package me.mingliang.kafka

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.codehaus.jackson.map.util.StdDateFormat


val kafkaTopic = "student"
val kafkaAvroTopic = "avroStudent"

val jsonMapper = ObjectMapper().apply {
    registerKotlinModule()
    disable(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS)
    setDateFormat(StdDateFormat())
}
