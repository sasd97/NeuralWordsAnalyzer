package sample.ui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import sample.sources.services.ImageService;

import java.io.File;
import java.io.IOException;

public class Controller implements ImageService.OnImageHandleListener {

    @FXML
    protected void handle(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        File selected = fileChooser.showOpenDialog(null);

        ImageService service = ImageService.getService(this);
        service.getImageVector(selected);

    }


    @Override
    public void onVectorize(int[] vector) {
        System.out.print(vector.length);
    }

    @Override
    public void onError() {

    }
}
