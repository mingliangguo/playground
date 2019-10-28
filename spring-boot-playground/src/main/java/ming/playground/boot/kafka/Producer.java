package ming.playground.boot.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Producer {

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  public void sendMessage(String message) {
    log.info(String.format("#### -> Producing message -> %s", message));
    this.kafkaTemplate.send(KafkaConstants.TOPIC_NAME, message);
  }
}
