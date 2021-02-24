package polynomialCalculator;

public class PolyTerm {

    private Scalar coefficient;
    private Integer exponent;

    public PolyTerm(Scalar coefficient, Integer exponent)
    {
        this.coefficient = coefficient;
        this.exponent = exponent;
    }

    public Scalar GetCoefficient(){
        return this.coefficient;
    }
    public Integer GetExponent(){
        return this.exponent;
    }

    public void SetCoefficient(Scalar coefficient){
        this.coefficient = coefficient;
    }

    public boolean canAdd(PolyTerm pt)
    {
        return (pt.GetExponent() == this.exponent);
    }

    public PolyTerm add(PolyTerm pt)
    {
        if (canAdd(pt))//first checking f the exponents has the same value
        {
            //using add from the Scalar classes
            PolyTerm newpt = new PolyTerm(this.coefficient.add(pt.GetCoefficient()),this.exponent);
            return newpt;
        }
        return this;
    }

    public PolyTerm mul(PolyTerm pt)
    {
        //multiplying the coefficients using mul from the Scalar classes and adding the exponents
        PolyTerm newpt = new PolyTerm(coefficient.mul(pt.GetCoefficient()),(exponent + pt.GetExponent()));
        return newpt;
    }

    public Scalar evaluate(Scalar scalar)
    {
        //first increasing scalar as needed
        Scalar tmp = scalar.pow(exponent);
        //multiplying the coefficients using mul method found in Scalar
        return (this.GetCoefficient().mul(tmp));
    }

    public PolyTerm derivate()
    {
        PolyTerm tmp;
        if (exponent == 0)//meaning tmp is basically a number
        {
            tmp = new PolyTerm(coefficient.emptyScalar(),0);
        }
        else
        {
            //multiplying the coefficient with the exponent and reducing the exponent by 1
            tmp = new PolyTerm((coefficient.derivateMul(exponent)),exponent-1);
        }
        return tmp;
    }

    public boolean equals(PolyTerm pt)
    {
        return (pt.exponent == exponent & pt.coefficient.equals(coefficient));
    }

    public String toString()
    {
        if (exponent == 0) {//meaning the PolyTerm is a number
            if (coefficient.toString().equals("-"))
                return "-1";
            return (coefficient.toString());
        }
        else if (coefficient.toString().equals("1")){
            if (exponent == 1){
                return ("x");
            }
            else {
                return ("x^" + exponent);
            }
        }
        else if (exponent == 1)
            return (coefficient.toString() + "x");
        else
            return (coefficient.toString() + "x^" + exponent);
    }
}
