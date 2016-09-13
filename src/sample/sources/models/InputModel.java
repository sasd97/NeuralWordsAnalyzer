package sample.sources.models;

/**
 * Created by alexander on 13.09.16.
 */

public class InputModel {

    public LinkModel[] outgoingLinks;

    public InputModel(LinkModel[] links) {
        outgoingLinks = links;
    }
}
