package com.crossover.techtrial.domain.repository.user;

import org.springframework.data.repository.CrudRepository;

import com.crossover.techtrial.domain.model.user.Passenger;

public interface PassengerRepository extends CrudRepository<Passenger, Long> {

}
