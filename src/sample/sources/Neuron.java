package sample.sources;

import com.sun.istack.internal.NotNull;

/**
 * Created by alexander on 13.09.16.
 */

public class Neuron {

    private String title;

    public Link[] incomingLinks;
    public double power;

    public Neuron(@NotNull String title) {
        this.title = title;
        this.power = 0;
    }

    public void setLinks(@NotNull Link[] incomingLinks) {
        this.incomingLinks = incomingLinks;
    }
}
