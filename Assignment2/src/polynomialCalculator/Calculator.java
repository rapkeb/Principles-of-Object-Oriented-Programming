package polynomialCalculator;

import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) {
        Integer userInput = 0;
        String filed;
        String firstPol;
        String secondPol;
        Scanner myScanner = new Scanner(System.in);
        while (userInput != 5) {
            System.out.println("Please select an operation:\n" + "1.Addition\n" + "2.Multiplication\n" + "3.Evaluation\n" + "4.Derivate\n" + "5.Exit");
            userInput = myScanner.nextInt();
            if (userInput == 5) {
                break;
            }
            System.out.println("Please select the scalar field\n" + "Rational (Q) or Real (R)");
            filed = myScanner.next();
            System.out.println("Please insert the first polynomial:");
            firstPol = myScanner.next();
            if (userInput == 4) {
                Polynomial pol = new Polynomial(firstPol, filed);
                System.out.println("The derivative polynomial is:\n" + pol.derivate());
            } else if (userInput == 3) {
                System.out.println("Please insert the scalar:");
                if (filed.equals("Q")) {
                    String scalar = myScanner.next();
                    RationalScalar num;
                    if (scalar.contains("/")) {
                        String numerator = scalar.substring(0, scalar.indexOf("/"));
                        String denominator = scalar.substring(scalar.indexOf("/") + 1);
                        num = new RationalScalar(Integer.parseInt(numerator), Integer.parseInt(denominator));
                    } else {
                        num = new RationalScalar(Integer.parseInt(scalar), 1);
                    }
                    Polynomial pol = new Polynomial(firstPol, filed);
                    System.out.println("The solution is:\n" +pol.evaluate(num));
                } else {
                    double scalar = myScanner.nextDouble();
                    Polynomial pol = new Polynomial(firstPol, filed);
                    RealScalar num = new RealScalar(scalar);
                    System.out.println("The solution is:\n" +pol.evaluate(num));
                }
            } else {
                System.out.println("Please insert the second polynomial:");
                secondPol = myScanner.next();
                Polynomial firstPoly = new Polynomial(firstPol, filed);
                Polynomial secondPoly = new Polynomial(secondPol, filed);
                if (userInput == 1) {
                    System.out.println("The solution is:\n" + firstPoly.add(secondPoly));
                }
                //else user input is 2
                else{
                    System.out.println("The solution is:\n" + firstPoly.mul(secondPoly));
                }
            }
        }
    }
}
