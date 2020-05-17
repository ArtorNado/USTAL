package com.repository;

import com.models.MatchSingle;
import com.models.UserData;
import com.models.UserMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserMatchRepository extends JpaRepository<UserMatch, Integer> {

    void deleteAllByMatchId(Integer matchId);

    Optional<List<UserMatch>>  getUserMatchByUserIdAndRole(Integer userId, String Role);

    Optional<List<UserMatch>> getUserMatchByRole(String role);

    @Query("SELECT userMatch FROM UserMatch userMatch WHERE  userMatch.userId = :userId")
    Optional<List<UserMatch>> getUserMatchWithoutRole(Integer userId);

    @Query("SELECT DISTINCT um.matchId FROM UserMatch um")
    List<MatchSingle> getAll();

}
