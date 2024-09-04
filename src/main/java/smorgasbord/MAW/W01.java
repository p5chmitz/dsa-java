package smorgasbord.MAW;

/**
 * This is a sandbox class for chapter 1 of Data Structures and Algorithm Analysis in Java by Mark Allen Weiss
 */
public class W01<T> {

    /** Just a recursive test */
    public static void ch01_1(int n) {
        // Defines base case
        if (n >= 10) {
            // Recursive call to self
            ch01_1(n / 10);
        }
        // Prints the digit
        System.out.println(n % 10);
    }

    // Genericity
    /** Illustrates genericity post Java 5 */
    public static class GenericMemoryCell<T> {
        /** storedValue has read() and write() */
        private T storedValue;

        // GenericMemoryCell methods
        public T read() {
            return storedValue;
        }
        public void write(T x) {
            storedValue = x;
        }

        /** Uses raw parameterized class*/
        public static void rawGeneric() {
            GenericMemoryCell m = new GenericMemoryCell();
            m.write("12");
            String val = (String) m.read();
            System.out.println("value: " + val); // Uses inferred call to val.toString()
        }

        /** Specifies parameterized class */
        public static void impleentedGeneric() {
            GenericMemoryCell<Integer> n = new GenericMemoryCell<>();
            n.write(23);
            Integer val = n.read();
            System.out.println("value: " + val.toString());
        }

    }


}