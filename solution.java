public class Solution {
    static int sign = 1;
    
    public static int[] solution(int[] pegs) {
        return calculateFirstPeg(pegs);
    }
    
    public static int[] calculateFirstPeg(int[] pegs) {
        
        int nth_Size;
        int size_R;
        int firstPeg;
        
        int pegSize = pegs.length;
        int counter = pegSize-1;
        int divisor = 3;
        
        if (!isEven(pegSize)) {
            sign *= -1;
            divisor =1;
        }
        
        size_R = pegs[counter] * sign;
        counter--;
        
        while (counter >= 1) {
            sign *= -1;
            nth_Size = (2 * pegs[counter]) * sign;
            counter--;
            size_R += nth_Size;
        }
        
        sign *= -1;
        size_R += (pegs[counter] * sign);
        firstPeg = size_R * 2;
        
        if (divisor == 3) {
            if ((firstPeg % 3) == 0) {
                firstPeg /= 3;
                divisor /= 3;
            }
        }
        
        if (firstPeg < 0) {
            return new int[]{-1,-1};
        } else {
            if (checkAllFits(pegs, firstPeg, divisor)) {
                return new int[]{firstPeg, divisor};
            } else {
                return new int[]{-1,-1};
            }
        }
    } 
    
    public static boolean isEven(int n) {
        return (n % 2 == 0) ? true : false;
    }
    
    public static boolean checkAllFits(int[] pegs, int firstPeg, int divisor) {
        double first_radius = firstPeg / divisor; 
        double rad_Checker = first_radius;
        double next_distance;
        boolean checkValid = true;
        
        for (int i = 0; i < pegs.length-1 && checkValid ; i++) {
            next_distance = (pegs[i+1] - pegs[i]);
            if ((next_distance - rad_Checker) > 0 ) {
                rad_Checker = next_distance - rad_Checker;
            } else {
                checkValid = false;
            }    
        }
        
        return checkValid;
    }
}
