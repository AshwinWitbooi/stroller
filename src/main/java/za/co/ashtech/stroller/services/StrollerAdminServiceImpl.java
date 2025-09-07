package za.co.ashtech.stroller.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
	public Stroll addStroll(Stroll stroll) throws StrollerServiceException {


		za.co.ashtech.stroller.db.entities.Stroll dbo = null;
		try {
			/*
			 *persist database entity
			 *values mapped from request entity
			 */
			dbo = strollerRepository.save(new za.co.ashtech.stroller.db.entities.Stroll(stroll.getName(), stroll.getDescription(),stroll.getLocation(),Double.valueOf(stroll.getLatitude()),Double.valueOf(stroll.getLatitude())));
			log.debug("-D DATABASE ENTITY SUCCESSFULLY PERSISTED");
		} catch (NumberFormatException e) {
			throw new StrollerServiceException("Error saving stroll", e);
		}catch (Exception e){
			throw new StrollerServiceException(e);
		}
		return new Stroll(dbo.getName(),dbo.getDescription(), dbo.getLocation(),Double.toString(dbo.getLatitude()),Double.toString(dbo.getLongitude()));
	}

	@Override
	public Stroll updateStroll(Long id, Stroll stroll) throws StrollerServiceException {


		za.co.ashtech.stroller.db.entities.Stroll updatedStroll = null;
		try {
			za.co.ashtech.stroller.db.entities.Stroll existing = strollerRepository.findById(id)
			        .orElseThrow(() -> new NullPointerException("Stroll not found with id: " + id));
			
			existing.setName(stroll.getName());
			existing.setLocation(stroll.getLocation());
			existing.setLatitude(Double.valueOf(stroll.getLatitude()));
			existing.setLongitude(Double.valueOf(stroll.getLongitude()));
			
			updatedStroll = strollerRepository.save(existing);
		} catch (NumberFormatException e) {
			throw new StrollerServiceException(e);
		} catch (NullPointerException e) {
			throw new StrollerServiceException(e);
		}
		
		return new Stroll(updatedStroll.getName(),updatedStroll.getDescription(), updatedStroll.getLocation(), updatedStroll.getLongitude().toString(), updatedStroll.getLongitude().toString());
	}

	@Override
	public void deleteStroll(Long id) throws StrollerServiceException {
		try {
			strollerRepository.deleteById(id);
		} catch (Exception e) {
			throw new StrollerServiceException(e);
		}
	}

	@Override
	public List<Stroll> getAllStrolls() throws StrollerServiceException {
		List<Stroll> strollList = null;
		
		strollList = strollerRepository.findAll().stream()
									.map(dbr -> {		
										Stroll stroll = new Stroll(dbr.getName(), dbr.getDescription(), dbr.getLocation(), dbr.getLongitude().toString(), dbr.getLongitude().toString());
											stroll.setImage(dbr.getImage());
										return stroll;
									}).collect(Collectors.toList());
		return strollList;
	}

	@Override
	public Stroll getStrollByName(String name) throws StrollerServiceException {
			
		return strollerRepository.findByName(name).map(s -> {			
			return new Stroll(s.getName(), s.getDescription(), s.getLocation(),s.getLatitude().toString(),s.getLongitude().toString());
		}).orElseThrow(() -> new StrollerServiceException("Error retrieving data."));

	}


}
