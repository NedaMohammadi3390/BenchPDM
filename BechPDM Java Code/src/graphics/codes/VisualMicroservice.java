package graphics.codes;

import data_structure.Microservice;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.Pair;

import java.util.ArrayList;

public class VisualMicroservice {
    private ArrayList<Pair<VisualConnection, Boolean>> lines;
    private final String URI;
    private final String[] connectedURIs;
    private final ArrayList<Pair<Microservice.ConnectionType, String>> connections;
    private final int x;
    private final int y;
    private int size;
    private double lastX;
    private double lastY;
    private final Text info;


    private static final int START_X = 25;
    private static final int START_Y = 25;

    private static int boundaryY = START_Y;
    private static int boundaryX = START_X;
    private final int constantLayerY = 90;
    private final int constantX = 90;
    private static int rowCounter = 0;
    private final int constantY = 375;


    static int layer = 0;
    public VisualMicroservice(String info,
                              String URI,
                              ArrayList<Pair<Microservice.ConnectionType, String>> connections,
                              String[] connectedURIs) {

        this.info = new Text(info);
        this.URI = URI;
        this.connections = connections;
        this.connectedURIs = connectedURIs;
        lastX = 0;
        lastY = 0;

        this.x = boundaryX;
        this.y = boundaryY;

        if (boundaryX > 1200) {
            boundaryX = START_X;
            boundaryY = START_Y + (++rowCounter * constantY);
            layer = 0;
        }else if (layer >= 3) {
            boundaryX = boundaryX + constantX;
            boundaryY = START_Y + (rowCounter * constantY);
            layer = 0;
        }
        else {
            boundaryX = boundaryX + constantX;
            boundaryY = boundaryY + constantLayerY;
            layer++;
        }
    }



    ImageView load(int size) {
        this.size = size;
        info.setX(x);
        info.setY(y);
        return getImageView(new Image("graphics/images/microservice.png"));
    }
//*********************************************************************************************************************

    private ImageView getImageView(Image image) {
        ImageView imageView = new ImageView(image);
        imageView.setX(x);
        imageView.setY(y);
        imageView.setFitHeight(size);
        imageView.setFitWidth(size);
        imageView.setPreserveRatio(true);

        imageView.setOnMousePressed(event -> {
            lastX = event.getX();
            lastY = event.getY();
        });

        imageView.setOnMouseDragged(event -> {
            double x = imageView.getX() + event.getX() - lastX;
            double y = imageView.getY() + event.getY() - lastY;

            imageView.setX(x);
            imageView.setY(y);


            info.setX(x);
            info.setY(y);

            this.lastX = x;
            this.lastY = y;


            for (Pair<VisualConnection, Boolean> pair : lines) {
                VisualConnection visualConnection = (pair.getKey());
                if (pair.getValue()) {
                    visualConnection.updateStart(x, y, size);
                } else {
                    visualConnection.updateEnd(x, y, size);
                }
            }
        });
        return imageView;
    }

    public void addLine(VisualConnection visualConnection, Boolean b) {
        Pair pair = new Pair<>(visualConnection, b);
        if (lines == null) {
            lines = new ArrayList<>();
        }
        lines.add(pair);

        if (b) {
            visualConnection.setStartX(x);
            visualConnection.setStartY(y);
        } else {
            visualConnection.setEndX(x);
            visualConnection.setEndY(y);
        }
    }

    public String getConnectionType(String uri) {

        StringBuilder output = new StringBuilder();
        for (Pair<Microservice.ConnectionType, String> p : connections) {
            if (!p.getValue().equals(uri)) continue;
            output.append(p.getKey().toString()).append("/");
        }
        return output.toString();
    }

    public String[] getConnectedURIs() {
        return connectedURIs;
    }

    public Text getInfo() {
        return info;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getURI() {
        return URI;
    }

}
