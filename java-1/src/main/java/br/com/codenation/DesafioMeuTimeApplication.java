package br.com.codenation;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;
import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;
import br.com.codenation.entities.Jogador;
import br.com.codenation.entities.Time;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class DesafioMeuTimeApplication implements MeuTimeInterface {

    public List<Time> times = new ArrayList<>();
    public List<Jogador> jogadores = new ArrayList<>();

    @Desafio("incluirTime")
    public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
        if (teamExists(id)) throw new IdentificadorUtilizadoException();
        times.add(new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario));
    }

    @Desafio("incluirJogador")
    public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
        if (playerExists(id)) throw new IdentificadorUtilizadoException();
        if (!teamExists(idTime)) throw new TimeNaoEncontradoException();
        jogadores.add(new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario));
    }

    @Desafio("definirCapitao")
    public void definirCapitao(Long idJogador) {
        Jogador jogador = getPlayerById(idJogador);
        this.jogadores = jogadores.stream()
                .peek(j -> {
                    if (j.getIdTime() == jogador.getIdTime()) {
                        j.setCapitao(idJogador == j.getId());
                    }
                }).collect(Collectors.toList());
    }

    @Desafio("buscarCapitaoDoTime")
    public Long buscarCapitaoDoTime(Long idTime) {
        getTeamById(idTime);
        Optional<Jogador> jogador = jogadores.stream()
                .filter(j -> j.getIdTime() == idTime && j.isCapitao()).findFirst();
        if (!jogador.isPresent())
            throw new CapitaoNaoInformadoException();
        return jogador.get().getId();
    }

    @Desafio("buscarNomeJogador")
    public String buscarNomeJogador(Long idJogador) {
        return getPlayerById(idJogador).getNome();
    }

    @Desafio("buscarNomeTime")
    public String buscarNomeTime(Long idTime) {
        return getTeamById(idTime).getNome();
    }

    @Desafio("buscarJogadoresDoTime")
    public List<Long> buscarJogadoresDoTime(Long idTime) {
        getTeamById(idTime);
        return jogadores.stream()
                .filter(j -> j.getIdTime() == idTime)
                .map(Jogador::getId).sorted().collect(Collectors.toList());
    }

    @Desafio("buscarMelhorJogadorDoTime")
    public Long buscarMelhorJogadorDoTime(Long idTime) {
        getTeamById(idTime);
        return Collections.max(
                this.jogadores.stream().filter(j -> j.getIdTime() == idTime).collect(Collectors.toList()),
                Comparator.comparing(Jogador::getNivelHabilidade)
        ).getId();
    }

    @Desafio("buscarJogadorMaisVelho")
    public Long buscarJogadorMaisVelho(Long idTime) {
        getTeamById(idTime);
        return Collections.min(
                this.jogadores.stream().filter(j -> j.getIdTime() == idTime).collect(Collectors.toList()),
                Comparator.comparing(Jogador::getDataNascimento)
        ).getId();
    }

    @Desafio("buscarTimes")
    public List<Long> buscarTimes() {
        return times.stream()
                .map(Time::getId).sorted().collect(Collectors.toList());
    }

    @Desafio("buscarJogadorMaiorSalario")
    public Long buscarJogadorMaiorSalario(Long idTime) {
        getTeamById(idTime);
        return Collections.max(
                this.jogadores.stream().filter(j -> j.getIdTime() == idTime).collect(Collectors.toList()),
                Comparator.comparing(Jogador::getSalario)
        ).getId();
    }

    @Desafio("buscarSalarioDoJogador")
    public BigDecimal buscarSalarioDoJogador(Long idJogador) {
        return getPlayerById(idJogador).getSalario();

    }

    @Desafio("buscarTopJogadores")
    public List<Long> buscarTopJogadores(Integer top) {
        return jogadores.stream()
                .sorted(Comparator.comparing(Jogador::getNivelHabilidade).reversed().thenComparing(Jogador::getId))
                .mapToLong(Jogador::getId).boxed()
                .collect(Collectors.toList())
                .subList(0, top);
    }

    @Desafio("buscarCorCamisaTimeDeFora")
    public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
        Time timeCasa = getTeamById(timeDaCasa);
        Time timeFora = getTeamById(timeDeFora);
        return timeCasa.getCorUniformePrincipal().equals(timeFora.getCorUniformePrincipal())
                ? timeFora.getCorUniformeSecundario()
                : timeFora.getCorUniformePrincipal();
    }

    private Jogador getPlayerById(Long id) {
        Optional<Jogador> jogador = jogadores.stream().filter(j -> id == j.getId()).findFirst();
        if (!jogador.isPresent())
            throw new JogadorNaoEncontradoException();
        return jogador.get();
    }

    private Time getTeamById(Long id) {
        Optional<Time> time = times.stream().filter(j -> id == j.getId()).findFirst();
        if (!time.isPresent())
            throw new TimeNaoEncontradoException();
        return time.get();
    }

    private boolean teamExists(Long id) {
        Optional<Long> timeId = times.stream().filter(t -> t.getId() == id).map(Time::getId).findFirst();
        return timeId.isPresent();
    }

    private boolean playerExists(Long id) {
        Optional<Long> jogadorId = jogadores.stream().filter(t -> t.getId() == id).map(Jogador::getId).findFirst();
        return jogadorId.isPresent();
    }
}
