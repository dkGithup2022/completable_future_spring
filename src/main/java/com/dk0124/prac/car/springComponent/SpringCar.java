package com.dk0124.prac.car.springComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.stream.IntStream;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.dk0124.prac.car.Car;
import com.dk0124.prac.car.Move;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class SpringCar extends Move implements Car {


	private final Executor executor;

	@Override
	public void race(int iter, Long millis) {
		log.info("\n *** RACE START ***  \n");

		List<CompletableFuture> futures = new ArrayList<>();
		IntStream.range(0,iter)
			.forEach(v->{futures.add(run(millis));});

		CompletableFuture.allOf(
			futures.toArray(new CompletableFuture[futures.size()])
		).join();

		log.info("RELEASE ALL ~");
		for(CompletableFuture future: futures)
			future.join();

	}

	public CompletableFuture run(Long millis){
		return CompletableFuture.runAsync(()->move(millis),executor);
	}


	public void raceWithAsync(int iter, Long millis) {
		log.info("\n *** RACE START ***  \n");
		List<CompletableFuture> futures = new ArrayList<>();
		IntStream.range(0,iter)
			.forEach(v->{futures.add(runWithAsync(millis));});

		CompletableFuture.allOf(
			futures.toArray(new CompletableFuture[futures.size()])
		).join();

		log.info("RELEASE ALL ~");
		for(CompletableFuture future: futures)
			future.join();

	}


	@Async
	public CompletableFuture runWithAsync(Long millis){
		return CompletableFuture.runAsync(()->move(millis),executor);
	}


}
