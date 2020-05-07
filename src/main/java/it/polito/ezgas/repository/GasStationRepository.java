package it.polito.ezgas.repository;

import org.springframework.data.repository.CrudRepository;

import it.polito.ezgas.entity.GasStation;

public interface GasStationRepository extends CrudRepository<GasStation, Integer> {

}
