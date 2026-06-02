package Studentu_registracijas_sistema.Exceptions;

public class EmailAlreadyExistsException extends StudentException {
    public EmailAlreadyExistsException(String email) {
        super("E-pasts jau eksiste: " + email);
    }
}
