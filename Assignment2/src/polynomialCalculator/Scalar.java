package polynomialCalculator;

public interface Scalar {

    public Scalar add(Scalar s);

    public Scalar mul(Scalar scalar);

    public Scalar pow(int exponent);

    public Scalar neg();

    public Scalar derivateMul(int exponent);

    public Scalar emptyScalar();

    public boolean equals(Scalar s);

    public String toString();
}

