package no.inmeta.course.course;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public void addCourse(Course course) {
        courseRepository.save(course);
    }

    public void editCourse(UUID id, UpdateCourse updatedCourse) {
        Course course = courseRepository.findCourseById(id);
        course.setCourseName(updatedCourse.getCourseName());
        course.setInstructor(updatedCourse.getInstructor());
        course.setRoom(updatedCourse.getRoom());
        course.setCourseBegin(updatedCourse.getCourseBegin());
        course.setCourseEnd(updatedCourse.getCourseEnd());

        courseRepository.save(course);
    }

    public void deleteCourse(UUID id) {
        courseRepository.deleteCourseById(id);
    }

    public List<Course> findCourses(String name) {
        return courseRepository.searchByDirectorEndsWith(name);
    }
}
