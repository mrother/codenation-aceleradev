package br.com.codenation.models;

import br.com.codenation.entities.Jogador;

import java.util.ArrayList;
import java.util.List;

public class JogadorModel {
    private List<Jogador> jogadores;

    public JogadorModel() {
        this.jogadores = new ArrayList<Jogador>();
    }

    public List<Jogador> getJogadores() {
        return jogadores;
    }

    public void addJogador(Jogador jogador) {
        this.jogadores.add(jogador);
    }
}
