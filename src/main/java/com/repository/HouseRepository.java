package com.repository;

import com.domain.House;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface HouseRepository extends ElasticsearchRepository<House, Long> {
}
