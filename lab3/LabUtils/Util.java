package lab3.LabUtils;

public class Util {

        
        // Print a message and convert the boolean to an integer.
        public static int printPassFail(boolean bit) {
                if (bit) {
                        System.out.println("==> passed");
                } else {
                        System.out.println("==> FAILED");
                }
                System.out.println();
                return bit ? 1 : 0;
        }
        
        public static void printTotalNumTests(int num) {
                System.out.println("Running " + num + " tests.");
                System.out.println();
        }

}
