package com.dk0124.prac.car;

import java.util.Random;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class Move {
	Random rd = new Random();
	public  void move(Long millis){
		int carNum = rd.nextInt(1000);
		try {
			log.info("  CAR  goes : {} ", carNum);
			Thread.sleep(millis);
			log.info("  CAR  ARRIVED : {} ", carNum);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
