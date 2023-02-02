package no.inmeta.course;

import org.springframework.boot.builder.SpringApplicationBuilder;

public class LocalCourseApplication extends CourseApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(LocalCourseApplication.class)
				.profiles("local", "testdata")
				.build()
				.run();
	}
}
