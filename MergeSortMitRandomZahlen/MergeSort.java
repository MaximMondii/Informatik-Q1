public class MergeSort {

    public static void main(String[] args) {
        int temp;
        int[] arr = new int[20];
        for(int i = 0; i < arr.length; i++)  //Random Array
        {
             temp = (int)(Math.random() * 999) + 1;
             arr[i] = temp;
        }

        int[] merged = mergeSort(arr, 0, arr.length - 1); //Start

        for (int val : merged) {  //Wiedergabe
            String newline = System.getProperty("line.separator");
            System.out.print(val + " " + newline);
        }

    }

    public static int[] mergeTwoSortedArrays(int[] one, int[] two) {

        int[] sorted = new int[one.length + two.length];

        int i = 0;
        int j = 0;
        int k = 0;

        while (i < one.length && j < two.length) { // Array größer als zwei

            if (one[i] < two[j]) {
                sorted[k] = one[i];
                k++;
                i++;
            } else { 
                sorted[k] = two[j];
                k++;
                j++;
            }
        }

        if (i == one.length) { // two ist eine Zahl

            while (j < two.length) {
                sorted[k] = two[j];
                k++;
                j++;
            }
        }

        if (j == two.length) { // one ist eine Zahl

            while (i < one.length) {
                sorted[k] = one[i];
                k++;
                i++;
            }
        }

        return sorted;

    }

    public static int[] mergeSort(int[] arr, int min, int max) {

        if (min == max) { // Wenn der Array eins groß ist
            int[] br = new int[1];
            br[0] = arr[min];

            return br;
        }

        int mid = (min + max) / 2;

        int[] fh = mergeSort(arr, min, mid);
        int[] sh = mergeSort(arr, mid + 1, max);

        int[] merged = mergeTwoSortedArrays(fh, sh); //halbierte Arrays werden weitergegeben

        return merged;
    }

}