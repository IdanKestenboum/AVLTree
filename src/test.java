import java.util.Arrays;

public class test {
    public static void main(String[] args) {
        AVLTree mytree=new AVLTree();
        mytree.insert(3,"j");
        int num = mytree.insert(4,"k");
        System.out.println(Arrays.toString(mytree.keysToArray()) + " number of operations" + num);

    }
}
