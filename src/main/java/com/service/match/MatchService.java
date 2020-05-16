package com.service.match;

import com.dto.MatchCommandDto;
import com.dto.MatchSingleDto;
import com.dto.MessageDto;
import com.models.MatchCommand;
import com.models.MatchSingle;

import java.util.List;

public interface MatchService {

    MessageDto createSingleMatch(MatchSingleDto matchSingle);

    MessageDto endSingleMatch(Integer idSingleMatch);

    MessageDto joinSingleMatch(Integer idSingleMatch, Integer recipient);

    List<MatchSingle> getAllMatch();

    List<MatchSingle> getAllMatchSingleByRole(Integer userId, String role);

    MessageDto createCommandMatch(MatchCommandDto matchCommand);

    MessageDto joinCommandMatch(Integer idCommandMatch, Integer recipient);

    List<MatchCommand> getAllCommandMatch();

    List<MatchSingle> getSingleMatchByRole(Integer userId, String role);
}
