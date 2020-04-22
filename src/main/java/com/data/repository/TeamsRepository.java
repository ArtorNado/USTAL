package com.data.repository;

import com.data.entity.Teams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TeamsRepository extends JpaRepository<Teams, Integer> {

    Optional<Teams> findByTeamName(String teamName);

    Optional<Teams> findTeamsByTeamId(Integer tamId);

    @Query("select teams from Teams teams")
    Optional<List<Teams>> getAll();
}
