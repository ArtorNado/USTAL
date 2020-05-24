package com.repository;

import com.models.EndedCommandMatch;
import com.models.Teams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EndCommandMatchRepository extends JpaRepository<EndedCommandMatch, Integer> {

    @Query("SELECT ecm FROM EndedCommandMatch ecm WHERE(ecm.firstTeam = :teamId OR ecm.secondTeam =:teamId)")
    Optional<List<EndedCommandMatch>> getEndedCommandBM(Teams teamId);
}
