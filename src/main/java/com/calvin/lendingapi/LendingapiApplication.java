package com.calvin.lendingapi;

import com.africastalking.AfricasTalking;
import com.africastalking.Logger;
import com.africastalking.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;


@SpringBootApplication
@EnableScheduling
public class LendingapiApplication {

	private static Server server;

	public static void main(String[] args) throws IOException {
		setupAfricastalking();
		SpringApplication.run(LendingapiApplication.class, args);
	}


	private static void setupAfricastalking() throws IOException {
		// SDK Server
		AfricasTalking.initialize("calvin", "1862b765ee2ac5bc1023a29da10c10618eb42dc76550e83839546db0706efd8e");
		AfricasTalking.setLogger(new Logger(){
			@Override
			public void log(String message, Object... args) {
				System.out.println(message);
			}
		});
		
		server = new Server();
		server.startInsecure();
	}
}
