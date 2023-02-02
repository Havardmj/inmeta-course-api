package no.inmeta.course.course;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import no.inmeta.course.participant.Participant;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    private String courseName;

    private String instructor;

    private String room;

    private LocalDate courseBegin;

    private LocalDate courseEnd;

    @OneToMany(
            mappedBy = "course",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    @Fetch(FetchMode.SUBSELECT)
    private List<Participant> participant = new ArrayList<Participant>();

    public Course(
            UUID id,
            String courseName,
            String instructor,
            String room,
            LocalDate courseBegin,
            LocalDate courseEnd
    ) {
        this.id = id;
        this.courseName = courseName;
        this.instructor = instructor;
        this.room = room;
        this.courseBegin = courseBegin;
        this.courseEnd = courseEnd;
    }

    public Course(
            String courseName,
            String instructor,
            String room,
            LocalDate courseBegin,
            LocalDate courseEnd
    ) {
        this.courseName = courseName;
        this.instructor = instructor;
        this.room = room;
        this.courseBegin = courseBegin;
        this.courseEnd = courseEnd;
    }

    public List<String> findUsingStream(String search, List<String> list) {
        List<String> matchingElements = list.stream()
                .filter(str -> str.trim().contains(search))
                .collect(Collectors.toList());

        return matchingElements;
    }

    private boolean matchVaule(String matchValue, String searchKey) {
        return matchValue.trim().toLowerCase().contains(searchKey.trim().toLowerCase());
    }

    public List<Participant> findParticipants(String searchKey) {
        return this.participant.stream().filter(
                p -> matchVaule(p.getEmailAddress(), searchKey) ||
                        matchVaule(p.getFirstName(), searchKey) ||
                        matchVaule(p.getLastName(), searchKey)
        ).toList();
    }


    public void removeParticipant(String emailAddress) {
        List<Participant> updatedParticipants = new ArrayList<Participant>();

        Iterator<Participant> courseParticipants = this.participant.iterator();
        while (courseParticipants.hasNext()) {
            if (Objects.equals(courseParticipants.next().getEmailAddress(), emailAddress)) {
                courseParticipants.remove();
            }
            courseParticipants.next();
        }
        courseParticipants.forEachRemaining(updatedParticipants::add);
        this.participant = updatedParticipants;

    }

    public void editParticipant(String emailAddress, Participant participant) {
        this.setParticipant(this.participant.stream().map(p -> {
           if(p.getEmailAddress().equals(emailAddress)) {
               return participant;
           }
           return p;
        }).collect(Collectors.toList()));
    }

    public void addParticipants(List<Participant> participant) {
        participant.forEach(p -> p.setCourse(this));
        this.participant.addAll(participant);
    }

    public void addParticipant(Participant participant) {
        participant.setCourse(this);
        this.participant.add(participant);
    }
}
