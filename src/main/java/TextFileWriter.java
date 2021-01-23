import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Implementation of {@link AbstractFileWriter} for .txt
 * extension file output.
 *
 * Works as Singleton class.
 * */
public class TextFileWriter extends AbstractFileWriter {

    private static volatile TextFileWriter INSTANCE;

    private TextFileWriter() throws IOException {
        super();
    }

    /**
     * Lazy loading singleton access method.
     *
     * Contains synchronized block for
     * avoiding of race condition case
     * when referencing it's instance
     * to not duplicate instance.
     * */
    public static TextFileWriter getInstance() throws IOException {
        TextFileWriter result = INSTANCE;
        if (result != null) {
            return result;
        }
        synchronized(TextFileWriter.class) {
            if (INSTANCE == null) {
                INSTANCE = new TextFileWriter();
            }
            return INSTANCE;
        }
    }

    public synchronized void write(CustomGenericArray<?> genericArray) throws RuntimeException {
        StringBuffer finalString = new StringBuffer();
        for (int i=0; i < genericArray.getSize(); i++) {
            finalString
                    .append(genericArray.get(i))
                    .append(System.lineSeparator());
        }

        try {
            byte[] stringBytes = finalString.toString().getBytes();

            if(stringBytes.length > CommonMessages.MAX_FILE_SIZE){
                throw new FileSizeException(CommonMessages.FILE_SIZE__LIMIT_EXCEEDED);
            }
            outputStream.write(stringBytes);
        } catch (IOException e) {
            throw new RuntimeException(CommonMessages.ERROR_AT_WRITING);
        }
    }

}
