// SF Tester

import java.util.*;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/**
 * Test
 */

class DebugTest {
    static Logger logger = Logger.getLogger("Tester");

    public static void testForDango() throws InterruptedException {
        AVLTree tree = new AVLTree();
        AVLTree[] afterSplit;
        Random rand = new Random();
        int[] keys = {4, 7, 5, 8, 3, 2, 6, 10, 1, 9};
        int size = 0;
        for (int k : keys) {
            tree.insert(k, Integer.toString(k));
            System.out.println(tree.getRoot());
        }

        System.out.println(tree.getRoot());
        tree.delete(2);
//        System.out.println(tree.getRoot(), "key");
        assertOnTree(tree, "");
//
//        afterSplit = tree.split(1);
//        System.out.println(afterSplit[0].getRoot(), "key");
//        System.out.println(afterSplit[0].getRoot(), "size");
//        assertOnTree(afterSplit[0], "");
//        assertOnTree(afterSplit[1], "");

    }

    public static AVLTree testForSid() throws InterruptedException {
        AVLTree tree = new AVLTree();
        int[] keys = {7, 6, 5, 1, 2, 3, 4};

        for (int k : keys) {
            tree.insert(k, Integer.toString(k));
            Thread.sleep(200L);
            if (!testParents(tree.getRoot())) {
                System.out.println("error with parents after inserting " + k);
            }
            System.out.println("------");
        }
        return tree;
    }

    public static AVLTree randomTest() throws InterruptedException {
        AVLTree tree = new AVLTree();
        double key;
        int intKey;
        for (int i = 1; i < 8; i++) {
            key = ((Math.random() * ((100 - 1) + 1)) + 1);
            intKey = (int) key;
            logger.info("inserting " + intKey);
            tree.insert(intKey, Integer.toString(intKey));
            System.out.println(tree.getRoot());
            System.out.println("-----------------");
            Thread.sleep(500L);
        }
        return tree;
    }

    public static boolean testParents() {
        int[] keys = {8, 4, 2, 1, 3, 6, 5, 7, 12, 10, 9, 11, 14, 13, 15};
        AVLTree tree = new AVLTree();
        for (int k : keys) {
            tree.insert(k, Integer.toString(k));
        }
        return testParents(tree.getRoot());
    }

    public static boolean testMin(AVLTree tree) {
        AVLTree.IAVLNode root = tree.getRoot();
        if (root == null) return tree.min() == null;
        while (root.getLeft() != null) {
            root = root.getLeft();
        }
        return root.getValue().equals(tree.min());
    }

    public static boolean testMax(AVLTree tree) {
        AVLTree.IAVLNode root = tree.getRoot();
        if (root == null) return tree.max() == null;
        while (root.getRight() != null) {
            root = root.getRight();
        }
        return root.getValue().equals(tree.max());
    }

    public static boolean testParents(AVLTree.IAVLNode node) {
        if (node.getHeight() == -1) return true;
        else if (node.getRight() == null) {
            boolean res = node.getLeft().getParent() == node;
            if (!res) System.out.println("node " + node.getValue() + "'s left child has wrong parent");
            return res && testParents(node.getLeft());
        } else if (node.getLeft() == null) {
            boolean res = node.getRight().getParent() == node;
            if (!res) System.out.println("node " + node.getValue() + "'s right child has wrong parent");
            return res &&
                    testParents(node.getRight());
        }

        boolean res =
                (node.getLeft().getParent() == node || node.getLeft().getHeight() == -1) &&
                        (node.getRight().getParent() == node || node.getLeft().getHeight() == -1) &&
                        testParents(node.getRight()) &&
                        testParents(node.getLeft());
        if (!res) System.out.println("node " + node.getValue() + "'s children have wrong parent");
        return res;
    }

    private static boolean testTreeToArray(AVLTree tree) {
        int[] keys = tree.keysToArray();
        int[] sortedKeys = keys.clone();
        Arrays.sort(sortedKeys);
        for (int i = 0; i < keys.length; i++) {
            if (keys[i] != sortedKeys[i]) return false;
        }
        return true;
    }

    public static boolean testTreeToArray() {
        int[] keys = {8, 4, 2, 1, 3, 6, 5, 7, 12, 10, 9, 11, 14, 13, 15};
        AVLTree tree = new AVLTree();
        for (int k : keys) {
            tree.insert(k, Integer.toString(k));
        }
        return testTreeToArray(tree);
    }

//    public static boolean testTreeSize() {
//        int[] keys = {8, 4, 2, 1, 3, 6, 5, 7, 12, 10, 9, 11, 14, 13, 15};
//        AVLTree tree = new AVLTree();
//        for (int k : keys) {
//            tree.insert(k, Integer.toString(k));
//        }
//        return testTreeSize(tree);
//    }
////
//    public static boolean testTreeSize(AVLTree tree) {
//        if (tree.getRoot() == null) {
//            return tree.size() == 0;
//        }
//        boolean res = testSizes(tree.getRoot());
//        return res && ((AVLTree.AVLNode) tree.getRoot()).getSize() == tree.size();
//    }
//
//    public static boolean testSizes(AVLTree.IAVLNode node) {
//        if (node == null || !node!= null) return true;
//        boolean sizeOK = ((AVLTree.AVLNode) node).getSize() == ((AVLTree.AVLNode) node.getLeft()).getSize() + ((AVLTree.AVLNode) node.getRight()).getSize() + 1;
//        if (!sizeOK) {
//            System.out.println("dd");
//        }
//        return sizeOK
//                && testSizes(node.getLeft())
//                && testSizes(node.getRight());
//    }

    public static AVLTree testInsert() throws InterruptedException {
        AVLTree tree = new AVLTree();
        //int[] keys = {8, 4, 2, 1, 3, 6, 5, 7, 12, 10, 9, 11, 14, 13, 15};
        int[] keys = new int[50];
        for (int i = 1; i <= 50; i++) keys[i - 1] = i;

        for (int k : keys) {
            tree.insert(k, Integer.toString(k));
            System.out.println(tree.getRoot().getKey());
            Thread.sleep(200L);
            if (!testParents(tree.getRoot())) {
                System.out.println("error with parents after inserting " + k);
                break;
            }
            System.out.println("------");
        }
        return tree;
    }

//    public static boolean testHeights(AVLTree.IAVLNode node) {
//        if (node == null) return true;
//        return node.getHeight() == 1 + Math.max(node.getLeft().getHeight(), node.getRight().getHeight())
//                && testHeights(node.getLeft()) && testHeights(node.getRight());
//    }

/*    public static void testSplit(AVLTree tree) {
        int[] keys = tree.keysToArray();
        int splitKey = new Random().nextInt(keys.length-1);
        List<Integer> res = tree.splitAnalysis(splitKey);
        System.out.println(res);
    }*/

    public static boolean testNonVirtual(AVLTree tree) {
        return (tree.getRoot() == null || tree.getRoot() != null)
                && (tree.min() == null || !tree.min().equals("V"))
                && (tree.max() == null || !tree.max().equals("V"));
    }

    public static boolean assertOnTree(AVLTree tree, String msgSuffix) {
        return testNonVirtual(tree) && testTreeToArray(tree) && testParents(tree.getRoot())
                && testMin(tree) && testMax(tree);
        //assert testNonVirtual(tree) : "testNonVirtual are wrong " + msgSuffix;
        //assert testTreeSize(tree) : "testTreeSize are wrong " + msgSuffix;
        //assert testTreeToArray(tree) : "testTreeToArray wrong " + msgSuffix;
        //assert testParents(tree.getRoot()) : "testParents are wrong " + msgSuffix;
        //assert testHeights(tree.getRoot()) : "testHeights are wrong " + msgSuffix;
        //assert testMin(tree) : "testMin are wrong " + msgSuffix;
        //assert testMax(tree) : "testMax are wrong " + msgSuffix;
    }

    public static void testInserts(List<Integer> keys, AVLTree tree, AVLTree debug_tree) {
        for (int k : keys) {
            //System.out.println("insert " + k);
            tree.insert(k, Integer.toString(k));
            if (!assertOnTree(tree, "after inserting " + k))
                System.out.println("put breakpoint here");
            debug_tree.insert(k, Integer.toString(k));
        }
    }

    public static void testDeletes(List<Integer> keys, AVLTree tree, AVLTree debug_tree, int deleteAmount) {
        int delIndex;
        int k;
        Random random = new Random();
        System.out.println("--normal delete");
        for (int i = 0; i < deleteAmount; i++) {
            delIndex = random.nextInt(keys.size());
            k = keys.remove(delIndex);
            tree.delete(k);
            //System.out.println("deleted " + k);
            if (!assertOnTree(tree, "after deleting " + k))
                System.out.println("put breakpoint here");
            debug_tree.delete(k);
        }
        List<Integer> deletedKeys = new ArrayList<>();
        if (keys.size() > 0) {
            System.out.println("--max key delete");
            delIndex = keys.indexOf(Integer.parseInt(tree.max()));
            k = keys.get(delIndex);
            tree.delete(k);
            keys.remove(delIndex);
            if (!assertOnTree(tree, "deleting max key"))
                System.out.println("put breakpoint here");
            debug_tree.delete(k);
            deletedKeys.add(k);
        }
        if (keys.size() > 0) {
            System.out.println("--second max key delete");
            delIndex = keys.indexOf(Integer.parseInt(tree.max()));
            k = keys.get(delIndex);
            tree.delete(k);
            keys.remove(delIndex);
            if (!assertOnTree(tree, "deleting max key second time"))
                System.out.println("put breakpoint here");
            debug_tree.delete(k);
            deletedKeys.add(k);
        }
        if (keys.size() > 0) {
            System.out.println("--min key delete");
            delIndex = keys.indexOf(Integer.parseInt(tree.min()));
            k = keys.get(delIndex);
            tree.delete(k);
            keys.remove(delIndex);
            if (!assertOnTree(tree, "deleting min key"))
                System.out.println("put breakpoint here");
            debug_tree.delete(k);
            deletedKeys.add(k);
        }
        if (keys.size() > 0) {
            System.out.println("--second min key delete");
            delIndex = keys.indexOf(Integer.parseInt(tree.min()));
            k = keys.get(delIndex);
            tree.delete(k);
            keys.remove(delIndex);
            if (!assertOnTree(tree, "deleting max key second time"))
                System.out.println("put breakpoint here");
            debug_tree.delete(k);
            deletedKeys.add(k);
        }
        for (int dk : deletedKeys) {
            tree.insert(dk, Integer.toString(dk));
            debug_tree.insert(dk, Integer.toString(dk));
            keys.add(dk);
        }
    }

    public static void testSplit(List<Integer> keys, AVLTree tree, AVLTree debug_tree) {
        Random random  = new Random();
        int splitKey = keys.get(random.nextInt(keys.size()));
        int smallerAmount = 0, biggerAmount = 0;
        for (int key : keys) {
            if (key < splitKey) smallerAmount++;
            if (key > splitKey) biggerAmount++;
        }
        //System.out.println(tree.getRoot());
        AVLTree[] splitParts = tree.split(splitKey);

        //assert splitParts[0].size() == smallerAmount : "smaller tree wrong size when splitting by " + splitKey;
        //assert splitParts[1].size() == biggerAmount : "bigger tree wrong size when splitting by " + splitKey;
        if (splitParts[0].size() != smallerAmount)
            System.out.println("put breakpoint here");
        else if (splitParts[1].size() != biggerAmount)
            System.out.println("put breakpoint here");
        else if (!assertOnTree(splitParts[0], "on smaller tree when splitting by " + splitKey))
            System.out.println("put breakpoint here");
        else if (!assertOnTree(splitParts[1], "on bigger tree when splitting by " + splitKey))
            System.out.println("put breakpoint here");
        debug_tree.split(splitKey);
    }

    public static void testRandomTree(int size) {
        AVLTree tree = new AVLTree();
        AVLTree debug_tree = new AVLTree();
        List<Integer> keys = IntStream.rangeClosed(1, size).boxed().collect(Collectors.toList());
        Collections.shuffle(keys);
        System.out.println("-testing inserts");
        testInserts(keys, tree, debug_tree);
        System.out.println(tree.getRoot());
        //System.out.println("-testing deletes");
        //testDeletes(keys, tree, debug_tree, (int) Math.ceil(size / 10.0));

        if (keys.size() > 0) {
            System.out.println("-testing split");
            testSplit(keys, tree, debug_tree);
        }
    }

    public static void main(String[] args) throws InterruptedException {
//        System.out.println("testing tree of size 0");
//        testRandomTree(0);
//        System.out.println("testing tree of size 1");
//        testRandomTree(1);
//        Random rand = new Random();
//        for (int i = 0; i < 200; i++) {
//            System.out.println(i);
//            int size = rand.nextInt(10000);
//            System.out.println("testing tree of size " + size);
//            testInsert();
//        }
        //testRandomTree(10);
    }
}


