package br.com.codenation.calculadora;

public class Program {
    public static void main(String[] args) {
        CalculadoraSalario c = new CalculadoraSalario();

        System.out.println(c.calcularSalarioLiquido(1500));
        System.out.println(c.calcularSalarioLiquido(3500));
        System.out.println(c.calcularSalarioLiquido(6500));
    }
}
