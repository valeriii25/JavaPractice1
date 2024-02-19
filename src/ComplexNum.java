public class ComplexNum {
    private double real, imaginary;
    public ComplexNum() {
        real = 0;
        imaginary = 0;
    }
    public ComplexNum(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }
    public ComplexNum(ComplexNum complexNum) {
        if (complexNum == null) throw new IllegalArgumentException("complex number can't be null");
        real = complexNum.real;
        imaginary = complexNum.imaginary;
    }
    public double getReal() {
        return real;
    }
    public double getImaginary() {
        return imaginary;
    }
    public void setReal(double real) {
        this.real = real;
    }
    public void setImaginary(double imaginary) {
        this.imaginary = imaginary;
    }
    public ComplexNum add(ComplexNum ... complexNums) {
        ComplexNum result = new ComplexNum(this);
        for (ComplexNum element: complexNums) {
            if (element == null) throw new IllegalArgumentException("complex number can't be null");
            result.real += element.real;
            result.imaginary += element.imaginary;
        }
        return result;
    }
    public ComplexNum subtract(ComplexNum ... complexNums) {
        ComplexNum result = new ComplexNum(this);
        for (ComplexNum element: complexNums) {
            if (element == null) throw new IllegalArgumentException("complex number can't be null");
            result.real -= element.real;
            result.imaginary -= element.imaginary;
        }
        return result;
    }
    public ComplexNum multiply(ComplexNum ... complexNums) {
        ComplexNum result = new ComplexNum(this);
        double realPart, imaginaryPart;
        for (ComplexNum element: complexNums) {
            if (element == null) throw new IllegalArgumentException("complex number can't be null");
            realPart = result.real * element.real - result.imaginary * element.imaginary;
            imaginaryPart = result.imaginary * element.real + result.real * element.imaginary;
            result.setReal(realPart);
            result.setImaginary(imaginaryPart);
        }
        return result;
    }
    public ComplexNum opposite() {
        return new ComplexNum(-1 * this.real, -1 * this.imaginary);
    }
    public ComplexNum divide(ComplexNum ... complexNums) {
        ComplexNum result = new ComplexNum(this);
        double realPart, imaginaryPart;
        for (ComplexNum element: complexNums) {
            if (element == null) throw new IllegalArgumentException("complex number can't be null");
            if (element.real == 0 || element.imaginary == 0) throw new ArithmeticException("division by 0");
            realPart = (result.real * element.real + result.imaginary * element.imaginary) /
                    (element.real * element.real + element.imaginary * element.imaginary);
            imaginaryPart = (result.imaginary * element.real - result.real * element.imaginary) /
                    (element.real * element.real + element.imaginary * element.imaginary);
            result.setReal(realPart);
            result.setImaginary(imaginaryPart);
        }
        return result;
    }
    @Override
    public String toString() {
        String result = String.format("%.2f", real);
        if (imaginary >= 0) result += "+";
        result += String.format("%.2f", imaginary) + "i";
        return String.format("%-25s", result);
    }
}
