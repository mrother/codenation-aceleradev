package br.com.codenation.calculadora;


public class CalculadoraSalario {

	private final double SALARIO_MINIMO = 1039.0;

	public long calcularSalarioLiquido(double salarioBase) {
		if (salarioBase < SALARIO_MINIMO) {
			return 0;
		}
		double salarioLiquidoSemInss = salarioBase - calcularInss(salarioBase);
		double salarioLiquido = salarioLiquidoSemInss - calcularIrpf(salarioLiquidoSemInss);

		return Math.round(salarioLiquido);
	}
	
	
	//Exemplo de método que pode ser criado para separar melhor as responsábilidades de seu algorítmo
	private double calcularInss(double salarioBase) {
		double inss;

		if (salarioBase <= 1500.0) {
			inss = salarioBase * 0.08;
		} else if(salarioBase > 1500.0 && salarioBase <= 4000.0) {
			inss = salarioBase * 0.09;
		} else {
			inss = salarioBase * 0.11;
		}

		return inss;
	}

	private double calcularIrpf(double salarioBase) {
		if (salarioBase <= 3000.0) {
			return 0;
		} else if(salarioBase > 3000.0 && salarioBase <= 6000.0) {
			return salarioBase * 0.075;
		} else {
			return salarioBase * 0.15;
		}
	}

}
/*Dúvidas ou Problemas?
Manda e-mail para o meajuda@codenation.dev que iremos te ajudar! 
*/