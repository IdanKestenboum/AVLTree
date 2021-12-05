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
            List<Integer> keys = IntStream.rangeClosed(1, 10000).boxed().collect(Collectors.toList());

            Collections.shuffle(keys);
//            System.out.println(keys.toString());
            for (int i : keys) {
                mytree.insert(i, "k");
//                System.out.println(i +"");
                mytree.keysToArray();

            }
            System.out.println("**end state, start of deletion**");
            Collections.shuffle(keys);
//            System.out.println(keys.toString());
            System.out.println("root " + mytree.getRoot().getKey() + " right son " + mytree.getRoot().getRight().getKey()+"left son"+mytree.getRoot().getLeft().getKey());
            System.out.println("right left son is:"+mytree.getRoot().getRight().getLeft().getKey() );
            int num = 1;
            for (int i : keys) {

//                System.out.println("deleting key " + i + "deletion number " + num);
                mytree.delete(i);
                mytree.keysToArray();
                num ++;
            }
            System.out.println("**end state**");
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
        AVLTree tree3=new AVLTree();
        for(int i = 1; i < 5; i++) {
            tree3.insert(i, "k");
        }

        AVLTree tree4=new AVLTree();
        for(int i = 9; i < 11; i++) {
            tree4.insert(i, "k");
        }


        AVLTree.IAVLNode x=tree1. new AVLNode(6,"HI");
//        System.out.println(Arrays.toString(tree1.keysToArray()));
//        tree1.join(x,tree2);
//        System.out.println(Arrays.toString(tree1.keysToArray()));

//        System.out.println(Arrays.toString(tree2.keysToArray()));
//        tree2.join(x,tree1);
//        System.out.println(Arrays.toString(tree2.keysToArray()));

//        System.out.println(Arrays.toString(tree3.keysToArray()));
//        tree3.join(x,tree4);
//        System.out.println(Arrays.toString(tree3.keysToArray()));

        System.out.println(Arrays.toString(tree4.keysToArray()));
        tree4.join(x,tree3);
        System.out.println(Arrays.toString(tree4.keysToArray()));
//        System.out.println("**end state**");
//        System.out.println("root is "+ tree2.getRoot().getKey());
//        System.out.println("right is "+tree2.getRoot().getRight().getKey());
//        System.out.println("left is "+tree2.getRoot().getLeft().getKey());


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

    public static void test_split(boolean random) {
        AVLTree[] res={};
        if (random){
            for (int j = 0; j < 10; j++) {
                AVLTree mytree = new AVLTree();
                List<Integer> keys = IntStream.rangeClosed(1, 1000000).boxed().collect(Collectors.toList());

                Collections.shuffle(keys);
//            System.out.println(keys.toString());
                for (int i : keys) {
                    mytree.insert(i, "k");
                }
                res = mytree.split(keys.get(15));



            }
        }
//        else{
//            for (int j = 5; j < 19; j++) {
//                mytree.insert(j,"k");
//
//            }
//            System.out.println(Arrays.toString(mytree.keysToArray()));
//            res =mytree.split(15);
//        }
//        System.out.println(Arrays.toString(res[0].keysToArray()));
//        AVLTree.IAVLNode curr= mytree.getMin(mytree.getRoot());
////        while(curr != null){
////            System.out.println(curr.getKey() +" curr");
////            curr = mytree.successor(curr);
////        }
        System.out.println(Arrays.toString(res[1].keysToArray()));
    }

    public static void Q_2(){
        AVLTree mytree1 = new AVLTree();
        AVLTree mytree2 = new AVLTree();
        List<Integer> keys = IntStream.rangeClosed(1, 1000*32).boxed().collect(Collectors.toList());
        Collections.shuffle(keys);
        for (int i : keys) {
            mytree1.insert(i, "k");
            mytree2.insert(i, "k");
        }
        System.out.println("tree 1 - left max:");
        System.out.println("height"+mytree1.getRoot().getHeight());
        mytree1.split(mytree1.getMax(mytree1.getRoot().getLeft()).getKey());
        System.out.println("tree 2 - random:");
        mytree2.split(mytree2.tree_position(100,false,false).getKey());


    }

    public static void main(String[] args) {
//        test_join();
//        loop_insert_check();
        //double_rot_check();
        //test_split(true);
        //test_join();
        Q_2();


    }
}
