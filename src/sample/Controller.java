package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import javax.naming.ldap.Control;
import java.io.IOException;

public class Controller {


    @FXML
    private ListView numberListView;

    private GetRequest client;

    public void initialize() throws IOException {
        client = new GetRequest("http://dt-gwitczak-recruitment.westeurope.cloudapp.azure.com:8080/rest/task");
        client.setAuthData("candidate", "abc123");
    }

    @FXML
    private void buttonAction() throws IOException {
        String data = client.getNextDataset();
        DynatraceDataParser parser = new DynatraceDataParser(data);
        parser.eliminateDuplicates();
        parser.sortList();

        ObservableList<Integer> observable = FXCollections.observableArrayList(parser.getPrimeNumbers());
        numberListView.setItems(observable);

    }
}
