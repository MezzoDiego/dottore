package it.prova.dottore.repository;

import org.springframework.data.repository.CrudRepository;

import it.prova.dottore.model.Dottore;

public interface DottoreRepository extends CrudRepository<Dottore, Long> {

}
