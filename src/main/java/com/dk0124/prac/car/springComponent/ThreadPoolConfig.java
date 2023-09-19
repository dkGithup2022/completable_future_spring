package com.dk0124.prac.car.springComponent;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@EnableAsync(proxyTargetClass = true)
@Configuration
public class ThreadPoolConfig {
	@Bean
	public Executor treadPoolTaskExecutor() {
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setCorePoolSize(10);
		taskExecutor.setMaxPoolSize(1000);
		taskExecutor.setQueueCapacity(1000);
		taskExecutor.setThreadNamePrefix("THIS_IS_PREFIX_");
		taskExecutor.initialize();
		return taskExecutor;
	}

}
