package logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class MyLogger {
    private final BufferedWriter bw;
    public MyLogger() throws IOException {
        bw = new BufferedWriter(new FileWriter("MyLogger.log",true));
    }

    public synchronized void log(String level, String message, Throwable t) throws IOException {
        String log = LocalDateTime.now() + " [" +  level + "] " + message + "\n" + t;
        bw.write(log);
        bw.newLine();

    }
}
