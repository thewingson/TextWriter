import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import java.io.*;

public class XmlFileWriter extends AbstractFileWriter {

    public XmlFileWriter() throws IOException {
        super(CommonMessages.PATH_TO_THE_XMLFILE);
    }

    public XmlFileWriter(String fileName) throws IOException {
        super(fileName);
    }

    @Override
    void write(CustomGenericArray<?> genericArray) throws RuntimeException {
        try {
            JAXBContext context = JAXBContext.newInstance(genericArray.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StringWriter sw = new StringWriter();
            marshaller.marshal(genericArray, sw);
            String result = sw.toString();

            if(result.getBytes().length > CommonMessages.MAX_FILE_SIZE){
                throw new FileSizeException(CommonMessages.FILE_SIZE__LIMIT_EXCEEDED);
            }
            outputStream.write(result.getBytes());
        } catch (PropertyException e) {
            throw new RuntimeException(CommonMessages.JAXB_PROPERTY_IS_INCORRECTLY_CONFIGURED);
        } catch (JAXBException e) {
            throw new RuntimeException(CommonMessages.JAXB_MARSHALING_STRATEGY_IS_INCORRECTLY_CONFIGURED);
        } catch (IOException e) {
            throw new RuntimeException(CommonMessages.ERROR_AT_WRITING);
        }
    }
}
