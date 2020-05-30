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

    @Query("SELECT matchCommand FROM MatchCommand  matchCommand WHERE (matchCommand.firstTeamId = :teamId OR matchCommand.secondTeamId = :teamId)")
    List<MatchCommand> getMatchBRole(Integer teamId);

    @Query("SELECT matchCommand FROM MatchCommand  matchCommand WHERE matchCommand.creatorId = :teamId")
    List<MatchCommand> getMatchbyRoleAdmin(Integer teamId);

    @Query("SELECT matchCommand FROM MatchCommand  matchCommand WHERE matchCommand.creatorId = :teamId AND matchCommand.matchCity = :city")
    List<MatchCommand> getMatchbyRoleAdminAndCity(Integer teamId, String city);

    @Query("SELECT matchCommand FROM MatchCommand  matchCommand WHERE (matchCommand.firstTeamId = :teamId OR matchCommand.secondTeamId = :teamId) AND matchCommand.creatorId <> :teamId AND matchCommand.matchCity = :city")
    List<MatchCommand> getMatchBRoleAndCity(Integer teamId, String city);

    @Query("SELECT matchCommand FROM MatchCommand matchCommand WHERE (matchCommand.firstTeamId = :teamId OR matchCommand.secondTeamId =:teamId)")
    List<MatchCommand> getAllTeamCommandMatches(Integer teamId);

    List<MatchCommand> getMatchCommandByMatchCity(String city);

    @Query("SELECT matchCommand FROM MatchCommand  matchCommand WHERE (matchCommand.firstTeamId <> :teamId AND matchCommand.secondTeamId = null)")
    List<MatchCommand> getMatchFree(Integer teamId);

    @Query("SELECT matchCommand FROM MatchCommand  matchCommand WHERE (matchCommand.firstTeamId <> :teamId AND matchCommand.secondTeamId = null) AND matchCommand.matchCity = :city")
    List<MatchCommand> getMatchFreeAndCity(Integer teamId, String city);
}
