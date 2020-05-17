package com.service.match;

import com.dto.MatchCommandDto;
import com.dto.MatchSingleDto;
import com.dto.MessageDto;
import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import com.models.MatchCommand;
import com.models.MatchSingle;
import com.models.UserData;
import com.models.UserMatch;
import com.repository.MatchCommandRepository;
import com.repository.MatchSingleRepository;
import com.repository.UserMatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        if (!matchFromDb.isPresent()) {
            MatchCommand nm = new MatchCommand(matchCommand.getDate(), matchCommand.getTime(), matchCommand.getCreatorId(),
                    matchCommand.getCreatorId(), 0, matchCommand.getMatchCity(), matchCommand.getDescription());
            matchCommandRepository.save(nm);
            return new MessageDto("success");
        } else return new MessageDto("failed");
    }

    @Override
    public MessageDto joinCommandMatch(Integer idCommandMatch, Integer recipient) {
        Optional<MatchCommand> matchFromDb = matchCommandRepository.getMatchCommandByMatchId(idCommandMatch);
        if (matchFromDb.isPresent()) {
            if (matchFromDb.get().getSecondTeamId() == 0) {
                MatchCommand nm = matchFromDb.get();
                nm.setSecondTeamId(recipient);
                matchCommandRepository.save(nm);
                return new MessageDto("succeess");
            } else return new MessageDto("failed");

        } else return new MessageDto("failed");

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

    private List<MatchSingle> getSingleMatchWithoutRole(Integer userId) {
        Optional<List<UserMatch>> listWR = userMatchRepository.getUserMatchWithoutRole(userId);
        List<MatchSingle> lwr = new ArrayList<>();
        List<MatchSingle> listAll = userMatchRepository.getAll();
        List<MatchSingle> lms = listAll;
        if (listWR.isPresent()) {
            for (UserMatch um :
                    listWR.get()) {
                lwr.add(um.getMatchId());
            }
            if (listWR.isPresent()) {
                lms.removeAll(lwr);
                return lms;
            } else return lwr;
        } else return lwr;
    }
}
