import java.io.*;

public abstract class AbstractFileWriter {

    protected String fileName = CommonMessages.PATH_TO_THE_TEXTFILE;

    /**
     * Default is chosen as {@link FileOutputStream}
     * */
    protected OutputStream outputStream;

    /**
     * Simple simple with no arguments.
     *
     * @throws FileNotFoundException if file not found or access denied
     *                          this will be thrown.
     */
    public AbstractFileWriter() throws IOException {
        File file = new File(this.fileName);
        file.createNewFile();
        this.outputStream = new FileOutputStream(file);
    }

    /**
     * @param fileName - full path to the file
     *
     * @throws FileNotFoundException if file not found or access denied
     *                          this will be thrown.
     */
    public AbstractFileWriter(String fileName) throws IOException {
        this.fileName = fileName;
        File file = new File(this.fileName);
        file.createNewFile();
        this.outputStream = new FileOutputStream(file);
    }


    /**
     * Writes data to file
     *
     * @param genericArray
     *
     * @throws FileSizeException - occurs when size exceeds {@link CommonMessages#MAX_FILE_SIZE} in KB.
     * This value compared by getBytes().
     */
    abstract void write(CustomGenericArray<?> genericArray) throws FileSizeException;

    /**
     * Closes output stream.
     *
     * @throws IOException if an I/O error occurs.
     */
    public void close() throws IOException {
        this.outputStream.close();
    }

}
