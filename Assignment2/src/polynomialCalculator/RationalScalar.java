package polynomialCalculator;

public class RationalScalar implements Scalar {

    private  int a;//numerator
    private  int b;//denominator

    public RationalScalar(int a, int b) {
        //checking first if the RationalScalar can be reduced;
        for (int i = 2; i<= Math.max(a,b); i++){
            while (a%i==0 && b%i==0){
                a=a/i;
                b=b/i;
            }
        }
        this.a = a;
        this.b = b;
    }

    public Scalar mul(Scalar scalar){
        RationalScalar rs = new RationalScalar(((RationalScalar)scalar).a,((RationalScalar)scalar).b);
        //multiplying numerator with numerator and denominator with denominator
        rs.a = rs.a*a;
        rs.b = rs.b*b;
        return division(rs.a,rs.b);
    }

    public int geta() {
        return a;
    }

    public int getb() {
        return b;
    }

    public Scalar add(Scalar s){
        int x = (((RationalScalar)s).a);
        int w = (((RationalScalar)s).b);
        RationalScalar rs = new RationalScalar(x,w);
        //adding the numerators after finding common ground and that checking if the fracture can be reduced by common divider
        return division((rs.a * b + rs.b * a),rs.b * b);
    }

    public Scalar pow(int exponent){
        if (exponent != 0) {
            int tmpa = a;
            int tmpb = b;
            if (exponent != 1) {
                //if exponent isn't 1, multiplying the numbers as needed
                for (int i = 2; i <= exponent; i++) {
                    tmpa = a * tmpa;
                    tmpb = b * tmpb;
                }
            }
            //if the exponent equals 1 than the method won't change the numbers
            return new RationalScalar(tmpa,tmpb);
        }
        //else the exponent equals 0
        return new RationalScalar(1,1);
    }

    public Scalar neg(){
        //multiplying a is enough to turn the fracture to it's negative form
        RationalScalar rs= new RationalScalar(-a,b);
        return rs;
    }

    private Scalar division(int a, int b) {//finds the larges number that divides the numerator and the denominator
        for (int i = 2; i<= Math.max(a,b); i++){
            while (a%i==0 && b%i==0){
                a=a/i;
                b=b/i;
            }
        }
        return new RationalScalar(a,b);
    }

    public boolean equals(Scalar s){
        /*if the multiplication of s's numerator and b
        equals to the multiplication of s's denominator and a
        then the numbers has the same value*/
        return  ((RationalScalar)s).a *b == ((RationalScalar)s).b * a;
    }

    public Scalar emptyScalar(){
        Scalar rs = new RationalScalar(0,0);
        return rs;
    }

    public RationalScalar derivateMul(int exponent)
    {
        return new RationalScalar((a * exponent), b);
    }

    public String toString()
    {
        if (a==0){
            return ("0");
        }
        if (b == 1)
            return (String.valueOf(a));
        else
            return (a + "/" + b);
    }
}

