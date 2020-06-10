package it.polito.ezgas.service.impl;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exception.GPSDataException;
import exception.InvalidGasStationException;
import exception.InvalidGasTypeException;
import exception.InvalidUserException;
import exception.PriceException;
import it.polito.ezgas.converter.GasStationConverter;
import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.entity.GasStation;
import it.polito.ezgas.entity.User;
import it.polito.ezgas.repository.GasStationRepository;
import it.polito.ezgas.repository.UserRepository;
import it.polito.ezgas.service.GasStationService;
 

/**
 * Created by softeng on 27/4/2020.
 */
@Service
public class GasStationServiceimpl implements GasStationService {

	@Autowired 
	GasStationRepository gasStationRepository; 
	
	@Autowired
	UserRepository userRepository; 
	
	@Autowired 
	GasStationConverter gasStationConverter; 
	
	
	
	public GasStationServiceimpl(GasStationRepository gasStationRepositoryInput, UserRepository userRepositoryInput, GasStationConverter gasStationConverterInput) {
		gasStationRepository= gasStationRepositoryInput;
		gasStationConverter= gasStationConverterInput;
		userRepository = userRepositoryInput;
	}
	
	@Override
	public GasStationDto getGasStationById(Integer gasStationId) throws InvalidGasStationException {
		System.out.println("getGasStationById\ninput: " + gasStationId);
		if(gasStationId < 0)
			throw new InvalidGasStationException("Invalid gasStationId!"); 
		
		GasStation gasStation = gasStationRepository.findOne(gasStationId);		
		
		if(gasStation != null)
			return gasStationConverter.map(gasStation, GasStationDto.class);
		else		
			return null;
	}
	@Override
	public GasStationDto saveGasStation(GasStationDto gasStationDto) throws PriceException, GPSDataException {
		System.out.println("saveGasStation");
		if((gasStationDto.getDieselPrice()<0		&& gasStationDto.getDieselPrice()!=-1)		||
			(gasStationDto.getGasPrice()<0			&& gasStationDto.getGasPrice()!=-1)			||
			(gasStationDto.getMethanePrice()<0		&& gasStationDto.getMethanePrice()!=-1)		||			
			(gasStationDto.getSuperPlusPrice()<0	&& gasStationDto.getSuperPlusPrice()!=-1)	||
			(gasStationDto.getSuperPrice()<0 		&& gasStationDto.getSuperPrice()!=-1))
		{
			throw new PriceException("Prices must be positive!"); 
		}
		if (gasStationDto.getLat() > 90.0 || gasStationDto.getLat() < -90.0 || gasStationDto.getLon() > 180.0 || gasStationDto.getLon() < -180.0) {
			throw new GPSDataException("Invalid coordinates!");
		}
		if(gasStationDto.getCarSharing().equals("null"))
		{
			gasStationDto.setCarSharing(null);
		}
		
		GasStation gasStationConverted = gasStationConverter.map(gasStationDto, GasStation.class);
		
		if (getGasStationsByProximity(gasStationDto.getLat(), gasStationDto.getLon()).size()==0) {
			GasStation gasStation = gasStationRepository.save(gasStationConverted); 
			return gasStationConverter.map(gasStation, GasStationDto.class);
		}
		else
			return null;
	}

	@Override
	public List<GasStationDto> getAllGasStations() {
		System.out.println("getAllGasStations");
		
		List<GasStation> listEntity= (List<GasStation>)gasStationRepository.findAll();
	    List<GasStationDto> gasStationDtoList =  new ArrayList<GasStationDto>();
		
		listEntity.forEach((gs)->{gasStationDtoList.add(gasStationConverter.map(gs, GasStationDto.class));});
		return gasStationDtoList;
	}

	@Override
	public Boolean deleteGasStation(Integer gasStationId) throws InvalidGasStationException {
		 // In the case of negative id throw exception
		if(gasStationId<0)
		{
			throw new InvalidGasStationException("Invalid gasStationId!"); 
		}
		 // Check if exists in db
		if(!gasStationRepository.exists(gasStationId))
		{
			return null; 
		}
		gasStationRepository.delete(gasStationId);			
		return true;
	}

	@Override
	public List<GasStationDto> getGasStationsByGasolineType(String gasolinetype) throws InvalidGasTypeException {
		
		//Findout the gastype and set the related boolean
		boolean hasDiesel =(gasolinetype.toLowerCase().equals("diesel"))?true:false;
		boolean hasSuper=(gasolinetype.toLowerCase().equals("super"))?true:false; 
        boolean hasSuperPlus=(gasolinetype.toLowerCase().equals("superplus"))?true:false;
        boolean hasGas=(gasolinetype.toLowerCase().equals("gas"))?true:false; 
        boolean hasMethane=(gasolinetype.toLowerCase().equals("methane"))?true:false;
 
        // If it is not in gas types throw an exception
        if(!(hasDiesel || hasSuper || hasSuperPlus || hasGas || hasMethane))
         throw new InvalidGasTypeException("Invalid gasoline type!");
       
        //Get the stream of gasstations and then filter by the gas type
	 Stream<GasStation> gasStationList =   ((List<GasStation>) gasStationRepository.findAll()).stream();
	 if(hasDiesel)
		 gasStationList= gasStationList.filter(gs ->gs.getHasDiesel()==hasDiesel).sorted(Comparator.comparingDouble(gs->gs.getDieselPrice()));
	 else if(hasSuper)
	 gasStationList= gasStationList.filter(gs ->gs.getHasSuper()==hasSuper).sorted(Comparator.comparingDouble(gs->gs.getSuperPrice()));
	else if(hasSuperPlus)
		gasStationList= gasStationList.filter(gs ->gs.getHasSuperPlus()==hasSuperPlus).sorted(Comparator.comparingDouble(gs->gs.getSuperPlusPrice()));
	else if(hasGas)
		gasStationList= gasStationList.filter(gs ->gs.getHasGas()==hasGas).sorted(Comparator.comparingDouble(gs->gs.getGasPrice()));
	else if(hasMethane)
		gasStationList= gasStationList.filter(gs ->gs.getHasMethane()==hasMethane).sorted(Comparator.comparingDouble(gs->gs.getMethanePrice()));
	  
	 // convert gasGtationDto list to gasStation list
	    List<GasStationDto> gasStationDtoList =  new ArrayList<GasStationDto>();
	    gasStationList.collect(Collectors.toList()).forEach((gs)->{
		  gasStationDtoList.add(gasStationConverter.map(gs, GasStationDto.class)); 
	  });

         return gasStationDtoList;
		   
 
		 
	}
	
	private static List<GasStationDto> getGasStationByProximityFromList (double lat, double lon, List<GasStationDto> gasStationDtoList) {
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
	
	private static List<GasStationDto> getGasStationByCarsharingFromList (String carsharing, List<GasStationDto> gasStationDtoList) {
		if (carsharing == null || carsharing.equals("null")) {
			return gasStationDtoList;
		}
		List<GasStationDto> gasStationDtoReturnList =  new ArrayList<GasStationDto>();
		gasStationDtoList.forEach((gs)->
		{
			if(gs.getCarSharing().contentEquals(carsharing))
			{
				gasStationDtoReturnList.add(gs);
				//System.out.println(carsharing + " " + gs.getGasStationName() + " " + gs.getGasStationAddress());
			}
		});
		return gasStationDtoReturnList;
	}

	@Override
	public List<GasStationDto> getGasStationsByProximity(double lat, double lon) throws GPSDataException {
		//System.out.println("getGasStationsByProximity\ninput: lat=" + lat + ", lon=" + lon);
		if (lat > 90.0 || lat < -90.0 || lon > 180.0 || lon < -180.0) {
			throw new GPSDataException("Invalid coordinates!");
		}
		List<GasStation> gasStationList= (List<GasStation>)gasStationRepository.findAll();
		List<GasStationDto> gasStationDtoList =  new ArrayList<GasStationDto>();
		
		gasStationList.forEach((gs)->
		{
			gasStationDtoList.add(gasStationConverter.map(gs, GasStationDto.class));
			//System.out.println(gs.getGasStationName() + " " + gs.getGasStationAddress());
		});
		
		return getGasStationByProximityFromList(lat, lon, gasStationDtoList);
	}

	public static double distance(double lat1, double lon1, double lat2, double lon2) {
		if (lat1 == lat2 && lon1 == lon2) {
			return 0.0;
		}
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
		//System.out.println("getGasStationsWithCoordinates\ninput: " + lat + ", " + lon + ", " + gasolinetype + ", " + carsharing);
		if (lat > 90.0 || lat < -90.0 || lon > 180.0 || lon < -180.0) {
			throw new GPSDataException("Invalid coordinates!");
		}
		
		List<GasStationDto> gasStationDtoList = getGasStationsByGasolineType(gasolinetype);
		//System.out.println(gasolinetype + ", size: " + gasStationDtoList.size());
		gasStationDtoList = getGasStationByCarsharingFromList(carsharing, gasStationDtoList);
		//System.out.println(carsharing + ", size: " + gasStationDtoList.size());
		
		return getGasStationByProximityFromList(lat, lon, gasStationDtoList);
	}

	@Override
	public List<GasStationDto> getGasStationsWithoutCoordinates(String gasolinetype, String carsharing)
			throws InvalidGasTypeException {
		System.out.println("getGasStationsWithoutCoordinates\ninput: " + gasolinetype + ", " + carsharing);
		List<GasStationDto> gasStationDtoList = getGasStationsByGasolineType (gasolinetype);
		return getGasStationByCarsharingFromList(carsharing, gasStationDtoList);
	}

	@Override
	public void setReport(Integer gasStationId, double dieselPrice, double superPrice, double superPlusPrice,
			double gasPrice, double methanePrice, Integer userId)
			throws InvalidGasStationException, PriceException, InvalidUserException {
		//System.out.println("setReport\ninput: " + gasStationId + ", " + dieselPrice + ", " + superPrice + ", " + gasPrice + ", " + methanePrice + ", " + userId);
		if(userId<0)
		{
			throw new InvalidUserException("Invalid userId!"); 
		}
		if(gasStationId<0)
		{
			throw new InvalidGasStationException("Invalid gasStationId!"); 
		}
		
		
		if((dieselPrice<=0 && dieselPrice!=-1) ||
				(superPrice<=0 && superPrice!=-1) ||
				(superPlusPrice<=0 && superPlusPrice!=-1) ||
				(gasPrice<=0 && gasPrice!=-1) ||
				(methanePrice<=0 && methanePrice!=-1))
		{
			throw new PriceException("Prices cannot be zero!"); 
		}
		
		gasStationConverter.typeMap(GasStation.class, GasStationDto.class).addMappings(mapper -> {
			  mapper.map(GasStation::getUser, GasStationDto::setUserDto);
			 
			});
		GasStation gasStation = gasStationRepository.findOne(gasStationId); 
		System.out.println("serviceImpl before changes "+gasStation.getDieselPrice()+","+gasStation.getSuperPrice()+","+gasStation.getSuperPlusPrice()+","+gasStation.getGasPrice());
		gasStation.setDieselPrice(dieselPrice);
		gasStation.setHasDiesel( (dieselPrice == -1) ? false:true);
		gasStation.setSuperPrice(superPrice);
		gasStation.setHasSuper( (superPrice == -1) ? false:true);
		gasStation.setSuperPlusPrice(superPlusPrice);
		gasStation.setHasSuperPlus( (superPlusPrice == -1) ? false:true);
		gasStation.setGasPrice(gasPrice);
		gasStation.setHasGas( (gasPrice == -1) ? false:true);
		gasStation.setMethanePrice(methanePrice);
		gasStation.setHasMethane( (methanePrice == -1) ? false:true);

		User us = userRepository.findOne(userId);
		Integer obs = 0;
		gasStation.setReportDependability(50 * (us.getReputation()+5) / 10 + 50*obs);
        DateFormat formatter = new SimpleDateFormat("MM-dd-YYYY");
        Date date = new Date(System.currentTimeMillis());
		gasStation.setReportTimestamp(formatter.format(date).toString());
		gasStation.setReportUser(userId);
		gasStation.setUser(us);
		gasStationRepository.save(gasStation); // don't remove, this line updates the db

	}

	@Override
	public List<GasStationDto> getGasStationByCarSharing(String carSharing) {
		System.out.println("getGasStationByCarSharing\ninput: " + carSharing);

	    List<GasStation> gasStationList = (List<GasStation>) gasStationRepository.findByCarSharing(carSharing);
	    List<GasStationDto> gasStationDtoList =  new ArrayList<GasStationDto>();
	    
	    gasStationList.forEach((gs)->{gasStationDtoList.add(gasStationConverter.map(gs, GasStationDto.class)); System.out.println(gs.getGasStationName() + " " + carSharing);});
	    
        return gasStationDtoList;
	}

}
