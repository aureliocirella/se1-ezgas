package it.polito.ezgas.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.polito.ezgas.entity.GasStation;

public interface GasStationRepository extends CrudRepository<GasStation, Integer> {

	List<GasStation> findByCarSharing(String carSharing);
	List<GasStation> findByHasDieselOrHasSuperOrHasSuperPlusOrHasGasOrHasMethane( boolean hasdiesel , boolean hasuper, 
               boolean hassuperplus,boolean hasgas, boolean hasmethane);
}
