package com.controller;

import com.common.ResultBuilder;
import com.controller.vo.SearchVo;
import com.domain.House;
import com.domain.ServerResult;
import com.domain.mysql.HouseTagDB;
import com.repository.mysql.HouseTagDBRepository;
import com.service.HouseSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HouseController {

    @Autowired
    private HouseSerivce houseSerivce;

    @PostMapping("/house/{id}")
    public ServerResult createHouse(@PathVariable("id") String id) {

        boolean create = houseSerivce.createHouseById(Long.valueOf(id));

        return ResultBuilder.build(create);
    }

    @DeleteMapping("/house/{id}")
    public ServerResult deleteHouseById(@PathVariable("id") String id) {

        boolean delete = houseSerivce.deleteHouseById(id);

        return ResultBuilder.build(delete);
    }

    @GetMapping("/house")
    public ServerResult query(SearchVo searchVo) {

        List<House> houses = houseSerivce.query(searchVo);

        return ResultBuilder.build(houses);
    }

    @Autowired
    private HouseTagDBRepository houseTagDBRepository;

    @GetMapping("/test")
    public ServerResult test(long houseId) {

        List<HouseTagDB> houseTagDBs = houseTagDBRepository.findByHouseId(houseId);

        return ResultBuilder.build(houseTagDBs);
    }
}
