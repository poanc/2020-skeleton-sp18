public class HelloNumbers {
    public static void main(String[] args) {
        int x = 0;
        int increase = 0;
	while (increase < 10) {
            System.out.print(x + " ");
            x = x + increase;
	    increase++;
        }
    }
}
