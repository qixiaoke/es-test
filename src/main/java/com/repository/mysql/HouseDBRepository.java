package com.repository.mysql;

import com.domain.mysql.HouseDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseDBRepository extends JpaRepository<HouseDB, Long> {
}
