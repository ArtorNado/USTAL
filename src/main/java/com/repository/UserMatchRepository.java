package com.repository;

import com.models.MatchSingle;
import com.models.UserMatch1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserMatchRepository extends JpaRepository<UserMatch1, Integer> {

    void deleteAllByMatchId(Integer matchId);

    Optional<List<UserMatch1>>  getUserMatchByUserIdAndRole(Integer userId, String Role);

    @Query("SELECT userMatch FROM UserMatch1 userMatch WHERE  userMatch.userId = :userId ")
    Optional<List<UserMatch1>>  getUserMatchByUserIdAndRole2(Integer userId);

    Optional<List<UserMatch1>> getUserMatchByRole(String role);

    Optional<List<UserMatch1>> getUserMatchByMatchId(MatchSingle matchSingle);

    @Query("SELECT userMatch FROM UserMatch1 userMatch WHERE  userMatch.userId = :userId")
    Optional<List<UserMatch1>> getUserMatchWithoutRole(Integer userId);

    @Query("SELECT DISTINCT um.matchId FROM UserMatch1 um")
    List<MatchSingle> getAll();

    Optional<UserMatch1> getUserMatchByMatchIdAndUserId(MatchSingle matchSingle, Integer userId);

}
