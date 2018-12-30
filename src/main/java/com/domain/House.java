package com.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;
import java.util.List;

@Document(indexName = "xunwu", type = "house")
public class House {

      @Id
      private long houseId;
      private String title;
      private int price;
      private Date createTime;
      private Date lastUpdateTime;
      private String cityEnName;
      private String regionEnName;
      private String tags;

      public long getHouseId() {
            return houseId;
      }

      public void setHouseId(long houseId) {
            this.houseId = houseId;
      }

      public String getTitle() {
            return title;
      }

      public void setTitle(String title) {
            this.title = title;
      }

      public int getPrice() {
            return price;
      }

      public void setPrice(int price) {
            this.price = price;
      }

      public Date getCreateTime() {
            return createTime;
      }

      public void setCreateTime(Date createTime) {
            this.createTime = createTime;
      }

      public Date getLastUpdateTime() {
            return lastUpdateTime;
      }

      public void setLastUpdateTime(Date lastUpdateTime) {
            this.lastUpdateTime = lastUpdateTime;
      }

      public String getCityEnName() {
            return cityEnName;
      }

      public void setCityEnName(String cityEnName) {
            this.cityEnName = cityEnName;
      }

      public String getRegionEnName() {
            return regionEnName;
      }

      public void setRegionEnName(String regionEnName) {
            this.regionEnName = regionEnName;
      }

      public String getTags() {
            return tags;
      }

      public void setTags(String tags) {
            this.tags = tags;
      }
}
