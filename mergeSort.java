// Creates an array of random integers, then sorts it with Merge Sort

public class mergeSort {
	private int[] array;
    private int[] tempMergArr;
    private int length; 
    public static void main(String a[]){
    	int size = 8;
        int[] arr = new int[size];
        for (int i = 0; i < size; i++){											
        	arr[i] = (int)(Math.random() * 100);								//fills the array with random values
        	System.out.print(arr[i] + " ");
        }
        System.out.println();
        mergeSort mms = new mergeSort();
        mms.sort(arr);
        for(int i:arr){															//prints the array
            System.out.print(i + " ");
        }
    }
    
    public void sort(int inputArr[]) {
        this.array = inputArr;
        this.length = inputArr.length;
        this.tempMergArr = new int[length];
        doMergeSort(0, length - 1);
    }
    
    private void doMergeSort(int lowerIndex, int higherIndex) {
        if (lowerIndex < higherIndex) {
            int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
            doMergeSort(lowerIndex, middle);  									//sorts the left side of the array
            doMergeSort(middle + 1, higherIndex);								//sorts the right side of the array
            mergeParts(lowerIndex, middle, higherIndex);						//merges both sides
        }
    }
    private void mergeParts(int lowerIndex, int middle, int higherIndex) {
        for (int i = lowerIndex; i <= higherIndex; i++) {
            tempMergArr[i] = array[i];
        }
        int i = lowerIndex;
        int j = middle + 1;
        int k = lowerIndex;
        while (i <= middle && j <= higherIndex) {
            if (tempMergArr[i] <= tempMergArr[j]) {
                array[k] = tempMergArr[i];
                i++;
            } else {
                array[k] = tempMergArr[j];
                j++;
            }
            k++;
        }
        while (i <= middle) {
            array[k] = tempMergArr[i];
            k++;
            i++;
        }
    }
}