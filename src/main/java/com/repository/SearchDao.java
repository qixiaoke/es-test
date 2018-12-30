package com.repository;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonParser;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.delete.DeleteRequestBuilder;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SearchDao {

    public final static String INDEX_NAME = "xunwu";
    public final static String INDEX_TYPE = "house";

    @Autowired
    private TransportClient esClient;

    public boolean create(String json) {
        IndexResponse indexResponse = esClient.prepareIndex(INDEX_NAME, INDEX_TYPE)
                .setSource(json, XContentType.JSON).get();

        return indexResponse.getResult() == DocWriteResponse.Result.CREATED;
    }

    public boolean deleteById(String id) {
        DeleteResponse deleteResponse = esClient.prepareDelete(INDEX_NAME, INDEX_TYPE, id).get();

        return deleteResponse.getResult() == DocWriteResponse.Result.DELETED;
    }
}
