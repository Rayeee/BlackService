package me.zgy.es;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rayee on 2017/12/28.
 */
public class ESSearchResult<T> {

    private Long total;
    private String scrollId;
    private List<T> hits = new ArrayList<>();
    private JsonObject aggregations;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getHits() {
        return hits;
    }

    public void setHits(List<T> hits) {
        this.hits = hits;
    }

    public String getScrollId() {
        return scrollId;
    }

    public void setScrollId(String scrollId) {
        this.scrollId = scrollId;
    }

    public JsonObject getAggregations() {
        return aggregations;
    }

    public void setAggregations(JsonObject aggregations) {
        this.aggregations = aggregations;
    }

}
