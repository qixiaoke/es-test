package com.domain.mysql;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "house")
@Data
public class HouseDB {

  @Id
  private Long id;

  private String title;
  private Integer price;
  private Integer area;
  private Integer room;
  private Integer floor;
  private Integer totalFloor;
  private Integer watchTimes;
  private Integer buildYear;
  private Integer status;
  private String createTime;
  private String lastUpdateTime;
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

}
