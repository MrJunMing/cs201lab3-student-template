import java.util.*;

public class Q1Test {
     public static void main(String[] args) {

          String result, traversal1, traversal2, traversal3;

          traversal1 = "1-2";
          traversal2 = "2-1-3";
          traversal3 = "1-3-2";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println("Expected Result : Invalid traversals");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "1-2-3";
          traversal2 = "2-1-3";
          traversal3 = "1-3-2";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Inorder, Traversal 2 - Preorder, Traversal 3 - Postorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "1-2-3";
          traversal2 = "3-2-1";
          traversal3 = "2-3-1";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println("Expected Result : Invalid traversals");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "3-1-2-5-4";
          traversal2 = "2-1-4-5-3";
          traversal3 = "1-2-3-4-5";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Preorder, Traversal 2 - Postorder, Traversal 3 - Inorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "10-20-30-40-50";
          traversal2 = "20-10-30-40-50";
          traversal3 = "10-50-40-30-20";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Inorder, Traversal 2 - Preorder, Traversal 3 - Postorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();
     } 

     
     public static String verify(String traversal1, String traversal2, String traversal3){
          String[] traversals = {traversal1, traversal2, traversal3};
    
          int[][] assignments = {{0, 1, 2}, {0, 2, 1}, {1, 0, 2}, {1, 2, 0}, {2, 0, 1}, {2, 1, 0}};
          for (int[] a : assignments) {
               if (isBstTraversal(traversals[a[0]], traversals[a[1]], traversals[a[2]])) {
                    String[] labels = new String[3];
                    labels[a[0]] = "Inorder";
                    labels[a[1]] = "Preorder";
                    labels[a[2]] = "Postorder";
                    return "Traversal 1 - " + labels[0] + ", Traversal 2 - " + labels[1] + ", Traversal 3 - " + labels[2];
               }
          }
          return "Invalid traversals";
     }

     // rebuild it and compare all three traversals
     private static boolean isBstTraversal(String inorder, String preorder, String postorder) {
          Node root = null;
          for (String value : preorder.split("-")) {
               root = insert(root, Integer.parseInt(value));
          }
          return inorder.equals(traverse(root, "Inorder"))
                    && preorder.equals(traverse(root, "Preorder"))
                    && postorder.equals(traverse(root, "Postorder"));
     }

     private static Node insert(Node root, int value) {
          if (root == null) {
               return new Node(value);
          }
          if (value < root.value) {
               root.left = insert(root.left, value);
          } else {
               root.right = insert(root.right, value);
          }
          return root;
     }

     private static String traverse(Node root, String order) {
          List<String> values = new ArrayList<>();
          walk(root, order, values);
          return String.join("-", values);
     }

     private static void walk(Node node, String order, List<String> values) {
          if (node == null) {
               return;
          }
          if (order.equals("Preorder")) {
               values.add(String.valueOf(node.value));
          }
          walk(node.left, order, values);
          if (order.equals("Inorder")) {
               values.add(String.valueOf(node.value));
          }
          walk(node.right, order, values);
          if (order.equals("Postorder")) {
               values.add(String.valueOf(node.value));
          }
     }

     private static class Node {
          int value;
          Node left;
          Node right;

          Node(int value) {
               this.value = value;
          }
     }
}