package no.inmeta.course.course;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCourse {
    private String courseName;

    private String instructor;

    private String room;

    private LocalDate courseBegin;

    private LocalDate courseEnd;
}
