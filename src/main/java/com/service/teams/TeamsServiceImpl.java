package com.service.teams;

import static com.constants.Constants.*;

import com.aspect.LogExecutionTime;
import com.dto.StatusDto;
import com.dto.UserTeamStatus;
import com.models.Teams;
import com.models.UserData;
import com.repository.TeamsRepository;
import com.repository.UserDataRepository;
import com.dto.TeamDto;
import com.dto.MessageDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.xml.ws.http.HTTPException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Scope(scopeName = "tenant")
public class TeamsServiceImpl implements TeamsService {

    @Autowired
    TeamsRepository teamsRepository;

    @Autowired
    UserDataRepository userDataRepository;

    @LogExecutionTime
    @ApplicationScope
    public MessageDto createTeam(TeamDto teamDto) {
        Optional<Teams> teamsFromDb = teamsRepository.findByTeamName(teamDto.getTeamName());
        if (teamsFromDb.isPresent()) {
            throw new IllegalArgumentException("Команда с таким именем уже сущствует");
        }
        Optional<UserData> senderFromDb = userDataRepository.findUserDataByUserId(teamDto.getCreatorId());
        if (senderFromDb.isPresent()) {
            if (senderFromDb.get().getTeam() != null) throw new AccessDeniedException("У вас уже есть команда");
        } else throw new IllegalArgumentException("Ошибка серверва");
        Teams newTeam = new Teams();
        newTeam.setTeamName(teamDto.getTeamName());
        newTeam.setTeamCity(teamDto.getTeamCity());
        newTeam.setCreatorId(teamDto.getCreatorId());
       /* newTeam.setUsers(new HashSet<UserData>(){{
            add(userDataRepository.findUserDataByUserId(teamDto.getCreatorId()));
        }});*/
        teamsRepository.save(newTeam);
        senderFromDb.get().setTeam(newTeam);
        userDataRepository.save(senderFromDb.get());
        return new MessageDto("success");
    }

    @Override
    @LogExecutionTime
    public List<UserData> getTeamPlayers(Integer teamId) {
        Optional<Teams> teamsFromDb = teamsRepository.findTeamsByTeamId(teamId);
        if (teamsFromDb.isPresent()) {
            return userDataRepository.findUserDataByTeam(teamsFromDb.get());
        } else throw new AccessDeniedException("User not found");
    }

    @Override
    @LogExecutionTime
    public List<Teams> getTeams() {
        Optional<List<Teams>> teamsFromDb = teamsRepository.getAll();
        if (teamsFromDb.isPresent()) {
            return teamsFromDb.get();
        } else throw new AccessDeniedException("Teams not found");
    }

    @Override
    @LogExecutionTime
    public List<Teams> getTeamsByName(String name) {
        Optional<List<Teams>> teamsFromDb = teamsRepository.findTeamsByTeamNameIgnoreCase(name);
        if (teamsFromDb.isPresent()) {
            return teamsFromDb.get();
        } else throw new AccessDeniedException("Team not found");
    }

    @Override
    @LogExecutionTime
    public List<Teams> getTeamsByTeamCity(String city) {
        Optional<List<Teams>> teamsFromDb = teamsRepository.findTeamsByTeamCityIgnoreCase(city);
        if (teamsFromDb.isPresent()) {
            return teamsFromDb.get();
        } else throw new AccessDeniedException("Teams not found");
    }

    @Override
    @LogExecutionTime
    public List<Teams> getTeamsByTeamStatus(String status) {
        Optional<List<Teams>> teamsFromDb = teamsRepository.findTeamsByTeamStatus(status);
        if (teamsFromDb.isPresent()) {
            return teamsFromDb.get();
        } else throw new AccessDeniedException("Teams not found");
    }

    @Override
    @LogExecutionTime
    public Teams getTeamById(Integer id) {
        Optional<Teams> teamsFromDb = teamsRepository.findTeamsByTeamId(id);
        if (teamsFromDb.isPresent()) {
            return teamsFromDb.get();
        } else throw new AccessDeniedException("Team not found");
    }

    @Override
    @LogExecutionTime
    public StatusDto determineUserStatusInTeam(Integer userId, Integer teamId) {
        Optional<UserData> userFromDb = userDataRepository.findUserDataByUserId(userId);
        if (userFromDb.isPresent()) {
            if (userFromDb.get().getTeam() != null) {
                Teams userTeam = userFromDb.get().getTeam();
                if (userTeam.getTeamId().equals(teamId)) {
                    System.out.println(userTeam.getCreatorId().toString() + " - 1");
                    System.out.println(userId + " - 2");
                    if (userTeam.getCreatorId().equals(userId)) {
                        return new StatusDto(ADMIN_STATUS);
                    } else return new StatusDto(PARTICIPANT_STATUS);
                } else return new StatusDto(USER_STATUS);
            } else return new StatusDto(USER_STATUS);
        } else throw new AccessDeniedException("Team not found");
    }

    public UserTeamStatus determineUserStatus(Integer userId){
        Optional<UserData> userFromDb = userDataRepository.findUserDataByUserId(userId);
        if(userFromDb.get().getTeam() != null){
            if(userFromDb.get().getTeam().getCreatorId().equals(userId)){
                return new UserTeamStatus(userId, userFromDb.get().getTeam().getTeamId(), "Admin");
            } else return new UserTeamStatus(userId, userFromDb.get().getTeam().getTeamId(), "Participant");
        } else return new UserTeamStatus(userId, 0, "Undefined");
    }

    @Override
    @LogExecutionTime
    public List<Teams> getTeamsByCityAndStatus(String city, String status) {
        return null;
    }
}
