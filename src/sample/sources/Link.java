package sample.sources;

import com.sun.istack.internal.NotNull;

/**
 * Created by alexander on 13.09.16.
 */

public class Link {

    public Neuron neuron;
    public double weight;

    public Link(@NotNull Neuron neuron) {
        this.neuron = neuron;
        this.weight = 0;
    }
}
