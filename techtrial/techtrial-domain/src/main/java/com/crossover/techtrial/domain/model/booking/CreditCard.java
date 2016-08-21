package com.crossover.techtrial.domain.model.booking;

/**
 * Represents a credit card information
 * 
 * The objects of this class are not persisted in database, Therefore it is not an @Entity class
 * 
 * @author egunay
 *
 */
public class CreditCard {

	private String cardOwner;
	
	private String number;
	
	private String expirationMonth;

	private Integer expirationYear;
	
	private Integer cvv2;

	public String getCardOwner() {
		return cardOwner;
	}
	public void setCardOwner(String cardOwner) {
		this.cardOwner = cardOwner;
	}
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}

	public String getExpirationMonth() {
		return expirationMonth;
	}
	public void setExpirationMonth(String expirationMonth) {
		this.expirationMonth = expirationMonth;
	}
	
	public Integer getExpirationYear() {
		return expirationYear;
	}
	public void setExpirationYear(Integer expirationYear) {
		this.expirationYear = expirationYear;
	}
	
	public Integer getCvv2() {
		return cvv2;
	}
	public void setCvv2(Integer cvv2) {
		this.cvv2 = cvv2;
	}
	
	
	
}
