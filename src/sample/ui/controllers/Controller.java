package sample.ui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import sample.sources.models.NeuronModel;
import sample.sources.services.ImageService;

import java.io.File;
import java.io.IOException;

import static sample.App.network;

public class Controller implements ImageService.OnImageHandleListener {

    private int[] vector;

    @FXML
    protected void handle(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        File selected = fileChooser.showOpenDialog(null);
        if (selected == null) return;

        System.out.println(selected.getAbsolutePath());
        ImageService service = ImageService.getService(this);
        service.getImageVector(selected);

    }

    @FXML
    protected void studyA(ActionEvent event) {
        network().Study(vector, 0);
    }

    @FXML
    protected void studyB(ActionEvent event) {
        network().Study(vector, 1);
    }

    @FXML
    protected void studyC(ActionEvent event) {
        network().Study(vector, 2);
    }

    @FXML
    protected void studyD(ActionEvent event) {
        network().Study(vector, 3);
    }

    @FXML
    protected void tryObtain(ActionEvent event) {
        int position = network().Handle(vector);
        NeuronModel neuron = network().getLucky(position);
        System.out.println(neuron.getTitle());
    }


    @Override
    public void onVectorize(int[] vector) {
        this.vector = vector;
        System.out.println(vector.length);
    }

    @Override
    public void onError() {

    }
}
