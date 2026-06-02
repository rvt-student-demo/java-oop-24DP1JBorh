package Studentu_registracijas_sistema.Exceptions;

public class InvalidInputException extends StudentException {
    public InvalidInputException(String fieldName) {
        super("Nepareiza ievade lauka: " + fieldName);
    }
}