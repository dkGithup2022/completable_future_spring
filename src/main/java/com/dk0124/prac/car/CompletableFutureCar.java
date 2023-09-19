package com.dk0124.prac.car;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CompletableFutureCar extends Move implements Car {

	ExecutorService executorService = Executors.newCachedThreadPool();

	@Override
	public void race(int iter, Long millis) {
		log.info("RACE AND RELEASE ");

		List<CompletableFuture> futures = new ArrayList<>();

		IntStream.range(0,iter)
			.forEach(e-> {
				try {
					futures.add(runWithCompletable(millis));
				} catch (ExecutionException ex) {
					throw new RuntimeException(ex);
				} catch (InterruptedException ex) {
					throw new RuntimeException(ex);
				}
			});

		CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]))
			.join()
			;

		for(CompletableFuture future: futures)
			future.join();

	}

	public CompletableFuture runWithCompletable(Long millis) throws ExecutionException, InterruptedException {
		return CompletableFuture.supplyAsync(
			() -> {
				move(millis);
				return "FINISHED";
			}
			, executorService);
	}


}
