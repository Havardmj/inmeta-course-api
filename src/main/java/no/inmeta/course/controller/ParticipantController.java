package no.inmeta.course.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import no.inmeta.course.course.Course;
import no.inmeta.course.course.CourseRepository;
import no.inmeta.course.participant.Participant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/participant")
@Slf4j
@RequiredArgsConstructor
public class ParticipantController {

    private final CourseRepository courseRepository;

    // add participant
    @PostMapping("/add-participant/{courseId}")
    public ResponseEntity<?> addParticipant(
            @PathVariable("courseId") UUID id,
            @RequestBody Participant participant
    ) throws Exception {
        try {
            Course course = courseRepository.findCourseById(id);
            course.addParticipant(participant);
            courseRepository.save(course);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new Exception();
        }
    }

    // edit participant
    @PutMapping("/edit-participant/{courseId}/{email}")
    public ResponseEntity<?> editParticipant(
            @PathVariable("courseId") UUID id,
            @PathVariable("email") String emailAddress,
            @RequestBody Participant participant
    ) throws Exception {
        try {
            Course course = courseRepository.findCourseById(id);
            course.editParticipant(emailAddress, participant);
            courseRepository.save(course);
            return ResponseEntity.ok().build();

        } catch (Exception e) {
            throw new Exception();
        }
    }

    // remove participant from course
    @GetMapping("/remove-participant/{courseId}/{email}")
    public ResponseEntity<?> removeParticipant(
            @PathVariable("courseId") UUID id,
            @PathVariable("email") String emailAddress
    ) throws Exception {
        try {
            Course course = courseRepository.findCourseById(id);
            course.removeParticipant(emailAddress);
            courseRepository.save(course);
            return ResponseEntity.ok().build();
        }catch (Exception e) {
            throw new Exception();
        }
    }

    // search participant from course
    @GetMapping("/find-participant")
    public ResponseEntity<?> findParticipant(
            @PathVariable("courseId") UUID id,
            @PathVariable("searchKey") String searchKey
    ) throws Exception {
        try {
            Course course = courseRepository.findCourseById(id);
            List<Participant> participants = course.findParticipants(searchKey);
            return new ResponseEntity<>(participants, HttpStatus.OK);
        }catch (Exception e) {
            throw new Exception();
        }

    }

    // list all participants from a given course
    @GetMapping("/get-all-participants")
    public ResponseEntity<?> getAllParticipants(@PathVariable("courseId") UUID id) throws Exception {
        try {
            List<Participant> participant = courseRepository.findCourseById(id).getParticipant();

            return new ResponseEntity<>(participant, HttpStatus.OK);
        }catch (Exception e) {
            throw new Exception();
        }
    }

    // get selected courses for a time period.
}
