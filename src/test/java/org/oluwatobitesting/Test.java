package org.oluwatobitesting;

public class Test {
    public int centeredArray(int[] sampleArray) {
        int arraySize = sampleArray.length;
        if (arraySize % 2 == 0) {
            return 0;
        }

        for (int i : sampleArray) {
            int centeredElementIndex = arraySize / 2;
            int centeredElement = sampleArray[centeredElementIndex];
            if (i <= centeredElement && i != centeredElement) {
                return 0;
            }
        }
        return 1;
    }

    public int arrayDifference(int[] sampleArray) {
        int X = 0, Y = 0;
        for (int i : sampleArray) {
            if (i % 2 == 0) {
                Y = Y + i;
            } else {
                X = X + i;
            }
        }
        return X - Y;
    }

    public static void main(String[] args) {
        int[] centeredArray = {3,2,1,4,5};
        Test testObject = new Test();
        System.out.println(testObject.centeredArray(centeredArray));
        System.out.println(testObject.arrayDifference(centeredArray));
    }
}
