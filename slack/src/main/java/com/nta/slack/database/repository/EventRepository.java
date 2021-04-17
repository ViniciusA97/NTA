package com.nta.slack.database.repository;

import com.nta.slack.database.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
