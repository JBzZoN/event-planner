package com.event.planner.entity;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bookinId;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserDetail user;
	@ManyToOne
	@JoinColumn(name = "package_id")
	private Package packageDetail;
	private BigDecimal bookingAmount;
	private LocalDateTime bookingDate;
}
