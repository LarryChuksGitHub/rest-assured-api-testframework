package tutorial.pojostudent;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@ToString
//@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonInclude(value = JsonInclude.Include.NON_NULL)

public  class Student {


    @Setter
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private int matNr;
    private int nrOfCar;
    private String firstname;
    private String lastname;
    private String email;
    private String address;
    //public static Student.StudentInnerBuilder StudentInnerBuilder;

    public Student(int matNr, int nrOfCar, String firstname, String lastname, String email, String address) {
        this.matNr = matNr;
        this.nrOfCar = nrOfCar;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.address = address;
    }

    public static class StudentInnerBuilder{
        private int matNr;
        private int nrOfCar;
        private String firstname;
        private String lastname;
        private String email;
        private String address;

        public StudentInnerBuilder builder(){
            return this;
        }
        @JsonInclude(JsonInclude.Include.NON_NULL)
        public Student build(){
            return new Student(this.matNr,this.nrOfCar, this.firstname, this.lastname, this.email,this.address);
        }

        public StudentInnerBuilder setMatNr(int matNr) {
            this.matNr = matNr;
            return this;
        }

        public StudentInnerBuilder setNrOfCar(int nrOfCar) {
            this.nrOfCar = nrOfCar;
            return this;
        }

        public StudentInnerBuilder setFirstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public StudentInnerBuilder setLastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public StudentInnerBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public StudentInnerBuilder setAddress(String address) {
            this.address = address;
            return this;
        }
    }
}
