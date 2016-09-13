package sample.sources.models;

import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexander on 13.09.16.
 */

public class NeuronModel {

    private String title;

    public List<LinkModel> incomingLinks;
    public double power;

    public NeuronModel(@NotNull String title) {
        this.title = title;
        this.power = 0;
        this.incomingLinks = new ArrayList<>();
    }

    public void addLink(@NotNull LinkModel link) {
        this.incomingLinks.add(link);
    }

    public LinkModel get(int index) {
        return incomingLinks.get(index);
    }

    public String getTitle() {
        return title;
    }
}
