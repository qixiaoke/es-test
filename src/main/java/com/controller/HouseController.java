package com.controller;

import com.controller.vo.HouseVo;
import com.domain.House;
import com.common.ResultBuilder;
import com.domain.ServerResult;
import com.service.HouseSerivce;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class HouseController {

    @Autowired
    private HouseSerivce houseSerivce;

    @PostMapping("/house")
    public ServerResult createHouse(@RequestBody HouseVo houseVo) {

        House house = new House();
        BeanUtils.copyProperties(houseVo, house);
        Date now = new Date();
        house.setCreateTime(now);
        house.setLastUpdateTime(now);
        boolean create = houseSerivce.createHouse(house);

        return ResultBuilder.build(create);
    }

    @DeleteMapping("/house/{id}")
    public ServerResult createHouse(@PathVariable("id") String id) {

        boolean delete = houseSerivce.deleteHouseById(id);

        return ResultBuilder.build(delete);
    }
}
