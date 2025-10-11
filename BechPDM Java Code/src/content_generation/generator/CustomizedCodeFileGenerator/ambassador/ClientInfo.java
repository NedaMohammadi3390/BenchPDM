package content_generation.generator.CustomizedCodeFileGenerator.ambassador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientInfo {
    private final String name;
    private final int port;
    private final List<Map<Map<String, Integer>,Integer>> ambassadorMap;
    private final Map<Map<String, Integer>, Integer> outerMap;
    private final Map<String, Integer> innerKey;

    public ClientInfo(String name, int port) {
        this.name = name;
        this.port = port;
        this.ambassadorMap = new ArrayList<>();
        this.outerMap =   new HashMap<>();
        this.innerKey = new HashMap<>();
    }
    public void addAmbassador(String ambassadorName, int ambassadorPort,int ambassadorID) {
        innerKey.put(ambassadorName,ambassadorPort);
        outerMap.put(innerKey,ambassadorID);
        ambassadorMap.add(outerMap);
    }

    public String getName() {
        return name;
    }

    public int getPort() {
        return port;
    }

    public List<Map<Map<String, Integer>,Integer>> getAmbassadorMap() {
        return ambassadorMap;
    }


}
