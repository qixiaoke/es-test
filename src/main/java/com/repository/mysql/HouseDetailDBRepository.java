package com.repository.mysql;

import com.domain.mysql.HouseDetailDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseDetailDBRepository extends JpaRepository<HouseDetailDB, Long> {

    HouseDetailDB findByHouseId(long houseId);
}
