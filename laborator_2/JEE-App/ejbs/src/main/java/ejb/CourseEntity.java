package ejb;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CourseEntity {
    @Id
    @GeneratedValue
    private int id;
    private String nume;
    private int credits;
    private int semester_number;

    public CourseEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getSemester_number() {
        return semester_number;
    }

    public void setSemester_number(int semester_number) {
        this.semester_number = semester_number;
    }
}