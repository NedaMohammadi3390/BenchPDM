package graphics.codes;

/*
 * TODO: UI line 140
 *
 * */

import data_structure.Microservice;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.util.Pair;
import main.Main;
import strategy.*;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UI {
    @FXML
    public TextField sidecarTxt;
    @FXML
    public TextField priorityqTxt;
    @FXML
    public TextField cahceasideTxt;
    @FXML
    public TextField aggregatorTxt;
    @FXML
    public TextField staticContentHostTxt;
    @FXML
    public TextField ambassadorTxt;
    @FXML
    public TextField apiGatewayTxt;
    @FXML
    public TextField eventSourcingTxt;
    @FXML
    public TextField sagaTxt;
    @FXML
    public TextField serviceDiscoveryTxt;
    @FXML
    private TextField leaderElectionTxt;
    @FXML
    private TextField consolidationTxt;
    @FXML
    private TextField pipesAndFiltersTxt;
    @FXML
    private Button submitBtn;
    @FXML
    private TextField uriTxt;
    @FXML
    private TextField microserviceTxt;
    @FXML
    private TextField patternNameTxt;
    @FXML
    private TextField roleTxt;
    @FXML
    private TableView<MatrixView> tableView;
    @FXML
    private ChoiceBox connectionTypeCB;
    @FXML
    private Text pathTxt;
    @FXML
    private Button saveToBtn;
    @FXML
    private Button doneBtn;
    @FXML
    private TextField textField;
    @FXML
    private VBox textfieldVBox;
    @FXML
    private VBox choiceBoxVBox;
    @FXML
    private VBox connectionTypeVBox;
    @FXML
    private Text errorTxt;

    private int creationTime;

    String path = "";
    private List<Microservice> matrices;
    private Set<String> microURIs;
    private Set<String> connectedURIs;


    public UI() {
        matrices = new ArrayList<>(50);
        microURIs = new HashSet<>();
        connectedURIs = new HashSet<>();
        creationTime = 0;

    }

    @FXML
    private void saveToBtnLogic() { // text="Click to Set Target Directory for Contents Generated"
        DirectoryChooser directoryChooser = new DirectoryChooser();
        Stage stage = (Stage) saveToBtn.getScene().getWindow();
        File selectedFile = directoryChooser.showDialog(stage);
        if (selectedFile == null) return;
        pathTxt.setText("Your Project will be Saved on: " + selectedFile.getAbsolutePath());
        pathTxt.setFill(Color.BLUE);
        path = selectedFile.getAbsolutePath();
    }

    @FXML
    private void doneBtnLogic() throws IOException { // text="Generate"
        List<Pair<Pair<Microservice.Pattern, Integer>, Strategy>> userSelectedPatterns = new ArrayList<>();
        Main main = new Main();
        Stage stage = (Stage) doneBtn.getScene().getWindow();
        if (!validateUserInput(userSelectedPatterns)) {
            JOptionPane.showMessageDialog(null, "Error is Detected. Please Resolve it First.",
                    "Alert", JOptionPane.ERROR_MESSAGE);

            return;

        }

        connectedURIs.removeAll(microURIs);

        main.startGenerator(matrices, userSelectedPatterns, path, stage);

    }

    private boolean validateUserInput(List<Pair<Pair<Microservice.Pattern, Integer>, Strategy>> userSelectedPatterns) {
        if (path.equals("")) {
            pathTxt.setText("Please Set the Target Directory");
            pathTxt.setFill(Color.RED);
            return false;
        }

        System.out.println("start time is:"+LocalTime.now());

        System.out.println("startTime milisecondes:"+System.currentTimeMillis());

        if (processOnInput(userSelectedPatterns, new LoadBalancerStrategy(), Microservice.Pattern.LoadBalanceing, consolidationTxt)) return false;
        if (processOnInput(userSelectedPatterns, new SidecarStrategy(), Microservice.Pattern.Sidecar, sidecarTxt)) return false;
        if (processOnInput(userSelectedPatterns, new PriorityQueueStrategy(), Microservice.Pattern.PriorityQueue, priorityqTxt)) return false;
        if (processOnInput(userSelectedPatterns, new CacheAsideStrategy(), Microservice.Pattern.CacheASide, cahceasideTxt)) return false;
        if (processOnInput(userSelectedPatterns, new AggregatorStrategy(), Microservice.Pattern.Aggregator, aggregatorTxt)) return false;
        if (processOnInput(userSelectedPatterns, new ServicePerContainer(), Microservice.Pattern.StaticContentHost, staticContentHostTxt)) return false;
        if (processOnInput(userSelectedPatterns, new AmbassadorStrategy(), Microservice.Pattern.Ambassador, ambassadorTxt)) return false;
        if (processOnInput(userSelectedPatterns, new LeaderElectionStrategy(), Microservice.Pattern.LeaderElection, leaderElectionTxt)) return false;
        if (processOnInput(userSelectedPatterns, new PipesAndFilterStrategy(), Microservice.Pattern.PipesAndFilters, pipesAndFiltersTxt)) return false;
        if (processOnInput(userSelectedPatterns, new ApiGatewayStrategy(), Microservice.Pattern.ApiGateway, apiGatewayTxt)) return false;
        if (processOnInput(userSelectedPatterns, new EventSourcingStrategy(), Microservice.Pattern.EventSourcing, eventSourcingTxt)) return false;
        if (processOnInput(userSelectedPatterns, new SagaStrategy(), Microservice.Pattern.Saga, sagaTxt)) return false;
        if (processOnInput(userSelectedPatterns, new ServiceDiscoveryStrategy(), Microservice.Pattern.ServiceDiscovery, serviceDiscoveryTxt)) return false;
        return true;
    }

    public boolean processOnInput(List<Pair<Pair<Microservice.Pattern, Integer>, Strategy>> userSelectedPatterns,
                                  Strategy strategy,
                                  Microservice.Pattern patternName,
                                  TextField textField){
        boolean isValid = true;
        try {
            Pair<Microservice.Pattern, Integer> patternIntegerPair = new Pair<>(patternName, Integer.parseInt(textField.getText()));
            userSelectedPatterns.add(new Pair<>(patternIntegerPair, strategy));
            Pair<Microservice.Pattern, Integer> patternIntegerPair2 = new Pair<>(Microservice.Pattern.NoPattern, Integer.parseInt(textField.getText()));
//            Pair<Microservice.Pattern, Integer> patternIntegerPair2 = new Pair<>(Microservice.Pattern.NoPattern, Integer.parseInt(textField.getText()));

            userSelectedPatterns.add(new Pair<>(patternIntegerPair2, new NoPatternStrategy()));

            textField.setStyle("-fx-border-color: blue; -fx-text-inner-color: blue;");
        } catch (NumberFormatException e) {
            textField.setStyle("-fx-border-color: red; -fx-text-inner-color: red;");
            isValid = false;
        }
        return !isValid;
    }

    //*****************************************************************************************************

    @FXML
    private void submitBtn() throws IOException {
        if (allFilled()) {

            addColumn();

        }
    }

    private boolean allFilled() {
        return !microserviceTxt.getText().isEmpty()
                && !uriTxt.getText().isEmpty();
    }

    private void addColumn() throws IOException {
        tableView.getItems().add(new MatrixView(

                microserviceTxt.getText(),
                uriTxt.getText(),
                roleTxt.getText(),
                patternNameTxt.getText(),
                implementedConnectionTypeToString(),
                connectedToToString()
        ));

        microURIs.add(uriTxt.getText());

        Microservice microservice = new Microservice(0,Strategy.id++, microserviceTxt.getText(), uriTxt.getText(),
                implementedConnections(), false, String.valueOf(creationTime),
                new Pair<>(patternNameTxt.getText(), roleTxt.getText()),null
        );

        creationTime++;
        connectedMicroservices(microservice);
        matrices.add(microservice);

    }
    //********************************************************************************************************************************************

    private void connectedMicroservices(Microservice microservice) {

        List textfieldList = textfieldVBox.getChildren();
        List choiceBoxList = choiceBoxVBox.getChildren();
        for (int i = 0; i < textfieldList.size(); i++) {
            Object value = ((ChoiceBox) choiceBoxList.get(i)).getValue();
            if (value == null || ((TextField) textfieldList.get(i)).getText().equals("")) continue;
            Microservice.ConnectionType connectionType = Microservice.ConnectionType.valueOf(value.toString());
            String connectURI = ((TextField) textfieldList.get(i)).getText();
            microservice.setConnections(new Pair<>(connectionType, connectURI));


                connectedURIs.add(connectURI);
        }
    }

    private Pair<Microservice.ConnectionType, String>[] implementedConnections() {
        List<Pair<Microservice.ConnectionType, String>> list = new ArrayList<>(20);

        for (Node n : connectionTypeVBox.getChildren()) {
            Object value = ((ChoiceBox) n).getValue();
            if (value == null) continue;
            Microservice.ConnectionType connectionType = Microservice.ConnectionType.valueOf(value.toString());
            list.add(new Pair<>(connectionType, ""));
        }
        return list.toArray(new Pair[0]);
    }

    private String implementedConnectionTypeToString() {
        StringBuilder builder = new StringBuilder();

        for (Node n : connectionTypeVBox.getChildren()) {
            if (((ChoiceBox) n).getValue() == null) continue;
            builder.append(((ChoiceBox) n).getValue()).append(" ");
        }
        return builder.toString();
    }

    private String connectedToToString() {

        StringBuilder builder = new StringBuilder();

        List textfieldList = textfieldVBox.getChildren();
        List choiceBoxList = choiceBoxVBox.getChildren();

        for (int i = 0; i < textfieldList.size(); i++) {
            if (((TextField) textfieldList.get(i)).getText().equals("")) continue;
            builder.append(((TextField) textfieldList.get(i))
                            .getText())
                    .append(" ")
                    .append(((ChoiceBox) choiceBoxList.get(i)).getValue())
                    .append(" ");
        }
        return builder.toString();
    }

    @FXML
    private void tableViewLogic() {
        tableView.setOnMouseClicked(mouseEvent -> {

            if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {

                if (mouseEvent.getClickCount() == 2 && tableView.getSelectionModel().getSelectedIndex() >= 0) {

                    tableView.getItems().remove(tableView.getSelectionModel().getSelectedIndex());
                }
            }
        });
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void connectedToAdd(KeyEvent keyEvent) {

        int len = textfieldVBox.getChildren().size();
        if (len > 2 &&
                ((((TextField) textfieldVBox.getChildren().get(len - 2)).getText().length() == 1
                        && keyEvent.getCode() == KeyCode.BACK_SPACE) ||
                        ((TextField) textfieldVBox.getChildren().get(len - 2)).getText().isEmpty())) {
            textfieldVBox.getChildren().remove(len - 1);
            choiceBoxVBox.getChildren().remove(len - 1);
            textField = (TextField) textfieldVBox.getChildren().get(len - 2);
        }


        if (keyEvent.getSource() != textField || keyEvent.getCode() == KeyCode.BACK_SPACE) return;

        TextField textField1 = new TextField();

        ChoiceBox<String> choiceBox = new ChoiceBox<>(FXCollections.observableArrayList("POST", "PUT", "GET", "DELETE"));
        choiceBox.setValue("POST");

        textField1.setOnKeyPressed(this::connectedToAdd);

        textField = textField1;

        textfieldVBox.getChildren().add(textField1);
        choiceBoxVBox.getChildren().add(choiceBox);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void connectionTypeAdd(MouseEvent mouseEvent) {

        int len = connectionTypeVBox.getChildren().size();
        if (len > 2 && ((ChoiceBox) connectionTypeVBox.getChildren().get(len - 2)).getValue() == null) {
            connectionTypeVBox.getChildren().remove(len - 1);
            connectionTypeCB = (ChoiceBox) connectionTypeVBox.getChildren().get(len - 2);
        }

        if (mouseEvent.getSource() != connectionTypeCB) return;
        ChoiceBox<String> choiceBox = new ChoiceBox<>(FXCollections.observableArrayList("POST", "PUT", "GET", "DELETE", ""));

        connectionTypeCB = choiceBox;
        choiceBox.setOnMouseClicked(this::connectionTypeAdd);

        connectionTypeVBox.getChildren().add(choiceBox);
    }
}

