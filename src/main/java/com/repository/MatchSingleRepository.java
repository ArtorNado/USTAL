package com.repository;


import com.models.MatchSingle;
import com.models.Teams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MatchSingleRepository extends JpaRepository<MatchSingle, Integer> {


    Optional<MatchSingle> findMatchSingleByCreatorIdAndAndDateAndTime(Integer creatorId, String date, String time);

    void deleteMatchSingleByMatchId(Integer id);

    Optional<MatchSingle> findMatchSingleByMatchId(Integer id);

    @Query("select matchSingle from MatchSingle matchSingle")
    Optional<List<MatchSingle>> getAll();

}
