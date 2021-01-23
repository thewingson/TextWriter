import javax.xml.bind.annotation.*;
import java.lang.reflect.Array;


/**
 * Generic type array
 *
 * This is not dynamic, also static as simple arrays.
 * So, be careful with size when working with it.
 * */
@XmlRootElement(name = "square-numbers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomGenericArray<T extends SquareNumber> {

    // HERE IS USED ENCAPSULATION. CLASS FIELDS CLOSED TO REFERENCE TO WORK DIRECTLY
    // AND OPENED ONLY BY METHOD ACCESS, WHICH ARE ALSO CONTAINS OWN LOGIC TO LIMIT
    // ACCESS OF CLIENT.

    @XmlElement(name = "square-number")
    private T[] objectArray = null;
    @XmlTransient
    private int index = 0;

    public CustomGenericArray() {
    }

    public CustomGenericArray(Class<T> type, int length) {
        @SuppressWarnings("unchecked") final T[] a = (T[]) Array.newInstance(type, length);
        this.objectArray = a;
    }

    public T[] getAll() {
        return this.objectArray;
    }

    public T get(int i) {
        return this.objectArray[i];
    }

    public void add(T element) {
        if (this.index < objectArray.length) {
            objectArray[index] = element;
            this.index++;
        }
    }

    public void addAll(T[] elements) {
        if (this.objectArray.length - this.index <= elements.length) {
            for (T element : elements) {
                add((element));
            }
            this.index += elements.length;
        }
    }

    public int getSize() {
        return this.objectArray.length;
    }

}
