package challenge;

import java.util.*;
import java.util.stream.Collectors;

public class Estacionamento {

    public static final short MAX_CARROS = 10;

    private List<Carro> estacionados = new LinkedList<>();

    public void estacionar(Carro carro) {

        if (estacionados.size() >= MAX_CARROS) {
            Optional<Carro> remover = estacionados
                    .stream()
                    .filter(e -> e.getMotorista().getIdade() < 55)
                    .findFirst();
            if (!remover.isPresent()) throw new EstacionamentoException("Limite ultrapassado");
            remover.ifPresent(c -> estacionados.remove(c));
            estacionados.add(carro);
        } else {
            estacionados.add(carro);
        }
    }

    public int carrosEstacionados() {
        return estacionados.size();
    }

    public boolean carroEstacionado(Carro carro) {
        return estacionados.stream().anyMatch(c -> c.equals(carro));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estacionamento that = (Estacionamento) o;
        return estacionados.equals(that.estacionados);
    }

    @Override
    public int hashCode() {
        return Objects.hash(estacionados);
    }

    @Override
    public String toString() {
        return "Estacionamento{" +
                "estacionado=" + estacionados +
                '}';
    }
}
