package graphics.codes;

import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.Arrays;

class Arrow {
    private Line[] lines;
    private int len;
    private int size;

    ArrayList load(double startX, double startY, int size, int len, int side) {
        if (side < 0) {
            startX -= size >> 2;
        } else {
            startX += size >> 2;
        }

        lines = new Line[2];
        lines[0] = new Line(startX, startY, startX + len * side, startY + len * side);
        lines[1] = new Line(startX, startY, startX + len * side, startY - len * side);

        this.len = len;
        this.size = size;
        return new ArrayList(Arrays.asList(lines));
    }

    void update(double startX, double startY, int side) {
        if (side < 0) {
            startX -= size >> 2;
        } else {
            startX += size >> 2;
        }
        for (Line line : lines) {
            line.setStartX(startX);
            line.setStartY(startY);
            line.setEndX(startX + len * side);
        }
        lines[0].setEndY(startY + len * side);
        lines[1].setEndY(startY - len * side);
    }

}

