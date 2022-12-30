package employee;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor

//@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder(alphabetic = true)
//@JsonIgnoreProperties(value = {"firstname"})
public class EmployeeLombok {
    private int id;
    //@JsonIgnore
    private String email;
    private String firstname;
    private String lastname;
    private String avatar;
    private List<String> jobs;
    private Foods foods;

    public EmployeeLombok(){}

}
