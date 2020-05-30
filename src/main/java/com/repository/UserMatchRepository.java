package com.repository;

import com.models.MatchSingle;
import com.models.UserData;
import com.models.UserMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface UserMatchRepository extends JpaRepository<UserMatch, Integer> {

    void deleteAllByMatchId(Integer matchId);

    Optional<List<UserMatch>>  getUserMatchByUserIdAndRole(Integer userId, String Role);

    @Query("SELECT userMatch FROM UserMatch userMatch WHERE  userMatch.userId = :userId AND (userMatch.role =: Admin OR userMatch.role = Participant)")
    Optional<List<UserMatch>>  getUserMatchByUserIdAndRole2(Integer userId, String Role);

    Optional<List<UserMatch>> getUserMatchByRole(String role);

    Optional<List<UserMatch>> getUserMatchByMatchId(MatchSingle matchSingle);

    @Query("SELECT userMatch FROM UserMatch userMatch WHERE  userMatch.userId = :userId")
    Optional<List<UserMatch>> getUserMatchWithoutRole(Integer userId);

    @Query("SELECT DISTINCT um.matchId FROM UserMatch um")
    List<MatchSingle> getAll();

    Optional<UserMatch> getUserMatchByMatchIdAndUserId(MatchSingle matchSingle, Integer userId);

}
