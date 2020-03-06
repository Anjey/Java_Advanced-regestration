package ua.lviv.lgs.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.lviv.lgs.domain.Statement;

@Repository
public interface StatementRepository extends JpaRepository<Statement, Integer> {

}
