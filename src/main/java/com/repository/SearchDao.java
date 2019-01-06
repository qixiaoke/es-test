package com.repository;

import com.alibaba.fastjson.JSONObject;
import com.controller.vo.SearchVo;
import com.domain.House;
import com.domain.HouseFieldKey;
import org.apache.logging.log4j.util.Strings;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.NumberUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class SearchDao {

    public final static String INDEX_NAME = "xunwu";
    public final static String INDEX_TYPE = "house";

    @Autowired
    private TransportClient esClient;

    public boolean create(String json) {
        IndexResponse indexResponse = esClient.prepareIndex(INDEX_NAME, INDEX_TYPE)
                .setSource(json, XContentType.JSON).get();

        return indexResponse.status() == RestStatus.CREATED;
    }

    public boolean deleteById(String id) {
        DeleteResponse deleteResponse = esClient.prepareDelete(INDEX_NAME, INDEX_TYPE, id).get();

        return deleteResponse.status() == RestStatus.NOT_FOUND;
    }

    public List<House> query(SearchVo searchVo) {

        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();

        // 城市
        boolQuery.filter(
                QueryBuilders.termQuery(HouseFieldKey.CITY_EN_NAME, searchVo.getCityEnName())
        );

        // 区域
        if(Strings.isNotEmpty(searchVo.getRegionEnName())) {
            boolQuery.filter(
                    QueryBuilders.termQuery(HouseFieldKey.REGION_EN_NAME, searchVo.getRegionEnName())
            );
        }

        // 关键字
        if(Strings.isNotEmpty(searchVo.getKeyWords())) {
            boolQuery.must(
                    QueryBuilders.multiMatchQuery(searchVo.getKeyWords(),
                            HouseFieldKey.TITLE,
                            HouseFieldKey.TRAFFICE,
                            HouseFieldKey.DISTRICT,
                            HouseFieldKey.ROUND_SERVICE)
            );
        }

        // 面积
        if(Objects.nonNull(searchVo.getMinArea()) && Objects.nonNull(searchVo.getMaxArea())) {
            RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery(HouseFieldKey.AREA);
            rangeQueryBuilder.gte(searchVo.getMinArea());
            rangeQueryBuilder.lte(searchVo.getMaxArea());
            boolQuery.filter(rangeQueryBuilder);
        }

        SearchRequestBuilder searchRequestBuilder = esClient.prepareSearch(INDEX_NAME)
                .setTypes(INDEX_TYPE)
                .setQuery(boolQuery)
                .setFrom(searchVo.getPage())
                .setSize(searchVo.getSize());

        System.out.println(searchRequestBuilder);

        SearchResponse searchResponse = searchRequestBuilder.get();
        if (searchResponse.status() != RestStatus.OK) {
            System.out.println("error");
            return null;
        }

        List<House> houses = new ArrayList<>();
        searchResponse.getHits().forEach(hit -> {
            House house = JSONObject.parseObject(hit.getSourceAsString(), House.class);
            houses.add(house);
        });

        return houses;
    }

    public long aggDistrictHouse(String cityEnName, String regionEnName, String district) {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();

        boolQuery.filter(
                QueryBuilders.termQuery(HouseFieldKey.CITY_EN_NAME, cityEnName)
        );
        boolQuery.filter(
                QueryBuilders.termQuery(HouseFieldKey.REGION_EN_NAME, regionEnName)
        );
        boolQuery.filter(
                QueryBuilders.termQuery(HouseFieldKey.DISTRICT, district)
        );

        SearchRequestBuilder requestBuilder = this.esClient.prepareSearch(INDEX_NAME)
                .setTypes(INDEX_TYPE)
                .setQuery(boolQuery)
                .addAggregation(
                        AggregationBuilders.terms(HouseFieldKey.AGG_DISTRICT)
                                .field(HouseFieldKey.DISTRICT)
                ).setSize(0);

        System.out.println(requestBuilder);

        SearchResponse response = requestBuilder.get();
        if(response.status() == RestStatus.OK) {
            Terms terms = response.getAggregations().get(HouseFieldKey.AGG_DISTRICT);
            if (!CollectionUtils.isEmpty(terms.getBuckets())) {
                long docCount = terms.getBucketByKey(district).getDocCount();
                return docCount;
            }
        }

        return -1;
    }
}
