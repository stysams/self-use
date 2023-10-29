package com.stysa;

import com.stysa.client.MessageProcessor;
import com.stysa.client.QueueHolder;
import com.stysa.client.ThreadFactoryImpl;
import com.stysa.client.model.NettyMsgModel;
import com.stysa.server.NettyServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
@Slf4j
@Configuration
public class SelfNettyTcpApplication implements CommandLineRunner {

	@Autowired
	private MessageProcessor messageProcessor;

	private static final Integer MAIN_THREAD_POOL_SIZE = 4;

	private final ExecutorService executor = Executors.newFixedThreadPool(MAIN_THREAD_POOL_SIZE,
			new ThreadFactoryImpl("Demo_TestThread_", false));

	@Autowired
	private NettyServer nettyServer;

	@Override
	public void run(String... args) throws Exception {

		// 启动队列
		Thread loopThread = new Thread(new LoopThread());
		loopThread.start();

		// 启动客户端
		nettyServer.start();
	}

	public class LoopThread implements Runnable {
		@Override
		public void run() {
			for (int i = 0; i < MAIN_THREAD_POOL_SIZE; i++) {
				executor.execute(() -> {
					while (true) {
						//取走BlockingQueue里排在首位的对象,若BlockingQueue为空,阻断进入等待状态直到
						try {
							NettyMsgModel nettyMsgModel = QueueHolder.get().take();
							messageProcessor.process(nettyMsgModel);
						} catch (InterruptedException e) {
							log.error(e.getMessage(), e);
						}
					}
				});
			}
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(SelfNettyTcpApplication.class, args);
	}

}
