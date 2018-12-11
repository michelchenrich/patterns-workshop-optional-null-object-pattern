package example.logging;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Logger {
    private DataOutputStream outputStream;

    // ...

    private Logger(OutputStream outputStream) {
        this.outputStream = new DataOutputStream(outputStream);
    }

    public void error(String message) {
        write("ERROR", message);
    }

    // ...

    private void write(String type, String message) {
        try {
            outputStream.writeChars("[" + type + "] - " + message + "\n");
        } catch (IOException error) {
            throw new RuntimeException(error);
        }
    }

    // ...

    private static final Logger STANDARD = new Logger(System.out);
    private static final Logger NULL = new Logger(new NullOutputStream());

    public static Logger getLogger() {
        return ApplicationSettings.shouldLog() ?
               STANDARD :
               NULL;
    }

    public void debug(String message) {
        write("DEBUG", message);
    }

    public void info(String message) {
        write("INFO", message);
    }

    public void warning(String message) {
        write("WARNING", message);
    }
}

