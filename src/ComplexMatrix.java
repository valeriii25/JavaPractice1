public class ComplexMatrix {
    private int rows, columns;
    private ComplexNum[][] matrix;
    public ComplexMatrix() {
        rows = 0;
        columns = 0;
    }
    public ComplexMatrix(int rows, int columns) {
        if (rows <= 0 || columns <= 0) throw new IllegalArgumentException("inappropriate number of rows or columns");
        this.rows = rows;
        this.columns = columns;
        matrix = new ComplexNum[rows][columns];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = new ComplexNum();
            }
    }
    public int getRows() {
        return rows;
    }
    public int getColumns() {
        return columns;
    }

    public void setRows(int rows) {
        if (this.rows == 0) this.rows = rows;
        else throw new IllegalArgumentException("rows already set");
    }
    public void setColumns(int columns) {
        if (this.columns == 0) this.columns = columns;
        else throw new IllegalArgumentException("columns already set");
    }
    public ComplexNum getMatrixElem(int row, int column) {
        if (rows <= 0 || columns <= 0) throw new IllegalArgumentException("inappropriate number of row or column");
        return matrix[row][column];
    }
    public void setMatrixElem(ComplexNum complexNum, int row, int column) {
        if (complexNum == null) throw new IllegalArgumentException("complex number can't be null");
        matrix[row][column] = new ComplexNum(complexNum);
    }
    public void setWholeMatrix(ComplexNum ... complexNums) {
        if (complexNums.length != rows * columns) throw new IllegalArgumentException("wrong number of arguments");
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++) {
                if (complexNums[i * columns + j] == null) throw new IllegalArgumentException("complex number can't be null");
                setMatrixElem(complexNums[i * columns + j], i, j);
            }
    }
    public ComplexMatrix add(ComplexMatrix summand) {
        if (summand == null) throw new IllegalArgumentException("matrix can't be null");
        if (rows != summand.rows || columns != summand.columns) throw new IllegalArgumentException("matrices have different sizes");
        ComplexMatrix result = new ComplexMatrix(rows, columns);
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                result.matrix[i][j] = matrix[i][j].add(summand.matrix[i][j]);
        return result;
    }
    public ComplexMatrix subtract(ComplexMatrix subtrahend) {
        if (subtrahend == null) throw new IllegalArgumentException("matrix can't be null");
        if (rows != subtrahend.rows || columns != subtrahend.columns) throw new IllegalArgumentException("matrices have different sizes");
        ComplexMatrix result = new ComplexMatrix(rows, columns);
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                result.matrix[i][j] = matrix[i][j].subtract(subtrahend.matrix[i][j]);
        return result;
    }
    public ComplexMatrix multiply(ComplexMatrix multiplier) {
        if (multiplier == null) throw new IllegalArgumentException("matrix can't be null");
        if (columns != multiplier.rows) throw new IllegalArgumentException("matrices have inappropriate sizes");
        ComplexMatrix result = new ComplexMatrix(rows, multiplier.columns);
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < multiplier.columns; j++) {
                ComplexNum elem = new ComplexNum();
                for (int k = 0; k < columns; k++)
                    elem = elem.add(matrix[i][k].multiply(multiplier.matrix[k][j]));
                result.matrix[i][j] = elem;
            }
        return result;
    }
    public ComplexMatrix multiply(ComplexNum multiplier) {
        if (multiplier == null) throw new IllegalArgumentException("complex number can't be null");
        ComplexMatrix result = new ComplexMatrix(rows, columns);
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                result.matrix[i][j] = matrix[i][j].multiply(multiplier);
        return result;
    }
    public ComplexMatrix transpose() {
        ComplexMatrix result = new ComplexMatrix(rows, columns);
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                result.matrix[i][j] = new ComplexNum(matrix[j][i]);
        return result;
    }
    public ComplexNum determinant() {
        if (rows != columns) throw new IllegalArgumentException("matrix must be square");
        ComplexNum result = new ComplexNum();
        if (rows == 1) return matrix[0][0];
        if (rows == 2)
            return matrix[0][0].multiply(matrix[1][1]).subtract(matrix[0][1].multiply(matrix[1][0]));
        for (int k = 0; k < columns; k++) {
            ComplexNum power = new ComplexNum(Math.pow(-1, k), 0);
            ComplexMatrix minor = this.createMinor(0, k);
            result = result.add(matrix[0][k].multiply(power).multiply(minor.determinant()));
        }
        return result;
    }
    public ComplexMatrix createMinor(int row, int column) {
        ComplexMatrix minor = new ComplexMatrix(rows - 1, columns - 1);
        int ind1 = 0, ind2 = 0;
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++) {
                if (i != row && j != column) {
                    minor.matrix[ind1][ind2++] = new ComplexNum(matrix[i][j]);
                    if (ind2 >= columns - 1) {
                        ind2 = 0;
                        ++ind1;
                    }
                }
            }
        return minor;
    }
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++)
                result.append(matrix[i][j].toString());
            result.append("\n");
        }
        return result.toString();
    }
}
