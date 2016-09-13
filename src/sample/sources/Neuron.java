package sample.sources;

import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexander on 13.09.16.
 */

public class Neuron {

    private String title;

    public List<Link> incomingLinks;
    public double power;

    public Neuron(@NotNull String title) {
        this.title = title;
        this.power = 0;
        this.incomingLinks = new ArrayList<>();
    }

    public void addLink(@NotNull Link link) {
        this.incomingLinks.add(link);
    }

    public Link get(int index) {
        return incomingLinks.get(index);
    }

    public String getTitle() {
        return title;
    }
}
