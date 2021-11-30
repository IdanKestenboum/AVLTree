import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class test {
    public static void loop_insert_check() {
        AVLTree mytree=new AVLTree();
        for(int i = 10; i > 0; i--){
            int num = mytree.insert(i,"k");

            System.out.println(Arrays.toString(mytree.keysToArray()) + " number of operations " + num);
        }
        System.out.println("**end state**");
        System.out.println("root is "+ mytree.getRoot().getKey());
        System.out.println("root height is "+ mytree.getRoot().getHeight());
        System.out.println("root size is "+ mytree.getRoot().getSize());
        System.out.println("root Bf is "+ mytree.getRoot().getBF());
        System.out.println("right is "+mytree.getRoot().getRight().getKey());
        System.out.println("right height is "+mytree.getRoot().getRight().getHeight());
        System.out.println("right size is "+mytree.getRoot().getRight().getSize());
        System.out.println("right Bf is "+mytree.getRoot().getRight().getBF());
        System.out.println("right left is "+mytree.getRoot().getRight().getLeft().getKey());
        System.out.println("right left height "+mytree.getRoot().getRight().getLeft().getHeight());
        System.out.println("right left size "+mytree.getRoot().getRight().getLeft().getSize());
        System.out.println("right right is "+mytree.getRoot().getRight().getRight().getKey());
        System.out.println("right right height "+mytree.getRoot().getRight().getRight().getHeight());
        System.out.println("right right right is "+mytree.getRoot().getRight().getRight().getRight().getKey());
        System.out.println("left is "+mytree.getRoot().getLeft().getKey());
        System.out.println("left height is "+mytree.getRoot().getLeft().getHeight());
        System.out.println("left size is "+mytree.getRoot().getLeft().getSize());
        System.out.println("left left is "+mytree.getRoot().getLeft().getLeft().getKey());
        System.out.println("left right is "+mytree.getRoot().getLeft().getRight().getKey());
        System.out.println("left right size is "+mytree.getRoot().getLeft().getRight().getSize());
        System.out.println("left right right is "+mytree.getRoot().getLeft().getRight().getRight().getKey());
        System.out.println("left left is "+mytree.getRoot().getLeft().getLeft().getKey());
        System.out.println("left left left is "+mytree.getRoot().getLeft().getLeft().getLeft().getKey());
    }


    public static void double_rot_check(){
        AVLTree mytree=new AVLTree();
        int total_moves = 0;
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i=1; i<50; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        int[] list2 = {13, 5, 21, 12, 2, 11, 18, 22, 1, 19, 4, 17, 24, 6, 3, 15, 26, 8, 28, 27, 29, 10, 25, 14, 23, 16, 20, 7, 9};
        System.out.println(list.toString());
        for(int i : list){

            int reb_moves = mytree.insert(i,"hi");
            total_moves += reb_moves;
            System.out.println(i + " rebalancing moves: " + reb_moves);
        }
        //mytree.insert(10,"a");
        /*mytree.insert(7,"c");
        mytree.insert(3,"e");
        mytree.insert(25,"d");
        mytree.insert(17,"g");
        mytree.insert(29,"k");
        mytree.insert(19,"o");*/
        System.out.println("**end state**");
        System.out.println("total moves done: " + total_moves + " size: " + mytree.size());
        System.out.println(Arrays.toString(mytree.keysToArray()));
        System.out.println(Arrays.toString(mytree.infoToArray()));
        System.out.println("root is "+ mytree.getRoot().getKey());
        System.out.println("root height is "+ mytree.getRoot().getHeight());
        System.out.println("root size is "+ mytree.getRoot().getSize());
        System.out.println("root Bf is "+ mytree.getRoot().getBF());
        System.out.println("right is "+mytree.getRoot().getRight().getKey());
        System.out.println("right height is "+mytree.getRoot().getRight().getHeight());
        System.out.println("right size is "+mytree.getRoot().getRight().getSize());
        System.out.println("right left is "+mytree.getRoot().getRight().getLeft().getKey());
        System.out.println("right left left is "+mytree.getRoot().getRight().getLeft().getLeft().getKey());
        System.out.println("left is "+mytree.getRoot().getLeft().getKey());
        System.out.println("left height is "+mytree.getRoot().getLeft().getHeight());
        System.out.println("left size is "+mytree.getRoot().getLeft().getSize());
        System.out.println("left right is "+mytree.getRoot().getLeft().getRight().getKey());




    }
    public static void main(String[] args) {
    double_rot_check();
    //loop_insert_check();




    }
}
