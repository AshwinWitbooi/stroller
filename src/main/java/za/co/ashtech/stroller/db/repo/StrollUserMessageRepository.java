package za.co.ashtech.stroller.db.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import za.co.ashtech.stroller.db.entities.StrollUserMessage;

@Repository
public interface StrollUserMessageRepository extends JpaRepository<StrollUserMessage, Long> {
}
