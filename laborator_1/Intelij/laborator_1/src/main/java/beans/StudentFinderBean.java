package beans;

public class StudentFinderBean implements java.io.Serializable{
    private String nume = null;
    private String prenume = null;

    public StudentFinderBean() {
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }
}
