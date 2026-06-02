package Studentu_registracijas_sistema;

import Studentu_registracijas_sistema.Exceptions.InvalidInputException;

public class Validator {

    public static void validateFirstName(String firstName) {
        if (!firstName.matches("[a-zA-ZāčēģīķļņōŗšūžĀČĒĢĪĶĻŅŌŖŠŪŽ]{3,}")) {
            throw new InvalidInputException("Vārds");
        }
    }

    public static void validateLastName(String lastName) {
        if (!lastName.matches("[a-zA-ZāčēģīķļņōŗšūžĀČĒĢĪĶĻŅŌŖŠŪŽ]{3,}")) {
            throw new InvalidInputException("Uzvārds");
        }
    }

    public static void validateEmail(String email) {
        if (!email.matches("^[\\w._%+\\-]+@[\\w.\\-]+\\.[a-zA-Z]{2,}$")) {
            throw new InvalidInputException("E-pasts");
        }
    }

    public static void validatePersonId(String personId) {
        if (!personId.matches("\\d{6}-\\d{5}")) {
            throw new InvalidInputException("Personas kods");
        }
    }
}