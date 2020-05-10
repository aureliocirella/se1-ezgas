package it.polito.ezgas.service.impl;

import java.util.ArrayList;
import java.util.List;
 

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exception.GPSDataException;
import exception.InvalidGasStationException;
import exception.InvalidGasTypeException;
import exception.InvalidUserException;
import exception.PriceException;
import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.entity.GasStation;
import it.polito.ezgas.repository.GasStationRepository;
import it.polito.ezgas.repository.UserRepository;
import it.polito.ezgas.service.GasStationService;
import javassist.bytecode.Descriptor.Iterator;

/**
 * Created by softeng on 27/4/2020.
 */
@Service
public class GasStationServiceimpl implements GasStationService {

	@Autowired 
	GasStationRepository gasStationRepository; 
	
	@Autowired
	UserRepository userRepository; 

	ModelMapper modelMapper = new ModelMapper(); 
	@Override
	public GasStationDto getGasStationById(Integer gasStationId) throws InvalidGasStationException {
		GasStation gasStation = gasStationRepository.findOne(gasStationId);		
		
		if(gasStation != null)
			return modelMapper.map(gasStation, GasStationDto.class);
		else		
			return null;
	}
	@Override
	public GasStationDto saveGasStation(GasStationDto gasStationDto) throws PriceException, GPSDataException {
		
		gasStationRepository.save(modelMapper.map(gasStationDto, GasStation.class)); 
		return gasStationDto;
	}

	@Override
	public List<GasStationDto> getAllGasStations() {
		
		
		List<GasStation> listEntity= (List<GasStation>)gasStationRepository.findAll();
	    List<GasStationDto> gasStationDtoList =  new ArrayList<GasStationDto>();
		
		listEntity.forEach((gs)->{gasStationDtoList.add(modelMapper.map(gs, GasStationDto.class));});
		return gasStationDtoList;
	}

	@Override
	public Boolean deleteGasStation(Integer gasStationId) throws InvalidGasStationException {
		if(  gasStationRepository.exists(gasStationId)) {
			gasStationRepository.delete(gasStationId);			
			  return true;
		}
		else
	 
		return false;
		
	}

	@Override
	public List<GasStationDto> getGasStationsByGasolineType(String gasolinetype) throws InvalidGasTypeException {
		
		boolean hasDiesel =(gasolinetype.contains("Disel"))?true:false;
		boolean hasSuper=(gasolinetype.contains("Super"))?true:false; 
        boolean hasSuperPlus=(gasolinetype.contains("SuperPlus"))?true:false;
        boolean hasGas=(gasolinetype.contains("Gas"))?true:false; 
        boolean hasMethane=(gasolinetype.contains("Methane"))?true:false;
 
	 List<GasStation> gasStationList = (List<GasStation>) gasStationRepository.findByHasDieselOrHasSuperOrHasSuperPlusOrHasGasOrHasMethane( hasDiesel,hasSuper,hasSuperPlus,hasGas,hasMethane);
	    List<GasStationDto> gasStationDtoList =  new ArrayList<GasStationDto>();
	    gasStationList.forEach((gs)->{
		  gasStationDtoList.add(modelMapper.map(gs, GasStationDto.class)); 
	  System.out.println(gs.getGasStationName());
	  });
		   
        // List<GasStationDto> postDTOList = modelMapper.map(gslist, listType);
         return gasStationDtoList;
		   
 
		 
	}

	@Override
	public List<GasStationDto> getGasStationsByProximity(double lat, double lon) throws GPSDataException {
		// TODO Auto-generated method stub
		return null;
	}

	private static double distance(double lat1, double lon1, double lat2, double lon2) {
		double EarthRadius = 6371e3; // metres
		double φ1 = lat1 * Math.PI/180; // φ, λ in radians
		double φ2 = lat2 * Math.PI/180;
		double Δφ = (lat2-lat1) * Math.PI/180;
		double Δλ = (lon2-lon1) * Math.PI/180;

		double a = Math.sin(Δφ/2) * Math.sin(Δφ/2) +
		          Math.cos(φ1) * Math.cos(φ2) *
		          Math.sin(Δλ/2) * Math.sin(Δλ/2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		
		return EarthRadius * c;
	}
	
	@Override
	public List<GasStationDto> getGasStationsWithCoordinates(double lat, double lon, String gasolinetype,
			String carsharing) throws InvalidGasTypeException, GPSDataException {
		List<GasStationDto> gasStationDtoList = getGasStationsByGasolineType (gasolinetype);
		List<GasStationDto> gasStationDtoReturnList =  new ArrayList<GasStationDto>();
		gasStationDtoList.forEach((gs)->
		{
			if(distance(lat, lon, gs.getLat(), gs.getLon())<1.0e3)
			{
				gasStationDtoReturnList.add(gs);
			}
		});
		return gasStationDtoReturnList;
	}

	@Override
	public List<GasStationDto> getGasStationsWithoutCoordinates(String gasolinetype, String carsharing)
			throws InvalidGasTypeException {
		List<GasStationDto> gasStationDtoList = getGasStationsByGasolineType (gasolinetype);
		List<GasStationDto> gasStationDtoReturnList =  new ArrayList<GasStationDto>();
		gasStationDtoList.forEach((gs)->
		{
			if(gs.getCarSharing()==carsharing)
			{
				gasStationDtoReturnList.add(gs);
			}
		});
		return gasStationDtoReturnList;
	}

	@Override
	public void setReport(Integer gasStationId, double dieselPrice, double superPrice, double superPlusPrice,
			double gasPrice, double methanePrice, Integer userId)
			throws InvalidGasStationException, PriceException, InvalidUserException {
		
		GasStation gasStation = gasStationRepository.findOne(gasStationId); 
		gasStationRepository.delete(gasStationId);
		gasStation.setDieselPrice(dieselPrice);
		gasStation.setSuperPrice(superPrice);
		gasStation.setSuperPlusPrice(superPlusPrice);
		gasStation.setGasPrice(gasPrice);
		gasStation.setMethanePrice(methanePrice);
		gasStation.setUser(userRepository.findOne(userId));
		gasStationRepository.save(gasStation); 
		
	}

	@Override
	public List<GasStationDto> getGasStationByCarSharing(String carSharing) {
	    List<GasStation> gasStationList = (List<GasStation>) gasStationRepository.findByCarSharing(carSharing);
	    List<GasStationDto> gasStationDtoList =  new ArrayList<GasStationDto>();
	    
	    gasStationList.forEach((gs)->{gasStationDtoList.add(modelMapper.map(gs, GasStationDto.class)); System.out.println(gs.getGasStationName());});
	    
        return gasStationDtoList;
	}

}
