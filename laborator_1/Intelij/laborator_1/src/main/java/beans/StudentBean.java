package beans;

public class StudentBean implements java.io.Serializable {
    private String nume = null;
    private String prenume = null;

    private int varsta = 0;
    private int CNP = 0;

    private String adresa = null;
    private String hobby = null;
    private String initialaTatalui = null;

    public StudentBean() {
    }

    public String getNume() {
        return nume;
    }

    public int getCNP() {
        return CNP;
    }

    public void setCNP(int CNP) {
        this.CNP = CNP;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getInitialaTatalui() {
        return initialaTatalui;
    }

    public void setInitialaTatalui(String initialaTatalui) {
        this.initialaTatalui = initialaTatalui;
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

    public int getVarsta() {
        return varsta;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }
}