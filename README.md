
# Spring Cloud Stream with Kotlin sample project

This is a sample project spring cloud stream with Kotlin and Java 17.


## Host

To run this project, you will need to add the following dns to hosts file

`127.0.0.1 kafka`


## Usage/Examples

Start docker stack with next command:
```bash
docker-compose up -d
```
Sample response:
```log
2022-11-27 23:04:06.975  INFO 244 --- [container-0-C-1] i.s.s.listener.KafkaListener             : logging text message: GenericMessage [payload=Quia dolores qui sed dolorem architecto dolor earum. Saepe sint animi dolorem ipsum sed voluptatem id vero illo. Hic odit quibusdam officia minima sunt nemo quia velit. Eveniet odit in hic voluptas. Fuga a voluptas perferendis aut voluptatem. Incidunt sunt qui quos sapiente saepe sunt., headers={deliveryAttempt=1, kafka_timestampType=CREATE_TIME, kafka_receivedMessageKey=[B@f1b79a7, kafka_receivedTopic=io.spring.scs_kotlin_sample.texts, kafka_offset=2, scst_nativeHeadersPresent=true, kafka_consumer=org.apache.kafka.clients.consumer.KafkaConsumer@364b7c14, source-type=kafka, id=396cc566-6432-fef0-37c8-9c3a8ddbc167, kafka_receivedPartitionId=0, contentType=text/plain, kafka_receivedTimestamp=1669608246968, kafka_groupId=anonymous.81264ece-29b0-4829-985a-ab1a1ec0fdb4, timestamp=1669608246974}]
2022-11-27 23:04:30.798  INFO 244 --- [container-0-C-1] i.s.s.listener.KafkaListener             : logging user: UserDto(uuid=66727fd4-fd46-4697-a597-350c8e702a61, username=Baby.Pfannerstill68, scope=INTERNAL, isLocked=true, age=500, createdAt=2022-01-01T14:03:56)
2022-11-27 23:04:30.798  INFO 244 --- [container-0-C-1] i.s.s.listener.KafkaListener             : logging user unlocked message: UserDto(uuid=66727fd4-fd46-4697-a597-350c8e702a61, username=Baby.Pfannerstill68, scope=INTERNAL, isLocked=false, age=500, createdAt=2022-01-01T14:03:56)
2022-11-27 23:04:38.119  INFO 244 --- [container-0-C-1] i.s.s.listener.KafkaListener             : logging object: ObjectB(uuid=beb06ecb-3fd5-4c51-8fa7-2aeb9e7306eb, data=id aspernatur ut, createdAt=null)
```

## License

[Apache License](https://choosealicense.com/licenses/apache-2.0/)

