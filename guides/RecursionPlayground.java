class RecursionPlayground {
    public int fibonacci(int n) {
        System.out.println("Entering fibonacci n="+n);
        
        // let's deal with the two easy cases first:
        if (n == 0)
            return 0;
        else if (n == 1)
            return 1;

        // the rest of the F-numbers are calculated by summing the two previous F-numbers
        else return fibonacci(n-1) + fibonacci(n-2);
    }

    
    public int factorial(int n) {
        System.out.println("Entering factorial n="+n);
        
        // the easy case--we know that 0! is 1
        if (n == 0)
            return 1;
        else

            // otherwise, we can use the formula to calculate our result
            return n * factorial(n-1);
    }

    public boolean isPalindrome(String s) {
        System.out.println("Entering isPalindrome s= |"+s+"|");
        
        // idea: compare first (index 0) and last (index length-1) characters
        // if they're the same, test the "middle substring" to see if it's a palindrome

        if (s.charAt(0) == s.charAt(s.length()-1))
            return isPalindrome(s.substring(1,s.length()-1));
        else
            return false;
    }
}
