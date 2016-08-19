package com.crossover.techtrial.domain.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.crossover.techtrial.domain.model.Plane;

public interface PlaneRepository extends PagingAndSortingRepository<Plane, Long> {

}
