package ExceptionAssignement;

public class StringToIntArraySummator {
    private static  final int size = 4;
    public static int sum(String[][] matrix) throws MyArrayDataException, MySizeArrayException{
        if(matrix.length != size){
            throw new MySizeArrayException("Array size must be " +
                    size + "X" + size + ", got " + matrix.length + " rows");
        }
        int sum = 0;
        for(int row = 0; row < size; row++){
            if(matrix[row].length != size){
                throw new MySizeArrayException("Array size must be " +
                    size + "X" + size + ", got " + matrix[row].length + " cells in row #" + row);
            }
            for(int col = 0; col < size; col++){
                try{
                    sum += Integer.parseInt(matrix[row][col]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException("Cannot convert cell (" +
                            row + "," + col + ") to Integer. Wrong format: '" + matrix[row][col] + "'");
                }
            }
        }
        return sum;
    }
}
