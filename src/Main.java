import java.util.*;
import java.io.*;
 
public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter path to file: ");
        String filepath = scanner.nextLine();
        File file = new File(filepath);
        Scanner fileInCounter = new Scanner(file);
        int counterLines=0;
        while (fileInCounter.hasNext())
        {
            counterLines++;
            fileInCounter.nextLine();
        }
 
        Scanner fileIn = new Scanner(new File(filepath));
        String[][] matrix = new String[counterLines][];
 
        int count = 0;
        while (fileIn.hasNext()) 
        {
            String[] line = fileIn.nextLine().split("\\s+");
            matrix[count] = line;
            count++;
 
        }
        printMatrix(matrix);
        while(true)
        {
        	System.out.println("1.Swap line");
            System.out.println("2.Swap words");
            System.out.println("0.Exit");
            String menu = scanner.nextLine();
            int menuInt = Integer.parseInt(menu);
        	if(menuInt == 1)
            {
            	System.out.println("Enter first and second index of line you want to change");
                String[] tokensOne = scanner.nextLine().split("\\s+");
                int firstLine = Integer.parseInt(tokensOne[0]);
                int secondLine = Integer.parseInt(tokensOne[1]);
                matrix = changeLine(firstLine, secondLine, matrix);
                printMatrix(matrix);
            }
            else if(menuInt == 2 )
            {
            	System.out.println("Enter 4 indexes");
                String[] tokens = scanner.nextLine().split(" ");
                int firstRow = Integer.parseInt(tokens[0]);
                int firstCol = Integer.parseInt(tokens[1]);
                int secondRow = Integer.parseInt(tokens[2]);
                int secondCol = Integer.parseInt(tokens[3]);
                matrix = changeMatrixElementWithAnother(matrix, firstRow, firstCol, secondRow, secondCol);
                printMatrix(matrix);
            }
            else if(menuInt == 0)
            {
            	break;
            }
        }
        
        
        StringBuilder sb = new StringBuilder();
        for (String[] strings : matrix) {
            for (String string : strings) {
                sb.append(string).append(" ");
            }
            sb.append(System.lineSeparator());
        }
        String text = sb.toString().trim();
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.append(text);
        fileWriter.flush();
 
    }
 
    private static void printMatrix(String[][] matrix) {
        for (String[] strings : matrix) {
            for (String string : strings) {
                System.out.print(string + " ");
            }
            System.out.println();
        }
    }
 
    private static String[][] changeMatrixElementWithAnother(String[][] matrix, int firstRow, int firstCol, int secondRow, int secondCol) {
        if (firstRow < 0 || firstRow > matrix.length || secondRow < 0 || secondRow > matrix.length || firstCol < 0 || firstCol > matrix[firstRow].length || secondCol < 0
                || secondCol > matrix[secondRow].length) {
            throw new IndexOutOfBoundsException("INVALID INDEXES");
        }
        String firstElement = matrix[firstRow][firstCol];
        matrix[firstRow][firstCol] = matrix[secondRow][secondCol];
        matrix[secondRow][secondCol] = firstElement;
 
 
        return matrix;
    }
 
    private static String[][] changeLine(int firstLine, int secondLine, String[][] matrix) {
        if(firstLine < 0 || firstLine > matrix.length || secondLine < 0 || secondLine > matrix.length){
            throw new IndexOutOfBoundsException("INVALID INDEXES");
        }
        String[] firstArray = matrix[firstLine];
        matrix[firstLine] = matrix[secondLine];
        matrix[secondLine] = firstArray;
 
        return matrix;
    }
}
 