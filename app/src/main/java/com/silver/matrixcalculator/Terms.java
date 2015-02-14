package com.silver.matrixcalculator;

import java.util.ArrayList;

/**
 * Created by Rex on 14/2/2015.
 */
//Summation of Term
public class Terms extends ArrayList<Term> {
    static final char[] preSet = new char[]{'x' , 'y' , 'z'};

    public Terms(){
    }

    public Terms(Term T){
        this.add(T);
    }

    @Override
    public String toString(){
        String tmp = "";
        for (Term hi : this){
            tmp = tmp + hi.toString();
        }
        try{
            if (tmp.charAt(0)=='+'){
                tmp = tmp.substring(1);
            }
        }catch(Exception e){
        }
        if (tmp.length()==0){
            tmp="0";
        }

        return tmp;
    }

    //Addition of 2 terms
    public Terms Sum(Terms T2){
        Terms result = new Terms();
        Terms T1 = SumWithin(this);
        T2 = SumWithin(T2);
        boolean[] done1 = new boolean[T1.size()];
        boolean[] done2 = new boolean[T2.size()];
        int i1 = 0;
        for (Term t1:T1){
            int i2 = 0;
            for (Term t2:T2){
                if (!done2[i2]){
                    if (t1.number[1]==t2.number[1] && t1.number[2]==t2.number[2] && t1.number[3]==t2.number[3])
                    {
                        Term newTerm = new Term(t1.number[0]+t2.number[0],t1.number[1],t1.number[2],t1.number[3]);
                        result.add(newTerm);
                        done1[i1] = true;
                        done2[i2] = true;
                    }
                }
                i2++;
            }
            i1++;
        }

        for (int i =0; i<T1.size(); i++){
            if (!done1[i]){
                result.add(T1.get(i));
            }
        }

        for (int i =0; i<T2.size(); i++){
            if (!done2[i]){
                result.add(T2.get(i));
            }
        }

        return result;
    }

    //Subtraction of 2 terms
    public Terms Sub(Terms T2){
        Terms result = new Terms();
        Terms T1 = SumWithin(this);
        T2 = SumWithin(T2);
        boolean[] done1 = new boolean[T1.size()];
        boolean[] done2 = new boolean[T2.size()];
        int i1 = 0;
        for (Term t1:T1){
            int i2 = 0;
            for (Term t2:T2){
                if (!done2[i2]){
                    if (t1.number[1]==t2.number[1] && t1.number[2]==t2.number[2] && t1.number[3]==t2.number[3])
                    {
                        Term newTerm = new Term(t1.number[0]-t2.number[0],t1.number[1],t1.number[2],t1.number[3]);
                        result.add(newTerm);
                        done1[i1] = true;
                        done2[i2] = true;
                    }
                }
                i2++;
            }
            i1++;
        }

        for (int i =0; i<T1.size(); i++){
            if (!done1[i]){
                result.add(T1.get(i));
            }
        }

        for (int i =0; i<T2.size(); i++){
            if (!done2[i]){
                result.add(T2.get(i));
            }
        }

        return result;
    }

    //Multiply 2 terms, expanding them
    public Terms EMulti(Terms T2){
        Terms T1 = SumWithin(this);
        T2 = SumWithin(T2);
        Terms result = new Terms();
        for (Term t1:T1){
            for (Term t2:T2){
                result.add((Multi(t1,t2)));
            }
        }
        result = SumWithin(result);
        return result;
    }


    //Perform simplification of terms
    public static Terms SumWithin(Terms T){
        Terms result = new Terms();
        boolean[] done = new boolean[T.size()];
        for (int i=0; i<T.size()-1; i++){
            if (!done[i]){
                Term t1 = T.get(i);
                for (int l=i+1; l<T.size(); l++){
                    if (!done[l]&&!done[i]){
                        Term t2 = T.get(l);
                        if (t1.number[1]==t2.number[1] && t1.number[2]==t2.number[2] && t1.number[3]==t2.number[3])
                        {
                            T.set(l, new Term(t1.number[0]+t2.number[0],t1.number[1],t1.number[2],t1.number[3]));
                            done[i]=true;
                        }
                    }
                }
            }
        }

        for (int i =0; i<T.size(); i++){
            if (!done[i]){
                result.add(T.get(i));
            }
        }
        return result;
    }

    //Perform multiplication of 2 lone term
    public static Term Multi(Term T1, Term T2){
        return new Term(T1.number[0]*T2.number[0],T1.number[1]+T2.number[1],T1.number[2]+T2.number[2],T1.number[3]+T2.number[3]);
    }
}

