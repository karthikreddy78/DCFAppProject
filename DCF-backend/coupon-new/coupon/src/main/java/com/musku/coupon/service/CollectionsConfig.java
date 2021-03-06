package com.musku.coupon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;

import javax.annotation.PostConstruct;

@Configuration
@DependsOn("mongoTemplate")
public class CollectionsConfig {

    @Autowired
    private MongoTemplate mongoTemplate;

    //Adding a unique index to coupon name
    @PostConstruct
    public void initIndexes() {
        mongoTemplate.indexOps("coupons") // collection name string or .class
            .ensureIndex(
                new Index().on("couponname",  Sort.Direction.ASC).unique());
    }
}