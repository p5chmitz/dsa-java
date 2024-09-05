package smorgasbord;

import smorgasbord.TGG.GTG01;
import smorgasbord.MAW.W01;
import smorgasbord.lists.ArrayLists;
import smorgasbord.lists.LinkedLists;


/**
 * Centrally located driver for convenience
 */
public class ProjectRunner {

    public static void main( String[] args ) {

        // Takes a number and prints its digits recursively
        W01.ch01_1(498);
        // Illustrates genericity post Java 5 with the custom GenericMemoryCell<T> class
        // Prints hard-coded value (12)
        W01.GenericMemoryCell.rawGeneric();
        // Prints hard-coded value (23)
        W01.GenericMemoryCell.impleentedGeneric();
        // Illustrates a binary search implementation; If the target is in the array it prints the index
        int target = 735;
        int[] array = {1, 4, 5, 6, 10, 12, 16, 21, 23, 24, 25, 27, 31, 32, 33, 35, 37, 39, 40, 41, 42, 43, 45, 47, 49, 50, 51, 52, 54, 56, 57, 60, 61, 67, 70, 71, 72, 73, 74};
        int result = GTG01.binarySearch(array, target);
        if (result == -1) {
            System.out.println("The target " + target + " is not in the array");
        } else {
            System.out.println("Given an array of "
                    + array.length + " indexes, the target "
                    + target + " exists at index "
                    + result + ".");
        }

        // lemmeSee is a test method written to take two generic types that implement Comparable
        // and prints a quantitative statement based on parameter order
        GTG01.lemmeSee(12, 15);
        // The Comp class has two instance variables; This experiment sets up two Comp objects and compares them
        // with the generic lemmeSee() method to prove that it takes ints and objects
        GTG01.Comp a = new GTG01.Comp(19, 10);
        GTG01.Comp b = new GTG01.Comp(20, 9);
        try {
            GTG01.lemmeSee(a, b);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }

        //
        System.out.print("\nInsertion sort empirical test:\nUnsorted array ");
        GTG01.empiricalTest();
        System.out.println("");

        // List drivers
        //ArrayLists.staticListDriver();
        //ArrayLists.dynamicListDriver();
        LinkedLists.singlyLinkedListDriver();
    }

}
