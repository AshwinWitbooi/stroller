package za.co.ashtech.stroller.db.repo;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import za.co.ashtech.stroller.db.entities.Stroll;

@Repository
public interface StrollerRepository extends JpaRepository<Stroll, Long> {
	
	Optional<Stroll> findByStrollId(Integer strollId);
	void deleteByStrollId(Integer strollId);

}
