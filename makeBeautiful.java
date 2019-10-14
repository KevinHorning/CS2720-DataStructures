// Creates a list of random ints and determines the minimum number of swaps 
// needed to make the list sorted (beautiful)

public class makeBeautiful {
	public static void main (String[] args){
		int listSize = 4; 
		int[] list = new int[listSize];
		for (int i = 0; i < listSize; i++){
			list[i] = (int)(Math.random() * 100);
			System.out.print(list[i] + " ");
		}
		int swaps = makeBeautiful(list);
		System.out.println();
		System.out.println("Minimum number of swaps to make Beautiful: " + swaps);
	}
	
	public static int makeBeautiful(int[] list){
		int numberOfSwaps = 0;
		for (int i = 0; i < list.length - 1; i++){
			int minimumIndex = i;
			for (int j = i + 1; j < list.length; j++){
				if (list[j] < list[minimumIndex]){
					minimumIndex = j;
				}
			}
			if (minimumIndex != i){
				numberOfSwaps++;
			}
			int temp = list[minimumIndex];
			list[minimumIndex] = list[i];
			list[i] = temp;
		}
		return numberOfSwaps;
	}
}