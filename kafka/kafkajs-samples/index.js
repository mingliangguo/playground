const { Kafka } = require('kafkajs')

const kafka = new Kafka({
  clientId: 'my-app',
  brokers: ['p2pppicusfra020kafka001.fs.local:9095', 'p2pppicusfra020kafka002.fs.local:9095', 'p2pppicusfra020kafka003.fs.local:9095'],
  // authenticationTimeout: 1000,
  // reauthenticationThreshold: 10000,
  ssl: true,
  sasl: {
    mechanism: 'scram-sha-256', // scram-sha-256 or scram-sha-512, or plain
    username: 'app-services-w-scram-user',
    password: 'NWU2NjEzMzE4YzdmZjkx'
  },
});

const producer = kafka.producer()
const consumer = kafka.consumer({ groupId: 'uihub-test-group' })

const run = async () => {
  // Producing
  await producer.connect()
  await producer.send({
    topic: 'app-services-logs',
    messages: [
      { value: 'Hello uihub user!' },
    ],
  })


  // Consuming
  await consumer.connect()
  await consumer.subscribe({ topic: 'app-services-logs', fromBeginning: true })

  await consumer.run({
    eachMessage: async ({ topic, partition, message }) => {
      console.log({
        partition,
        offset: message.offset,
        value: message.value.toString(),
      })
    },
  })
};

run().catch(console.error);
