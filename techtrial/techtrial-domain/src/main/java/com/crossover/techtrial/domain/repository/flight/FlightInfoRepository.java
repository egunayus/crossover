package com.crossover.techtrial.domain.repository.flight;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.crossover.techtrial.domain.model.flight.FlightInfo;

public interface FlightInfoRepository extends PagingAndSortingRepository<FlightInfo, Long> {

}
