package tutorial.pojostudent;

public class StudentBuilderDemo {
    public static void main(String[] args) {
        StudentBuilder builder = new StudentBuilder();
        Student student =builder.setFirstname("larry")
                .setMatNr(2)
                .setEmail("larry@.com")
                .setLastname("chuks")
                .build();
        System.out.println(student);

        Student.StudentInnerBuilder innerBuilder = new Student.StudentInnerBuilder();
        var student2 =innerBuilder.builder().setMatNr(2)
                .setEmail("email")
                .setNrOfCar(2)
                .setFirstname("Ebu")
                .build();
        System.out.println(student2);

       var studentLombok =StudentLombok.builder()
        .address("Berlin")
                .firstname("larry")
                .lastname("Chuks")
                .nrOfCar(2)
                .build();
        System.out.println(" this is lombok:" +studentLombok);


    }
}
