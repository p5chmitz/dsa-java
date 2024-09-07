package smorgasbord.lists;

public class LinkedLists {

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
        /** Prints a formatted list to specified length */
        public void printList(int len) {
            String noun;
            if (numEntries == 1) { noun = "entry"; } else { noun = "entries"; }
            System.out.println("The list has " + numEntries + " " + noun + ", here are the first " + len + ":");

            SinglyLinkedList.Node current = head;
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

        /** Local implementation of a class to store the entry payload values */
        private static class Entry {
            // The Entry's instance variables
            private final String name;
            private final int score;

            public Entry(String n, int s) {
                name = n;
                score = s;
            }

            // The Entry's API
            public String getName() { return name; }
            public int getScore() { return score; }

        }

        /** Each node of the linked list stores a pointer to the next node and a generic entry that contains the scores */
        private static class Node<E> {
            // The Node's instance variables
            private Entry entry;
            private Node<E> next;
            private Node<E> prev;

            // Constructors
            public Node() {}
            public Node(Entry e, Node<E> p, Node<E> n) {
                this.entry = e;
                this.prev = p;
                this.next = n;
            }

            // The Node's API
            public String toString() { return "(" + entry.getName() + ", " + entry.getScore() + ")"; }

        }

        // SinglyLinkedList's instance variables

        // Declares a counter to track the number of nodes/entries in the list, some implementations call this size;
        // IMPORTANT: You must update this field for all methods that change the number of Node/Entry objects in the list
        private int numEntries = 0;
        // Just a lonely, singly-linked list
        private Node<E> head;

        // The SinglyLinkedList's API
        /** Returns the number of elements in the list */
        public int size() { return numEntries; }
        /** Returns boolean based on whether the list is empty or not */
        public boolean isEmpty() { return numEntries == 0; }
        /** Adds a new score to the collection */
        public void insert(Node<E> newNode) {
            // Adds first head node
            if (head == null) {
                head = newNode;
                numEntries++;
                System.out.println(" at first head node.");
            // Replaces the head node if the new score is higher
            } else if (head.entry.score <= newNode.entry.score) {
                System.out.println(" as new head node.");
                //newNode.prev = null; // Unnecessary because the new node doesn't have any pointers yet anyway
                newNode.next = head;
                head.prev = newNode;
                //currentNode.next = null; // Unnecessary because the sole head node's next pointer is already null
                head = newNode;
                numEntries++;
            } else {
                System.out.println("\nIterating...");
                Node<E> iterNode = head; // Keeps the original head intact
                while (iterNode.next != null && newNode.entry.score <= iterNode.entry.score) {
                    // Advances the current node
                    iterNode = iterNode.next;
                }
                iterNode.next = newNode;
                newNode.prev = iterNode;
                numEntries++;
            }
        }
        /** Remove and return the score at index i. Works like pop by returning the removed node. */
        public Node<E> remove(int index) {
            // Gets angry if you try to pass an OOB index
            if (index < 0 || index >= numEntries) {
                throw new IndexOutOfBoundsException("Error: out of bounds\n\tTried to access index " + index + " with list size " + numEntries);
            }

            Node<E> iterNode = head;
            int counter = 0;

            // Edge case to remove the head of the list
            if (head != null && index == 0) {
                    head.prev = null;
                    head = head.next;
            } else {
                // Finds the target node to remove
                while (counter < index) {
                    iterNode = iterNode.next;
                    counter++;
                }
                // Resets pointers if the target is not the tail
                if (iterNode.next != null) {
                    iterNode.next.prev = iterNode.prev;
                    iterNode.prev.next = iterNode.next;
                }
                // Edge case to remove the tail
                else {
                    iterNode.prev.next = null;
                }
            }

            numEntries--; // Gotta keep score!
            return iterNode; // Returns the removed node

        }
        /** Prints a formatted list to specified length */
        public void printList(int len) {
            String noun;
            if (numEntries == 1) { noun = "entry"; } else { noun = "entries"; }
            System.out.println("The list has " + numEntries + " " + noun + ", here are the first " + len + ":");

            DoublyLinkedList.Node<E> node = head;
            int e = 0;
            while (node != null && e < len) {
                System.out.printf("%2d: %-6s %6s%n", e + 1, node.entry.name, node.entry.score);
                e++;
                node = node.next;
            }
            while (e < len) {
                System.out.printf("%2d:%n", e + 1);
                e++;
            }
            System.out.println("");
        }

    }

    /** This driver contains the calling code for the singly-linked list based scoreboard list structure */
    public static void singlyLinkedListDriver() {

        // Print size variables
        int podiumSize = 3;
        int fullSize = 5;

        // Declares a new list and outlines some compact sample data
        SinglyLinkedList singlyLinkedList = new SinglyLinkedList();
        String[] names = {"Peter", "Brain", "Dingus", "Bobson"};
        int[] scores = {1223, 616, 34, 42069};
        // Creates a list of entries from value arrays and adds them to the list
        for (int i = 0; i < names.length; i++) {
            SinglyLinkedList.Node gE = new SinglyLinkedList.Node(names[i], scores[i], null);
            System.out.println("Adding " + gE);
            singlyLinkedList.insert(gE);
            singlyLinkedList.printList( 3);
        }
        System.out.println("After all additions:");
        singlyLinkedList.printList(fullSize);

        // Removes entries at specified indices and prints the scoreboard after each removal
        int[] indicesToRemove = {0, 1, 0};
        for (int index : indicesToRemove) {
            System.out.println("Removing " + singlyLinkedList.remove(index) + " at index " + index);
            singlyLinkedList.printList( podiumSize);
        }
        System.out.println("After all removals:");
        singlyLinkedList.printList( fullSize);
    }
    /** This driver contains the calling code for the doubly-linked list based scoreboard list structure */
    public static <T> void doublyLinkedListDriver() {

        // Declares a new list and outlines some compact sample data
        DoublyLinkedList<T> doublyLinkedList = new DoublyLinkedList<T>();
        String[] names = {"Peter", "Brain", "Dingus", "Bobson"};
        int[] scores = {1223, 616, 34, 42069};
        // Creates a list of entries from value arrays and adds them to the list
        for (int i = 0; i < names.length; i++) {
            DoublyLinkedList.Node<T> gE = new DoublyLinkedList.Node<>(new DoublyLinkedList.Entry(names[i], scores[i]), null, null);
            System.out.print("Adding " + gE);
            doublyLinkedList.insert(gE);
            doublyLinkedList.printList( 3);
        }
        System.out.println("The list after adding all scores:");
        doublyLinkedList.printList( 5);

        // Removes entries at specified indices and prints the scoreboard after each removal
        int[] indicesToRemove = {0, 2, 1, 0};
        for (int index : indicesToRemove) {
            DoublyLinkedList.Node<T> node = doublyLinkedList.remove(index);
            System.out.println("Podium after removing " + node.toString() + " at index " + index);
            doublyLinkedList.printList(3);
        }
    }

}
