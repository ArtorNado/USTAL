package com.repository;

import com.models.UserMatch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMatchRepository extends JpaRepository<UserMatch, Integer> {

    void deleteAllByMatchId(Integer matchId);

}
