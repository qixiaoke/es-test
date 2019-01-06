package com.controller.vo;

import lombok.Data;

@Data
public class SearchVo {

    // 城市
    private String cityEnName;

    // 区域
    private String regionEnName;

    // 关键字
    private String keyWords;

    // 最小面积
    private Integer minArea;

    // 最大面积
    private Integer maxArea;

    // 小区
    private String district;

    private int page = 0;

    private int size = 20;
}
