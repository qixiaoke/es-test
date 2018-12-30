package com.service;

import com.alibaba.fastjson.JSONObject;
import com.domain.House;
import com.repository.HouseRepository;
import com.repository.SearchDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HouseSerivce {

//    @Autowired
//    private HouseRepository houseRepository;

    @Autowired
    private SearchDao searchDao;

    public boolean createHouse(House house) {

        return searchDao.create(JSONObject.toJSONString(house));

//        houseRepository.save(house);
    }

    public boolean deleteHouseById(String id) {

        return searchDao.deleteById(id);
    }

    public void query() {

    }
}
