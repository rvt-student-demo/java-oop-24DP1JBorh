package Studentu_registracijas_sistema.Exceptions;

public class StudentNotFoundException extends StudentException {
    public StudentNotFoundException(String personId) {
        super("Students nav atrasts ar kodu: " + personId);
    }
}
