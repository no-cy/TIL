//
// 개선 후 코드
//
class Solution {
  public String intToRoman(int num) {
    int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    StringBuilder result = new StringBuilder();

    for (int i = 0; i < values.length; i++) {
      while (num >= values[i]) {
        result.append(symbols[i]);
        num -= values[i];
      }
    }

    return result.toString();
  }
}


//
// 개선 전 코드
//
class Solution {
    public StringBuilder subtractive(int num) {
        StringBuilder roman = new StringBuilder();
        
        if (num == 4)
            roman.append("IV");
        else if (num == 9)
            roman.append("IX");
        else if (num == 40)
            roman.append("XL");
        else if (num == 90)
            roman.append("XC");
        else if (num == 400)
            roman.append("CD");
        else if (num == 900)
            roman.append("CM");
        
        return roman;
    }

    public StringBuilder romanNumerals(int num)
    {
        StringBuilder roman = new StringBuilder();

        while (num > 0) {
            if (num >= 1000) {
                roman.append("M");
                num -= 1000;
            } else if (num >= 500) {
                roman.append("D");
                num -= 500;
            } else if (num >= 100) {
                roman.append("C");
                num -= 100;
            }else if (num >= 50) {
                roman.append("L");
                num -= 50;
            }else if (num >= 10) {
                roman.append("X");
                num -= 10;
            }else if (num >= 5) {
                roman.append("V");
                num -= 5;
            }else if (num >= 1) {
                roman.append("I");
                num -= 1;
            }
        }

        return roman;
    }

    public String intToRoman(int num) {
        int placeValue = 1;
        StringBuilder roman = new StringBuilder();
        List<StringBuilder> romanList = new ArrayList<>();
        StringBuilder result = new StringBuilder();

        while (num > 0) {
            int remainder = num % 10;
            
            remainder *= placeValue;

            if (remainder == 4 || remainder == 9 || remainder == 40 || remainder == 90 || remainder == 400 || remainder == 900 ) {
                roman = subtractive(remainder);
            } else {
                roman = romanNumerals(remainder);
            }

            placeValue *= 10;
            num /= 10;

            romanList.add(roman);
        }
        
        for (int i = romanList.size() - 1; i >= 0; i--) {
            result.append(romanList.get(i));
        }
        
        System.out.println(result);
        return result.toString();
    }
}
