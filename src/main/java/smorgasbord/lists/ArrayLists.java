package smorgasbord.lists;

import java.util.ArrayList;

public class ArrayLists {

    /** Class for storing high scores in an array in non-decreasing order */
    public static class StaticList {

        /** Basic data store that represents each "node" of the list */
        public static class Entry {

            // GameEntry instance values
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
        // Declares list as an array of Entry objects (that contain name and score)
        private final Entry[] list;
        // Declares numEntries as a counter to track entries in a GameEntry object array;
        // You must update this field for all methods that change the number of Entry objects in the list
        private int numEntries = 0;

        // The scoreboard's API
        /** Constructs an empty scoreboard with the given capacity for storing entries */
        public StaticList(int capacity) {
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

    /** Class for storing high scores in an array in non-decreasing order */
    public static class DynamicList {

        /** Basic data store that represents each "node" of the list */
        public static class Entry {

            // GameEntry instance values
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
        public DynamicList(int capacity) {
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

    /** Class for storing high scores in an array in non-decreasing order */
    public static class LinkedList {

        /** Basic data store that represents each "node" of the list */
        public static class Entry {

            // GameEntry instance values
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

        // Declares numEntries as a counter to track the number of Entry objects in the list;
        // You must update this field for all methods that change the number of Entry objects in the list
        private int numEntries = 0;

        // The list's API
        /** Constructs an empty scoreboard with the given capacity for storing entries */
        public LinkedList(int capacity) {

        }

        /** Adds a new score to the collection */
        public void add(Entry e) {
            numEntries++; // Gotta keep score!
        }

        /** Remove and return the score at index i. */
        //public Entry remove(int i) throws IndexOutOfBoundsException {
        //    numEntries--; // Gotta keep score!
        //    return Entry;
        //}

        /** Prints a formatted list to specified length */
        public void printList(int len) {
        }

        /** Prints the number of entries and capacity of the list */
        public void printSize() {
            int entries = numEntries;
            //int length = list.size();
            //System.out.println("The list has " + entries + " entries and a total capacity of " + length + ".");
            //double percent = (double) entries / length * 100;
            //System.out.println("You've used ~" + (int) percent + "% of the list's capacity.\n");
        }

    }

    // This is where all the magic happens
    /** This driver contains the calling code for the static array based scoreboard list structure */
    public static void staticListDriver() {

        // Static array-based list implementation

        // Creates new scoreboard, populates it with four entries, and prints the top 3 spots
        StaticList staticList = new StaticList(10);
        String[] names = {"Peter", "Brain", "Dingus", "Bobson"};
        int[] scores = {1223, 616, 34, 42069};
        // Creates GameEntry pairs from local names and scores arrays,
        // adds them to the highscores (Scoreboard.GameEntry) array
        for (int i = 0; i < names.length; i++) {
            StaticList.Entry gE = new StaticList.Entry(names[i], scores[i]);
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
        DynamicList dynamicList = new DynamicList(10);
        String[] names = {"Peter", "Brain", "Dingus", "Bobson"};
        int[] scores = {1223, 616, 34, 42069};
        // Creates GameEntry pairs from local names and scores arrays,
        // adds them to the highscores (Scoreboard.GameEntry) array
        for (int i = 0; i < names.length; i++) {
            DynamicList.Entry gE = new DynamicList.Entry(names[i], scores[i]);
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
    /** This driver contains the calling code for the linked list based scoreboard list structure */
    public static void LinkedListDriver() {

        // Dynamic array-based list implementation

        // Creates new scoreboard, populates it with four entries, and prints the top 3 spots
        DynamicList dynamicList = new DynamicList(10);
        String[] names = {"Peter", "Brain", "Dingus", "Bobson"};
        int[] scores = {1223, 616, 34, 42069};
        // Creates GameEntry pairs from local names and scores arrays,
        // adds them to the highscores (Scoreboard.GameEntry) array
        for (int i = 0; i < names.length; i++) {
            DynamicList.Entry gE = new DynamicList.Entry(names[i], scores[i]);
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
}
