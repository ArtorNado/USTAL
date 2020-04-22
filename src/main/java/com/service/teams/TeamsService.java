package com.service.teams;

import com.data.entity.Teams;
import com.data.entity.UserData;
import com.dto.TeamDto;
import com.response.Message;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TeamsService {
    Message createTeam(TeamDto teamDto);

    List<UserData> getTeamPlayers(Integer teamId);

    List<Teams> getTeams();

    List<Teams> getTeamsByTeamCity(String city);

    List<Teams> getTeamsByTeamStatus(String status);

}
