package br.com.codenation.models;

import br.com.codenation.entities.Time;

import java.util.ArrayList;
import java.util.List;

public class TimeModel {
    private List<Time> times;

    public TimeModel() {
        this.times = new ArrayList<Time>();
    }

    public List<Time> getTimes() {
        return times;
    }

    public void addTime(Time time) {
        this.times.add(time);
    }
}
