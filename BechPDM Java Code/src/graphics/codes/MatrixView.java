package graphics.codes;

import javafx.beans.property.SimpleStringProperty;

public class MatrixView {
    private final SimpleStringProperty microserviceName;
    private final SimpleStringProperty uRI;
    private final SimpleStringProperty role;
    private final SimpleStringProperty patternName;
    private final SimpleStringProperty implementedConnectionType;
    private final SimpleStringProperty connectedTo;

    public MatrixView(String microserviceName, String uRI,
                      String role, String patternName,
                      String implementedConnectionType, String connectedTo) {
        this.uRI = new SimpleStringProperty(uRI);
        this.microserviceName = new SimpleStringProperty(microserviceName);
        this.role = new SimpleStringProperty(role);
        this.patternName = new SimpleStringProperty(patternName);
        this.implementedConnectionType = new SimpleStringProperty(implementedConnectionType);
        this.connectedTo = new SimpleStringProperty(connectedTo);
    }

    public String getMicroserviceName() {
        return microserviceName.get();
    }

    public String getURI() {
        return uRI.get();
    }

    public String getImplementedConnectionType() {
        return implementedConnectionType.get();
    }

    public String getRole() {
        return role.get();
    }

    public String getPatternName() {
        return patternName.get();
    }

    public String getConnectedTo() {
        return connectedTo.get();
    }

}