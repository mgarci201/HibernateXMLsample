package hibernatestudent3;

import java.util.Set;

public class Student{
    
    private long studentId;
    private String matricNo;
    private String firstName;
    private String surname;
    private String email;
    private Programme programme;
    private Set<Programme> programmes;
    
    public Student() {
        this.matricNo = null;
        this.firstName = null;
        this.surname = null;
        this.email = null;
        this.programme = null;
    }
    
    public Student(String matric, String fn, String ln) {
        this.matricNo = matric;
        this.firstName = fn;
        this.surname = ln;
        this.email = null;
        this.programme = null;
    }
    
    public Student (String matric, String fn, String ln, String e, Programme prg) {        
        this.matricNo = matric;
        this.firstName = fn;
        this.surname = ln;
        this.email = e;
        this.programme = prg;
    }
    
    
    public long getStudentId() {
        return this.studentId;
    }
    
    public void setStudentId(long sid) {
        this.studentId = sid;
    }
        
    public String getMatricNo() {
        return this.matricNo;
    }
    
    public void setMatricNo(String matric) {
        this.matricNo = matric;
    }
    
    public String getFirstName() {
        return this.firstName;
    }
    
    public void setFirstName(String fn) {
        this.firstName = fn;
    }
    
    public String getSurname() {
        return this.surname;
    }
    
    public void setSurname(String ln) {
        this.surname = ln;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public Programme getProgramme() {
        return this.programme;
    }
    
    public void setProgramme(Programme prog) {
        this.programme = prog;
    }
    
        public Set<Programme> getProgrammes() {
        return this.programmes;
    }
    
    public void setProgrammes(Set<Programme> programme) {
        this.programmes = programmes;
    }
    
    @Override
    public int hashCode() {
        return getMatricNo().hashCode() * 31 + getSurname().hashCode() * 31 +  getFirstName().hashCode() * 31 + getEmail().hashCode() * 31 + getProgramme().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Student) {
            Student s = (Student)o;
            return  s.getMatricNo() == getMatricNo() &&
                    s.getFirstName().equals(getFirstName()) && 
                    s.getSurname().equals(getSurname()) && 
                    s.getEmail().equals(getEmail()) &&
                    s.getProgramme().equals(getProgramme());
        } else {
            return false;
        }
    }
    
    @Override
    public String toString() {
        return  "student id: " + getStudentId() + ", " +
                "matric number: " + getMatricNo() + ", " +
                "first name: " + getFirstName() + ", " +
                "surname: " + getSurname() + ", " +
                "email: " + getEmail() + ", " + 
                "programme: " + getProgramme();
    }
    
}
