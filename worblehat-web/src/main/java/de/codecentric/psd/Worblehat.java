package de.codecentric.psd;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Worblehat {

	public static void main(String[] args) {
		try(Scanner scan = new Scanner(System.in)) {
			ConfigurableApplicationContext applicationContext = SpringApplication.run(Worblehat.class, args);

			// this code is basically to (a) demonstrate how to stop a Spring application and (b)
			// get rid of the SonarQube warning to close the context properly
			System.out.println("Enter 'stop' to stop Worblehat.");

			String line;
			do {
				line = scan.nextLine();
			} while (!"stop".equals(line));
			applicationContext.close();
		}
	}

}
