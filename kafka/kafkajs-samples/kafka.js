const fs = require('fs');
const _ = require('lodash');

const {
  Kafka,
  CompressionTypes,
} = require('kafkajs');

const {
  SchemaRegistry,
} = require('@kafkajs/confluent-schema-registry');

const kafkaConfig = require('config').get('kafka');

const emitter = require('events').EventEmitter;
const em = new emitter();

const https = require('https');
https.globalAgent.options.rejectUnauthorized = false;

const config = {
  clientId: kafkaConfig.clientId,
  brokers: _.split(process.env.KAFKA_BROKERS, ','),
  ssl: {
    rejectUnauthorized: false,
    ca: [fs.readFileSync(process.env.KAFKA_CERT_CHAIN_PATH, 'utf-8')],
  },
  sasl: {
    mechanism: process.env.KAFKA_SASL_MECHANISM,
    username: process.env.KAFKA_JAAS_USERNAME,
    password: process.env.KAFKA_JAAS_PASSWORD,
  },
};

const kafka = new Kafka(config);
const registry = new SchemaRegistry({
  host: process.env.KAFKA_SCHEMA_REGISTRY_URL,
});
const isAvroEncoded = (topics, topicName) => {
  const topic = _.find(topics, t => t.name === topicName);
  return topic && topic.avroEncoded;
};

class KafkaConsumer {
  constructor(consumerConfig) {
    consumerConfig = consumerConfig || kafkaConfig.consumer;

    this.consumer = kafka.consumer({
      groupId: consumerConfig.groupId,
    });
    this.topics = consumerConfig.topics;
  }

  async run() {
    await this.consumer.connect();
    for (const topic of this.topics) {
      await this.consumer.subscribe({
        topic: topic.name,
        fromBeginning: true,
      });
    }

    await this.consumer.run({
      eachMessage: async ({
        topic,
        partition,
        message,
      }) => {
        let messageValue = message.value;
        let messageKey = message.key;
        if (isAvroEncoded(this.topics, topic)) {
          messageValue = await registry.decode(messageValue);
        }
        console.log(topic, partition, JSON.stringify(messageValue, null, 2));
        em.emit(topic, {
          key: messageKey,
          value: messageValue,
        });
      },
    });
  }
}

class KafkaProducer {
  constructor(producerConfig) {
    producerConfig = producerConfig || kafkaConfig.producer;

    this.producer = kafka.producer();
    this.topics = producerConfig.topics;
  }

  async connect() {
    await this.producer.connect();
  }

  async sendMessage(topic, message, compression = CompressionTypes.GZIP) {
    if (isAvroEncoded(this.topics, topic)) {
      message = registry.encode(message);
    }
    await this.producer.send(topic,
      compression,
      message );
  }

}

const consumer = new KafkaConsumer(kafkaConfig.consumer);
consumer.run()
  .catch(e => console.error(e));

module.exports = {
  KafkaConsumer,
  KafkaProducer,
};
