package com.service.teams;

import com.models.Teams;
import com.models.UserData;
import com.dto.TeamDto;
import com.dto.MessageDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TeamsService {
    MessageDto createTeam(TeamDto teamDto);

    Teams getTeamById(Integer id);

    List<UserData> getTeamPlayers(Integer teamId);

    List<Teams> getTeams();

    List<Teams> getTeamsByName(String name);

    List<Teams> getTeamsByTeamCity(String city);

    List<Teams> getTeamsByTeamStatus(String status);

    List<Teams> getTeamsByCityAndStatus(String city, String status);

}
