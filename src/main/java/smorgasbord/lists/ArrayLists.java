package smorgasbord.lists;

import java.util.ArrayList;

public class ArrayLists {

    // Array-based lists
    /** Class for storing sorted high scores in an array */
    public static class StaticArrayList {

        /** Basic data store that represents each "node" or index of the list */
        public static class Entry {

            // Instance values
            private final String name;
            private final int score;

            // Entry API
            /** Constructs an object tuple with parameters */
            public Entry(String n, int s) {
                name = n;
                score = s;
            }

            /** Returns the name field */
            public String getName( ) { return name; }

            /** Returns the score field */
            public int getScore( ) { return score; }

            /** Because OFC Java cant just infer that a string is a string */
            public String toString() { return "(" + name + ", " + score + ")"; }

        }

        // StaticScoreboard instance values
        // Declares list as an array of Entry objects (that contain name and score)
        private final Entry[] list;
        // Declares numEntries as a counter to track entries in a GameEntry object array;
        // You must update this field for all methods that change the number of Entry objects in the list
        private int numEntries = 0;

        // The scoreboard's API
        /** Constructs an empty scoreboard with the given capacity for storing entries */
        public StaticArrayList(int capacity) {
            list = new Entry[capacity];
        }

        /** Attempt to add a new score to the collection (if it is high enough) */
        public void add(Entry e) {
            int newScore = e.getScore( );

            // Adds score if there are either open entries or its higher than the last score on the list
            if (numEntries < list.length || newScore > list[numEntries-1].getScore( )) {
                // Drops no scores and increases the length of the object array
                if (numEntries < list.length) {
                    numEntries++;
                }

                // shift any lower scores rightward to make room for the new entry
                int j = numEntries - 1;
                while (j > 0 && list[j - 1].getScore( ) < newScore) {
                    // shift entry from j-1 to j and decrement j
                    list[j] = list[j - 1];
                    j--;
                }
                // when done, add new entry
                list[j] = e;
            }
        }

        /** Remove and return the score at index i. */
        public Entry remove(int i) throws IndexOutOfBoundsException {
            if (i < 0 || i >= numEntries)
                throw new IndexOutOfBoundsException("Invalid index: " + i);
            Entry temp = list[i];                      // save the object to be removed
            for (int j = i; j < numEntries - 1; j++)   // count up from i (not down)
                list[j] = list[j+1];                   // move one cell to the left
            list[numEntries -1 ] = null;               // null out the old last score
            numEntries--;
            return temp;                               // return the removed object
        }

        /** Prints a formatted list to specified length */
        public void printList(int len) {
            for (int e = 0; e < len; e++) {
                if (e >= list.length) {return;} // Prevents OOB errors
                if (list[e] != null) {
                    System.out.printf("%2d: %-6s %6s%n", e + 1, list[e].getName(), list[e].getScore());
                } else {
                    System.out.printf("%2d:%n", e + 1);
                }
            }
            System.out.println("");
        }

        /** Prints the number of entries and capacity of the list */
        public void printSize() {
            int entries = numEntries;
            int length = list.length;
            System.out.println("The list has " + entries + " entries and a total capacity of " + length + ".");
            double percent = (double) entries / length * 100;
            System.out.println("You've used ~" + (int) percent + "% of the list's capacity.\n");
        }

    }

    /** Class for storing sorted high scores in a dynamic array (ArrayList) */
    public static class DynamicArrayList {

        /** Basic data store that represents each "node" or index of the list */
        public static class Entry {

            // Instance values
            private final String name;
            private final int score;

            // GameEntry API
            /** Constructs an object tuple with parameters */
            public Entry(String n, int s) {
                name = n;
                score = s;
            }

            /** Returns the name field */
            public String getName( ) { return name; }

            /** Returns the score field */
            public int getScore( ) { return score; }

            /** Because OFC Java cant just infer that a string is a string */
            public String toString() { return "(" + name + ", " + score + ")"; }

        }

        // StaticScoreboard instance values
        // Declares list as a vector of Entry objects (that contain name and score)
        private final ArrayList<Entry> list;
        // Declares numEntries as a counter to track entries in a GameEntry object array;
        // You must update this field for all methods that change the number of Entry objects in the list
        private int numEntries = 0;

        // The list's API
        /** Constructs an empty scoreboard with the given capacity for storing entries */
        public DynamicArrayList(int capacity) {
            list = new ArrayList(capacity);
        }

        /** Adds a new score to the collection */
        public void add(Entry e) {
            numEntries++; // Gotta keep score!
            int newScore = e.getScore( );
            // Adds element to the first index for new lists, otherwise checks each
            // index's score to insert at the correct place
            if (list.size() == 0) {
                list.add(0, e);
            } else {
                int index = 0;
                for (int j = 0; j < list.size(); j++) {
                    if (newScore <= list.get(j).getScore()) { index++; } else { break; }
                }
                list.add(index, e);
            }
        }

        /** Remove and return the score at index i. */
        public Entry remove(int i) throws IndexOutOfBoundsException {
            numEntries--; // Gotta keep score!
            Entry e = list.get(i);
            list.remove(i);
            return e;
        }

        /** Prints a formatted list to specified length */
        public void printList(int len) {
            for (int e = 0; e < len; e++) {
                if (e >= list.size()) {break;} // Prevents OOB errors
                if (list.get(e) != null) {
                    System.out.printf("%2d: %-6s %6s%n", e + 1, list.get(e).getName(), list.get(e).getScore());
                } else {
                    System.out.printf("%2d:%n", e + 1);
                }
            }
            System.out.println("");
        }

        /** Prints the number of entries and capacity of the list */
        public void printSize() {
            int entries = numEntries;
            int length = list.size();
            System.out.println("The list has " + entries + " entries and a total capacity of " + length + ".");
            double percent = (double) entries / length * 100;
            System.out.println("You've used ~" + (int) percent + "% of the list's capacity.\n");
        }

    }

    // Array-based list drivers
    /** This driver contains the calling code for the static array based scoreboard list structure */
    public static void staticListDriver() {

        // Static array-based list implementation

        // Creates new scoreboard, populates it with four entries, and prints the top 3 spots
        StaticArrayList staticList = new StaticArrayList(10);
        String[] names = {"Peter", "Brain", "Dingus", "Bobson"};
        int[] scores = {1223, 616, 34, 42069};
        // Creates GameEntry pairs from local names and scores arrays,
        // adds them to the highscores (Scoreboard.GameEntry) array
        for (int i = 0; i < names.length; i++) {
            StaticArrayList.Entry gE = new StaticArrayList.Entry(names[i], scores[i]);
            System.out.println("Adding " + gE);
            staticList.add(gE);
            staticList.printList(3);
        }
        staticList.printSize();

        System.out.println("The final list:");
        staticList.printList(10);

        // Removes scores at specified indices and prints the scoreboard after each removal
        int[] indicesToRemove = {0, 1, 0};
        for (int index : indicesToRemove) {
            System.out.println("Podium after removing score at index " + index);
            staticList.remove(index);
            staticList.printList(3);
        }
        staticList.printSize();
    }
    /** This driver contains the calling code for the dynamic array based scoreboard list structure */
    public static void dynamicListDriver() {

        // Dynamic array-based list implementation

        // Creates new scoreboard, populates it with four entries, and prints the top 3 spots
        DynamicArrayList dynamicList = new DynamicArrayList(10);
        String[] names = {"Peter", "Brain", "Dingus", "Bobson"};
        int[] scores = {1223, 616, 34, 42069};
        // Creates GameEntry pairs from local names and scores arrays,
        // adds them to the highscores (Scoreboard.GameEntry) array
        for (int i = 0; i < names.length; i++) {
            DynamicArrayList.Entry gE = new DynamicArrayList.Entry(names[i], scores[i]);
            System.out.println("Adding " + gE);
            dynamicList.add(gE);
            dynamicList.printList(3);
        }
        dynamicList.printSize();

        System.out.println("The final list:");
        dynamicList.printList(10);

        // Removes scores at specified indices and prints the scoreboard after each removal
        int[] indicesToRemove = {0, 1, 0};
        for (int index : indicesToRemove) {
            System.out.println("Podium after removing score at index " + index);
            dynamicList.remove(index);
            dynamicList.printList(3);
        }
        dynamicList.printSize();
    }

    // Linked lists
    /** Class for storing sorted high scores in a singly-linked list */
    public static class SinglyLinkedList {

        /** Each node of the linked list stores a pointer to the next node and a generic entry that contains the scores */
        private static class Node {
            // The Node's instance variables
            private String name;
            private int score;
            private Node next;

            public Node(String n, int s, Node node) {
                name = n;
                score = s;
                next = node;
            }

            // The Node's API
            public String getName() { return name; }
            public int getScore() { return score; }
            public Node getNext() { return next; }
            public void setNext(Node n) { next = n; }
            /** Because OFC Java cant just infer the class's member types */
            public String toString() { return "(" + name + ", " + score + ")"; }

        }

        // SinglyLinkedList's instance variables

        // Declares a counter to track the number of nodes/entries in the list, some implementations call this size;
        // IMPORTANT: You must update this field for all methods that change the number of Node/Entry objects in the list
        private int numEntries = 0;

        // Just a lonely, singly-linked list
        private Node head;

        // No-arg (only, and therefore implied and unnecessary) constructor
        //public SinglyLinkedList() {}

        // The SinglyLinkedList's API
        /** Returns the number of elements in the list */
        public int size() { return numEntries; }
        /** Returns boolean based on whether the list is empty or not */
        public boolean isEmpty() { return numEntries == 0; }

        /** Adds a new score to the collection */
        public void insert(Node newNode) {
            // Sets head if its null or replaces it if the score is larger than the current head
            if (head == null || newNode.score >= head.score) {
                newNode.next = head;
                head = newNode;
            }
            else {
                Node current = head;
                // Iterates from the top of the list until either the Node's next pointer is null
                // or the new score is smaller than or equal to the current node
                while (current.next != null && newNode.score <= current.next.score) {
                    current = current.next;
                }
                // Inserts the newNode at the proper location and resets relevant pointers
                newNode.next = current.next;
                current.next = newNode;
            }
            numEntries++; // Gotta keep score!
        }

        /** Remove and return the score at index i. Works like pop by returning the removed node. */
        public Node remove(int i) {
            // Gets angry if you try to pass an OOB index
            if (i < 0 || i >= numEntries) {
                throw new IndexOutOfBoundsException("Error: out of bounds\n\tTried to access index " + i + " with list size " + numEntries);
            }

            Node current = head;
            Node prev = null;
            int c = 0;

            // Accounts for the special case of removing the head node;
            // Otherwise walks the list to the target node
            // and resets the previous node's pointer to the node after the target
            if (i == 0) {
                head = current.next;
            } else {
                while (c < i) {
                    prev = current;
                    current = current.next;
                    c++;
                }
                prev.next = current.next;
            }

            numEntries--; // Gotta keep score!
            return current; // Returns the removed node
        }

        // TODO: Remove unused print function?
        /** Prints a formatted list to specified length */
        public void printList(int len) {
            Node current = head;
            int e = 0;
            while (current != null && e < len) {
                System.out.printf("%2d: %-6s %6s%n", e + 1, current.name, current.score);
                e++;
                current = current.next;
            }
            while (e < len) {
                System.out.printf("%2d:%n", e + 1);
                e++;
            }
            System.out.println("");
        }

    }

    /** Class for storing sorted high scores in a singly-linked list */
    public static class DoublyLinkedList<E> {

        /** Each node of the linked list stores a pointer to the next node and a generic entry that contains the scores */
        private static class Node<E> {
            // The Node's instance variables
            private String name;
            private int score;
            private Node<E> next;

            public Node(String n, int s, Node<E> node) {
                name = n;
                score = s;
                next = node;
            }

            // The Node's API
            public String getName() { return name; }
            public int getScore() { return score; }
            public Node<E> getNext() { return next; }
            public void setNext(Node<E> n) { next = n; }
            /** Because OFC Java cant just infer the class's member types */
            public String toString() { return "(" + name + ", " + score + ")"; }

        }

        // SinglyLinkedList's instance variables

        // Declares a counter to track the number of nodes/entries in the list, some implementations call this size;
        // IMPORTANT: You must update this field for all methods that change the number of Node/Entry objects in the list
        private int numEntries = 0;

        // Just a lonely, singly-linked list
        private Node head;

        // No-arg (only, and therefore implied and unnecessary) constructor
        //public SinglyLinkedList() {}

        // The SinglyLinkedList's API
        /** Returns the number of elements in the list */
        public int size() { return numEntries; }
        /** Returns boolean based on whether the list is empty or not */
        public boolean isEmpty() { return numEntries == 0; }

        /** Adds a new score to the collection */
        public void insert(Node newNode) {
            // Sets head if its null or replaces it if the score is larger than the current head
            if (head == null || newNode.score >= head.score) {
                newNode.next = head;
                head = newNode;
            }
            else {
                Node current = head;
                // Iterates from the top of the list until either the Node's next pointer is null
                // or the new score is smaller than or equal to the current node
                while (current.next != null && newNode.score <= current.next.score) {
                    current = current.next;
                }
                // Inserts the newNode at the proper location and resets relevant pointers
                newNode.next = current.next;
                current.next = newNode;
            }
            numEntries++; // Gotta keep score!
        }

        /** Remove and return the score at index i. Works like pop by returning the removed node. */
        public Node remove(int i) {
            // Gets angry if you try to pass an OOB index
            if (i < 0 || i >= numEntries) {
                throw new IndexOutOfBoundsException("Error: out of bounds\n\tTried to access index " + i + " with list size " + numEntries);
            }

            Node current = head;
            Node prev = null;
            int c = 0;

            // Accounts for the special case of removing the head node;
            // Otherwise walks the list to the target node
            // and resets the previous node's pointer to the node after the target
            if (i == 0) {
                head = current.next;
            } else {
                while (c < i) {
                    prev = current;
                    current = current.next;
                    c++;
                }
                prev.next = current.next;
            }

            numEntries--; // Gotta keep score!
            return current; // Returns the removed node
        }

        /** Prints a formatted list to specified length */
        public void printList(int len) {
            Node current = head;
            int e = 0;
            while (current != null && e < len) {
                System.out.printf("%2d: %-6s %6s%n", e + 1, current.name, current.score);
                e++;
                current = current.next;
            }
            while (e < len) {
                System.out.printf("%2d:%n", e + 1);
                e++;
            }
            System.out.println("");
        }

    }

    // Linked list drivers
    /** This driver contains the calling code for the singly-linked list based scoreboard list structure */
    public static void singlyLinkedListDriver() {

        // Print size variables
        int podiumSize = 3;
        int fullSize = 5;

        // Creates new scoreboard, populates it with four entries, and prints the top 3 spots
        SinglyLinkedList singlyLinkedList = new SinglyLinkedList();
        String[] names = {"Peter", "Brain", "Dingus", "Bobson"};
        int[] scores = {1223, 616, 34, 42069};
        // Creates GameEntry pairs from local names and scores arrays,
        // adds them to the highscores (Scoreboard.GameEntry) array
        for (int i = 0; i < names.length; i++) {
            SinglyLinkedList.Node gE = new SinglyLinkedList.Node(names[i], scores[i], null);
            System.out.println("Adding " + gE);
            singlyLinkedList.insert(gE);
            printList(singlyLinkedList, 3);
        }
        System.out.println("After all additions:");
        printList(singlyLinkedList, fullSize);

        // Removes scores at specified indices and prints the scoreboard after each removal
        int[] indicesToRemove = {0, 1, 0};
        for (int index : indicesToRemove) {
            System.out.println("Removing " + singlyLinkedList.remove(index) + " at index " + index);
            printList(singlyLinkedList, podiumSize);
        }
        System.out.println("After all removals:");
        printList(singlyLinkedList, fullSize);
    }
    /** This driver contains the calling code for the doubly-linked list based scoreboard list structure */
    public static void DoublyLinkedListDriver() {

        // Creates new scoreboard, populates it with four entries, and prints the top 3 spots
        SinglyLinkedList dynamicList = new SinglyLinkedList();
        String[] names = {"Peter", "Brain", "Dingus", "Bobson"};
        int[] scores = {1223, 616, 34, 42069};
        // Creates GameEntry pairs from local names and scores arrays,
        // adds them to the highscores (Scoreboard.GameEntry) array
        for (int i = 0; i < names.length; i++) {
            SinglyLinkedList.Node gE = new SinglyLinkedList.Node(names[i], scores[i], null);
            System.out.println("Adding " + gE);
            dynamicList.insert(gE);
            //dynamicList.printList(3);
        }

        System.out.println("The final list:");
        //dynamicList.printList(10);

        // Removes scores at specified indices and prints the scoreboard after each removal
        int[] indicesToRemove = {0, 1, 0};
        for (int index : indicesToRemove) {
            System.out.println("Podium after removing score at index " + index);
            //dynamicList.remove(index);
            //dynamicList.printList(3);
        }
    }

    // Linked list print utility function
    /** Prints a formatted list to specified length */
    public static void printList(SinglyLinkedList list, int len) {
        String noun;
        if (list.numEntries == 1) { noun = "entry"; } else { noun = "entries"; }
        System.out.println("The list has " + list.numEntries + " " + noun + ", here are the first " + len + ":");

        SinglyLinkedList.Node current = list.head;
        int e = 0;
        while (current != null && e < len) {
            System.out.printf("%2d: %-6s %6s%n", e + 1, current.name, current.score);
            e++;
            current = current.next;
        }
        while (e < len) {
            System.out.printf("%2d:%n", e + 1);
            e++;
        }
        System.out.println("");
    }

}
