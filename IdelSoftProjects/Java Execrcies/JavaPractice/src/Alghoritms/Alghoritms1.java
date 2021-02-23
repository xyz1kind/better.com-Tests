package Alghoritms;

import Helpers.BaseClass;

public class Alghoritms1 extends BaseClass {
    public static void printArrays() {
        for (int i = 0; i < theArray.length; i++) {
            System.out.println(theArray[i]);
        }
    }

    public static boolean doesArrayContainsValue (int searchValue) {
        boolean value = false;

        for (int i = 0; i < theArray.length; i ++ ) {
            if (theArray[i] == searchValue) {
                value = true;
            }
        }
        System.out.println(value);
        return value;
    }

    public static void sortArrays () {
        for (int i = 0; i < theArray.length - 1; i--) {

        }
    }
}
