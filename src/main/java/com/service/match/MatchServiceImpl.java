package com.service.match;

import com.dto.MatchCommandDto;
import com.dto.MatchSingleDto;
import com.dto.MessageDto;
import com.dto.StatusDto;
import com.models.*;
import com.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.*;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
@Transactional
public class MatchServiceImpl implements MatchService {

    @Autowired
    MatchSingleRepository matchSingleRepository;

    @Autowired
    UserMatchRepository userMatchRepository;

    @Autowired
    MatchCommandRepository matchCommandRepository;

    @Autowired
    UserDataRepository userDataRepository;

    @Autowired
    TeamsRepository teamsRepository;

    @Autowired
    EndCommandMatchRepository endCommandMatchRepository;

    @Override
    public MessageDto createSingleMatch(MatchSingleDto matchSingle) {
        Optional<MatchSingle> matchFromDb = matchSingleRepository.findMatchSingleByCreatorIdAndAndDateAndTime(
                matchSingle.getCreatorId(), matchSingle.getDate(), matchSingle.getTime());
        if (!matchFromDb.isPresent()) {
            MatchSingle nm = new MatchSingle(matchSingle.getDate(), matchSingle.getTime(), matchSingle.getCreatorId(),
                    matchSingle.getNumberParticipant(), 1, matchSingle.getDescription(), matchSingle.getMatchCity());
            matchSingleRepository.save(nm);
            UserMatch num = new UserMatch(nm, matchSingle.getCreatorId(), "Admin");
            userMatchRepository.save(num);
            return new MessageDto("success");
        } else return new MessageDto("failed");
    }

    @Override
    public MessageDto endSingleMatch(Integer idSingleMatch) {
        matchSingleRepository.deleteMatchSingleByMatchId(idSingleMatch);
        userMatchRepository.deleteAllByMatchId(idSingleMatch);
        return new MessageDto("success");
    }

    @Override
    public MessageDto joinSingleMatch(Integer idSingleMatch, Integer participant) {
        Optional<MatchSingle> matchFromDb = matchSingleRepository.findMatchSingleByMatchId(idSingleMatch);
        if (matchFromDb.isPresent()) {
            System.out.println(idSingleMatch.toString());
            System.out.println(participant.toString());
            UserMatch um = new UserMatch(matchFromDb.get(), participant, "Participant");
            userMatchRepository.save(um);
            matchFromDb.get().setCurrentNumberParticipant(matchFromDb.get().getCurrentNumberParticipant() + 1);
            matchSingleRepository.save(matchFromDb.get());
            return new MessageDto("success");
        }
        return new MessageDto("failed");
    }

    @Override
    public List<MatchSingle> getAllMatch() {
        return matchSingleRepository.getAll().get();
    }

    @Override
    public List<MatchSingle> getAllMatchSingleByRole(Integer userId, String role) {
        Optional<List<UserMatch>> userDataFromDb = userMatchRepository.getUserMatchByUserIdAndRole(userId, role);
        if (userDataFromDb.isPresent()) {
            List<MatchSingle> list = new ArrayList<>();
            for (UserMatch um :
                    userDataFromDb.get()) {
                list.add(um.getMatchId());
            }
            return list;
        } else throw new NullPointerException("Elements not found");
    }


    @Override
    public MessageDto createCommandMatch(MatchCommandDto matchCommand) {
        Optional<MatchCommand> matchFromDb = matchCommandRepository.getMatchCommandByDateAndTimeAndMatchCityAndCreatorId(
                matchCommand.getDate(), matchCommand.getTime(), matchCommand.getMatchCity(), matchCommand.getCreatorId()
        );
        Optional<Teams> firstTeam = teamsRepository.findTeamsByCreatorId(matchCommand.getCreatorId());
        if (firstTeam.isPresent()) {
            if (!matchFromDb.isPresent()) {
                MatchCommand nm = new MatchCommand(matchCommand.getDate(), matchCommand.getTime(), firstTeam.get().getTeamId(),
                        firstTeam.get().getTeamId(), firstTeam.get().getTeamName(), null, null, matchCommand.getMatchCity(), matchCommand.getDescription());
                matchCommandRepository.save(nm);
                return new MessageDto("success");
            } else return new MessageDto("failed");
        } else throw new AccessDeniedException("You are not admin");
    }

    @Override
    public MessageDto joinCommandMatch(Integer idCommandMatch, Integer recipient) {
        Optional<MatchCommand> matchFromDb = matchCommandRepository.getMatchCommandByMatchId(idCommandMatch);
        Optional<Teams> secondTeam = teamsRepository.findTeamsByCreatorId(recipient);
        if (secondTeam.isPresent()) {
            if (matchFromDb.isPresent()) {
                if (matchFromDb.get().getSecondTeamId() == null) {
                    MatchCommand nm = matchFromDb.get();
                    nm.setSecondTeamId(secondTeam.get().getTeamId());
                    nm.setSecondTeamName(secondTeam.get().getTeamName());
                    matchCommandRepository.save(nm);
                    return new MessageDto("succeess");
                } else return new MessageDto("failed");
            } else return new MessageDto("failed");
        } else return new MessageDto("failed");
    }

    public MatchCommand getCommandMatchById(Integer id) {
        return matchCommandRepository.getMatchCommandByMatchId(id).get();
    }

    @Override
    public List<MatchCommand> getAllCommandMatch() {
        return matchCommandRepository.getAll().get();
    }

    @Override
    public List<MatchSingle> getSingleMatchByRole(Integer userId, String role) {
        List<MatchSingle> listM = new ArrayList<>();
        System.out.println("ROLE" + role);
        if (role.equals("Free")) {
            System.out.println("ROLE" + role);
            return getSingleMatchWithoutRole(userId);
        } else {
            Optional<List<UserMatch>> list = userMatchRepository.getUserMatchByUserIdAndRole(userId, role);
            if (list.isPresent()) {
                for (UserMatch um :
                        list.get()) {
                    listM.add(um.getMatchId());
                }
                return listM;
            } else return listM;
        }
    }

    @Override
    public MatchSingle findMatchSingleById(Integer matchId) {
        Optional<MatchSingle> matchFromDb = matchSingleRepository.findMatchSingleByMatchId(matchId);
        if (matchFromDb.isPresent()) return matchFromDb.get();
        else throw new IllegalArgumentException("Match not found");
    }

    @Override
    public List<UserData> getMatchParticipant(Integer matchId) {
        List<UserData> usersData = new ArrayList<>();
        Optional<MatchSingle> mfd = matchSingleRepository.findMatchSingleByMatchId(matchId);
        Optional<List<UserMatch>> users = userMatchRepository.getUserMatchByMatchId(mfd.get());
        if (users.isPresent()) {
            for (UserMatch um :
                    users.get()) {
                usersData.add(userDataRepository.findUserDataByUserId(um.getUserId()).get());
            }
            return usersData;
        } else return usersData;
    }


    private List<MatchSingle> getSingleMatchWithoutRole(Integer userId) {
        Optional<List<UserMatch>> listWR = userMatchRepository.getUserMatchWithoutRole(userId);
        List<MatchSingle> lwr = new ArrayList<>();
        List<MatchSingle> listAll = userMatchRepository.getAll();
        List<MatchSingle> lms = listAll;
        for (MatchSingle m :
                lms) {
            if (m.getNumberParticipant() == m.getCurrentNumberParticipant()) {
                lms.remove(m);
            }
        }
        if (listWR.isPresent()) {
            for (UserMatch um :
                    listWR.get()) {
                lwr.add(um.getMatchId());
            }
            lms.removeAll(lwr);
            return lms;
        } else return lms;
    }

    @Override
    public StatusDto determineUserStatusInMatch(Integer matchId, Integer userId) {
        Optional<MatchSingle> mfd = matchSingleRepository.findMatchSingleByMatchId(matchId);
        Optional<UserMatch> user = userMatchRepository.getUserMatchByMatchIdAndUserId(mfd.get(), userId);
        if (user.isPresent()) {
            if (mfd.get().getCreatorId() == user.get().getUserId()) {
                return new StatusDto("Admin");
            } else return new StatusDto("Participant");
        } else return new StatusDto("Undefined");
    }

    @Override
    public List<MatchCommand> getCommandMatchByRole(Integer userId, String role) {
        Optional<UserData> userDataFromDb = userDataRepository.findUserDataByUserId(userId);
        List<MatchCommand> list = new ArrayList<>();
        if (userDataFromDb.isPresent()) {
            switch (role) {
                case "Admin": {
                    if (userDataFromDb.get().getTeam() == null) {
                        return list;
                    } else {
                        if (userDataFromDb.get().getTeam().getCreatorId() == userId) {
                            return matchCommandRepository.getMatchbyRoleAdmin(userDataFromDb.get().getTeam().getTeamId());
                        } else {
                            return list;
                        }
                    }
                }
                case "Participant": {
                    if (userDataFromDb.get().getTeam() == null) {
                        return list;
                    } else {
                        return matchCommandRepository.getMatchBRole(userDataFromDb.get().getTeam().getTeamId());
                    }
                }
                case "Free": {
                    return getAllCommandMatchFree(userId);
                }
            }
        } else return list;
        return list;
    }

    @Override
    public List<MatchCommand> getCommandMatchByRoleAndCity(Integer userId, String role, String city) {
        Optional<UserData> userDataFromDb = userDataRepository.findUserDataByUserId(userId);
        List<MatchCommand> list = new ArrayList<>();
        if (userDataFromDb.isPresent()) {
            switch (role) {
                case "Admin": {
                    if (userDataFromDb.get().getTeam() == null) {
                        return list;
                    } else {
                        if (userDataFromDb.get().getTeam().getCreatorId() == userId) {
                            return matchCommandRepository.getMatchbyRoleAdminAndCity(userDataFromDb.get().getTeam().getTeamId(), city);
                        } else {
                            return list;
                        }
                    }
                }
                case "Participant": {
                    if (userDataFromDb.get().getTeam() == null) {
                        return list;
                    } else {
                        return matchCommandRepository.getMatchBRoleAndCity(userDataFromDb.get().getTeam().getTeamId(), city);
                    }
                }
                case "Free": {
                    return getAllCommandMatchFreeAndCity(userId, city);
                }
            }
        } else return list;
        return list;
    }

    @Override
    public List<MatchCommand> getAllCommandMatchByCity(String city) {
        List<MatchCommand> list = matchCommandRepository.getMatchCommandByMatchCity(city);
        return list;
    }

    public List<MatchCommand> getAllCommandMatchFree(Integer userId){
        Optional<UserData> user = userDataRepository.findUserDataByUserId(userId);
        List<MatchCommand> list = new ArrayList<>();
        if(user.isPresent()) {
            List<MatchCommand> matchFromDb = matchCommandRepository.getMatchFree(user.get().getTeam().getTeamId());
            return matchFromDb;
        } else return list;
    }

    public List<MatchCommand> getAllCommandMatchFreeAndCity(Integer userId, String city){
        Optional<UserData> user = userDataRepository.findUserDataByUserId(userId);
        List<MatchCommand> list = new ArrayList<>();
        if(user.isPresent()) {
            List<MatchCommand> matchFromDb = matchCommandRepository.getMatchFreeAndCity(user.get().getTeam().getTeamId(), city);
            return matchFromDb;
        } else return list;
    }

    public MessageDto endCommandMatch(Integer matchId, Integer firstTeamScore, Integer secondTeamsScore) {
        Optional<MatchCommand> matchFromDb = matchCommandRepository.getMatchCommandByMatchId(matchId);
        Teams winTeam = new Teams();
        if (matchFromDb.isPresent()) {
            Optional<Teams> firstTeamFromDb = teamsRepository.findTeamsByTeamId(matchFromDb.get().getFirstTeamId());
            Optional<Teams> secondTeamFromDb = teamsRepository.findTeamsByTeamId(matchFromDb.get().getSecondTeamId());
            if (firstTeamFromDb.isPresent() && secondTeamFromDb.isPresent()) {
                if (firstTeamScore > secondTeamsScore) {
                    winTeam = firstTeamFromDb.get();
                } else if (secondTeamsScore > firstTeamScore) {
                    winTeam = secondTeamFromDb.get();
                } else {
                    winTeam = null;
                }
                EndedCommandMatch endedCommandMatch = new EndedCommandMatch(matchFromDb.get().getMatchId(), matchFromDb.get().getDate(),
                        winTeam.getTeamId(), firstTeamScore, secondTeamsScore, firstTeamFromDb.get(), secondTeamFromDb.get());
                endCommandMatchRepository.save(endedCommandMatch);
                matchCommandRepository.delete(matchFromDb.get());
                Optional<EndedCommandMatch> check = endCommandMatchRepository.findById(endedCommandMatch.getMatchId());
                return new MessageDto("success");
            } else throw new IllegalArgumentException("Teams not found");
        } else throw new IllegalArgumentException("Match not found");
    }

    @Override
    public List<EndedCommandMatch> getEndedCommandMatch(Integer teamId) {
        Optional<Teams> teamfromDb = teamsRepository.findTeamsByTeamId(teamId);
        Optional<List<EndedCommandMatch>> matchfromDb = endCommandMatchRepository.getEndedCommandBM(teamfromDb.get());
        System.out.println(matchfromDb.get().toString());
        List<EndedCommandMatch> list = new ArrayList<>();
        if (matchfromDb.isPresent()) return matchfromDb.get();
        else return list;
    }

    @Override
    public List<MatchCommand> getAllMatchesTeam(Integer teamId){
        List<MatchCommand> matchesFromDb = matchCommandRepository.getAllTeamCommandMatches(teamId);
        return matchesFromDb;
    }


}
