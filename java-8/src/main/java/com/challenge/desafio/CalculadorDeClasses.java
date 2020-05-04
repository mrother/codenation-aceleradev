package com.challenge.desafio;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;

import com.challenge.annotation.Somar;
import com.challenge.annotation.Subtrair;
import com.challenge.interfaces.Calculavel;

public class CalculadorDeClasses implements Calculavel {

    @Override
    public BigDecimal somar(Object o) {
        return calcular(o, Somar.class);
    }

    @Override
    public BigDecimal subtrair(Object o) {
        return calcular(o, Subtrair.class);
    }

    @Override
    public BigDecimal totalizar(Object o) {
        return somar(o).subtract(subtrair(o));
    }

    private BigDecimal calcular(Object o, Class<? extends Annotation> operacao) {
        BigDecimal resultado = BigDecimal.ZERO;

        for (Field attr : o.getClass().getDeclaredFields()) {
            attr.setAccessible(true);
            if (attr.isAnnotationPresent(operacao) && attr.getType().equals(BigDecimal.class)) {
                try {
                    resultado = resultado.add((BigDecimal) attr.get(o));
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return resultado;
    }
}