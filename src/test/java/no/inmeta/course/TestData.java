package no.inmeta.course;

import lombok.AllArgsConstructor;
import lombok.Data;
import no.inmeta.course.course.Course;
import no.inmeta.course.participant.Participant;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

@Data
@AllArgsConstructor
public class TestData {

    public static final String SCRUM_COURSE = "scrum course 101";
    public static final String OO_COURSE = "object oriented programming";
    public static final String KOTLIN_COURSE = "kotlin programming course";
    public static final String JAVASCRIPT_COURSE = "javascript programming course";
    public static final String PRODUCT_OWNER_COURSE = "product owner course";
    public static final String AGILE_COURSE = "agile course";




    public static final String A_COURSE_UUID = "2463f61b-c86f-4921-b167-ef41aa1889ea";

    public static final String A_SECOND_COURSE_UUID = "ce72b27a-1260-4e2f-92c7-8536d9a02d48";

    public static Course addScrumCourse() {
        return addCourse(UUID.fromString(A_COURSE_UUID), SCRUM_COURSE);
    }

    public static Course addOOCourse() {
        return addCourse(UUID.fromString(A_SECOND_COURSE_UUID), OO_COURSE);
    }

    public static Course addkotlinCourse() {
        return addCourse(UUID.randomUUID(), KOTLIN_COURSE, "Trond Heimen", "317");
    }

    public static Course addJavascriptCourse() {
        return addCourse(UUID.randomUUID(), JAVASCRIPT_COURSE, "Per Persen", "411");
    }

    public static Course addProductOwnerCourse() {
        return addCourse(UUID.randomUUID(), PRODUCT_OWNER_COURSE, "Nina Nilsen", "310");
    }

    public static Course addAgileCourse() {
        return addCourse(UUID.randomUUID(), AGILE_COURSE, "Kari Nilsen", "210");
    }

    public static Course addCourse(UUID courseId, String courseName, String instructor, String room) {
        Random r = new Random();
        int randomIntStart = r.nextInt(100) + 1;
        int randomIntEnd = r.nextInt(100) + 1;
        return new Course(
                courseId,
                courseName,
                instructor,
                room,
                LocalDate.now().plusDays(randomIntStart),
                LocalDate.now().plusDays(randomIntEnd)
        );
    }

    public static Course addCourse(UUID courseId, String courseName) {
        return new Course(
                courseId,
                courseName,
                "Ola Hansen",
                "101",
                LocalDate.now().plusMonths(2),
                LocalDate.now().plusMonths(2)
        );
    }

    public static Participant aParticipant() {
        return new Participant(UUID.randomUUID(), "Ole", "Hansen", "olehansen@online.no");
    }

    public static Participant anotherParticipant() {
        return new Participant(UUID.randomUUID(), "Kari", "Olsen", "kariolsen@online.no");
    }
    public static Participant addOlaNormanParticipant() {
        return new Participant(UUID.randomUUID(), "Ola", "Norman", "olanorman@online.no");
    }

    public static Participant addGustavsenParticipant() {
        return new Participant(UUID.randomUUID(), "Gustav", "Gustavsen", "gustavsen@online.no");
    }

    public static Participant addNilsParticipant() {
        return new Participant(UUID.randomUUID(), "Nils", "Carew", "nilsenn@online.no");
    }

    public static Participant addIvarParticipant() {
        return new Participant(UUID.randomUUID(), "Ivar", "Åsen", "Ivar@yahoo.no");
    }

    public static Participant addBjornParticipant() {
        return new Participant(UUID.randomUUID(), "Bjørn stjerne", "Bjørnson", "bjorn@gmail.no");
    }

    public static Participant addSiljeParticipant() {
        return new Participant(UUID.randomUUID(), "Silje", "Jensen", "siljejensen@gmail.com.no");
    }
}
