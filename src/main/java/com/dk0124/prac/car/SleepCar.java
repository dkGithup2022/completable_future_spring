package com.dk0124.prac.car;

import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SleepCar extends Move implements SyncCar {

	/*
	* 매 요청 멈췄다가 실행
	* */

	Random rd = new Random();
	@Override
	public void run(Long millis) {
		move(millis);
	}

	@Override
	public void race(int iter, Long millis) {
		IntStream.range(0,iter).forEach((e)-> { this.run(millis); });
	}

}
