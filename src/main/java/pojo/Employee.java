package pojo;

import lombok.*;

@Setter
@Getter
@Builder(setterPrefix = "set")
@ToString
@AllArgsConstructor
public class Employee {
    private int id;
    private String firstname, lastname;

    public Employee(){}

}
