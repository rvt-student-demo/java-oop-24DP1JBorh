package Studentu_registracijas_sistema.Exceptions;

public class PersonalCodeAlreadyExistsException extends StudentException {
    public PersonalCodeAlreadyExistsException(String code) {
        super("Personas kods jau eksiste: " + code);
    }
}
