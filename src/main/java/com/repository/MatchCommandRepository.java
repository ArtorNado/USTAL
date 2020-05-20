package com.repository;

import com.models.MatchCommand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MatchCommandRepository extends JpaRepository<MatchCommand, Integer> {

    Optional<MatchCommand> getMatchCommandByDateAndTimeAndMatchCityAndCreatorId(
            String date, String time, String matchCity, Integer creatorId
    );

    Optional<MatchCommand> getMatchCommandByMatchId(Integer matchId);

    @Query("SELECT matchCommand FROM MatchCommand matchCommand")
    Optional<List<MatchCommand>> getAll();

    @Query("SELECT matchCommand FROM MatchCommand  matchCommand WHERE (matchCommand.firstTeamId = :teamId OR matchCommand.secondTeamId = :teamId) AND matchCommand.creatorId <> :teamId")
    List<MatchCommand> getMatchBRole(Integer teamId);

    @Query("SELECT matchCommand FROM MatchCommand  matchCommand WHERE matchCommand.creatorId = :teamId")
    List<MatchCommand> getMatchbyRoleAdmin(Integer teamId);
}
