package com.service.match;

import com.dto.MessageDto;
import com.models.MatchSingle;

import java.util.List;

public interface MatchService {

    MessageDto createSingleMatch(MatchSingle matchSingle);

    MessageDto endSingleMatch(Integer idSingleMatch);

    MessageDto joinSingleMatch(Integer idSingleMatch, Integer recipient);

    List<MatchSingle> getAllMatch();
}
