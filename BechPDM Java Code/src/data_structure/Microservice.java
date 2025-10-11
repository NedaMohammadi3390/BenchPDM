package data_structure;

import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;
import java.time.LocalTime;
import javafx.util.Pair;
import main.CSVGenerator;
import main.TracingFileGenerator;
import strategy.Strategy;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import static main.Main.path2;
import static main.Main.strtrace;
import static main.Main.strUsagMemo;
import static main.Main.strCPUUsage;


public class Microservice {
    private final int id;
    private final String microserviceName;
    private final String URI;
    public Pair<ConnectionType, String>[] connectionTypes;
    private final boolean duplicate;
    private final String creationTime;
    private final Pair<String, String> role;
    private final int port;

    private final ArrayList<Pair<ConnectionType, String>> connections;

    private final ArrayList<Pair<Long, Long>> usageMemory;
    private final ArrayList<Pair<Double, Double>> usageCPU;
    private final Strategy strategy;
    private static int traceId=1;
    public enum ConnectionType {POST, DELETE, PUT, GET, FROM, TO}

    public enum Pattern {
        LoadBalanceing, Sidecar, Aggregator, PipesAndFilters, LeaderElection,
        PriorityQueue, CacheASide, StaticContentHost, Ambassador, ApiGateway,
        Saga, ServiceDiscovery, EventSourcing, NoPattern,CustomPattern
    }

    public enum Role {
        Head, Worker, Leader, Filter, Client,Point,
        Sidecar, Publisher, Bus, Cache, Aggregator,
        Storage, Ambassador, ApiGateway, LoadBalancer, ServiceRegistry,
        EventOrderService, EventShippingService, LoadBalancingService, ProviderService,ClientService
        , DispatcherService, ExternalService, Shipping, Payment, Order, MainService;
    }

    public Microservice(int port, int id, String microserviceName, String URI, Pair<ConnectionType, String>[] connectionTypes,
                        boolean duplicate, String creationTime, Pair<String, String> role, Strategy strategy) throws IOException {
        this.port = port;
        this.id = id;
        this.microserviceName = microserviceName;
        this.URI = URI;
        this.connectionTypes = connectionTypes;
        this.duplicate = duplicate;
        this.creationTime = creationTime;

        this.role = role;
        this.strategy = strategy;
        connections = new ArrayList<>();
        usageMemory = new ArrayList<>();
        usageCPU = new ArrayList<>();
    }

    public ArrayList<Pair<ConnectionType, String>> getConnectionsPair() {
        return connections;
    }

    public String getURI() {
        return URI;
    }

    public void setConnections(Pair<ConnectionType, String> connection) {
        connections.add(connection);
    }



    public ArrayList<Pair<Long, Long>> getUsageMemory() {
        return usageMemory;
    }

    public void setUsageMemory() {
        Runtime runtime = Runtime.getRuntime();
        usageMemory.add(new Pair<>(System.currentTimeMillis(), (runtime.totalMemory() - runtime.freeMemory()) / (1024 * 1024) ));


    }

    public int getPort() {
        return port;
    }

    public void setUsageCPU() {
         OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
        double cpuLoad = osBean.getSystemCpuLoad() * 100; // System-wide CPU load
        double processCpuLoad = osBean.getProcessCpuLoad() * 100; // Process-specific CPU load
        usageCPU.add(new Pair<>(cpuLoad,processCpuLoad));
    }

    public Strategy getStrategy() {
        return strategy;
    }

    @Override
    public String toString() {
        String connection = getConnectionType();
        String roleString = role.getKey() + ": " + role.getValue();
        String otherKnownMicroservices = getConnectedMicroservices();

        try {
            tracingFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        usageMemoryFile();
        usageCPUFile();
        printTraceID();
//            tracingGenerator.closeFile();

        return id + " | "
                + microserviceName + " | "
                + URI + " | "
                + connection + "| "
                + duplicate + " | "
                + creationTime + " | "
                + roleString + " | "
                + otherKnownMicroservices;
    }


    public void tracingFile() throws IOException {

        for (Pair<ConnectionType, String> p : connections) {
            strtrace.append("{traceId:").append(traceId).append(",").
                    append("{serviveId:").append(id).append(",").
                    append("serviceName:").append(microserviceName).append(",").
                    append("serviceRole:").append(role.getKey()).append("_").append(role.getValue()).append(",").
                    append("serviceuri:").append(URI).append(",").
                    append("serviceCreationTime:").append(creationTime).append(",").
                    append("duplicate:").append(duplicate).append(",").
                    append("annotations:[{endpoint:{destinationServiceURI:").append(p.getValue()).append(",").
                    append("timeStamp:").append(System.nanoTime()).append(",").
                    append("typeRequest:").append(p.getKey()).append(",").append("},").append("]},").append(System.lineSeparator());

            traceId=traceId+1;

        }

    }
 public void printTraceID(){
     System.out.println("traceID is:"+traceId);
 }
    public void usageMemoryFile() {
        //  System.out.println("number of conenctions are:"+connections.size());

        for (Pair<Long, Long> um : usageMemory) {
            strUsagMemo.append("{serviveId:").append(id).append(",").
                    append("serviceName:").append(microserviceName).append(",").
                    append("serviceRole:").append(role.getKey()).append("_").append(role.getValue()).append(",").
                    append("serviceCreationTime:").append(creationTime).append(",").
                    append("Time").append(",").append(um.getKey()).append(",").
                    append("usageMemory:").append(",").
                    append((um.getValue())).append(",").append(System.lineSeparator());

            // System.out.println(traceId);
        }
    }

        public void usageCPUFile()  {
        for (Pair<Double, Double> cp : usageCPU) {
            strCPUUsage.append("{serviveId:").append(id).append(",").
                    append("serviceName:").append(microserviceName).append(",").
                    append("serviceRole:").append(role.getKey()).append("_").append(role.getValue()).append(",").
                    append("serviceCreationTime:").append(creationTime).append(",").
                    append("cpu load:").append(",").append(cp.getKey()).append(",").
                    append("Process CPU load:").append(",").append(cp.getValue()).append(",").append(System.lineSeparator());

            // System.out.println(traceId);
        }

    }
    public String getConnectedMicroservices() {
        StringBuilder otherKnownMicroservices = new StringBuilder();
        for (Pair<ConnectionType, String> p : connections) {
            otherKnownMicroservices.append(p.getKey()).append(" ").append(p.getValue()).append(" ");
        }
        return otherKnownMicroservices.toString();
    }

    public String getConnectionType() {
        StringBuilder connection = new StringBuilder();
        for (Pair<ConnectionType, String> connectionType : connectionTypes) {
            connection.append(connectionType.getKey()).append(" ");
        }
        return connection.toString();
    }

    public int getId() {
        return id;
    }

    public boolean isDuplicate() {
        return duplicate;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public Pair<String, String> getRole() {
        return role;
    }

    public String getPatternName() {
        return role.getKey();
    }

    public String getRoleInPattern() {
        return role.getValue();
    }

    public String getInfo() {
        return
                "Creation Time: " +
                creationTime +
                "/ " +
                microserviceName;
    }

    public String getMicroserviceName() {
        return microserviceName;
    }

    public String[] getConnections() {
        String[] c = new String[connections.size()];
        for (int i = 0; i < c.length; i++) {
            c[i] = connections.get(i).getValue();
        }
        return c;
    }

    public Pair<ConnectionType, String>[] getConnectionTypes() {
        return connectionTypes;
    }

    public String getMatrixAttributes() {
        String comma = ",";
        return "id" + comma + "microserviceName" + comma +
                "URI" + comma + "connectionTypes" + comma +
                "duplicate" + comma + "creationTime" + comma +
                "pattern" + comma + "role" + comma + "connections" + "\n";
    }

}
