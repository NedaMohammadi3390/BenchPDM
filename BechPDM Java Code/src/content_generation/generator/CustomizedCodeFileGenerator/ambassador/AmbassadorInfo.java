package content_generation.generator.CustomizedCodeFileGenerator.ambassador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AmbassadorInfo {
    private final String name;
    private final int port;
    private final List<Map<Map<String, Integer>,Integer>> externalServiceMap;
    private final Map<Map<String, Integer>, Integer> outerMap;
    private final Map<String, Integer> innerKey;

    public AmbassadorInfo(String name, int port) {
        this.name = name;
        this.port = port;
        this.externalServiceMap = new ArrayList<>();
        this.outerMap =   new HashMap<>();
        this.innerKey = new HashMap<>();
    }
    public void addexternalService(String externalServiceName, int externalServicePort,int externalServiceID) {
        innerKey.put(externalServiceName,externalServicePort);
        outerMap.put(innerKey,externalServiceID);
        externalServiceMap.add(outerMap);
    }

    public String getName() {
        return name;
    }

    public int getPort() {
        return port;
    }

    public List<Map<Map<String, Integer>,Integer>> getExternalServiceMap() {
        return externalServiceMap;
    }


}