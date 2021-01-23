import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


/**
 * Client side code
 */
public class Main {

    private static Scanner in = new Scanner(System.in);
    private static int[] squareArray;
    private static CustomGenericArray<SquareNumber> squareNumberArray;
    private static AbstractFileWriter fileWriter;
    private static ApplicationService service;

    public static void main(String[] args) throws IOException {
        initFields();

        System.out.println("Welcome to this console application. " +
                "\nPlease, enter desired number between 1 and 100:");
        int desiredNum = in.nextInt();
        if (desiredNum < 1 || desiredNum > 100) {
            throw new RuntimeException(CommonMessages.NUMBER_OUT_OF_RANGE);
        }

//        printNumbers(desiredNum); // uncomment if you want to print simple array values
        copyNumbers(desiredNum);

        service = new ApplicationService(fileWriter);
        service.writeToFile(squareNumberArray);
    }

    /**
     * Simple method for initialization of array values.
     */
    private static void initFields() throws IOException {
        squareArray = new int[100];
        for (int i = 1; i <= squareArray.length; i++) {
            squareArray[i - 1] = i * i;
        }

        // HERE IS USED POLYMORPHISM. REFERENCED TO THE INSTANCE OF CHILD CLASS.
        // AND IT'S VERY USEFUL IN CASES OF LS(LISKOV'S SUBSTITUTION) PRINCIPLE.
        // For example when creating ApplicationService class whe can substitute file writer implementations.
        // Also services method called write(args) is receives generic type, which gives us more flexibility.

//        fileWriter = new XmlFileWriter(); // uncomment it you want to work with TextFileWriter
        fileWriter = TextFileWriter.getInstance(); // uncomment it you want to work with TextFileWriter
    }

    /**
     * Method for printing squared values.
     *
     * @param rangeEnd - last index to read to.
     */
    private static void printNumbers(Integer rangeEnd) {
        for (int i = 0; i < rangeEnd; i++) {
            System.out.printf("(%d)^2 = %d%n", i + 1, squareArray[i]);
        }
    }

    /**
     * @param rangeStart - start index to read from.
     * @param rangeEnd   - last index to read to.
     * @see Main#printNumbers(Integer rangeEnd) overload
     */
    private static void printNumbers(Integer rangeStart, Integer rangeEnd) {
        for (int i = rangeStart - 1; i < rangeEnd; i++) {
            System.out.printf("(%d)^2 = %d%n", i + 1, squareArray[i]);
        }
    }

    /**
     * Copies values from array to generic array with objects.
     * @param desiredNum
     */
    private static void copyNumbers(int desiredNum) {
        squareNumberArray = new CustomGenericArray<>(SquareNumber.class, desiredNum); // initialization of generic array
        for (int i = 0; i < desiredNum; i++) {
            SquareNumber squareNumber = new SquareNumber(i + 1, squareArray[i]);
            squareNumberArray.add(squareNumber);
        }
    }
}
