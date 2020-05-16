package com.repository;

import com.models.MatchSingle;
import com.models.UserData;
import com.models.UserMatch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserMatchRepository extends JpaRepository<UserMatch, Integer> {

    void deleteAllByMatchId(Integer matchId);

    Optional<List<UserMatch>>  getUserMatchByUserIdAndRole(Integer userId, String Role);

    Optional<List<UserMatch>> getUserMatchByRole(String role);

}
