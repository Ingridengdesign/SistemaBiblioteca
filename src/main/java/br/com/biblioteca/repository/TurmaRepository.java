package br.com.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.biblioteca.entity.Turma;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Integer>{

}
