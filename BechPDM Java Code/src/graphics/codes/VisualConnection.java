package graphics.codes;

import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class VisualConnection {
    private final CubicCurve cubicCurve;
    private final Arrow arrow;
    private Text connectionText;

    private final int arrowLength;
    private int size;
    private final int offset;

    private int startX;
    private int startY;
    private int endX;
    private int endY;

    public VisualConnection(int startX, int startY, int endX, int endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        cubicCurve = new CubicCurve();
        arrow = new Arrow();
        arrowLength = 5;
        offset = 5;
    }

    ArrayList load(int size, String connection) {
        ArrayList arrayList = new ArrayList();

        cubicCurve.setStartX(startX + (size >> 1));
        cubicCurve.setStartY(startY + (size >> 1));

        cubicCurve.setEndX(endX + (size >> 1));
        cubicCurve.setEndY(endY + (size >> 1));

        cubicCurve.setControlX1(cubicCurve.getStartX());
        cubicCurve.setControlY1(cubicCurve.getEndY());

        cubicCurve.setControlX2(cubicCurve.getStartX());
        cubicCurve.setControlY2(cubicCurve.getEndY());

        cubicCurve.setStroke(Color.BLACK);
        cubicCurve.setStrokeWidth(1.5);
        cubicCurve.setFill(Color.TRANSPARENT);

        this.size = size;

        this.connectionText = new Text((cubicCurve.getStartX() + cubicCurve.getEndX()) / 2, cubicCurve.getControlY1(), connection);
        this.connectionText.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 12));
        this.connectionText.setFill(Color.BROWN);

        textMicroserviceCollisionCheck(cubicCurve.getEndX(), cubicCurve.getEndY());

        arrayList.addAll(arrow.load(cubicCurve.getEndX(), cubicCurve.getEndY(), size,
                arrowLength, (int) Math.signum(cubicCurve.getStartX() - cubicCurve.getEndX())));

        arrayList.add(cubicCurve);
        arrayList.add(connectionText);

        return arrayList;
    }

    void updateStart(double mouseX, double mouseY, int size) {
        cubicCurve.setStartX(mouseX + (size >> 1));
        cubicCurve.setStartY(mouseY + (size >> 1));
        updateControlPoints();
        arrow.update(cubicCurve.getEndX(), cubicCurve.getEndY(), (int) Math.signum(mouseX - cubicCurve.getEndX()));
    }


    void updateEnd(double mouseX, double mouseY, int size) {

        cubicCurve.setEndX(mouseX + (size >> 1));
        cubicCurve.setEndY(mouseY + (size >> 1));
        updateControlPoints();
        arrow.update(cubicCurve.getEndX(), cubicCurve.getEndY(), (int) Math.signum(cubicCurve.getStartX() - mouseX));
    }

    private void updateControlPoints() {
        cubicCurve.setControlX1(cubicCurve.getStartX());
        cubicCurve.setControlY1(cubicCurve.getEndY());
        cubicCurve.setControlX2(cubicCurve.getStartX());
        cubicCurve.setControlY2(cubicCurve.getEndY());
        updateTextPosition();
    }

    private void updateTextPosition() {
        connectionText.setX((cubicCurve.getStartX() + cubicCurve.getEndX()) / 2);
        connectionText.setY(cubicCurve.getControlY1());
        textMicroserviceCollisionCheck(cubicCurve.getEndX(), cubicCurve.getEndY());
    }

    /**
     * This method is to check if the distance between text
     * and microservice is not less than microservice size
     * so that it will be covered by the microservice itself.
     * if the distance is less then it is positioned above
     * the microservice icon
     *
     * @param microserviceX The x position of microservice
     * @param microserviceY The y position of microservice
     */

    private void textMicroserviceCollisionCheck(double microserviceX, double microserviceY) {

        if (Math.hypot(microserviceX - connectionText.getX(), microserviceY - connectionText.getY()) < (size + offset)) {
            connectionText.setX(microserviceX - 10);
            connectionText.setY(microserviceY - 25);
        }
    }

    void setStartX(int startX) {
        this.startX = startX;
    }

    void setStartY(int startY) {
        this.startY = startY;
    }

    void setEndX(int endX) {
        this.endX = endX;
    }

    void setEndY(int endY) {
        this.endY = endY;
    }
}
