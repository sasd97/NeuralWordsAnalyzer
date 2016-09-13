package sample.sources.models;

import com.sun.istack.internal.NotNull;

/**
 * Created by alexander on 13.09.16.
 */

public class LinkModel {

    public NeuronModel neuron;
    public double weight;

    public LinkModel(@NotNull NeuronModel neuron) {
        this.neuron = neuron;
        this.weight = 0;
    }
}
