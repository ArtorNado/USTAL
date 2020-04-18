package com.service.teams;

import com.data.entity.UserData;
import com.dto.TeamDto;
import com.response.Message;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TeamsService {
    Message createTeam(TeamDto teamDto);

    List<UserData> getTeamPlayers(Integer teamId);
}
