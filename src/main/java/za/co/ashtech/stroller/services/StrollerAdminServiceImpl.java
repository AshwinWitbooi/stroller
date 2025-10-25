	package za.co.ashtech.stroller.services;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import za.co.ashtech.stroller.controller.entities.Stroll;
import za.co.ashtech.stroller.db.repo.StrollerRepository;
import za.co.ashtech.stroller.util.StrollerServiceException;

@Slf4j
@Service
public class StrollerAdminServiceImpl implements StrollerAdminService {

	@Autowired
	private StrollerRepository strollerRepository;

	@Override
	public void addStroll(String requestJson, MultipartFile file) throws StrollerServiceException {

		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			//get original file name
			String fileName = file.getOriginalFilename();
			
			 File uploadedFile = new File("uploads/"+fileName);
			 
			 FileUtils.
			 writeByteArrayToFile(uploadedFile, file.getBytes());
			
			//Convert JSON request string to Stroll object
			Stroll stroll = objectMapper.readValue(requestJson, Stroll.class);	
			
			strollerRepository.findByStrollName(stroll.getName());
			
			if(strollerRepository.findByStrollName(stroll.getName()).isPresent()) {
				throw new StrollerServiceException("Stroll by that name already exist.");
			}
			
			Integer strollId = new Random().nextInt(1000);
			
			/*
			 *persist database entity
			 *values mapped from request entity
			 */
			strollerRepository.save(new za.co.ashtech.stroller.db.entities.Stroll(strollId, stroll.getName(), stroll.getDescription(), stroll.getLocation(), Double.parseDouble(stroll.getLatitude()), Double.parseDouble(stroll.getLongitude()),"/images/"+fileName));
			log.debug("-D DATABASE ENTITY SUCCESSFULLY PERSISTED");
		} catch (NumberFormatException e) {
			throw new StrollerServiceException("Error saving stroll", e);
		}catch (Exception e){
			throw new StrollerServiceException(e);
		}
	}

	@Override
	public Stroll updateStroll(String strollId, Stroll stroll) throws StrollerServiceException {

		try {
			 Optional<za.co.ashtech.stroller.db.entities.Stroll> existing = strollerRepository.findByStrollId(Integer.parseInt(strollId));
			
			return existing
			 		.map(er -> {
			 			//Update existing record
						er.setStrollName(stroll.getName());
						er.setDescription(stroll.getDescription());
						er.setLocation(stroll.getLocation());
						er.setLatitude(Double.valueOf(stroll.getLatitude()));
						er.setLongitude(Double.valueOf(stroll.getLongitude()));
						er.setImage(stroll.getImage());
						
						//Update record
						za.co.ashtech.stroller.db.entities.Stroll ur = strollerRepository.save(er);
						
						return  new Stroll(ur.getStrollId().toString(),ur.getStrollName(), ur.getDescription(), ur.getLocation(), ur.getLatitude().toString(), ur.getLongitude().toString(), ur.getImage());
			 		})
			 		.orElseThrow(() -> new NullPointerException("Stroll not found with id: " + strollId));
			 
		} catch (NumberFormatException e) {
			throw new StrollerServiceException(e);
		} catch (NullPointerException e) {
			throw new StrollerServiceException(e);
		}catch (Exception e) {
			throw new StrollerServiceException(e);
		}
	}

	@Override
	public void deleteStroll(String strollId) throws StrollerServiceException {
		try {
			
			 Optional<za.co.ashtech.stroller.db.entities.Stroll> toDelete = strollerRepository.findByStrollId(Integer.parseInt(strollId));
			 
			 strollerRepository.delete(toDelete.orElseThrow(() -> new NullPointerException("Stroll not found")));

		} catch (Exception e) {
			throw new StrollerServiceException(e);
		}
	}
	
	@Override
	public List<Stroll> getAllStrolls() throws StrollerServiceException {
		List<Stroll> strollList = null;
		
		strollList = strollerRepository.findAll().stream()
									.map(dbr -> {		
										Stroll stroll = new Stroll(Integer.toString(dbr.getStrollId()),dbr.getStrollName(), dbr.getDescription(), dbr.getLocation(), dbr.getLongitude().toString(), dbr.getLongitude().toString(),dbr.getImage());
										return stroll;
									}).collect(Collectors.toList());
		return strollList;
	}

	@Override
	public Stroll getStrollById(String strollId) throws StrollerServiceException {
		return strollerRepository.findByStrollId(Integer.parseInt(strollId)).map(s -> {			
			return new Stroll(Integer.toString(s.getStrollId()), s.getStrollName(), s.getDescription(), s.getLocation(),s.getLatitude().toString(),s.getLongitude().toString(), s.getImage());
		}).orElseThrow(() -> new StrollerServiceException("Error retrieving data."));
	}


}
