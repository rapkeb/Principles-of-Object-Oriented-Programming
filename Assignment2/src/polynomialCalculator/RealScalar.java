package polynomialCalculator;

public class RealScalar implements Scalar {

    private double a;

    public RealScalar(double a){
        this.a = a;
    }

    public double getValue() {
        return a;
    }

    public Scalar add(Scalar s) {
        RealScalar rs = new RealScalar(((RealScalar)s).a);
        rs.a = rs.a+a;//adding the doubles
        return rs;
    }

    public Scalar mul(Scalar scalar) {
        double mul = (((RealScalar)scalar).a)*a;//multiplying the doubles
        return new RealScalar(mul);
    }

    public Scalar pow(int exponent) {
        if (exponent != 0){
            double tmpa = a;
            for (int i = 2; i<=exponent;i++){//multiplying a as many times as needed
                tmpa = a*tmpa;
            }
            if (exponent != 1){
                //turning the powered number into String
                String sr = Double.toString(tmpa);
                //reducing the String and returning it as a double back to tmpa
                tmpa = Double.parseDouble(reduce(sr));
            }
            //if the exponent equals 1 the no modification had been done, and tmpa stays the same
            return new RealScalar(tmpa);
        }
        //else the exponent equals 0
        return new RealScalar(1);
    }

    public Scalar neg() {
        return new RealScalar(-a);
    }

    public boolean equals(Scalar s) {
        return a == ((RealScalar)s).a;
    }

    private String reduce(String doub){//reduces the number till 3 number after the dot
        int i = 0;
        //there are to sets of String, one for before the dot, and the second for after the dot
        String st = "";//before dot
        while (doub.charAt(i) != '.')
        {
            i++;
            st = doub.substring(0, i);
        }
        i++;
        String sr = "";//after dot
        while(i<doub.length() & sr.length() != 3)//only adding 3 numbers after dot to sr
        {
            sr = sr + doub.charAt(i);
            i++;
        }
        int srLength = sr.length()-1;
        while (srLength>=0 && sr.charAt(srLength)=='0') {//checking if there are zeros to remove from sr
            sr = sr.substring(0,srLength);
            srLength--;
        }
        if (sr.length()==0){
            return st;
        }
        return st+"."+sr;
    }

    public Scalar emptyScalar(){
        return new RealScalar(0);
    }

    public RealScalar derivateMul(int exponent)
    {
        return new RealScalar(a * (double)exponent);
    }

    public String toString()
    {
        if (a==-1){
            return ("-");
        }
        return (reduce(Double.toString(a)));
    }
}
