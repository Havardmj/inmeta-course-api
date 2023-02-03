package no.inmeta.course;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import no.inmeta.course.course.Course;
import no.inmeta.course.course.CourseRepository;
import no.inmeta.course.participant.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
@Profile("testdata")
public class LoadingTestData implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private CourseRepository courseRepository;

    public void loadTestData() {
        loadOrderTestData();
    }

    public void loadOrderTestData() {
        addCourses();
        addTestDataForAllCourses();
    }

    public void addCourses() {
        courseRepository.save(TestData.addScrumCourse());
        courseRepository.save(TestData.addOOCourse());
        courseRepository.save(TestData.addProductOwnerCourse());
        courseRepository.save(TestData.addkotlinCourse());
        courseRepository.save(TestData.addAgileCourse());
        courseRepository.save(TestData.addJavascriptCourse());
    }

    public void addTestDataForAllCourses() {
        addTestDataForScrumCourse();
        addTestDataForKotlinCourse();
        addTestDataForJavascriptCourse();
        addTestDataForProductOwnerCourse();
        addTestDataForAgileCourse();
        addTestDataForOOCourse();
    }

    public void addTestDataForScrumCourse() {
        addTestDataForCourse(
                TestData.SCRUM_COURSE,
                List.of(
                        TestData.aParticipant(),
                        TestData.addSiljeParticipant(),
                        TestData.addBjornParticipant(),
                        TestData.addIvarParticipant(),
                        TestData.addOlaNormanParticipant()
                )
        );
    }

    public void addTestDataForKotlinCourse() {
        addTestDataForCourse(
                TestData.KOTLIN_COURSE,
                List.of(
                        TestData.aParticipant(),
                        TestData.addSiljeParticipant(),
                        TestData.addBjornParticipant(),
                        TestData.addOlaNormanParticipant(),
                        TestData.addNilsParticipant(),
                        TestData.addGustavsenParticipant()
                )
        );
    }

    public void addTestDataForJavascriptCourse() {
        addTestDataForCourse(
                TestData.JAVASCRIPT_COURSE,
                List.of(
                        TestData.aParticipant(),
                        TestData.addSiljeParticipant(),
                        TestData.addBjornParticipant(),
                        TestData.addIvarParticipant(),
                        TestData.addOlaNormanParticipant(),
                        TestData.addNilsParticipant(),
                        TestData.addGustavsenParticipant()
                )
        );
    }

    public void addTestDataForProductOwnerCourse() {
        addTestDataForCourse(
                TestData.PRODUCT_OWNER_COURSE,
                List.of(
                        TestData.aParticipant(),
                        TestData.addSiljeParticipant(),
                        TestData.addBjornParticipant(),
                        TestData.addIvarParticipant(),
                        TestData.addOlaNormanParticipant(),
                        TestData.addNilsParticipant(),
                        TestData.addGustavsenParticipant()
                )
        );
    }

    public void addTestDataForAgileCourse() {
        addTestDataForCourse(
                TestData.AGILE_COURSE,
                List.of(
                        TestData.aParticipant(),
                        TestData.addSiljeParticipant(),
                        TestData.addIvarParticipant(),
                        TestData.addOlaNormanParticipant(),
                        TestData.addNilsParticipant(),
                        TestData.addGustavsenParticipant()
                )
        );
    }

    public void addTestDataForOOCourse() {
        addTestDataForCourse(
                TestData.OO_COURSE,
                List.of(
                        TestData.addSiljeParticipant(),
                        TestData.addIvarParticipant(),
                        TestData.addOlaNormanParticipant(),
                        TestData.addNilsParticipant(),
                        TestData.addGustavsenParticipant()
                )
        );
    }

    public void addTestDataForCourse(String courseName, List<Participant> participants) {
        Course currentCourse = courseRepository.findAllByCourseName(courseName);
        currentCourse.addParticipants(participants);
        courseRepository.save(currentCourse);
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        event.getApplicationContext().getBean(LoadingTestData.class).loadTestData();

    }
}

