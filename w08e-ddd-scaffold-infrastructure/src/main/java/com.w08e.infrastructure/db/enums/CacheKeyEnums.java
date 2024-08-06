package com.w08e.infrastructure.db.enums;

public enum CacheKeyEnums {

    HASH_KEY("miaosha_v1_user_hash"),
    LIMIT_KEY("miaosha_v1_user_limit"),
    STOCK_COUNT("miaosha_v1_stock_count"),
    USER_HAS_ORDER("miaosha_v1_user_has_order");

    private String key;
    private CacheKeyEnums(String key) {
        this.key = key;
    }
    public String getKey() {
        return key;
    }
}
