package file_generation;

import data_structure.Microservice;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;

public class GraphGenerator {
    private final String xmlFilePath;
    private final ArrayList<Microservice> matrices;

    public GraphGenerator(String xmlFilePath, ArrayList<Microservice> matrices) {
        this.xmlFilePath = xmlFilePath + "\\graph.xml";
        this.matrices = matrices;
    }

    public void create() {

        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            Element root = document.createElement("graph");
            document.appendChild(root);

            for (Microservice microservice : matrices) {
                Element row = document.createElement("row");
                row.setAttribute("index", String.valueOf(microservice.getId()));
                root.appendChild(row);

                Element name = document.createElement("MicroserviceName");
                name.appendChild(document.createTextNode(microservice.getMicroserviceName()));
                row.appendChild(name);

                Element URI = document.createElement("URI");
                URI.appendChild(document.createTextNode(microservice.getURI()));
                row.appendChild(URI);

                Element connectionType = document.createElement("ConnectionType");
                connectionType.appendChild(document.createTextNode(microservice.getConnectionType()));
                row.appendChild(connectionType);

                Element isDuplicate = document.createElement("Duplicate");
                isDuplicate.appendChild(document.createTextNode(String.valueOf(microservice.isDuplicate())));
                row.appendChild(isDuplicate);

                Element creationTime = document.createElement("CreationTime");
                creationTime.appendChild(document.createTextNode(microservice.getCreationTime()));
                row.appendChild(creationTime);

                Element pattern = document.createElement("Pattern");
                pattern.appendChild(document.createTextNode(microservice.getPatternName()));
                row.appendChild(pattern);

                Element role = document.createElement("Role");
                role.appendChild(document.createTextNode(microservice.getRoleInPattern()));
                row.appendChild(role);

                Element connectedURIs = document.createElement("ConnectedURIs");
                connectedURIs.appendChild(document.createTextNode(microservice.getConnectedMicroservices()));
                row.appendChild(connectedURIs);

            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(xmlFilePath));

            transformer.transform(domSource, streamResult);

        } catch (ParserConfigurationException | TransformerException pce) {
            pce.printStackTrace();
        }
    }
}
