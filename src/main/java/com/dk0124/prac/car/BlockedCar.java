package com.dk0124.prac.car;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class BlockedCar extends Move implements AsyncCar<Future> {

	/*
	 *  비동기 & 블락 으로 수행되는 레이스
	 *
	 * race 조건은 get 을 호출하지 않아서
	 * */
	Random rd = new Random();
	List<Future> results = new ArrayList<>();
	ExecutorService executor = Executors.newCachedThreadPool();

	@Override
	public Future run(Long millis) {
		return executor.submit(() -> {
			move(millis);
		});
	}

	@Override
	public void race(int iter, Long millis) {
		IntStream.range(0, iter).forEach((e) -> {
			results.add(this.run(millis));
		});
	}

	public void release() throws ExecutionException, InterruptedException {
		for(Future future : results)
			future.get();
	}
}
