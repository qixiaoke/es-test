package com.domain.mysql;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "house_tag")
@Data
public class HouseTagDB {

  @Id
  private Long id;
  private Long houseId;
  private String name;
}
