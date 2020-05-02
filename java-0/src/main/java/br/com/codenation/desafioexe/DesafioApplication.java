package br.com.codenation.desafioexe;

import java.util.ArrayList;
import java.util.List;

public class DesafioApplication {

    public static List<Integer> fibonacci() {
        List<Integer> lista = new ArrayList<>();
        int elemento = 2;

        lista.add(0);
        lista.add(1);

        do {
            lista.add(lista.get(elemento - 1) + lista.get(elemento - 2));
            elemento++;
        } while (lista.get(lista.size() - 1) < 350);

        return lista;
    }

    public static Boolean isFibonacci(Integer a) {
        return fibonacci().contains(a);
    }

}