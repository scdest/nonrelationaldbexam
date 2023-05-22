package com.exam.relationaldbexam;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping("/car-showrooms")
@RequiredArgsConstructor
public class CarShowroomController {

    private final CarShowroomRedisRepository carShowroomRedisRepository;
    private final CarShowroomMongoRepository carShowroomMongoRepository;

    @PostMapping
    public CarShowroom createCarShowroom(@RequestBody CarShowroom carShowroom) {
        var saved = carShowroomRedisRepository.save(carShowroom);
        carShowroomMongoRepository.save(saved);
        return saved;
    }

    @GetMapping("/{id}")
    public CarShowroom getCarShowroom(@PathVariable("id") String id) {
        var result = carShowroomRedisRepository.findById(UUID.fromString(id));
        return result.orElseGet(() -> carShowroomMongoRepository.findById(UUID.fromString(id)).orElseThrow());
    }

    @DeleteMapping("/{id}")
    public void deleteCarShowroom(@PathVariable("id") String id) {
        carShowroomRedisRepository.deleteById(UUID.fromString(id));
        carShowroomMongoRepository.deleteById(UUID.fromString(id));
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> handleException(NoSuchElementException ex) {
        return ResponseEntity.notFound().build();
    }
}
