package polynomialCalculator;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Polynomial {

    private List<PolyTerm> PolyList;
    private String field;

    public Polynomial(String string, String field)
    {
        this.field = field;
        this.PolyList = new LinkedList<>();
        String[] arr = string.split("(?=[+-])(?!(?<=/)(?=-))");//using split method to separate the PolyTerm as wanted
        for (int i=0;i<arr.length;i++){//adding each value from arr to PolyList
            String strScalar;
            String strExponent;
            if(arr[i].contains("^")) {
                //checking if it's a sort of number
                if (arr[i].charAt(0) == '-' |arr[i].charAt(0) == '+'| (arr[i].charAt(0) <= '9' && arr[i].charAt(0) >= '0')) {
                    strScalar = arr[i].substring(0, arr[i].indexOf("^") - 1);
                } else {
                    strScalar = "1";
                }
                //setting the exponent to be the number after "^"
                strExponent = arr[i].substring(arr[i].indexOf("^")+1);
            }
            else if (arr[i].contains("x")){
                strScalar = arr[i].substring(0, arr[i].length()-2);
                strExponent = "1";
            }
            else
            {
                strScalar = arr[i];
                strExponent = "0";
            }
            Scalar sc;
            //differentiating between the kinds of Scalar
            if (field.equals("R")) {
                if (strScalar.equals("-")) {
                    sc = new RealScalar(-1);
                } else if (strScalar.charAt(0) == '+') {
                    if(strScalar.length() == 1)
                        sc = new RealScalar(1);
                    else
                        sc = new RealScalar(Double.parseDouble(strScalar.substring(1)));
                }
                else sc = new RealScalar(Double.parseDouble(strScalar));
            }
            else {
                int divIndex = strScalar.indexOf("/");
                if (divIndex == -1){
                    if (strScalar.equals("-"))
                        strScalar = "-1";
                    else if (strScalar.charAt(0) == '+')
                    {
                        if (strScalar.length() > 1)
                        {
                            strScalar = strScalar.substring(1);
                        }
                        else
                            strScalar = "1";
                    }
                    sc = new RationalScalar(Integer.parseInt(strScalar),1);
                }
                else {
                    sc = new RationalScalar((Integer.parseInt(strScalar.substring(0,divIndex))),Integer.parseInt(strScalar.substring(divIndex+1)));
                }
            }
            PolyTerm tmp = new PolyTerm(sc,Integer.parseInt(strExponent));
            //adding tmp to PolyList in a sorted manure using findIndexToAdd
            PolyList.add(findIndexToAdd(tmp,PolyList),tmp);
        }
    }

    public Polynomial(List<PolyTerm> polyList,String field){
        //a different constructor when the List is already given
        this.field = field;
        this.PolyList = polyList;
    }

    private int findIndexToAdd (PolyTerm pol, List<PolyTerm> list){
        /*a method that returns the place pol need to be added
        so list would stay organized from the smallest exponent to the largest one*/
        Iterator<PolyTerm> expIter = list.iterator();
        int index = 0;
        while (expIter.hasNext()&&expIter.next().GetExponent()<pol.GetExponent()){
            index++;
        }
        return index;
    }

    private void addExponent(List<PolyTerm> polList,PolyTerm polyTerm){
        Iterator<PolyTerm> iter = polList.iterator();
        int exponent = polyTerm.GetExponent();
        while (iter.hasNext()) {
            PolyTerm curr = iter.next();
            if (curr.GetExponent() == exponent) {
                if (curr.GetCoefficient().add(polyTerm.GetCoefficient()).toString().equals("0")) {
                    /*if the PolyTerm we want to add is basically 0 than there is no need to add it,
                    except in case that the answer is only zero, but this case is dalt with in toString()*/
                    polList.remove(curr);
                }
                else {
                    curr.SetCoefficient(curr.GetCoefficient().add(polyTerm.GetCoefficient()));
                }
                return;
            }
        }
        polList.add(findIndexToAdd(polyTerm,polList),polyTerm);
    }

    public Polynomial add(Polynomial poly){
        List<PolyTerm> ans = new LinkedList<>();
        Iterator<PolyTerm> Itler = this.PolyList.iterator();
        while(Itler.hasNext())
        {
            //first creating ans
            PolyTerm temp = Itler.next();
            ans.add(new PolyTerm(temp.GetCoefficient(),temp.GetExponent()));
        }
        Iterator<PolyTerm> secondIter = poly.PolyList.iterator();
        while (secondIter.hasNext()) {
            //now adding the PolyTerm in both PolyTerm using addExponent
            PolyTerm tmp = secondIter.next();
            addExponent(ans, tmp);
        }
        return new Polynomial(ans,this.field);
    }
    public Polynomial mul(Polynomial poly) {
        List<PolyTerm> ans = new LinkedList<>();
        Iterator<PolyTerm> thisIter = PolyList.iterator();
        while (thisIter.hasNext()) {
            PolyTerm firstPoly =thisIter.next();
            Iterator<PolyTerm> polyIter = poly.PolyList.iterator();
            while (polyIter.hasNext()){
                PolyTerm secondPoly = polyIter.next();
                PolyTerm tmp = firstPoly.mul(secondPoly);
                addExponent(ans,tmp);
            }
        }
        return new Polynomial(ans,field);
    }

    public Scalar evaluate(Scalar scalar){
        Scalar zero;
        if (field.equals("R")) {
            zero = new RealScalar(0);
        }
        else {
            zero = new RationalScalar(0,1);
        }
        Iterator<PolyTerm> iter = PolyList.iterator();
        while (iter.hasNext()){
            zero = zero.add(iter.next().evaluate(scalar));
        }
        return zero;
    }

    public Polynomial derivate(){
        Iterator<PolyTerm> iter = PolyList.iterator();
        List<PolyTerm> pol = new LinkedList<>();
        while (iter.hasNext()){
            pol.add(iter.next().derivate());
        }
        if (((LinkedList<PolyTerm>) pol).getFirst().GetCoefficient().toString().equals("0")) {
            ((LinkedList<PolyTerm>) pol).removeFirst();
        }
        return new Polynomial(pol,field);
    }

    public String toString(){
        Iterator<PolyTerm> iter = PolyList.iterator();
        String pol = "";
        int count = 0;
        if (PolyList.size()==0){//meaning the list is empty because of a previous mathematical manipulation
            return "0";
        }
        while (iter.hasNext()){
            String tmp = iter.next().toString();
            if (tmp.charAt(0) != '-' && count > 0) {
                tmp = "+" + tmp;
            }
            pol = pol+tmp;
            count++;
        }
        return pol;
    }

    public boolean equals(Polynomial poly){
        return this.toString().equals(poly.toString());
    }
}
