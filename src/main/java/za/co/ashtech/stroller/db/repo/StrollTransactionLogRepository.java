package za.co.ashtech.stroller.db.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.ashtech.stroller.db.entities.StrollTransactionLog;

public interface StrollTransactionLogRepository extends JpaRepository<StrollTransactionLog, Long> {
}
