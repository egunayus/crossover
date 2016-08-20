package com.crossover.techtrial.domain.repository.flight;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.crossover.techtrial.domain.model.flight.Plane;

public interface PlaneRepository extends PagingAndSortingRepository<Plane, Long> {

}
