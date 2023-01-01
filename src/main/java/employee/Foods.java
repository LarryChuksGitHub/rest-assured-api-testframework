package employee;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;



@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class Foods{
    private String breakfast;
    private String lunch;
    private List<String> dinner;

//    public Foods(String breakfast, String lunch, List<String> dinner) {
//        this.breakfast = breakfast;
//        this.lunch = lunch;
//        this.dinner = dinner;
//    }

//    public Foods() {
//    }

    public String getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(String breakfast) {
        this.breakfast = breakfast;
    }

    public String getLunch() {
        return lunch;
    }

    public void setLunch(String lunch) {
        this.lunch = lunch;
    }

    public List<String> getDinner() {
        return dinner;
    }

    public void setDinner(List<String> dinner) {
        this.dinner = dinner;
    }
}