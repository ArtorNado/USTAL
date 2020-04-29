package com.repository;

import com.models.Teams;
import com.models.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserDataRepository extends JpaRepository<UserData, Integer> {

    Optional<UserData> findUserDataByUserId(Integer userId);

    List<UserData> findUserDataByTeam(Teams team);
}
