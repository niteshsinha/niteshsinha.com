package com.niteshsinha.candid.repository;

import com.niteshsinha.candid.model.Candid;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CandidRepository extends ReactiveCrudRepository<Candid,Integer> {

}
