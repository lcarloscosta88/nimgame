package com.holisticon.nimgame.repository;

import com.holisticon.nimgame.entity.Nim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NimRepository extends JpaRepository<Nim, Long> {
}
