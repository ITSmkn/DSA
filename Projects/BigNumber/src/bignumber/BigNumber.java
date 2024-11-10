package bignumber;

import java.util.ArrayList;

public class BigNumber implements Comparable<BigNumber>{
   ArrayList<Byte> Digits;
   int numberOfDigits;
   boolean isPositive = true;
   
   // printing the number
   public void print(){
       if(!isPositive){
           System.out.print("-");
       }
       for(int i = 0 ; i < (this.Digits).size() ; i++){
           System.out.print(this.Digits.get(i));
       }
       System.out.println();
   }
   
   // if (bn > this) ---> the result would be negative ...
   @Override
   public int compareTo(BigNumber bn) { 
       
       if (this.Digits.size() != bn.Digits.size()) { 
            return this.Digits.size() - bn.Digits.size(); 
       } 
       
       for (int i = 0; i < this.Digits.size(); i++) { 
       if (this.Digits.get(i) != bn.Digits.get(i)) { 
           return this.Digits.get(i) - bn.Digits.get(i); 
       } 
   } 
   return 0; 
   }

// ====================================== Constructors ======================================== 
   public BigNumber(){
       Digits = new ArrayList<Byte>();
   }
   
   
   // converting int to BigNumber
   public BigNumber(int n){
       if(n < 0){
           this.isPositive = false;
           n = -n;
       }      
       String str = Integer.toString(n);
       int length = str.length();
       Digits = new ArrayList<Byte>();  
       int power = length;
       while(power != 0){
          byte temp = (byte) (((n%Math.pow(10, power)) - (n%Math.pow(10, power-1)))/(Math.pow(10, power-1)));          
          Digits.add(temp);
          power--;
       }
       this.numberOfDigits = this.Digits.size();
       
   }
   
   public BigNumber(BigNumber b){
       this.Digits = b.Digits;
       this.isPositive = b.isPositive;
       this.numberOfDigits = b.numberOfDigits;
   }
   
   // converting string to BigNumber
   public BigNumber(String str){
        Digits = new ArrayList<Byte>();
        if((str.substring(0,1)).equals("-")){
            this.isPositive = false;
        }
        this.numberOfDigits = str.length();
        for(int i = 0 ; i < this.numberOfDigits ; i++){
            if(str.substring(i,i+1).equals("-")){
                continue;
            }
            int number = Integer.parseInt(str.substring(i,i+1));
            byte n = (byte) number; 
            (this.Digits).add(n);
        }
   }
   
   // converting an array of bytes to BigNumber
   public BigNumber(byte b[]){
       Digits = new ArrayList<Byte>();
       numberOfDigits = b.length ;
       for(int i = 0 ; i < numberOfDigits ; i++){
           Digits.add(b[i]);
       }
   }
   
   
// =================================== PLUS ==========================================
   
   // Operators ...
   public BigNumber Plus(BigNumber bn){
       int max = Math.max(this.Digits.size(), bn.Digits.size()); 
       BigNumber bnew = new BigNumber();
       
       // (-) + (-)  
       if(!this.isPositive && !bn.isPositive){
           bnew.isPositive = false;
       }
       
       // (-) + (+)
       if(!this.isPositive && bn.isPositive){
           this.isPositive = true;
           bnew = bn.Minus(this);  // (+) - (+)
           this.isPositive = false;
           
           return bnew;
       }
       
       // (+) + (-)
       if(this.isPositive && !bn.isPositive){
           bn.isPositive = true;
           bnew = this.Minus(bn);  // (+) - (+)
           bn.isPositive = false;
           
           return bnew;
       }
       
       
       // normal case ...
       
       // filling the new array with 0!
       for (int i = 0; i < max + 1; i++){ 
           bnew.Digits.add((byte) 0);
       }
       
       byte carry = 0; 
       int maxCount = this.Digits.size() - 1; 
       int bnCount = bn.Digits.size() - 1 ;
       
       for (int i = max; i >= 0; i--) { 
            byte sum = carry; 
            if (maxCount >= 0){ 
                sum += this.Digits.get(maxCount); 
                maxCount--; 
            } 
            if (bnCount >= 0){ 
                sum += bn.Digits.get(bnCount); 
                bnCount--; 
            }
            // if the sum of two digits was greater than 9 then the carry variable will start from 1...
            carry = (byte) (sum / 10); 
            bnew.Digits.set(i, (byte) (sum % 10)); }
       
       
       if(bnew.Digits.get(0) == 0){ 
           bnew.Digits.remove(0); 
       } 
       bnew.numberOfDigits = bnew.Digits.size();
       return bnew;
 
   }
   
// ======================================= MINUS ==========================================  
   public BigNumber Minus(BigNumber bn){
       BigNumber bnew = new BigNumber();
       
       // (-) - (+) --> (-) + (-) 
       if(!this.isPositive && bn.isPositive){
           this.isPositive = true;
           bnew = this.Plus(bn);
           this.isPositive = false;
           
           bnew.isPositive = false;
           
           return bnew;
       }
       
       // (+) - (-) --> (+) + (+)
       if(this.isPositive && !bn.isPositive){
           bn.isPositive = true;
           bnew = this.Plus(bn);
           bn.isPositive = false;
           return bnew;
       }
       
       // (-) - (-) --> (+) - (+)
       if(!this.isPositive && !bn.isPositive){
           this.isPositive = true;
           bn.isPositive = true;
           bnew = bn.Minus(this);
           this.isPositive = false;
           bn.isPositive = false;
           
           return bnew;
       }
       
       // remaining cases ---> 1) (2 - 1) & (1 - 2)
       // one's result is positive and the other's is negative.
       
       // 1 - 2
       
       // change the numbers' place
       boolean resultIsPositive = true;
       if(this.compareTo(bn) < 0){
           resultIsPositive = false; 
           BigNumber temp = new BigNumber(this); 
           this.Digits = bn.Digits;
           this.isPositive = bn.isPositive;
           this.numberOfDigits = bn.numberOfDigits;
           bn = temp;
       }
       
       // according to CompareTo function , (this) is greater ...
       int max = this.Digits.size(); 
       for (int i = 0; i < max; i++){ 
           bnew.Digits.add((byte) 0);
       }
       
       byte borrow = 0; 
       int thisCount = this.Digits.size() - 1; 
       int bnCount = bn.Digits.size() - 1 ;
       
       
       for (int i = max - 1; i >= 0; i--) { 
           
           byte digit1 = (thisCount >= 0) ? this.Digits.get(thisCount) : 0;        
           byte digit2 = (bnCount >= 0) ? bn.Digits.get(bnCount) : 0; 
           
           digit1 -= borrow; 
           
           if (digit1 < digit2) { 
               digit1 += 10; borrow = 1; 
           } 
           
           else { 
               borrow = 0; 
           } 
           
           bnew.Digits.set(i, (byte) (digit1 - digit2)); 
           thisCount--; 
           bnCount--; 
       }
        while (bnew.Digits.size() > 1 && bnew.Digits.get(0) == 0) { 
            bnew.Digits.remove(0); 
        } 
        bnew.isPositive = resultIsPositive;
        
        return bnew;
   }
   
// ============================ SHIFTS =============================================
   
//   public void shiftLeft(int positions) {  
//       while(positions != 0){
//           Digits.add((byte) 0); 
//           positions--;
//       }
//   }
   
   public BigNumber shiftLeft(int positions) { 
       BigNumber shifted = new BigNumber(this); 
       for (int i = 0; i < positions; i++) { 
           shifted.Digits.add((byte) 0); 
       } 
       shifted.numberOfDigits += positions; 
       return shifted;   
}
   
   
   public void shiftRight(int positions) { 
       int i = this.numberOfDigits-1;
       while(positions != 0){
           Digits.remove(i);
           i--;
           positions--;
       }
   }
   
// ===================================== NORMAL MULTIPLY ==================================  
    public BigNumber Normal_multiply(BigNumber bn) { 
        BigNumber result = new BigNumber(); 
        result.isPositive = (this.isPositive == bn.isPositive); 

        int n = this.Digits.size(); 
        int m = bn.Digits.size(); 

        int[] resultArr = new int[n + m]; 

        for (int i = n - 1; i >= 0; i--) { 
            for (int j = m - 1; j >= 0; j--) { 
                int mul = this.Digits.get(i) * bn.Digits.get(j); 
                int sum = mul + resultArr[i + j + 1]; 
                resultArr[i + j + 1] = sum % 10; 

                // the following section will conserve the "carry" value !!
                resultArr[i + j] += sum / 10; 
            } 
        } 

        // converting our (int[]) to (byte[]) ...
        for (int num : resultArr) { 
            if (!(result.Digits.isEmpty() && num == 0)) { 
                result.Digits.add((byte) num); 
            }
        } 

        if (result.Digits.isEmpty()) { 
            result.Digits.add((byte) 0); 
            result.isPositive = true; 
        } 

        result.numberOfDigits = result.Digits.size(); 
        return result; 
    }


// ================================== KARATSUBA ============================================
    public BigNumber karatsuba(BigNumber bn) { 
        if (this.Digits.size() == 1 || bn.Digits.size() == 1) { 
            return Normal_multiply(bn); 
        } 

        int m = Math.max(this.Digits.size(), bn.Digits.size()); 
        int m2 = m / 2; 

        BigNumber high1  =  new BigNumber(); 
        BigNumber low1   =  new BigNumber(); 
        BigNumber high2  =  new BigNumber(); 
        BigNumber low2   =  new BigNumber(); 

        for (int i = 0; i < this.Digits.size(); i++) { 
            if (i < this.Digits.size() - m2) { 
                high1.Digits.add(this.Digits.get(i)); 
            } 
            else { 
                low1.Digits.add(this.Digits.get(i)); 
            } 
        } 
        for (int i = 0; i < bn.Digits.size(); i++) { 
            if (i < bn.Digits.size() - m2) { 
                high2.Digits.add(bn.Digits.get(i)); 
            } 
        else { 
                low2.Digits.add(bn.Digits.get(i)); 
            } 
        } 
        BigNumber z0 = low1.karatsuba(low2); 
        BigNumber z2 = high1.karatsuba(high2); 
        BigNumber z1 = (low1.Plus(high1)).karatsuba(low2.Plus(high2)); 

        z1 = (z1.Minus(z2)).Minus(z0); 
        z2 = z2.shiftLeft(2 * m2); 
        z1 = z1.shiftLeft(m2); 

        BigNumber res = (z2.Plus(z1)).Plus(z0);
        res.isPositive = (this.isPositive == bn.isPositive);
        return res; 
    }
// =================================== DIVIDE ===========================================
//    public BigNumber divide(BigNumber divisor) { 
//        
//        BigNumber dividend = new BigNumber(this); 
//        BigNumber quotient = new BigNumber(); 
//        BigNumber remainder = new BigNumber(); 
//        
//        if(divisor.compareTo(new BigNumber(1)) == 0){
//            return this;
//        }
//        
//        if (divisor.compareTo(new BigNumber(0)) == 0) { 
//            throw new ArithmeticException("Division by zero"); 
//        } 
//        
//        quotient.ispositive = (this.ispositive == divisor.ispositive); 
//        dividend.ispositive = true; 
//        divisor.ispositive = true; 
//        
//        for (int i = 0; i < dividend.Digits.size(); i++) { 
//            remainder.Digits.add(dividend.Digits.get(i)); 
//            quotient.Digits.add((byte) 0); 
//            while (remainder.compareTo(divisor) >= 0) { 
//                remainder = remainder.Minus(divisor); 
//                quotient.Digits.set(i, (byte) (quotient.Digits.get(i) + 1)); 
//            } 
//        } 
//            while (quotient.Digits.size() > 1 && quotient.Digits.get(0) == 0) { 
//                quotient.Digits.remove(0); 
//            } 
//            return quotient; 
//    }
//    
    
    
    
    
    
    public BigNumber divide(BigNumber divisor) { 
        if (divisor.compareTo(new BigNumber(0)) == 0) { 
            throw new ArithmeticException("Division by zero"); 
        } 
        BigNumber dividend = new BigNumber(this); 
        BigNumber quotient = new BigNumber(); 
        BigNumber remainder = new BigNumber(); 
        quotient.isPositive = this.isPositive == divisor.isPositive; 
        dividend.isPositive = true; 
        divisor.isPositive = true; 
        for (int i = 0; i < dividend.Digits.size(); i++) { 
            remainder.Digits.add(dividend.Digits.get(i)); 
            remainder.removeLeadingZeros(); 
            quotient.Digits.add((byte) 0); 
            while (remainder.compareTo(divisor) >= 0) { 
                remainder = remainder.Minus(divisor); 
                quotient.Digits.set(i, (byte) (quotient.Digits.get(i) + 1)); 
            } 
        } 
        quotient.removeLeadingZeros(); return quotient; 
    } 
    
    
    private void removeLeadingZeros() { 
        while (Digits.size() > 1 && Digits.get(0) == 0) { 
            Digits.remove(Digits.remove(0)); 
        } 
    }
    
// ====================================== Factorial ======================================
    
    public BigNumber factorial(){
        BigNumber result = new BigNumber(1); 
        BigNumber one = new BigNumber(1); // permanent
        
        BigNumber counter = new BigNumber(this);
        while(counter.compareTo(one) > 0){
            result = result.Normal_multiply(counter);
            counter = counter.Minus(one);
        }
        
        return result;
    }
// ============================= POWER ======================================================   
    public BigNumber power(int exponent){
        BigNumber one = new BigNumber(1);
        BigNumber result = new BigNumber(1); 
        if(exponent == 0){
            return one;
        }
        
        if(exponent == 1){
            return this;
        }
        
        else{
            result = this.Normal_multiply(this);
            BigNumber thisToPower2 = new BigNumber(result);
            
            for(int i = (exponent/2) ; i > 1 ; i--){
                result = result.Normal_multiply(thisToPower2);
            }
                      
            if(exponent % 2 != 0){
                result = result.Normal_multiply(this);
            }
            
            return result;
            
        }
        
    }
}
