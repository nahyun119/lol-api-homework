package org.ajou.realcoding.lol.homeworklol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HomeworkLolApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeworkLolApplication.class, args);
	}

}
