
/**
 * Simple service class with template methods
 * for using from client side.
 * */
public class ApplicationService {

    private AbstractFileWriter fileWriter;

    public ApplicationService(AbstractFileWriter fileWriter) {
        this.fileWriter = fileWriter;
    }

    public void writeToFile(CustomGenericArray<?> genericArray){
        this.fileWriter.write(genericArray);
    }
}
