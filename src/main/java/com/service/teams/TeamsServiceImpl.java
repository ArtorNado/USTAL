package com.service.teams;

import com.data.entity.Teams;
import com.data.entity.UserData;
import com.data.repository.TeamsRepository;
import com.data.repository.UserDataRepository;
import com.dto.TeamDto;
import com.response.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TeamsServiceImpl implements TeamsService {

    @Autowired
    TeamsRepository teamsRepository;

    @Autowired
    UserDataRepository userDataRepository;

    public Message createTeam(TeamDto teamDto){
        Optional<Teams> teamsFromDb = teamsRepository.findByTeamName(teamDto.getTeamName());
        if(teamsFromDb.isPresent()){
            return new Message("Команда с таким названием уже существует");
        }
        Teams newTeam = new Teams();
        newTeam.setTeamName(teamDto.getTeamName());
        newTeam.setTeamCity(teamDto.getTeamCity());
        newTeam.setCreatorId(teamDto.getCreatorId());
       /* newTeam.setUsers(new HashSet<UserData>(){{
            add(userDataRepository.findUserDataByUserId(teamDto.getCreatorId()));
        }});*/
        teamsRepository.save(newTeam);
        UserData user = userDataRepository.findUserDataByUserId(teamDto.getCreatorId());
        user.setTeam(newTeam);
        userDataRepository.save(user);
        return new Message("success");
    }

    @Override
    public List<UserData> getTeamPlayers(Integer teamId) {
        Optional<Teams> teamsFromDb = teamsRepository.findTeamsByTeamId(teamId);
        if(teamsFromDb.isPresent()){
            return userDataRepository.findUserDataByTeam(teamsFromDb.get());
        }else throw new AccessDeniedException("User not found");
    }

    @Override
    public List<Teams> getTeams() {
        Optional<List<Teams>> teamsFromDb = teamsRepository.getAll();
        if(teamsFromDb.isPresent()){
            return teamsFromDb.get();
        }else throw new AccessDeniedException("Teams not found");
    }

    @Override
    public List<Teams> getTeamsByName(String name) {
        Optional<List<Teams>> teamsFromDb = teamsRepository.findTeamsByTeamNameIgnoreCase(name);
        if(teamsFromDb.isPresent()){
            return teamsFromDb.get();
        }else throw new AccessDeniedException("Team not found");
    }

    @Override
    public List<Teams> getTeamsByTeamCity(String city) {
        Optional<List<Teams>> teamsFromDb = teamsRepository.findTeamsByTeamCityIgnoreCase(city);
        if(teamsFromDb.isPresent()){
            return teamsFromDb.get();
        }else throw new AccessDeniedException("Teams not found");
    }

    @Override
    public List<Teams> getTeamsByTeamStatus(String status) {
        Optional<List<Teams>> teamsFromDb = teamsRepository.findTeamsByTeamStatus(status);
        if(teamsFromDb.isPresent()){
            return teamsFromDb.get();
        }else throw new AccessDeniedException("Teams not found");
    }

    @Override
    public List<Teams> getTeamsByCityAndStatus(String city, String status) {
        return null;
    }
}
