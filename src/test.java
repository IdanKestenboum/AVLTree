import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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


    public static void double_rot_check() {

        for (int j = 0; j < 10; j++) {
            AVLTree mytree = new AVLTree();
            List<Integer> keys = IntStream.rangeClosed(1, 1000000).boxed().collect(Collectors.toList());

            Collections.shuffle(keys);
//            System.out.println(keys.toString());
            for (int i : keys) {
                mytree.insert(i, "k");
            }
            System.out.println("**end state**");
            mytree.keysToArray();
//            System.out.println(Arrays.toString(mytree.keysToArray()));
//            System.out.println(Arrays.toString(mytree.infoToArray()));
        }
//        System.out.println("root is "+ mytree.getRoot().getKey());
//        System.out.println("root height is "+ mytree.getRoot().getHeight());
//        System.out.println("root size is "+ mytree.getRoot().getSize());
//        System.out.println("root Bf is "+ mytree.getRoot().getBF());
//        System.out.println("right is "+mytree.getRoot().getRight().getKey());
//        System.out.println("right height is "+mytree.getRoot().getRight().getHeight());
//        System.out.println("right size is "+mytree.getRoot().getRight().getSize());
//        System.out.println("right left is "+mytree.getRoot().getRight().getLeft().getKey());
//        System.out.println("right left left is "+mytree.getRoot().getRight().getLeft().getLeft().getKey());
//        System.out.println("left is "+mytree.getRoot().getLeft().getKey());
//        System.out.println("left height is "+mytree.getRoot().getLeft().getHeight());
//        System.out.println("left size is "+mytree.getRoot().getLeft().getSize());
//        System.out.println("left right is "+mytree.getRoot().getLeft().getRight().getKey());
//
    }
    public static void test_join(){
        AVLTree tree1=new AVLTree();
        for(int i = 10; i < 20; i++) {
            tree1.insert(i, "k");
        }
        AVLTree tree2=new AVLTree();
        for(int i = 0; i < 4; i++) {
            tree2.insert(i, "k");
        }
        AVLTree.IAVLNode x=tree1. new AVLNode(6,"HI");
        System.out.println(Arrays.toString(tree1.keysToArray()));
        tree1.join(x,tree2);
        System.out.println(Arrays.toString(tree1.keysToArray()));
//        System.out.println("**end state**");
//        System.out.println("root is "+ tree1.getRoot().getKey());
//        System.out.println("root height is "+ tree1.getRoot().getHeight());
//        System.out.println("root size is "+ tree1.getRoot().getSize());
//        System.out.println("root Bf is "+ tree1.getRoot().getBF());
//        System.out.println("right is "+tree1.getRoot().getRight().getKey());
//        System.out.println("right height is "+tree1.getRoot().getRight().getHeight());
//        System.out.println("right size is "+tree1.getRoot().getRight().getSize());
//        System.out.println("right Bf is "+tree1.getRoot().getRight().getBF());
//        System.out.println("right left is "+tree1.getRoot().getRight().getLeft().getKey());
//        System.out.println("right left height "+tree1.getRoot().getRight().getLeft().getHeight());
//        System.out.println("right left size "+tree1.getRoot().getRight().getLeft().getSize());
//        System.out.println("right right is "+tree1.getRoot().getRight().getRight().getKey());
//        System.out.println("right right height "+tree1.getRoot().getRight().getRight().getHeight());
//        System.out.println("right right right is "+tree1.getRoot().getRight().getRight().getRight().getKey());
//        System.out.println("left is "+tree1.getRoot().getLeft().getKey());
//        System.out.println("left height is "+tree1.getRoot().getLeft().getHeight());
//        System.out.println("left size is "+tree1.getRoot().getLeft().getSize());
//        System.out.println("left left is "+tree1.getRoot().getLeft().getLeft().getKey());
//        System.out.println("left right is "+tree1.getRoot().getLeft().getRight().getKey());
//        System.out.println("left right size is "+tree1.getRoot().getLeft().getRight().getSize());
//        System.out.println("left right right is "+tree1.getRoot().getLeft().getRight().getRight().getKey());
//        System.out.println("left left is "+tree1.getRoot().getLeft().getLeft().getKey());
//        System.out.println("left left left is "+tree1.getRoot().getLeft().getLeft().getLeft().getKey());
    }

    public static void main(String[] args) {
        loop_insert_check();


    }
}
