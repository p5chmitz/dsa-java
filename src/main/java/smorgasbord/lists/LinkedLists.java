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
