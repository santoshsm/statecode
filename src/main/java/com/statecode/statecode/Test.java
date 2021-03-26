package com.statecode.statecode;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [] arr = {-2,1,-3,4,-1,2,1,-5,4};
		
		
		int max = 0;
		// -2 +1, -2 +1 -3 
		
		for(int i=0; i < arr.length-1; i++) {
			int sum = 0;
			sum = arr[i];
			for(int j=arr.length-1; j > i; j--) {
				
				for(int p = i+1; p < j; p++) {
					sum +=arr[p];
				}
				System.out.println("sum "+sum);
				if(sum >= max) {
					max = sum;
				}
				sum = arr[i];

			}

		}
		System.out.println("max "+max);
	}

}
