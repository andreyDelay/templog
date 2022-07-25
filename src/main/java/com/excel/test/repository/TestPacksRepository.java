package com.excel.test.repository;

import com.excel.test.model.TestPack;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TestPacksRepository extends MongoRepository<TestPack, String> {

}
