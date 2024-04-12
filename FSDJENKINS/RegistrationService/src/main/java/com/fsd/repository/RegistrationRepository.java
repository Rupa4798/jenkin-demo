package com.fsd.repository;

import com.fsd.entity.Registration;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
//	 @Query("SELECT r.user.username, r.event.name, COUNT(r) " +
//	            "FROM Registration r " +
//	            "GROUP BY r.user.username, r.event.name")
//	    List<Object[]> countRegistrationsByUsernameAndEventName();
//	    
//	    
//	    
//	    @Query("SELECT r.event.name, COUNT(r) FROM Registration r GROUP BY r.event.name")
//	    List<Object[]> countRegistrationsPerEvent();



		List<Registration> findByUserId(Long userId);
		 int countRegistrationsByEventId(Long eventId);
}

