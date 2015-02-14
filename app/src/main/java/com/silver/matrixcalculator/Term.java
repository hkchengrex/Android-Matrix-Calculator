package com.silver.matrixcalculator;

/**
 * Created by Rex on 14/2/2015.
 */

    // for example: 3xyz, x^2y^4, x, z^5, 4z^3... coefficient, followed by power of x, y, z

public class Term{
    static final char[] preSet = new char[]{'x' , 'y' , 'z'};
        public Term(){
        }

        public Term(int icoef,int ix,int iy,int iz){
            number[0]=icoef;
            number[1]=ix;
            number[2]=iy;
            number[3]=iz;
        }
        int[] number = new int[] {1,0,0,0};

        @Override
        public String toString(){
            String tmp = "+";
            if (number[0]<0){
                tmp="";
            }
            if (number[0]==0){
                return "";
            }
            if (number[0]==1 || number[0]==-1){
                if (number[1]==0 & number[2]==0 & number[3]==0){
                    tmp=tmp + String.valueOf(number[0]);
                }
            }else{
                tmp = tmp + String.valueOf(number[0]);
            }
            for (int i=1;i<4;i++){
                if (number[i]!=0){
                    if (number[i]!=1){
                        tmp  = tmp +preSet[i-1]+"^"+ String.valueOf(number[i]);
                    }else{
                        tmp  = tmp + preSet[i-1];
                    }
                }
            }
            return tmp;
        }
    }

