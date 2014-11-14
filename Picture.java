/* Assignment 2
 * 
 * Joshua O'Leary
 * University of Sheffield
 * Computer Science
 * 11/2014
 */

import sheffield.*;

public class Picture {
	public static void main(String args[]) {
		
		/* Screen size (in pixels) and input filename */
		final int SCREEN_SIZE = 600;
		final int BLOCK_SIZE = 3;
		final int ARRAY_SIZE = SCREEN_SIZE / BLOCK_SIZE;
		
		final String FILE_INPUT = "picture.txt";

		/* Colour values in R, G, B format */
		final int[] COLOUR_1 = { 255, 0, 0 };
		final int[] COLOUR_2 = { 0, 255, 0 };
		
		final int[] COLOUR_BLUE = { 0, 0, 255 };
		final int[] COLOUR_BROWN = { 179, 120, 12 };
		final int[] COLOUR_GREEN_1 = { 14, 148, 9 };
		final int[] COLOUR_GREEN_2 = { 10, 252, 42 };
		
		/* Create new objects for datafile and display */
		EasyReader fileInput = new EasyReader(FILE_INPUT);
		EasyGraphics display = new EasyGraphics(SCREEN_SIZE, SCREEN_SIZE);		
		
		/* Load file contents into string */
		final String fileData = fileInput.readString();
		
		/* Create new array to store picture contents */
		int[][] screenSquares = new int[ARRAY_SIZE][ARRAY_SIZE];
		
		boolean done = false;
		
		/* Load values from string into integer array */
		for(int screenRow=0; screenRow<ARRAY_SIZE && !done; screenRow++) {
			for(int screenColumn=0; screenColumn<ARRAY_SIZE; screenColumn++) {
				int currentChar = (screenRow * ARRAY_SIZE) + screenColumn;
				
				/* Check to make sure there aren't too many numbers for array */
				if( currentChar > (fileData.length() - 1) ) {
					System.out.println("Finished loading numbers (found " + (currentChar + 1) + ")");
					done = true;
					break;
				}
				
				screenSquares[screenRow][screenColumn] = Character.getNumericValue(fileData.charAt(currentChar));
				System.out.println(screenSquares[screenRow][screenColumn]);
			}
		}
		/* Display colour values on screen */
		for(int screenRow=0; screenRow<ARRAY_SIZE; screenRow++) {
			for(int screenColumn=0; screenColumn<ARRAY_SIZE; screenColumn++) {
				int colour = screenSquares[screenRow][screenColumn];
				if( colour >= 0 && colour <= 3 )
					display.setColor(COLOUR_BLUE[0], COLOUR_BLUE[1], COLOUR_BLUE[2]);
				else if( colour >= 4 && colour <= 5)
					display.setColor(COLOUR_GREEN_1[0], COLOUR_GREEN_1[1], COLOUR_GREEN_1[2]);
				else if( colour >=6 && colour <= 7)
					display.setColor(COLOUR_GREEN_2[0], COLOUR_GREEN_2[1], COLOUR_GREEN_2[2]);
				else
					display.setColor(COLOUR_BROWN[0], COLOUR_BROWN[1], COLOUR_BROWN[2]);
				
				/* {SCREEN_SIZE - coordinate} converts to screen coordinates
				 * Must use {3 + ...} as highest element is 199 (one-less) */
				int xCoord = SCREEN_SIZE - (3 + screenColumn * 3);
				int yCoord = SCREEN_SIZE - (3 + screenRow * 3);
				System.out.println(xCoord + " " +  yCoord);
				display.fillRectangle(xCoord, yCoord, 3, 3);
			}
		}
		
	
		
	}
}