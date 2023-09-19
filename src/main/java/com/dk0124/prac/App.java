package com.dk0124.prac;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dk0124.prac.car.BlockedCar;
import com.dk0124.prac.car.CompletableFutureCar;
import com.dk0124.prac.car.SleepCar;
import com.dk0124.prac.car.springComponent.SpringCar;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class App implements ApplicationRunner {

	private final SleepCar sleepCar;
	private final BlockedCar blockedCar;
	private final CompletableFutureCar completableFutureCar;

	private final SpringCar springCar;

	private final int ITER = 5;
	private final Long DURATION = 1000L;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		raceSpring();
	}

	public void raceNatice() throws Exception {
		log.info("\nSleep car goes , it stop this main thread and takes more than 3s ");

		Long begin = System.currentTimeMillis();
		sleepCar.race(ITER, DURATION);

		log.info(" *** DONE ***  : takes : {} \n\n", System.currentTimeMillis() - begin);

		log.info(" Blocked Car go without Blocking ( can not check when all thread is done) ");

		begin = System.currentTimeMillis();

		blockedCar.race(ITER, DURATION);

		Thread.sleep(DURATION + 1000L);
		blockedCar.release();

		log.info(" *** DONE ***  :  release all Future \n\n");

		log.info(""
			+ "\t\t/**\n"
			+ "\t\t * 위의 BLOCKING 은 모든 \"쓰레드 작업 종료\" 라는 이벤트를 루프를 통해서 밖에 확인 못함 .. \n"
			+ "\t\t * 아래의 Completable Future 과  all of 함수를 통해 가능하다 .\n"
			+ "\t\t * \n"
			+ "\t\t * **/"
			+ "");
		/**
		 * 위의 BLOCKING 은 모든 "쓰레드 작업 종료" 라는 이벤트를 루프를 통해서 밖에 확인 못함 ..
		 * 아래의 Completable Future 과  all of 함수를 통해 가능하다 .
		 *
		 * **/

		log.info(" CompletableFuture Car go without Blocking  ");

		begin = System.currentTimeMillis();

		completableFutureCar.race(ITER, DURATION);

		log.info(" *** DONE ***  : takes : {} \n\n", System.currentTimeMillis() - begin);
	}

	public void raceSpring() {
		springCar.race(ITER, DURATION);
		springCar.raceWithAsync(ITER, DURATION);
	}

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
