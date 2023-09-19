package com.dk0124.prac.car;

public interface AsyncCar<T> extends Car {
	T run(Long millis);
}
