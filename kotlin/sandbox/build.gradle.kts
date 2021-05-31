import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.10"
}

group = "me.mingliang"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
    maven { url = uri("https://packages.confluent.io/maven/") }
}

dependencies {
    // kafka
    implementation("org.apache.kafka:kafka-clients:2.0.0")
    implementation("io.confluent:kafka-avro-serializer:5.0.0")

    // logging
    implementation("io.github.microutils:kotlin-logging-jvm:2.0.6")
    implementation("org.slf4j:slf4j-api:1.7.25")
    implementation("org.apache.logging.log4j:log4j-api:2.11.1")
    implementation("org.apache.logging.log4j:log4j-core:2.11.1")
    implementation("org.apache.logging.log4j:log4j-slf4j-impl:2.11.1")

    // json
    implementation("com.fasterxml.jackson.core:jackson-databind:2.9.6")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.9.6")

    // faker
    implementation("com.github.javafaker:javafaker:0.15")

    // test
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}
