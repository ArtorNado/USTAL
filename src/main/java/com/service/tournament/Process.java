package com.service.tournament;

import com.dto.TeamDto;
import com.models.Teams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/*@Service
public class Process {

    @Autowired
    TournamentRepository tournamentRepository;

    public Process() {

    }

    public void sort(ArrayList<Teams> teamsList) {
        teamsList.sort((o1, o2) -> {
            if (o1.getTeamId() == o2.getTeamId()) return 0;
            else if (o1.getTeamId() > o2.getTeamId()) return 1;
            else return -1;
        });
    }

    public void startTourmament(int idTourmament, int maxTeams, ArrayList<Teams> teamsList) {
        switch (maxTeams) {
            case 4:
                startTourmamentFor4(idTourmament, teamsList);
                break;
            case 8:
                startTourmamentFor8(idTourmament, teamsList);
                break;
        }
    }

    public void startTourmamentFor4(int idTourmament, ArrayList<Teams> teamsList) {
        for (int i = 0; i < 2; i++) {
            int firstTeam = 1;
            if (i == 1) firstTeam = 0;
            tournamentRepository.createMatch(idTourmament, 1, i + 1, teamsList.get(firstTeam).getTeamId(), teamsList.get(firstTeam + 2).getTeamId());
        }
    }

    public void startTourmamentFor8(int idTourmament, ArrayList<Teams> teamsList) {
        for (int i = 0; i < 2; i++) {
            int firstTeam = 1;
            if (i == 1) firstTeam = 0;
            tournamentRepository.createMatch(idTourmament, 1, i + 1, teamsList.get(firstTeam).getId(), teamsList.get(firstTeam + 2).getId());
        }
    }

    public void registrationMatch(int idTourmament, int previousRound, int prevMatchNumber, int winTeamId, int maxTeams) {
        upTeamRaiting(winTeamId, 1);
        int curMatchinPrevRound = (maxTeams / 2) / previousRound;
        switch (curMatchinPrevRound) {
            case 8:
                register8prevMatch(idTourmament, previousRound, prevMatchNumber, winTeamId, maxTeams);
                break;
            case 4:
                register4prevMatch(idTourmament, previousRound, prevMatchNumber, winTeamId, maxTeams);
                break;
            case 2:
                register2prevMatch(idTourmament, previousRound, prevMatchNumber, winTeamId, maxTeams);
                break;
            case 1:
                setWinTeam(winTeamId,idTourmament);
        }
    }

    public void register2prevMatch(int idTourmament, int previousRound, int prevMatchNumber, int winTeamId, int maxTeams) {
        int nextMatchNumber;
        switch (prevMatchNumber) {
            case (1):
                nextMatchNumber = prevMatchNumber + maxTeams / 2;
                createMatch(idTourmament, previousRound + 1, nextMatchNumber, winTeamId, 0);
                break;
            case (2):
                nextMatchNumber = prevMatchNumber + maxTeams / 2 / previousRound - 1;
                createMatch(idTourmament, previousRound + 1, nextMatchNumber, 0, winTeamId);
                break;
        }
    }

    public void register4prevMatch(int idTourmament, int previousRound, int prevMatchNumber, int winTeamId, int maxTeams) {
        int nextMatchNumber;
        switch (prevMatchNumber) {
            case (1):
                nextMatchNumber = prevMatchNumber + maxTeams / 2 / previousRound;
                createMatch(idTourmament, previousRound + 1, nextMatchNumber, winTeamId, 0);
                break;
            case (2):
                nextMatchNumber = prevMatchNumber + maxTeams / 2 / previousRound - 1;
                createMatch(idTourmament, previousRound + 1, nextMatchNumber, 0, winTeamId);
                break;
            case (3):
                nextMatchNumber = prevMatchNumber + maxTeams / 2 / previousRound - 1;
                createMatch(idTourmament, previousRound + 1, nextMatchNumber, winTeamId, 0);
                break;
            case (4):
                nextMatchNumber = prevMatchNumber + maxTeams / 2 / previousRound - 2;
                createMatch(idTourmament, previousRound + 1, nextMatchNumber, 0, winTeamId);
                break;
        }
    }

    public void register8prevMatch(int idTourmament, int previousRound, int prevMatchNumber, int winTeamId, int maxTeams) {
        int nextMatchNumber;
        switch (prevMatchNumber) {
            case (1):
                nextMatchNumber = prevMatchNumber + maxTeams / 2;
                createMatch(idTourmament, previousRound + 1, nextMatchNumber, winTeamId, 0);
                break;
            case (2):
                nextMatchNumber = prevMatchNumber + maxTeams / 2 / previousRound - 1;
                createMatch(idTourmament, previousRound + 1, nextMatchNumber, 0, winTeamId);
                break;
        }
    }

    public void createMatch(int idTourmament, int round, int matchNumber, int firstTeamId, int secondTeamId) {
        Boolean answer = tournamentRepository.checkMatch(idTourmament,matchNumber);
        if(answer){
            if(firstTeamId == 0) tournamentRepository.updateMatchSecondTeam(idTourmament,matchNumber,secondTeamId);
            else tournamentRepository.updateMatchFirstTeam(idTourmament,matchNumber,firstTeamId);
        } else {
            tournamentRepository.createMatch(idTourmament, round, matchNumber, firstTeamId, secondTeamId);
        }
    }

    public void setWinTeam(int teamId, int idTourmament){
        tournamentRepository.setWinTeam(teamId, idTourmament);
    }

    public void upTeamRaiting(int teamId, double points){
        tournamentRepository.setTeamRaiting(teamId, points);
    }
}*/

