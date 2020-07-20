package it.polito.ezgas.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.polito.ezgas.entity.GasStation;

public interface GasStationRepository extends CrudRepository<GasStation, Integer> {

	List<GasStation> findByCarSharing(String carSharing);
	List<GasStation> findByReportUser(Integer userId);
	List<GasStation> findByHasDiesel(Boolean hasDiesel);
	List<GasStation> findByHasSuper(Boolean hasSuper);
	List<GasStation> findByHasSuperPlus(Boolean hasSuperPlus);
	List<GasStation> findByHasGas(Boolean hasGas);
	List<GasStation> findByHasMethane(Boolean hasMethane);
	List<GasStation> findByHasPremiumDiesel(Boolean hasPremiumDiesel);

	
	
	
}
