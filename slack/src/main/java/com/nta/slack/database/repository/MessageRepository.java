package com.nta.slack.database.repository;

import com.nta.slack.database.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
