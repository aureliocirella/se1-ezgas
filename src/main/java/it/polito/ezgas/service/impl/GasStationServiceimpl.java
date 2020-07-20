package it.polito.ezgas.service.impl;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exception.GPSDataException;
import exception.InvalidCarSharingException;
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
		if((gasStationDto.getDieselPrice()!=null	&& gasStationDto.getDieselPrice()<0		&& gasStationDto.getHasDiesel())		||
			(gasStationDto.getGasPrice()!=null		&& gasStationDto.getGasPrice()<0		&& gasStationDto.getHasGas())			||
			(gasStationDto.getMethanePrice()!=null	&& gasStationDto.getMethanePrice()<0	&& gasStationDto.getHasMethane())		||			
			(gasStationDto.getSuperPlusPrice()!=null && gasStationDto.getSuperPlusPrice()<0	&& gasStationDto.getHasSuperPlus())	||
			(gasStationDto.getSuperPrice()!=null	&& gasStationDto.getSuperPrice()<0 		&& gasStationDto.getHasSuper())	||
			(gasStationDto.getPremiumDieselPrice()!=null	&& gasStationDto.getPremiumDieselPrice()<0 		&& gasStationDto.getHasPremiumDiesel()))
		{
			throw new PriceException("Prices must be positive!"); 
		}

		/*if(gasStationDto.getDieselPrice()==null
			&& gasStationDto.getGasPrice()==null 
			&& gasStationDto.getMethanePrice()==null 
			&& gasStationDto.getSuperPlusPrice()==null
			&& gasStationDto.getSuperPrice()==null
			&& gasStationDto.getPremiumDieselPrice()==null)
		{
			throw new PriceException("At least 1 fuel price exist!"); 
		}*/
		
		if (gasStationDto.getLat() > 90.0 || gasStationDto.getLat() < -90.0 || gasStationDto.getLon() > 180.0 || gasStationDto.getLon() < -180.0) {
			throw new GPSDataException("Invalid coordinates!");
		}
		if(gasStationDto.getCarSharing().equals("null"))
		{
			gasStationDto.setCarSharing(null);
		}
		
		GasStation gasStationConverted = gasStationConverter.map(gasStationDto, GasStation.class);
		List<GasStationDto> neighbours = getGasStationsByProximity(gasStationDto.getLat(), gasStationDto.getLon());
		List<GasStationDto> same = new ArrayList<GasStationDto>();
		neighbours.forEach((gs)->{
			if (gs.getLat() == gasStationDto.getLat() && gs.getLon() == gasStationDto.getLon() && gs.getGasStationId() == gasStationDto.getGasStationId()) {
				same.add(gs);
			}
		});
		if (same.size()==0) {
			GasStation gasStation = gasStationRepository.save(gasStationConverted); 
			return gasStationConverter.map(gasStation, GasStationDto.class);
		}
		else {
			return same.get(0);
		}
	}

	@Override
	public List<GasStationDto> getAllGasStations() {
		System.out.println("getAllGasStations");
		
		List<GasStation> listEntity = (List<GasStation>)gasStationRepository.findAll();
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
		boolean hasDiesel = (gasolinetype.toLowerCase().equals("diesel"))?true:false;
		boolean hasSuper = (gasolinetype.toLowerCase().equals("super"))?true:false; 
        boolean hasSuperPlus = (gasolinetype.toLowerCase().equals("superplus"))?true:false;
        boolean hasGas = (gasolinetype.toLowerCase().equals("gas"))?true:false; 
        boolean hasMethane = (gasolinetype.toLowerCase().equals("methane"))?true:false;
        boolean hasPremiumDiesel = (gasolinetype.toLowerCase().equals("premiumdiesel"))?true:false;
        boolean hasAll = (gasolinetype.toLowerCase().equals("null"))?true:false;
 
        // If it is not in gas types throw an exception
        if(!(hasDiesel || hasSuper || hasSuperPlus || hasGas || hasMethane || hasPremiumDiesel || hasAll))
        	throw new InvalidGasTypeException("Invalid gasoline type!");
       
        //Get the stream of gasstations and then filter by the gas type
     
        List<GasStation> gasStationList=null;
        if(hasDiesel)
        	gasStationList = gasStationRepository.findByHasDiesel(hasDiesel);
        else if(hasSuper)
        	gasStationList = gasStationRepository.findByHasSuper(hasSuper);
        else if(hasSuperPlus)
        	gasStationList = gasStationRepository.findByHasSuperPlus(hasSuperPlus);
		else if(hasGas)
			gasStationList = gasStationRepository.findByHasGas(hasGas);
		else if(hasMethane)
			gasStationList = gasStationRepository.findByHasMethane(hasMethane);
		else if(hasPremiumDiesel)
			gasStationList = gasStationRepository.findByHasPremiumDiesel(hasPremiumDiesel);
		  
        // convert gasGtationDto list to gasStation list
        List<GasStationDto> gasStationDtoList =  new ArrayList<GasStationDto>();
        gasStationList.forEach((gs)->{
        	gasStationDtoList.add(gasStationConverter.map(gs, GasStationDto.class)); 
        });
	
        return gasStationDtoList; 
	}
	
	private static List<GasStationDto> getGasStationByProximityFromList (double lat, double lon, List<GasStationDto> gasStationDtoList, int radius) {
		List<GasStationDto> gasStationDtoReturnList =  new ArrayList<GasStationDto>();
		gasStationDtoList.forEach((gs)->
		{
			if(distance(lat, lon, gs.getLat(), gs.getLon())<((1.0e3)*radius))
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
		return getGasStationsByProximity(lat, lon, 1);
	}

	@Override
	public List<GasStationDto> getGasStationsByProximity(double lat, double lon, int radius) throws GPSDataException {
		//System.out.println("getGasStationsByProximity\ninput: lat=" + lat + ", lon=" + lon);
		if (lat > 90.0 || lat < -90.0 || lon > 180.0 || lon < -180.0) {
			throw new GPSDataException("Invalid coordinates!");
		}
		List<GasStationDto> gasStationDtoList = getAllGasStations();
		
		return getGasStationByProximityFromList(lat, lon, gasStationDtoList, radius);
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
	public List<GasStationDto> getGasStationsWithCoordinates(double lat, double lon, int radius, String gasolinetype,
			String carsharing) throws InvalidGasTypeException, GPSDataException, InvalidCarSharingException {
		//System.out.println("getGasStationsWithCoordinates\ninput: " + lat + ", " + lon + ", " + gasolinetype + ", " + carsharing);
		if (lat > 90.0 || lat < -90.0 || lon > 180.0 || lon < -180.0) {
			throw new GPSDataException("Invalid coordinates!");
		}
		
		List<GasStationDto> gasStationDtoList = getGasStationsByGasolineType(gasolinetype);
		//System.out.println(gasolinetype + ", size: " + gasStationDtoList.size());
		gasStationDtoList = getGasStationByCarsharingFromList(carsharing, gasStationDtoList);
		//System.out.println(carsharing + ", size: " + gasStationDtoList.size());
		
		return getGasStationByProximityFromList(lat, lon, gasStationDtoList, radius);
	}

	@Override
	public List<GasStationDto> getGasStationsWithoutCoordinates(String gasolinetype, String carsharing)
			throws InvalidGasTypeException {
		System.out.println("getGasStationsWithoutCoordinates\ninput: " + gasolinetype + ", " + carsharing);
		List<GasStationDto> gasStationDtoList = getGasStationsByGasolineType (gasolinetype);
		return getGasStationByCarsharingFromList(carsharing, gasStationDtoList);
	}

	@Override
	public void setReport(Integer gasStationId, Double dieselPrice, Double superPrice, Double superPlusPrice,
			Double gasPrice, Double methanePrice, Double premiumDieselPrice, Integer userId) 
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

		gasStationConverter.typeMap(GasStation.class, GasStationDto.class).addMappings(mapper -> {
			  mapper.map(GasStation::getUser, GasStationDto::setUserDto);
			});
		GasStation gasStation = gasStationRepository.findOne(gasStationId); 
		
		
		User reportUser = gasStation.getUser(); //report user
		
		
		if(reportUser!=null)//there's already a price report
		{
			String stringTimestamp = gasStation.getReportTimestamp();
			DateFormat format = new SimpleDateFormat("MM-dd-YYYY");
			Date dateTimestamp = null;
			try {
				dateTimestamp = format.parse(stringTimestamp);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Long diff = (new Date()).getTime() - dateTimestamp.getTime();
			Integer diffDays = Math.abs((int) (diff / (24 * 60 * 60 * 1000)));
			
			User oldUser = userRepository.findOne(reportUser.getUserId()); 
			User newUser =userRepository.findOne(userId);  
			if((oldUser.getReputation()<=newUser.getReputation())
					||(diffDays>4))//report has to be overwritten
			{
				reportUser = newUser; 
				if((dieselPrice!=null && dieselPrice<0) ||
						(superPrice!=null && superPrice<0) ||
						(superPlusPrice!=null && superPlusPrice<0) ||
						(gasPrice!=null && gasPrice<0) ||
						(methanePrice!=null && methanePrice<0) ||
						(premiumDieselPrice!=null && premiumDieselPrice<0))
				{
					throw new PriceException("Prices cannot be negative!"); 
				}
				
				//System.out.println("serviceImpl before changes "+gasStation.getDieselPrice()+","+gasStation.getSuperPrice()+","+gasStation.getSuperPlusPrice()+","+gasStation.getGasPrice());
				if(gasStation.getHasDiesel()) {
					gasStation.setDieselPrice(dieselPrice);
				}
				/*else if(dieselPrice!=null) {
					gasStation.setDieselPrice(dieselPrice);
					gasStation.setHasDiesel(true);
				}*/
				//super
				if(gasStation.getHasSuper()) {
					gasStation.setSuperPrice(superPrice);
				}
				/*else if(superPrice!=null) {
					gasStation.setDieselPrice(superPrice);
					gasStation.setHasSuper(true);
				}*/
				//super plus
				if(gasStation.getHasSuperPlus()) {
					gasStation.setSuperPlusPrice(superPlusPrice);
				}
				/*else if(superPlusPrice!=null) {
					gasStation.setDieselPrice(superPlusPrice);
					gasStation.setHasSuperPlus(true);
				}*/
				//gas
				if(gasStation.getHasGas()) {
					gasStation.setGasPrice(gasPrice);
				}
				/*else if(gasPrice!=null) {
					gasStation.setDieselPrice(gasPrice);
					gasStation.setHasGas(true);
				}*/
				//methane
				if(gasStation.getHasMethane()) {
					gasStation.setMethanePrice(methanePrice);
				}
				/*else if(methanePrice!=null) {
					gasStation.setDieselPrice(methanePrice);
					gasStation.setHasMethane(true);
				}*/
				//premium diesel
				if(gasStation.getHasPremiumDiesel()) {
					gasStation.setPremiumDieselPrice(premiumDieselPrice);
				}
				/*else if(premiumDieselPrice!=null) {
					gasStation.setDieselPrice(premiumDieselPrice);
					gasStation.setHasPremiumDiesel(true);
				}*/
				
				
				//gasStation.setReportDependability(50 * (us.getReputation()+5) / 10 + 50*obs);
		        DateFormat formatter = new SimpleDateFormat("MM-dd-YYYY");
		        Date date = new Date(System.currentTimeMillis());
				gasStation.setReportTimestamp(formatter.format(date).toString());
				gasStation.setReportUser(reportUser.getUserId());
				gasStation.setUser(reportUser);
				gasStationRepository.save(gasStation); // don't remove, this line updates the db
			}
		}
		else {//there's no report 
				if((dieselPrice!=null && dieselPrice<0) ||
						(superPrice!=null && superPrice<0) ||
						(superPlusPrice!=null && superPlusPrice<0) ||
						(gasPrice!=null && gasPrice<0) ||
						(methanePrice!=null && methanePrice<0) ||
						(premiumDieselPrice!=null && premiumDieselPrice<0))
				{
					throw new PriceException("Prices cannot be negative!"); 
				}
				
				//System.out.println("serviceImpl before changes "+gasStation.getDieselPrice()+","+gasStation.getSuperPrice()+","+gasStation.getSuperPlusPrice()+","+gasStation.getGasPrice());
				if(gasStation.getHasDiesel()) {
					gasStation.setDieselPrice(dieselPrice);
				}
				/*else if(dieselPrice!=null) {
					gasStation.setDieselPrice(dieselPrice);
					gasStation.setHasDiesel(true);
				}*/
				//super
				if(gasStation.getHasSuper()) {
					gasStation.setSuperPrice(superPrice);
				}
				/*else if(superPrice!=null) {
					gasStation.setDieselPrice(superPrice);
					gasStation.setHasSuper(true);
				}*/
				//super plus
				if(gasStation.getHasSuperPlus()) {
					gasStation.setSuperPlusPrice(superPlusPrice);
				}
				/*else if(superPlusPrice!=null) {
					gasStation.setDieselPrice(superPlusPrice);
					gasStation.setHasSuperPlus(true);
				}*/
				//gas
				if(gasStation.getHasGas()) {
					gasStation.setGasPrice(gasPrice);
				}
				/*else if(gasPrice!=null) {
					gasStation.setDieselPrice(gasPrice);
					gasStation.setHasGas(true);
				}*/
				//methane
				if(gasStation.getHasMethane()) {
					gasStation.setMethanePrice(methanePrice);
				}
				/*else if(methanePrice!=null) {
					gasStation.setDieselPrice(methanePrice);
					gasStation.setHasMethane(true);
				}*/
				//premium diesel
				if(gasStation.getHasPremiumDiesel()) {
					gasStation.setPremiumDieselPrice(premiumDieselPrice);
				}
				/*else if(premiumDieselPrice!=null) {
					gasStation.setDieselPrice(premiumDieselPrice);
					gasStation.setHasPremiumDiesel(true);
				}*/
				
				
				//gasStation.setReportDependability(50 * (us.getReputation()+5) / 10 + 50*obs);
				User us = userRepository.findOne(userId);
				DateFormat formatter = new SimpleDateFormat("MM-dd-YYYY");
		        Date date = new Date(System.currentTimeMillis());
				gasStation.setReportTimestamp(formatter.format(date).toString());
				gasStation.setReportUser(userId);
				gasStation.setUser(us);
				gasStationRepository.save(gasStation); // don't remove, this line updates the db
			}
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
