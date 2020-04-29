package com.service.teams;

import com.models.Teams;
import com.models.UserData;
import com.repository.TeamsRepository;
import com.repository.UserDataRepository;
import com.dto.TeamDto;
import com.dto.MessageDto;
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

    public MessageDto createTeam(TeamDto teamDto){
        Optional<Teams> teamsFromDb = teamsRepository.findByTeamName(teamDto.getTeamName());
        if(teamsFromDb.isPresent()){
            return new MessageDto("Команда с таким названием уже существует");
        }
        Teams newTeam = new Teams();
        newTeam.setTeamName(teamDto.getTeamName());
        newTeam.setTeamCity(teamDto.getTeamCity());
        newTeam.setCreatorId(teamDto.getCreatorId());
       /* newTeam.setUsers(new HashSet<UserData>(){{
            add(userDataRepository.findUserDataByUserId(teamDto.getCreatorId()));
        }});*/
        teamsRepository.save(newTeam);
        Optional<UserData> user = userDataRepository.findUserDataByUserId(teamDto.getCreatorId());
        if (user.isPresent()) {
            user.get().setTeam(newTeam);
            userDataRepository.save(user.get());
        }
        return new MessageDto("success");
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
    public Teams getTeamById(Integer id) {
        Optional<Teams> teamsFromDb = teamsRepository.findTeamsByTeamId(id);
        if(teamsFromDb.isPresent()){
            return teamsFromDb.get();
        }else throw new AccessDeniedException("Team not found");
    }



    @Override
    public List<Teams> getTeamsByCityAndStatus(String city, String status) {
        return null;
    }
}
