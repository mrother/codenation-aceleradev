package challenge;

import java.security.InvalidParameterException;

public class ValidatedString {

    public static void notEmpty(String text) {
        if (text.isEmpty()) {
            throw new InvalidParameterException("O texto não pode ser vazio");
        }
    }

    public static void notNull(String text) {
        if (text == null) {
            throw new NullPointerException("O texto não pode ser nulo");
        }
    }

    public static void notEmptyOrNull(String text) {
        notEmpty(text);
        notNull(text);
    }

}
