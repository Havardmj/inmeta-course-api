package no.inmeta.course.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface CourseRepository extends JpaRepository<Course, UUID>, JpaSpecificationExecutor {

    Course findAllByCourseName(String name);

    Course findCourseById(UUID id);

    Course deleteCourseById(UUID id);

    @Query("select c from Course c where c.courseName LIKE :name")
    List<Course> searchByDirectorEndsWith(@Param("name") String name);
}
