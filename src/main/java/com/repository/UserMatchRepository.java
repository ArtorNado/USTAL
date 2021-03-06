package com.repository;

import com.models.MatchSingle;
import com.models.UserMatch1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserMatchRepository extends JpaRepository<UserMatch1, Integer> {

    void deleteAllByMatchId(MatchSingle matchId);

    @Query("SELECT userMatch FROM UserMatch1 userMatch WHERE  userMatch.userId = :userId AND userMatch.role = :role")
    Optional<List<UserMatch1>>  getUserMatch1ByUserIdAndRole(String userId, String role);

    @Query("SELECT userMatch FROM UserMatch1 userMatch WHERE  userMatch.userId = :userId ")
    Optional<List<UserMatch1>>  getUserMatchByUserIdAndRole2(String userId);

    Optional<List<UserMatch1>> getUserMatchByRole(String role);

    Optional<List<UserMatch1>> getUserMatchByMatchId(MatchSingle matchSingle);

    @Query("SELECT userMatch FROM UserMatch1 userMatch WHERE  userMatch.userId = :userId")
    Optional<List<UserMatch1>> getUserMatchWithoutRole(String userId);

    @Query("SELECT DISTINCT um.matchId FROM UserMatch1 um")
    List<MatchSingle> getAll();

    Optional<UserMatch1> getUserMatchByMatchIdAndUserId(MatchSingle matchSingle, String userId);

}
