package com.crossover.techtrial.domain.model.booking;

/**
 * The objects of this class are not persisted in database, Therefore it is not an @Entity class
 * 
 * @author egunay
 *
 */
public class CreditCard {

	private String cardOwner;
	
	private String number;
	
	private String expirationDate;
	
	private Integer cvc2;

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

	public String getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public Integer getCvc2() {
		return cvc2;
	}
	public void setCvc2(Integer cvc2) {
		this.cvc2 = cvc2;
	}
	
	
	
}
