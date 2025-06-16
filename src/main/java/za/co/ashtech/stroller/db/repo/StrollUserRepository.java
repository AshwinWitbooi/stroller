package za.co.ashtech.stroller.db.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import za.co.ashtech.stroller.db.entities.StrollUser;

public interface StrollUserRepository extends JpaRepository<StrollUser, Long> {
	Optional<StrollUser> findByUsername(String username);
}
