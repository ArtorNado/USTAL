package com.repository;

import com.models.Teams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TeamsRepository extends JpaRepository<Teams, Integer> {

    Optional<Teams> findByTeamName(String teamName);

    Optional<Teams> findTeamsByTeamId(Integer tamId);

    @Query("select teams from Teams teams")
    Optional<List<Teams>> getAll();

    Optional<List<Teams>> findTeamsByTeamNameIgnoreCase(String name);

    Optional<List<Teams>> findTeamsByTeamCityIgnoreCase(String teamCity);

    Optional<List<Teams>> findTeamsByTeamStatus(String teamStatus);

    Optional<List<Teams>> findTeamsByTeamCityAndTeamStatus(String teamCity, String teamStatus);

    Optional<Teams> findTeamsByCreatorId(Integer creatorId);
}
