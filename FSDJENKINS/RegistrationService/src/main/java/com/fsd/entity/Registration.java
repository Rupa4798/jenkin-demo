package com.fsd.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

@Entity
@Table(name="registrations")
public class Registration {
    public Registration(Long id, @Min(value = 0, message = "Number of children cannot be negative") int numberOfAdults,
			@Min(value = 0, message = "Number of children cannot be negative") int numberOfChildren, Long userId,
			Long eventId, boolean paymentStatus) {
		super();
		this.id = id;
		this.numberOfAdults = numberOfAdults;
		this.numberOfChildren = numberOfChildren;
		this.userId = userId;
		this.eventId = eventId;
		this.paymentStatus = paymentStatus;
	}

	public Registration() {
		super();
	}

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Min(value = 0, message = "Number of children cannot be negative")
    private int numberOfAdults;
    
    @Min(value = 0, message = "Number of children cannot be negative")
    private int numberOfChildren;
    
    
   
    private Long userId;
    
    
    private Long eventId;
    
    private boolean paymentStatus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNumberOfAdults() {
		return numberOfAdults;
	}

	public void setNumberOfAdults(int numberOfAdults) {
		this.numberOfAdults = numberOfAdults;
	}

	public int getNumberOfChildren() {
		return numberOfChildren;
	}

	public void setNumberOfChildren(int numberOfChildren) {
		this.numberOfChildren = numberOfChildren;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public boolean isPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(boolean paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	@Override
	public String toString() {
		return "Registration [id=" + id + ", numberOfAdults=" + numberOfAdults + ", numberOfChildren="
				+ numberOfChildren + ", userId=" + userId + ", eventId=" + eventId + ", paymentStatus=" + paymentStatus
				+ "]";
	}

	    
}
