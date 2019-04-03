package com.ninositsolution.inveleapp.search_everywhere;

import com.google.gson.annotations.SerializedName;

public class SearchEverywhereRequest {

    @SerializedName("user_id")
    private String user_id;

    @SerializedName("search_keyword")
    private String search_keyword;

    @SerializedName("order_by")
    private String order_by;

    public SearchEverywhereRequest(String user_id, String search_keyword, String order_by) {
        this.user_id = user_id;
        this.search_keyword = search_keyword;
        this.order_by = order_by;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getSearch_keyword() {
        return search_keyword;
    }

    public void setSearch_keyword(String search_keyword) {
        this.search_keyword = search_keyword;
    }

    public String getOrder_by() {
        return order_by;
    }

    public void setOrder_by(String order_by) {
        this.order_by = order_by;
    }
}
