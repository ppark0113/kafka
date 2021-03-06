package com.kafka.listener;

import com.kafka.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaMessageConsumer {

  @KafkaListener(topics = "json-pds", groupId = "pds")
  public void listen(Student message) {
    log.info("Received Messasge in group : {}", message);
  }
}
