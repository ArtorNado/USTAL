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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        System.out.println("1");
        Optional<MatchSingle> matchFromDb = matchSingleRepository.findMatchSingleByCreatorIdAndDateAndTime(
                matchSingle.getCreatorId(), matchSingle.getDate(), matchSingle.getTime());
        if (!matchFromDb.isPresent()) {
            System.out.println("2");
            MatchSingle nm = new MatchSingle(matchSingle.getDate(), matchSingle.getTime(), matchSingle.getCreatorId(),
                    matchSingle.getNumberParticipant(), 1, matchSingle.getDescription(), matchSingle.getMatchCity());
            matchSingleRepository.save(nm);
            System.out.println("3");
            UserMatch1 num = new UserMatch1(null, nm, matchSingle.getCreatorId().toString(), "Admin");
            System.out.println("4");
            System.out.println(num.toString());
            userMatchRepository.save(num);
            return new MessageDto("success");
        } else return new MessageDto("failed");
    }

    @Override
    public MessageDto endSingleMatch(Integer idSingleMatch) {
        Optional<MatchSingle> matchFromDb = matchSingleRepository.findMatchSingleByMatchId(idSingleMatch);
        if (matchFromDb.isPresent()) {
            /*matchSingleRepository.deleteMatchSingleByMatchId(idSingleMatch);*/
            userMatchRepository.deleteAllByMatchId(matchFromDb.get());
        }
        return new MessageDto("success");
    }

    @Override
    public MessageDto joinSingleMatch(Integer idSingleMatch, Integer participant) {
        Optional<MatchSingle> matchFromDb = matchSingleRepository.findMatchSingleByMatchId(idSingleMatch);
        if (matchFromDb.isPresent()) {
            System.out.println(idSingleMatch.toString());
            System.out.println(participant.toString());
            UserMatch1 um = new UserMatch1(null, matchFromDb.get(), participant.toString(), "Participant");
            System.out.println(um.toString());
            userMatchRepository.save(um);
            matchFromDb.get().setCurrentNumberParticipant(matchFromDb.get().getCurrentNumberParticipant() + 1);
            matchSingleRepository.save(matchFromDb.get());
            return new MessageDto("success");
        }
        return new MessageDto("failed");
    }

    @Override
    public List<MatchSingle> getAllMatch() {
        return sortSingle(matchSingleRepository.getAll().get());
    }

    private List<MatchSingle> sortSingle(List<MatchSingle> list) {
        DateFormat format = new SimpleDateFormat("dd.MM.yy'T'HH:mm");
        if (list.size() != 0) {
            list.sort((o1, o2) -> {
                try {
                    return format.parse(o1.getDate() + "T" + o1.getTime()).compareTo(format.parse(o2.getDate() + "T" + o2.getTime()));
                } catch (ParseException e) {
                    return 5;
                }
            });
        }
        return list;
/*
        System.out.println(format.parse(list.get(0).getDate() +"T"+list.get(0).getTime()));
*/
    }

    private List<MatchCommand> sortCommand(List<MatchCommand> list) {
        DateFormat format = new SimpleDateFormat("dd.MM.yy'T'HH:mm");
        if (list.size() != 0) {
            list.sort((o1, o2) -> {
                try {
                    return format.parse(o1.getDate() + "T" + o1.getTime()).compareTo(format.parse(o2.getDate() + "T" + o2.getTime()));
                } catch (ParseException e) {
                    return 5;
                }
            });
        }
        return list;
/*
        System.out.println(format.parse(list.get(0).getDate() +"T"+list.get(0).getTime()));
*/
    }

    @Override
    public List<MatchSingle> getAllSingleMatchByCity(String city) {
        return sortSingle(matchSingleRepository.findMatchSingleByMatchCity(city));
    }

    @Override
    public List<MatchSingle> getAllMatchSingleByRole(Integer userId, String role) {
        Optional<List<UserMatch1>> userDataFromDb = userMatchRepository.getUserMatch1ByUserIdAndRole(userId.toString(), role);
        if (userDataFromDb.isPresent()) {
            List<MatchSingle> list = new ArrayList<>();
            for (UserMatch1 um :
                    userDataFromDb.get()) {
                list.add(um.getMatchId());
            }
            return sortSingle(list);
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
        List<MatchCommand> mac = new ArrayList<>();
        Optional<List<MatchCommand>> mc = matchCommandRepository.getAll();
        if (mc.isPresent()) {
            return sortCommand(matchCommandRepository.getAll().get());
        } else return mac;
    }

    @Override
    public List<MatchSingle> getSingleMatchByRole(Integer userId, String role) {
        List<MatchSingle> listM = new ArrayList<>();
        if (role.equals("Free")) {
            System.out.println("ROLE" + role);
            return sortSingle(getSingleMatchWithoutRole(userId));
        } else {
            System.out.println(" STEP! ASDASD");
            Optional<List<UserMatch1>> list;
            if (role.equals("Admin")) {
                list = userMatchRepository.getUserMatch1ByUserIdAndRole(userId.toString(), role);
            } else {
                list = userMatchRepository.getUserMatchByUserIdAndRole2(userId.toString());
            }
            /*Optional<List<UserMatch1>> list = userMatchRepository.getUserMatchByUserIdAndRole(userId, role);*/
            if (list.isPresent()) {
                for (UserMatch1 um :
                        list.get()) {
                    listM.add(um.getMatchId());
                }
                return sortSingle(listM);
            } else return listM;
        }
    }

    @Override
    public List<MatchSingle> getSingleMatchByRoleAndCity(Integer userId, String role, String city) {
        List<MatchSingle> listM = new ArrayList<>();
        if (role.equals("Free")) {
            return sortSingle(getSingleMatchWithoutRoleByCity(userId, city));
        } else {
            Optional<List<UserMatch1>> list = userMatchRepository.getUserMatch1ByUserIdAndRole(userId.toString(), role);
            if (list.isPresent()) {
                for (UserMatch1 um :
                        list.get()) {
                    if (um.getMatchId().getMatchCity().equals(city)) {
                        listM.add(um.getMatchId());
                    }
                }
                return sortSingle(listM);
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
        Optional<List<UserMatch1>> users = userMatchRepository.getUserMatchByMatchId(mfd.get());
        if (users.isPresent()) {
            for (UserMatch1 um :
                    users.get()) {
                usersData.add(userDataRepository.findUserDataByUserId(Integer.parseInt(um.getUserId())).get());
            }
            return usersData;
        } else return usersData;
    }


    private List<MatchSingle> getSingleMatchWithoutRole(Integer userId) {
        Optional<List<UserMatch1>> listWR = userMatchRepository.getUserMatchWithoutRole(userId.toString());
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
            for (UserMatch1 um :
                    listWR.get()) {
                lwr.add(um.getMatchId());
            }
            lms.removeAll(lwr);
            return sortSingle(lms);
        } else return lms;
    }

    private List<MatchSingle> getSingleMatchWithoutRoleByCity(Integer userId, String city) {
        Optional<List<UserMatch1>> listWR = userMatchRepository.getUserMatchWithoutRole(userId.toString());
        List<MatchSingle> lwr = new ArrayList<>();
        List<MatchSingle> listAll = userMatchRepository.getAll();
        List<MatchSingle> lms = listAll;
        if (lms != null) {
            for (MatchSingle m :
                    lms) {
                if ((m.getNumberParticipant() == m.getCurrentNumberParticipant()) || (!city.equals(m.getMatchCity()))) {
                    lms.remove(m);
                }
            }
            if (listWR.isPresent()) {
                for (UserMatch1 um :
                        listWR.get()) {
                    lwr.add(um.getMatchId());
                }
                lms.removeAll(lwr);
                return sortSingle(lms);
            } else return lms;
        } else return lwr;
    }

    @Override
    public StatusDto determineUserStatusInMatch(Integer matchId, Integer userId) {
        Optional<MatchSingle> mfd = matchSingleRepository.findMatchSingleByMatchId(matchId);
        Optional<UserMatch1> user = userMatchRepository.getUserMatchByMatchIdAndUserId(mfd.get(), userId.toString());
        if (user.isPresent()) {
            if (mfd.get().getCreatorId() == Integer.parseInt(user.get().getUserId())) {
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
                            return sortCommand(matchCommandRepository.getMatchbyRoleAdmin(userDataFromDb.get().getTeam().getTeamId()));
                        } else {
                            return list;
                        }
                    }
                }
                case "Participant": {
                    if (userDataFromDb.get().getTeam() == null) {
                        return list;
                    } else {
                        return sortCommand(matchCommandRepository.getMatchBRole(userDataFromDb.get().getTeam().getTeamId()));
                    }
                }
                case "Free": {
                    return sortCommand(getAllCommandMatchFree(userId));
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
                            return sortCommand(matchCommandRepository.getMatchbyRoleAdminAndCity(userDataFromDb.get().getTeam().getTeamId(), city));
                        } else {
                            return list;
                        }
                    }
                }
                case "Participant": {
                    if (userDataFromDb.get().getTeam() == null) {
                        return list;
                    } else {
                        return sortCommand(matchCommandRepository.getMatchBRoleAndCity(userDataFromDb.get().getTeam().getTeamId(), city));
                    }
                }
                case "Free": {
                    return sortCommand(getAllCommandMatchFreeAndCity(userId, city));
                }
            }
        } else return list;
        return list;
    }

    @Override
    public List<MatchCommand> getAllCommandMatchByCity(String city) {
        List<MatchCommand> list = matchCommandRepository.getMatchCommandByMatchCity(city);
        return sortCommand(list);
    }

    public List<MatchCommand> getAllCommandMatchFree(Integer userId) {
        Optional<UserData> user = userDataRepository.findUserDataByUserId(userId);
        List<MatchCommand> list = new ArrayList<>();
        if (user.isPresent()) {
            List<MatchCommand> matchFromDb = matchCommandRepository.getMatchFree(user.get().getTeam().getTeamId());
            return sortCommand(matchFromDb);
        } else return list;
    }

    public List<MatchCommand> getAllCommandMatchFreeAndCity(Integer userId, String city) {
        Optional<UserData> user = userDataRepository.findUserDataByUserId(userId);
        List<MatchCommand> list = new ArrayList<>();
        if (user.isPresent()) {
            List<MatchCommand> matchFromDb = matchCommandRepository.getMatchFreeAndCity(user.get().getTeam().getTeamId(), city);
            return sortCommand(matchFromDb);
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
            } else {
                matchCommandRepository.delete(matchFromDb.get());
                return new MessageDto("success");
            }
        } else throw new IllegalArgumentException("Match not found");
    }

    @Override
    public List<EndedCommandMatch> getEndedCommandMatch(Integer teamId) {
        Optional<Teams> teamfromDb = teamsRepository.findTeamsByTeamId(teamId);
        Optional<List<EndedCommandMatch>> matchfromDb = endCommandMatchRepository.getEndedCommandBM(teamfromDb.get());
        List<EndedCommandMatch> list = new ArrayList<>();
        if (matchfromDb.isPresent()) return matchfromDb.get();
        else return list;
    }

    @Override
    public List<MatchCommand> getAllMatchesTeam(Integer teamId) {
        List<MatchCommand> matchesFromDb = matchCommandRepository.getAllTeamCommandMatches(teamId);
        return sortCommand(matchesFromDb);
    }

    @Override
    public void deleteAll() {
      /*  matchSingleRepository.deleteAll();
        userMatchRepository.deleteAll();
        matchCommandRepository.deleteAll();*/
    }


}
