import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm") version "1.5.10"
    id("com.github.johnrengelman.shadow") version "5.1.0"
}

group = "me.mingliang"
version = "1.0-SNAPSHOT"
val exposedVersion = "0.32.1"

repositories {
    mavenCentral()
    maven { url = uri("https://packages.confluent.io/maven/") }
}

dependencies {
    // kafka
    implementation("org.apache.kafka:kafka-clients:2.0.0")
    implementation("io.confluent:kafka-avro-serializer:5.0.0")

    implementation("com.microsoft.sqlserver:mssql-jdbc:9.2.1.jre8")

    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")

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

tasks {
    build {
        dependsOn(shadowJar)
    }
}

tasks.withType<ShadowJar>() {
    manifest {
        attributes["Main-Class"] = "me.mingliang.MainKt"
    }
}
