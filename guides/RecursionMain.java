class RecursionMain {
    public static void main(String[] args) {
        RecursionPlayground pg = new RecursionPlayground();

        int result = pg.fibonacci(0);
        System.out.println("result is " + result);

        result = pg.factorial(0);
        System.out.println("result is " + result);

        /* this code will compile, but it will crash because isPalindrome isn't done

        boolean answer = pg.isPalindrome("noon");
        System.out.println("answer is " + answer);

        */
    }
}
