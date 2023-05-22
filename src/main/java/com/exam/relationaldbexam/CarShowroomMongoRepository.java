package com.exam.relationaldbexam;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface CarShowroomMongoRepository extends MongoRepository<CarShowroom, UUID> {
}
