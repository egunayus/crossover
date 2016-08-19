package com.crossover.techtrial.domain.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.crossover.techtrial.domain.model.FlightInfo;

public interface FlightInfoRepository extends PagingAndSortingRepository<FlightInfo, Long> {

}
