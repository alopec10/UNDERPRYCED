package com.inso2.inso2.domain.persistence;

import com.inso2.inso2.domain.entity.Ask;
import com.inso2.inso2.domain.entity.Bid;
import org.springframework.data.repository.CrudRepository;

public interface BidRepository extends CrudRepository<Bid, Long> {
}
