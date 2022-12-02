package io.spring.scs_kotlin_sample

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.kafka.test.context.EmbeddedKafka
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
@EmbeddedKafka(
	partitions = 1,
	ports = [9092]
)
class ScsKotlinSampleApplicationTests {
	
}
