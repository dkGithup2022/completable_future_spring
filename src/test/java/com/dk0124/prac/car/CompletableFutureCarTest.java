package com.dk0124.prac.car;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class CompletableFutureCarTest {

	CompletableFutureCar car = new CompletableFutureCar();


	@Test
	public void empty(){
		assertNotNull(car);
	}

	@Test
	public void tryCompletable() throws ExecutionException, InterruptedException {
		log.info("RUN AND HOLD");
		car.runWithCompletable(1000L);
		log.info("*** DONE *** ");
	}

	@Test
	public void race(){
		car.race(3,1000L);
	}
}