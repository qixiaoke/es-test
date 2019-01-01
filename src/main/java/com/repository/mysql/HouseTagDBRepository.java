package com.repository.mysql;

import com.domain.mysql.HouseTagDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseTagDBRepository extends JpaRepository<HouseTagDB, Long> {

    List<HouseTagDB> findByHouseId(long houseId);
}
