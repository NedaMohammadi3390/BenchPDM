package graphics.codes;

import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Display {
    public final static int screenX = 600;
    public final static int screenY = 600;
    private final int size = 30;

    public void start(Stage stage, ArrayList<VisualMicroservice> nodes, String defaultPath) {
        Group root = new Group();
//        Collections.reverse(nodes);
        for (VisualMicroservice visualMicroservice : nodes) {
            root.getChildren().add(visualMicroservice.load(size));
            root.getChildren().add(visualMicroservice.getInfo());
            for (int i = 0; i < visualMicroservice.getConnectedURIs().length; i++) {
                int startX = visualMicroservice.getX();
                int startY = visualMicroservice.getY();
                VisualMicroservice v = suitableMicroservice(visualMicroservice.getConnectedURIs()[i], nodes);
                int endX = v.getX();
                int endY = v.getY();
                VisualConnection visualConnection = new VisualConnection(startX, startY, endX, endY);
                visualMicroservice.addLine(visualConnection, true);
                v.addLine(visualConnection, false);
                ArrayList arrayList = visualConnection.load(size, visualMicroservice.getConnectionType(v.getURI()));
                for (Object o : arrayList) {
                    root.getChildren().add(0, (Node) o);
                }
            }
        }
        ScrollPane scrollPane = new ScrollPane();

        scrollPane.setContent(root);
        scrollPane.setPadding(new Insets(20,20,20,20));
        Scene scene = new Scene(scrollPane);



        stage.setScene(scene);
        stage.show();
        WritableImage writableImage = root.snapshot(new SnapshotParameters(), null);
        final File file = new File(defaultPath + "\\graph.png");
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(writableImage, null), "png", file);
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private VisualMicroservice suitableMicroservice(String uri, ArrayList<VisualMicroservice> nodes) {
        for (VisualMicroservice v : nodes) {
            if (uri.equals(v.getURI()))
                return v;
        }
        return null;
    }
}
