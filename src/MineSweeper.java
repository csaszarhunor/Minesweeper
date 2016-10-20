import java.util.Random;
import java.util.Arrays;

public class MineSweeper{

	// generate field
	// fill up with mines
	// write checkNeighbours method
	// fill up with numbers
	
	protected final int[][] DIRECTIONS = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
	private char[][] field;
	private final int MINLENGTH = 1;
	private final int MAXLENGTH = 20;
	private final int MINERATE = 2;
	
	public MineSweeper(){
		field = generateEmptyField();
		field = fillWithMines(field);
		field = fillWithNumbers(field);
	}
	
	public char[][] getField(){
		return field;
	}
	
	private char[][] generateEmptyField(){
		int row = generateIntBetween(MINLENGTH, MAXLENGTH);
		char[][] field = new char[row][];
		for(int i = 0; i < field.length; i++){
			int column = generateIntBetween(MINLENGTH, MAXLENGTH);
			field[i] = new char[column];
		}
		return field;
	}
	
	private char[][] fillWithMines(char[][] field){
		for(int i = 0; i < field.length; i++){
			for(int j = 0; j < field[i].length; j++){
				if(generateIntBetween(0, 10) < MINERATE){
					field[i][j] = '*';
				}
				else {
					field[i][j] = '.';
				}
			}
		}
		return field;
	}
	
	private char[][] fillWithNumbers(char[][] field){
		for(int i = 0; i < field.length; i++){
			for(int j = 0; j < field[i].length; j++){
				if(field[i][j] == '.'){
					int num = countNeighboringMines(i, j);
					field[i][j] = (num == 0) ? '.' : (char) (num + 48);
				}
			}
		}
		return field;
	}
	
	private int countNeighboringMines(int i, int j){
		int count = 0;
		for (int[] dir : DIRECTIONS){
			int di = i + dir[0];
			int dj = j + dir[1];
			if(di >= 0 && di < field.length && dj >= 0 && dj < field[di].length && field[di][dj] == '*'){
				count++;
			}
		}
		return count;
	}
	
	private int generateIntBetween(int from, int to){
		Random random = new Random();
		int result = random.nextInt();
		while(result < from || result >= to){
			result = random.nextInt(to);
		}
		return result;
	}
	
	public static void main(String[] args){
		MineSweeper ms = new MineSweeper();
		for(int i = 0; i < ms.getField().length; i++){
			System.out.println(Arrays.toString(ms.getField()[i]));
		}
	}
}