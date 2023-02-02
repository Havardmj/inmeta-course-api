package no.inmeta.course.course;

import no.inmeta.course.TestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.UUID;

@SpringBootTest
@ActiveProfiles({"local", "testdata"})
@DirtiesContext
public class CourseRepositoryTest {

    @Autowired
    CourseRepository courseRepository;


    private void addAndSaveACourse(UUID id, String courseName) {
        Course newCourse = TestData.addCourse(id, courseName);
        courseRepository.save(newCourse);
    }

    @Test
    public void add_course() {
        Assertions.assertDoesNotThrow(() -> {
            addAndSaveACourse(UUID.randomUUID(), TestData.SCRUM_COURSE);
        });
    }

    @Test
    public void add_participants_of_a_existing_course() {
        final UUID TEST_ID = UUID.randomUUID();
        final Integer NUMBER_OF_PARTICIPANTS_IS_TWO = 2;
        addAndSaveACourse(TEST_ID, TestData.SCRUM_COURSE);

        Course currentCourse = courseRepository.findAllByCourseName(TestData.SCRUM_COURSE);
        currentCourse.addParticipants(List.of(TestData.aParticipant(), TestData.anotherParticipant()));
        courseRepository.save(currentCourse);

        Course courseFound = courseRepository.findAllByCourseName(TestData.SCRUM_COURSE);

        Assertions.assertEquals(courseFound.getParticipant().size(), NUMBER_OF_PARTICIPANTS_IS_TWO);
    }
}
