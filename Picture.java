/* Assignment 2
 * 
 * Joshua O'Leary
 * University of Sheffield
 * Computer Science
 * 11/2014
 */

import sheffield.*;

public class Picture {
	enum Colours { BLUE, GREEN_1, GREEN_2, BROWN };

	public static Colours getColourValue(int colourValue) {
		if( colourValue >= 0 && colourValue <= 3 )
			return Colours.BLUE;
		else if( colourValue >= 4 && colourValue <= 5)
			return Colours.GREEN_1;
		else if( colourValue >=6 && colourValue <= 7)
			return Colours.GREEN_2;
		else
			return Colours.BROWN;
	}
	
	public static void main(String args[]) {
		
		/* Screen size (in pixels) and input filename */
		final int SCREEN_SIZE = 600;
		final int BLOCK_SIZE = 3;
		final int ARRAY_SIZE = SCREEN_SIZE / BLOCK_SIZE;
		
		/* Screen size for black and white image */
		final int BW_SCREEN_SIZE = 200;
		
		final String FILE_INPUT = "picture.txt";

		/* Colour values in R, G, B format */		
		final int[] COLOUR_BLUE = { 0, 0, 255 };
		final int[] COLOUR_BROWN = { 179, 120, 12 };
		final int[] COLOUR_GREEN_1 = { 14, 148, 9 };
		final int[] COLOUR_GREEN_2 = { 10, 252, 42 };
		
		final int[] COLOUR_WHITE = { 255, 255, 255 };
		final int[] COLOUR_BLACK = { 0, 0, 0 };
		
		/* Create new objects for datafile and display */
		EasyReader fileInput = new EasyReader(FILE_INPUT);
		EasyGraphics display = new EasyGraphics(SCREEN_SIZE, SCREEN_SIZE);
		EasyGraphics bwDisplay = new EasyGraphics(BW_SCREEN_SIZE, BW_SCREEN_SIZE);
		
		
		/* Load file contents into string */
		final String fileData = fileInput.readString();
		
		/* Create new array to store picture contents */
		int[][] screenSquares = new int[ARRAY_SIZE][ARRAY_SIZE];
		
		boolean done = false;
		
		int currentCharIndex = 0;
		
		/* Load values from string into integer array */
		for(int screenRow=0; screenRow<ARRAY_SIZE && !done; screenRow++) {
			for(int screenColumn=0; screenColumn<ARRAY_SIZE; screenColumn++) {
				
				/* Check to make sure there aren't too many numbers for array */
				if( currentCharIndex > (fileData.length() - 1) ) {
					done = true;
					break;
				}
				
				screenSquares[screenRow][screenColumn] = Character.getNumericValue(fileData.charAt(currentCharIndex));
				//System.out.println(screenSquares[screenRow][screenColumn]);
				currentCharIndex++;
				System.out.println(currentCharIndex);
			}
		}
		/* currentChar will be one higher than number of chars already (since it is incremented after the check)
		 * so no need to do (currentChar + 1) */
		System.out.println("Finished loading numbers (found " + currentCharIndex + ")");

		/* Display colour values on screen */
		for(int screenRow=0; screenRow<ARRAY_SIZE; screenRow++) {
			for(int screenColumn=0; screenColumn<ARRAY_SIZE; screenColumn++) {
				int currentColourValue = screenSquares[screenRow][screenColumn];
				Colours currentColour = getColourValue(currentColourValue);
				
				if( currentColour == Colours.BLUE )
					display.setColor(COLOUR_BLUE[0], COLOUR_BLUE[1], COLOUR_BLUE[2]);
				else if( currentColour == Colours.GREEN_1 )
					display.setColor(COLOUR_GREEN_1[0], COLOUR_GREEN_1[1], COLOUR_GREEN_1[2]);
				else if( currentColour == Colours.GREEN_2 )
					display.setColor(COLOUR_GREEN_2[0], COLOUR_GREEN_2[1], COLOUR_GREEN_2[2]);
				else
					display.setColor(COLOUR_BROWN[0], COLOUR_BROWN[1], COLOUR_BROWN[2]);
				
				/* {SCREEN_SIZE - coordinate} converts to screen coordinates
				 * Must use {screenColumn + 1} as array is zero-based index */
				int xCoord = SCREEN_SIZE - ((screenColumn + 1) * BLOCK_SIZE);
				int yCoord = SCREEN_SIZE - ((screenRow + 1) * BLOCK_SIZE);
				display.fillRectangle(xCoord, yCoord, BLOCK_SIZE, BLOCK_SIZE);
			}
		}
		
				
		/* File's numerical value of current colour (set to initial position)*/
		int currentColourValue = screenSquares[0][0];
		
		/* Current colour value */
		Colours currentColour = getColourValue(currentColourValue);
		
		Colours rowColour = currentColour;
		Colours columnColour = currentColour;
		
		int lastRowColourValue = currentColourValue;
		Colours lastRowColour = currentColour;
		Colours lastColumnColour = currentColour;
		boolean isColumnEdge = false;
		boolean isRowEdge = false;
		
		
		/* Display colour values on screen */
		for(int screenRow=0; screenRow<ARRAY_SIZE; screenRow++) {
			for(int screenColumn=0; screenColumn<ARRAY_SIZE; screenColumn++) {
				currentColourValue = screenSquares[screenRow][screenColumn];
				
				currentColour = getColourValue(currentColourValue);
				
				
				if( currentColour != lastColumnColour )
					isColumnEdge = true;
				else
					isColumnEdge = false;
				
				if( screenRow > 0 )
					lastRowColourValue = screenSquares[screenRow - 1][screenColumn];		
				
				lastRowColour = getColourValue(lastRowColourValue);

				if( currentColour != lastRowColour )
					isRowEdge = true;
				else
					isRowEdge = false;
				
				if( isColumnEdge || isRowEdge ) {
					bwDisplay.setColor(COLOUR_BLACK[0], COLOUR_BLACK[1], COLOUR_BLACK[2]);
				}
				else
					bwDisplay.setColor(COLOUR_WHITE[0], COLOUR_WHITE[1], COLOUR_WHITE[2]);

				bwDisplay.plot(BW_SCREEN_SIZE - (screenColumn + 1), BW_SCREEN_SIZE - (screenRow + 1));
				lastColumnColour = currentColour;
				

			}
			
		}
		
	}
}
