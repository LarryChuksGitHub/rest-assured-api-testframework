package tutorial.pojostudent;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentBuilder {
    private int matNr;
    private int nrOfCar;
    private String firstname;
    private String lastname;
    private String email;
    private String address;

    public StudentBuilder builder(){
        return this;
    }
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Student build(){
        return new Student(this.matNr,this.nrOfCar, this.firstname, this.lastname, this.email,this.address);
    }

    public StudentBuilder setMatNr(int matNr) {
        this.matNr = matNr;
        return this;
    }

    public StudentBuilder setNrOfCar(int nrOfCar) {
        this.nrOfCar = nrOfCar;
        return this;
    }

    public StudentBuilder setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public StudentBuilder setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public StudentBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public StudentBuilder setAddress(String address) {
        this.address = address;
        return this;
    }
}
