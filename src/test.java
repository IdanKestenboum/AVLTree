import java.util.Arrays;

public class test {
    public static void main(String[] args) {
        AVLTree mytree=new AVLTree();
        //mytree.insert(3,"j");
        for(int i = 1; i <= 10; i++){
            int num = mytree.insert(i,"k");

            System.out.println(Arrays.toString(mytree.keysToArray()) + " number of operations " + num);
            System.out.println("root is "+ mytree.getRoot().getKey());
        }
        //int num2 = mytree.insert(4,"k");
        //int num = mytree.insert(5,"k");

    }
}
