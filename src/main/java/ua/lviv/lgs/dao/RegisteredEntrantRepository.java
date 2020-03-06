package ua.lviv.lgs.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.lviv.lgs.domain.RegisteredEntrant;

public interface RegisteredEntrantRepository extends JpaRepository<RegisteredEntrant, Integer> {

}
