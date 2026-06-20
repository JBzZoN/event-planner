package com.event.planner.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.event.planner.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer>{
	    @Query("""
	        SELECT COALESCE(SUM(b.bookingAmount), 0)
	        FROM Booking b
	    """)
	    BigDecimal getTotalRevenue();
}
