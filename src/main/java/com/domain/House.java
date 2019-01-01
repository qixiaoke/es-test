package com.domain;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class House {

    private Long houseId;
    private String title;
    private Integer price;
    private Integer area;
    private Integer room;
    private Integer floor;
    private Integer totalFloor;
    private Integer watchTimes;
    private Integer buildYear;
    private Integer status;
    private Date createTime;
    private Date lastUpdateTime;
    private String cityEnName;
    private String regionEnName;
    private String cover;
    private Integer direction;
    private Integer distanceToSubway;
    private Integer parlour;
    private String district;
    private Integer adminId;
    private Integer bathroom;
    private String street;
    private String description;
    private String layoutDesc;
    private String traffic;
    private String roundService;
    private Integer rentWay;
    private String address;
    private Integer subwayLineId;
    private String subwayLineName;
    private Integer subwayStationId;
    private String subwayStationName;
    private List<String> tags;
}
