package challenge;

import java.security.InvalidParameterException;

class CriptografiaCesariana implements Criptografia {

    public String criptografar(String texto) {
        ValidatedString.notEmptyOrNull(texto);

        return encrypt(texto.toLowerCase(), 3);
    }

    public String descriptografar(String texto) {
        ValidatedString.notEmptyOrNull(texto);

        int shift = (26 - 3) % 26;

        return encrypt(texto.toLowerCase(), shift);
    }

    private String encrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch >= 65 && ch <= 90) {
                result.append((char) ((ch - 65 + shift) % 26 + 65));
            } else if ((ch >= 97 && ch <= 122)) {
                result.append((char) ((ch - 97 + shift) % 26 + 97));
            } else {
                result.append(text.charAt(i));
            }
        }
        return result.toString();
    }
}
