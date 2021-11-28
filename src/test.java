import java.util.Arrays;

public class test {
    public static void main(String[] args) {
        AVLTree mytree=new AVLTree();
        //mytree.insert(3,"j");
        for(int i = 1; i <= 9; i++){
            int num = mytree.insert(i,"k");

            System.out.println(Arrays.toString(mytree.keysToArray()) + " number of operations " + num);
        }
        System.out.println("**end state**");
        System.out.println("root is "+ mytree.getRoot().getKey());
        System.out.println("root height is "+ mytree.getRoot().getHeight());
        System.out.println("root Bf is "+ mytree.getRoot().getBF());
        System.out.println("right is "+mytree.getRoot().getRight().getKey());
        System.out.println("right height is "+mytree.getRoot().getRight().getHeight());
        System.out.println("right left is "+mytree.getRoot().getRight().getLeft().getKey());
        System.out.println("right left height "+mytree.getRoot().getRight().getLeft().getHeight());
        System.out.println("right right is "+mytree.getRoot().getRight().getRight().getKey());
        System.out.println("right right height "+mytree.getRoot().getRight().getRight().getHeight());
        System.out.println("right right right is "+mytree.getRoot().getRight().getRight().getRight().getKey());
        System.out.println("left is "+mytree.getRoot().getLeft().getKey());
        System.out.println("left height is "+mytree.getRoot().getLeft().getHeight());
        System.out.println("left left is "+mytree.getRoot().getLeft().getLeft().getKey());
        System.out.println("left right is "+mytree.getRoot().getLeft().getRight().getKey());
        System.out.println("left left is "+mytree.getRoot().getLeft().getLeft().getKey());
        //int num2 = mytree.insert(4,"k");
        //int num = mytree.insert(5,"k");

    }
}
