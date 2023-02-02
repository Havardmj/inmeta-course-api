package no.inmeta.course.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import no.inmeta.course.course.Course;
import no.inmeta.course.course.CourseService;
import no.inmeta.course.course.UpdateCourse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/course")
@Slf4j
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    // add course
    @PostMapping("/add-course")
    public ResponseEntity<?> addCourse(@RequestBody Course course) throws Exception {
        try {
            courseService.addCourse(course);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new Exception();
        }
    }

    // edit course
    @PutMapping("/edit-course/{courseId}")
    public ResponseEntity<?> editCourse(
            @PathVariable("courseId") UUID id,
            @RequestBody UpdateCourse updatedCourse
    ) throws Exception {
        try {
            courseService.editCourse(id, updatedCourse);
            return ResponseEntity.ok().build();

        } catch (Exception e) {
            throw new Exception();
        }
    }

    // delete course
    @GetMapping("/delete-course/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable("courseId") UUID id) throws Exception {
        try {
            courseService.deleteCourse(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new Exception();
        }
    }

    // search course
    @GetMapping("/find-course/{name}")
    public ResponseEntity<?> findCourses(@PathVariable("name") String name) throws Exception {
        try {
            List<Course> courses = courseService.findCourses(name);
            return new ResponseEntity<List<Course>>(courses, HttpStatus.OK);
        } catch (Exception e) {
            throw new Exception();
        }
    }

    // list all courses
    @GetMapping("get-all-courses")
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> allCouses = courseService.getAllCourses();
        return new ResponseEntity<List<Course>>(allCouses, HttpStatus.OK);
    }
}
