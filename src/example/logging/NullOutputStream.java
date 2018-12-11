package example.logging;

import java.io.IOException;
import java.io.OutputStream;

public class NullOutputStream extends OutputStream {
    public static NullOutputStream INSTANCE = new NullOutputStream();

    public NullOutputStream() {
    }

    public void write(int ignored) throws IOException {
    }
}


