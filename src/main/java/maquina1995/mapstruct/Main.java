package maquina1995.mapstruct;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import maquina1995.mapstruct.configuration.SpringConfig;

public class Main {

	public static void main(String... args) {

		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class)) {

		}
	}
}
