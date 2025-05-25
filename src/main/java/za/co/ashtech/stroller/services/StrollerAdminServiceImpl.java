package za.co.ashtech.stroller.services;

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

		/*
		 *persist database entity
		 *values mapped from request entity
		 */
		za.co.ashtech.stroller.db.entities.Stroll dbo = strollerRepository.save(new za.co.ashtech.stroller.db.entities.Stroll(stroll.getName(), stroll.getLocation(),Double.valueOf(stroll.getLatitude()),Double.valueOf(stroll.getLatitude())));
		log.debug("-D DATABASE ENTITY SUCCESSFULLY PERSISTED");
		
		return new Stroll(dbo.getName(), dbo.getLocation(),Double.toString(dbo.getLatitude()),Double.toString(dbo.getLongitude()));
	}

	@Override
	public Stroll updateStroll(Long id, Stroll stroll) throws StrollerServiceException {


		za.co.ashtech.stroller.db.entities.Stroll existing = strollerRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Stroll not found with id: " + id));
		
		existing.setName(stroll.getName());
		existing.setLocation(stroll.getLocation());
		existing.setLatitude(Double.valueOf(stroll.getLatitude()));
		existing.setLongitude(Double.valueOf(stroll.getLongitude()));
		
		za.co.ashtech.stroller.db.entities.Stroll updatedStroll = strollerRepository.save(existing);
		
		return new Stroll(updatedStroll.getName(), updatedStroll.getLocation(), updatedStroll.getLongitude().toString(), updatedStroll.getLongitude().toString());
	}

	@Override
	public void deleteStroll(Long id) throws StrollerServiceException {
		strollerRepository.deleteById(id);
	}


}
