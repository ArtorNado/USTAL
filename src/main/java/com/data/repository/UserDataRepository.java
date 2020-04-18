package com.data.repository;

import com.data.entity.Teams;
import com.data.entity.User;
import com.data.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserDataRepository extends JpaRepository<UserData, Integer> {
    UserData findUserDataByUserId(Integer userId);

    List<UserData> findUserDataByTeam(Teams team);
}
