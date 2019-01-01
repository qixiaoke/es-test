package com.service;

import com.alibaba.fastjson.JSONObject;
import com.controller.vo.SearchVo;
import com.domain.House;
import com.domain.mysql.HouseDB;
import com.domain.mysql.HouseDetailDB;
import com.domain.mysql.HouseTagDB;
import com.repository.SearchDao;
import com.repository.mysql.HouseDBRepository;
import com.repository.mysql.HouseDetailDBRepository;
import com.repository.mysql.HouseTagDBRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HouseSerivce {

//    @Autowired
//    private HouseRepository houseRepository;

    @Autowired
    private SearchDao searchDao;

    @Autowired
    private HouseDBRepository houseDBRepository;

    @Autowired
    private HouseDetailDBRepository houseDetailDBRepository;

    @Autowired
    private HouseTagDBRepository houseTagDBRepository;

    public HouseDB queryHouseDBById(Long houseId) {

        HouseDB houseDB = houseDBRepository.findById(houseId).get();

        return houseDB;
    }

    public HouseDetailDB queryHouseDetailDBById(Long houseId) {

        HouseDetailDB houseDetailDB = houseDetailDBRepository.findByHouseId(houseId);

        return houseDetailDB;
    }

    public List<HouseTagDB> queryHouseTagDBById(Long houseId) {

        List<HouseTagDB> houseTagDBs = houseTagDBRepository.findByHouseId(houseId);

        return houseTagDBs;
    }

    public boolean createHouseById(long houseId) {

        HouseDB houseDB = queryHouseDBById(houseId);
        HouseDetailDB houseDetailDB = queryHouseDetailDBById(houseId);
        List<HouseTagDB> houseTagDBs = queryHouseTagDBById(houseId);

        House house = wrapHouse(houseDB, houseDetailDB, houseTagDBs);
        boolean create = createHouse(house);

        return create;
    }

    public boolean createHouse(House house) {

        return searchDao.create(JSONObject.toJSONString(house));

//        houseRepository.save(house);
    }

    public boolean deleteHouseById(String id) {

        return searchDao.deleteById(id);
    }

    public List<House> query(SearchVo searchVo) {

        List<House> house = searchDao.query(searchVo);

        return house;

    }

    private House wrapHouse(HouseDB houseDB, HouseDetailDB houseDetailDB, List<HouseTagDB> houseTagDBs) {

        House house = new House();
        BeanUtils.copyProperties(houseDB, house);
        BeanUtils.copyProperties(houseDetailDB, house);
        List<String> tags = houseTagDBs.stream().map(houseTagDB -> houseTagDB.getName()).collect(Collectors.toList());
        house.setTags(tags);

        Date now = new Date();
        house.setCreateTime(now);
        house.setLastUpdateTime(now);

        return house;
    }
}
