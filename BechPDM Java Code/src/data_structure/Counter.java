package data_structure;

public class Counter {
    private static int clientCounter = 0;
    private static int workerCounter = 0;

    public static synchronized int getClientNext() {
        return clientCounter++;
    }

    public static synchronized int getClientCurrent() {
        return clientCounter;
    }

    public static synchronized int getWorkerNext() {
        return workerCounter++;
    }

    public static synchronized int getWorkerCurrent() {
        return workerCounter;
    }
}
