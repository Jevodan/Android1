package com.jevo.alexander.lesson1.model.entity.weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ListCities {

    @SerializedName("cnt")
    @Expose
    private Integer cnt;
    @SerializedName("list")
    @Expose
    private java.util.List<OneCity> list = null;

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public java.util.List<OneCity> getList() {
        return list;
    }

    public void setList(List<OneCity> list) {
        this.list = list;
    }

}
