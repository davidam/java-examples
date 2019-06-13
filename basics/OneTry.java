public class OneTry {
  public static void main(String[ ] args) {
    try {
      int[] myNumbers = {1, 2, 3};
      System.out.println(myNumbers[23]);
    } catch (Exception e) {
      System.out.println("Something went wrong.");
    }
  }
}
