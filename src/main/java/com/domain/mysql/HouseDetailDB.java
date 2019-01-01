package com.domain.mysql;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "house_detail")
@Data
public class HouseDetailDB {

  @Id
  private Long id;
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
  private Long houseId;
}
