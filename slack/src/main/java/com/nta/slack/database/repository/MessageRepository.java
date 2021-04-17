package com.nta.slack.database.repository;

import com.nta.slack.entidades.slack.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
