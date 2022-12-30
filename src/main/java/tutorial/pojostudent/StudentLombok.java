package tutorial.pojostudent;

import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class StudentLombok {

    private int matNr;
    private int nrOfCar;
    private String firstname;
    private String lastname;
    private String email;
    private String address;

}
