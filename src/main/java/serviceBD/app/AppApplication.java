package serviceBD.app;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import serviceBD.app.Controller.PersonController;

@SpringBootApplication
public class AppApplication {

	public static void main(String[] args) {
		
		new File(PersonController.uploadDirectory).mkdir();

		SpringApplication.run(AppApplication.class, args);
	}
}
