package com.service.match;

import com.dto.MessageDto;
import com.models.MatchSingle;
import com.models.UserMatch;
import com.repository.MatchSingleRepository;
import com.repository.UserMatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
@Transactional
public class MatchServiceImpl implements MatchService {

    @Autowired
    MatchSingleRepository matchSingleRepository;

    @Autowired
    UserMatchRepository userMatchRepository;

    @Override
    public MessageDto createSingleMatch(MatchSingle matchSingle) {
        matchSingle.setCurrentNumberParticipant(matchSingle.getCurrentNumberParticipant() + 1);
        matchSingleRepository.save(matchSingle);
        Optional<MatchSingle> matchFromDb = matchSingleRepository.find(matchSingle);
        matchFromDb.ifPresent(matchSingle1 -> userMatchRepository.save(new UserMatch(matchFromDb.get().getMatchId(),
                matchSingle.getCreatorId())));
        return new MessageDto("success");
    }

    @Override
    public MessageDto endSingleMatch(Integer idSingleMatch) {
        matchSingleRepository.deleteMatchSingleByMatchId(idSingleMatch);
        userMatchRepository.deleteAllByMatchId(idSingleMatch);
        return new MessageDto("success");
    }

    @Override
    public MessageDto joinSingleMatch(Integer idSingleMatch, Integer recipient) {
        Optional<MatchSingle> matchFromDb = matchSingleRepository.findMatchSingleByMatchId(idSingleMatch);
        if (matchFromDb.isPresent()) {
            System.out.println(idSingleMatch.toString());
            System.out.println(recipient.toString());
            UserMatch um = new UserMatch(matchFromDb.get().getMatchId(), recipient);
            userMatchRepository.save(um);
            return new MessageDto("success");
        }
        return new MessageDto("failed");
    }

    @Override
    public List<MatchSingle> getAllMatch() {
        return matchSingleRepository.getAll().get();
    }


}
