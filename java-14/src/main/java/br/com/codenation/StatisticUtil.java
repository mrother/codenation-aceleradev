package br.com.codenation;

import java.util.Arrays;

public class StatisticUtil {

    public static int average(int[] elements) {
        return Arrays.stream(elements).sum() / elements.length;

    }

    public static int mode(int[] elements) {
		int valorMax = 0;
		int contadorMax = 0;

		for (int element : elements) {
			int contador = 0;
			for (int i : elements) {
				if (i == element) ++contador;
			}
			if (contador > contadorMax) {
				contadorMax = contador;
				valorMax = element;
			}
		}
		return valorMax;
    }

    public static int median(int[] elements) {
    	int numeroElementos = elements.length;
    	Arrays.sort(elements);
    	if (numeroElementos % 2 !=0) return elements[elements.length/2];
    	return (elements[(numeroElementos-1) / 2] + elements[numeroElementos / 2]) / 2;
    }
}