package com.nta.slack.database.repository;

import com.nta.slack.database.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, String> {




}
