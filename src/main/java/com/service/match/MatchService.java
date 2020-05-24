package com.service.match;

import com.dto.MatchCommandDto;
import com.dto.MatchSingleDto;
import com.dto.MessageDto;
import com.dto.StatusDto;
import com.models.EndedCommandMatch;
import com.models.MatchCommand;
import com.models.MatchSingle;
import com.models.UserData;

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

    MatchSingle findMatchSingleById(Integer matchId);

    List<UserData> getMatchParticipant(Integer matchId);

    StatusDto determineUserStatusInMatch(Integer matchId, Integer userId);

    List<MatchCommand> getCommandMatchByRole(Integer userId, String role);

    MatchCommand getCommandMatchById(Integer id);

    MessageDto endCommandMatch(Integer matchId, Integer firstTeamScore, Integer secondTeamsScore);

    List<EndedCommandMatch> getEndedCommandMatch(Integer teamId);

    List<MatchCommand> getAllMatchesTeam(Integer teamId);
}
