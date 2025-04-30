package za.co.ashtech.stroller.db.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import za.co.ashtech.stroller.db.entities.StrollerDEntity;

@Repository
public interface StrollerRepository extends JpaRepository<StrollerDEntity, Long> {

}
