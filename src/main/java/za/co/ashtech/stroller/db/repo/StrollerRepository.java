package za.co.ashtech.stroller.db.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.ashtech.stroller.db.entities.StrollerDEntity;

public interface StrollerRepository extends JpaRepository<StrollerDEntity, Long> {

}
