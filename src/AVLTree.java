/**
 *
 * AVLTree
 *
 * An implementation of aמ AVL Tree with
 * distinct integer keys and info.
 *
 */

public class AVLTree {
    private IAVLNode Virtual_node;
    private IAVLNode min;
    private IAVLNode max;
    private IAVLNode root;

    public AVLTree() {
        this.Virtual_node = new AVLNode(-1, "virtual");
        Virtual_node.setHeight(-1);
        root = null;
        this.min=min;
        this.max=max;

    }
    public AVLTree(IAVLNode node, IAVLNode new_min, IAVLNode new_max){
        this.Virtual_node = new AVLNode(-1, "virtual");
        Virtual_node.setHeight(-1);
        root = node;
        this.min=new_min;
        this.max=new_max;
        if (root!=null) {
            root.setParent(null);
        }
    }
    /**
     * public boolean empty()
     *
     * Returns true if and only if the tree is empty.
     *
     */
    public boolean empty() {
        if (this.root==null){
            return true;
        }
        return false;
    }

    public IAVLNode tree_position(int key, boolean is_insert, boolean is_delete){
        IAVLNode curr = root;
        if(root == null) return null;
        IAVLNode y = curr;
        while (curr.getHeight() != -1){
            y = curr;
            if (key==curr.getKey()){
                return curr;          //we found the key
            }
            else if (key<curr.getKey()){
                if(is_insert) {
                    curr.insertSizeUpdate();
                }
                if(is_delete) {
                    curr.deleteSizeUpdate();
                }
                curr=curr.getLeft();
            }
            else{
                if(is_insert){
                    curr.insertSizeUpdate();
                }
                if(is_delete) {
                    curr.deleteSizeUpdate();
                }
                curr=curr.getRight();

            }
        }
        return y;
    }
    /**
     * public String search(int k)
     *
     * Returns the info of an item with key k if it exists in the tree.
     * otherwise, returns null.
     *
     */
    public String search(int k)
    {

        IAVLNode res=tree_position(k,false, false);
        if(res == null) return null;
        if (res.getKey()!=k){
            return null;
        }
        else{
            return res.getValue();

        }
    }
    /**
     * public int insert(int k, String i)
     *
     * Inserts an item with key k and info i to the AVL tree.
     * The tree must remain valid, i.e. keep its invariants.
     * Returns the number of re-balancing operations, or 0 if no re-balancing operations were necessary.
     * A promotion/rotation counts as one re-balance operation, double-rotation is counted as 2.
     * Returns -1 if an item with key k already exists in the tree.
     *
     * @pre k not in tree
     */
    public int insert(int k, String i) {
        IAVLNode inserted = new AVLNode(k, i);
        if(this.root == null){
            this.root = inserted;
            this.min = inserted;
            this.max = inserted;
            return 0;
        }
        if (search(k)!=null){
            return -1;
        }
        int[] counter = {0};
        inserted.setRight(Virtual_node);
        inserted.setLeft(Virtual_node);
        if (k<this.min.getKey()){
            this.min=inserted;
        }
        if (k>this.max.getKey()){
            this.max=inserted;
        }
        IAVLNode father = tree_position(k,true, false);
        if(father.getKey() > k){
            father.setLeft(inserted);

        }
        if (father.getKey()<k){
            father.setRight(inserted);
        }
        inserted.setParent(father);
        father.adjustHeight(counter);
        Rebalance(father,counter);
        return counter[0];
    }

    /**
     * public int delete(int k)
     *
     * Deletes an item with key k from the binary tree, if it is there.
     * The tree must remain valid, i.e. keep its invariants.
     * Returns the number of re-balancing operations, or 0 if no re-balancing operations were necessary.
     * A promotion/rotation counts as one re-balance operation, double-rotation is counted as 2.
     * Returns -1 if an item with key k was not found in the tree.
     */
    public int delete(int k)
    {

//        if(this.search(k) == null) return -1;
        IAVLNode node = this.tree_position(k, false, false);
        if(node == null) return -1;
        if(node.getKey() != k) return -1;
        int[] counter = {0};
        this.deleteNode(node, counter);
        return counter[0];	// to be replaced by student code
    }

    private void deleteNode(IAVLNode node, int[] counter){
        if (node == null) return;
        if(this.size() == 1){
            this.root = null;
            min = null;
            max = null;

            return;
        }
        if(node == min) min = successor(node);
        if(node == max) max = predecessor(node);
        IAVLNode father = null;
        if(node != root) {
            father = node.getParent();
        }
//        System.out.println("deleting node" + node.getKey());
        if(node.getRight() == Virtual_node) {//node is unary, has left son, or node is leaf and left son is virtual node
//            System.out.println("by bypassing " + node.getKey() + " node was unary or leaf");
            if(father != null) {
                if (node.getKey() < father.getKey()) {//is left child
                    father.setLeft(node.getLeft());
                } else {//is right child
                    father.setRight(node.getLeft());

                }
                if (node.getLeft() != Virtual_node) {
                    node.getLeft().setParent(father);
//                    System.out.println(father.getKey() + " is now father of " + node.getLeft().getKey());

                }
//                else System.out.println( "node " + node.getKey() + " was leaf");
                father.adjustHeight(counter);
                Rebalance(father, counter);
            }
            else{
                if(node.getLeft() == Virtual_node){
                    root = null;
                    min = null;
                    max = null;
                }

                else{
                    root = node.getLeft();
                    max = node.getLeft();
                }

            }

        }
        else if(node.getLeft() == Virtual_node){// node is unary has right son
            if(father != null) {
//                System.out.println("by bypassing " + node.getKey() + " node was unary and had only right son");

                if (node.getKey() < father.getKey()) {//is left child
                    father.setLeft(node.getRight());

                } else {//is right child
                    father.setRight(node.getRight());

                }
                if(node.getRight() != Virtual_node){
                    node.getRight().setParent(father);
//                    System.out.println(father.getKey() + " is now father of " + node.getRight().getKey());
                }
                father.adjustHeight(counter);
                Rebalance(father, counter);
            }
            else{
                root = node.getRight();
                min = node.getRight();
            }
        }
        else {
//            System.out.println("node has two sons");


            IAVLNode replacement = predecessor(node);
            boolean replacement_is_min = false;
            if(replacement == min) replacement_is_min = true;
//            System.out.println("replacement is " + replacement.getKey());
            deleteNode(replacement, counter);
            if (replacement_is_min) min = replacement;
            father = null;
            if(node != root) {
                father = node.getParent();
            }
            else root = replacement;
            IAVLNode left = node.getLeft();
            IAVLNode right = node.getRight();
//            if(father != null) System.out.println("father: " + father.getKey() +", left is: " + left.getKey() + ", right is : " + right.getKey());
//            else System.out.println("deleting root, left is: " + left.getKey() + ", right is : " + right.getKey());
            replacement.setParent(father);//now replace node by switching all the pointers
            if(father != null) {
                if(node.getKey() < father.getKey()){//is left child
                    father.setLeft(replacement);
                }
                else{//is right child
                    father.setRight(replacement);

                }
            }
            replacement.setRight(right);//left and right are not virtual, otherwise wouldnt get to this
            right.setParent(replacement);
            replacement.setLeft(left);
            left.setParent(replacement);//node is replaced
            replacement.setHeight(node.getHeight());//make height that of deleted
            int prev = counter[0];
            replacement.adjustHeight(counter);//update height as part of rebalance
            int post = counter[0];
//            System.out.println((post-prev) + "diff");
            replacement.adjustSize();//update size as part of rebalance, might be unnecessary
//            if(root == node)System.out.println(node.getKey() + " is root");
//            if(node.getSize() != replacement.getSize())System.out.println("sizes are different, node: " + node.getSize() + " replacement: " + replacement.getSize());
//            Rebalance(replacement, counter);

        }
        return;
    }
    /**
     * public String min()
     *
     * Returns the info of the item with the smallest key in the tree,
     * or null if the tree is empty.
     */
    public String min() {
        if(min == null) return null;
        return this.min.getValue();
    }

    /**
     * public String max()
     *
     * Returns the info of the item with the largest key in the tree,
     * or null if the tree is empty.
     */
    public String max()
    {
        if(max == null) return null;
        return this.max.getValue();
    }

    /**
     * public int[] keysToArray()
     *
     * Returns a sorted array which contains all keys in the tree,
     * or an empty array if the tree is empty.
     */
    public int[] keysToArray()
    {
//        if(root != null) System.out.println(size() + " is size, root is " + root.getKey());
//        else System.out.println(size() + " is size, root is " + null);
        IAVLNode curr = this.min;
        int tree_size = size();
        int[] array = new int[tree_size];
        if(this.root == null) return array;

        for(int i = 0; i < tree_size-1 ; i++){
            array[i] = curr.getKey();
//            System.out.println(curr.getKey() + " BF: " + curr.getBF() + " height diff" + (curr.getHeight()-curr.getLeft().getHeight()));
            int temp = curr.getKey();
            if(Math.abs(curr.getBF()) >1) System.out.println(curr.getKey() + " Error in BF: " + curr.getBF());
            if(curr.getParent() != null) {
                if (curr.getHeight() == curr.getParent().getHeight()) System.out.println(curr.getKey() + " is same height as parent: " + curr.getParent().getKey());
            }
            if(Math.abs(curr.getHeight()-curr.getLeft().getHeight()) >2){
                System.out.println("left son height problem, too high");
            }
            if (Math.abs(curr.getHeight()-curr.getLeft().getHeight()) == 0){
                System.out.println("left son height problem, same height");
            }
            if(Math.abs(curr.getHeight()-curr.getRight().getHeight()) >2){
                System.out.println(curr.getKey() + " right son height problem, too high");
            }
            if((Math.abs(curr.getHeight()-curr.getRight().getHeight()) == 0)){
                System.out.println(curr.getKey() + " right son height problem, same height");

            }
            curr = successor(curr);
            int temp2 = curr.getKey();
            if(temp2 <= temp) System.out.println(curr.getKey() + " Error in ascending order!");
        }
        array[tree_size-1] = curr.getKey();
//        System.out.println("good");
        return array;
    }

    public IAVLNode getMin(IAVLNode node){
        IAVLNode curr=node;
        while(curr.getLeft().getKey()!=-1){
            curr=curr.getLeft();
        }
        return curr;
    }

    public IAVLNode successor(IAVLNode node){
        if(node.getRight().getKey() != -1){
//            System.out.println("y = " + node.getRight().getKey());
            return getMin(node.getRight());
        }
        IAVLNode y = node.getParent();
//        System.out.println(y.getKey() + " is predecessor");
        while (y!=Virtual_node && node==y.getRight()){
//            System.out.println("finding successor of " + y.getKey() + "");
            node=y;
            y=node.getParent();
        }
//        System.out.println("y= " + y.getKey());
        return y;
    }

    public IAVLNode predecessor(IAVLNode node){
        if(node.getLeft().getKey() != -1){
            return getMax(node.getLeft());
        }
        IAVLNode y = node.getParent();
        while (y!=null&&node==y.getLeft()){
            node=y;
            y=node.getParent();
        }
        return y;
    }
    //    private IAVLNode deletePredecessor(IAVLNode node, int[] counter){
//        if(node.getLeft() != Virtual_node){//not virtual
//            node.deleteSizeUpdate();
//            IAVLNode x = getMaxDelete(node.getLeft());
//            deleteNode(x, counter);
//            return x;
//        }
//        System.out.println("issue in deletePredecessor");
////        IAVLNode y = node.getParent(); //commented because shouldnt get to this case, delete predecessor should only be called on a node with two sons!!!
////        while (y.getKey()!=-1&&node==y.getLeft()){
////            node=y;
////            y=node.getParent();
////        }
////        deleteNode(y, counter)
////        return y;
//        return node;
//    }
//    private IAVLNode getMaxDelete(IAVLNode node){
//        IAVLNode curr = node;
//        while(curr.getRight() != Virtual_node){
//            curr.deleteSizeUpdate();
//            curr = curr.getRight();
//        }
//        return curr;
//    }
    public IAVLNode getMax(IAVLNode node){
        IAVLNode curr = node;
        while(curr.getRight() != Virtual_node){
            curr = curr.getRight();
        }
        return curr;
    }

    /**
     * public String[] infoToArray()
     *
     * Returns an array which contains all info in the tree,
     * sorted by their respective keys,
     * or an empty array if the tree is empty.
     */
    public String[] infoToArray()
    {
        IAVLNode curr = this.min;
        int tree_size = this.root.getSize();
        String[] array = new String[tree_size];
        for(int i = 0; i < tree_size-1 ; i++){
            array[i] = curr.getValue();
            curr = successor(curr);
        }
        array[tree_size-1] = curr.getValue();

        return array;
    }

    /**
     * public int size()
     *
     * Returns the number of nodes in the tree.
     */
    public int size()
    {
        if (this.root==null){
            return 0;
        }
        else{
            return this.root.getSize();
        }
    }

    /**
     * public int getRoot()
     *
     * Returns the root AVL node, or null if the tree is empty
     */
    public IAVLNode getRoot()
    {
        return this.root;
    }

    public void Rebalance(IAVLNode node,int[] counter) {
//        if(node.getParent() != null){ /////     We dont need to adjust height of father
//            node.getParent().adjustHeight(counter);
//        }
        int BF = node.getBF();
//        System.out.println(BF);
        node.adjustSize();

        if (BF <= 1 && BF >= -1) { //balance factor is valid, but we dont know if rank difference with parent is valid
            if (node.getParent() == null)  { //checks if root
//                System.out.println("root1");
                return;
            }
//            int prev = node.getParent().getHeight();
            node.getParent().adjustSize();
            node.getParent().adjustHeight(counter);
//            int post = node.getParent().getHeight();
//            if(node.getParent().getBF() <= 1 && node.getParent().getBF() >= -1){//checks parent is balanced
//                if(prev == post){//father didnt change therefore problem doesnt continue
////                    System.out.println("problem doesnt continue");
//                    return;
//                }
//
//                Rebalance(node.getParent(),counter); //Idan added this - so the root can rotate
////                System.out.println("parent fixed, grandparent not fixed");
//
//                return;
//
//            }
            if (node.getParent() != null) {//checks if root
//                System.out.println("problem with parent");
                Rebalance(node.getParent(),counter);  // if not root pass problem to father
            }

            return;
        }
//        System.out.println("WE GOT HERE");
        int son_BF = 0;
        if (BF > 1) { //checks where is the bigger difference subtree +1 means right is deeper
            son_BF = -node.getRight().getBF();
        } else if (BF < -1) {// left is 0 right is 2 so we need to rotate so that left becomes father
            son_BF = node.getLeft().getBF();
        }
        if (son_BF == -1 | son_BF == 0) {
            Rotation(node, BF, counter);
            counter[0]+=1;
        } else if (son_BF == 1) {// decides if to do double rotation
            Double_Rotation(node, BF, counter);
            counter[0]+=2;
        }
        node.getParent().adjustHeight(counter);

        if (node.getParent().getParent() != null) {
            node.getParent().getParent().adjustHeight(counter);
            Rebalance(node.getParent().getParent(),counter);
        }
    }

    public void Rotation(IAVLNode node, int BF, int[] counter){
//        int node_height=node.getHeight();

        if (BF < -1){ // makes left father
//            System.out.println("left: "+ node.getLeft().getKey() + " becomes father of " + node.getKey());
            IAVLNode left=node.getLeft();
            IAVLNode father = node.getParent();
            IAVLNode left_right_son=left.getRight();
            left.setRight(node);
            node.setLeft(left_right_son);
            node.setParent(left);
            left_right_son.setParent(node);
            left.setParent(father);
            if (father!=null){
                if (father.getKey()<left.getKey()){//idan changed
                    father.setRight(left);//idan changed
                }
                if (father.getKey()> left.getKey()){//idan changed
                    father.setLeft(left);//idan changed
                }

            }
            if (left.getParent()==null){
                this.root=left;
            }
            node.adjustHeight(counter);
            node.adjustSize();
            left.adjustSize();
//            if (left.getParent()!=null){//idan added this
//                left.getParent().adjustHeight(counter);//idan added this
//            }
//            left.adjustHeight(counter);

        }
        else if (BF > 1){ //makes right father
//            System.out.println(node.getRight().getKey() + " becomes father of " + node.getKey());
            IAVLNode right=node.getRight();
            IAVLNode right_left_son=right.getLeft();
            IAVLNode father = node.getParent();
            right.setLeft(node);
            node.setRight(right_left_son);
            node.setParent(right);
            right_left_son.setParent(node);
            right.setParent(father);
            if (father!=null){
                if (father.getKey()<right.getKey()){//idan changed
                    father.setRight(right);//idan changed
                }
                if (father.getKey()> right.getKey()){//idan changed
                    father.setLeft(right);//idan changed
                }

            }
            if (right.getParent()==null){
                this.root=right;
            }
            node.adjustHeight(counter);
            node.adjustSize();
            right.adjustSize();
//            if (right.getParent()!=null){ //idan added this
//                right.getParent().adjustHeight(counter);//idan added this
//            }
//
//            right.adjustHeight(counter);

        }
    }

    public void Double_Rotation(IAVLNode node,int BF, int[] counter){
        int node_height=node.getHeight();
//        System.out.println("double rotation on "+node.getKey()+"its BF is"+node.getBF());
        if (BF < -1){//makes left right father
            IAVLNode left_node=node.getLeft();
            Rotation(left_node,2,counter);
            Rotation(node,-2,counter);
            node.getParent().getLeft().adjustHeight(counter);
        }
        else if (BF > 1){//makes right left father
            IAVLNode right_node=node.getRight();
            Rotation(right_node,-2,counter);
            Rotation(node,2,counter);
            node.getParent().getRight().adjustHeight(counter);
        }

    }

    /**
     * public AVLTree[] split(int x)
     *
     * splits the tree into 2 trees according to the key x.
     * Returns an array [t1, t2] with two AVL trees. keys(t1) < x < keys(t2).
     *
     * precondition: search(x) != null (i.e. you can also assume that the tree is not empty)
     * postcondition: none
     */
    public AVLTree[] split(int x) {
        IAVLNode x_node=this.tree_position(x,false,false);
        AVLTree smaller_tree=null;
        AVLTree bigger_tree=null;
        IAVLNode minimum = this.min;
        IAVLNode maximum = this.max;

        if (x == this.max.getKey()){
            bigger_tree = new AVLTree(); // empty tree
            smaller_tree = this;
            this.delete(x); // smaller tree is the entire tree without x
        }
        else if (x == this.min.getKey()){
            smaller_tree = new AVLTree(); // empty tree
            bigger_tree = this;
            this.delete(x); // bigger tree is the entire tree without x
        }
        else {
            IAVLNode smallerRoot = x_node.getLeft().isRealNode() ?  x_node.getLeft() : null;
            IAVLNode biggerRoot = x_node.getRight().isRealNode() ?  x_node.getRight() : null;
            IAVLNode xPredecessor = predecessor(x_node);
            IAVLNode xSuccessor =  successor(x_node);
            smaller_tree = new AVLTree(smallerRoot, this.min, xPredecessor);
            bigger_tree = new AVLTree(biggerRoot, xSuccessor, this.max);

            isolate_node(x_node);
            x_node.adjustHeight();
            x_node.adjustSize();

            IAVLNode node = x_node;
            IAVLNode parent = node.getParent();
            while (parent != null) {
                AVLTree tree;
                IAVLNode otherSon;
                if (parent.getKey() > node.getKey()) {
                    otherSon =  parent.getRight();
                    tree = bigger_tree;
                } else {
                    otherSon = parent.getLeft();
                    tree = smaller_tree;
                }
                node = parent;
                parent = parent.getParent(); // so i'll move up in cases I insert parent down the tree
                isolate_node(node);
                node.adjustSize();
                node.adjustHeight();
                if (otherSon.isRealNode()) {
                    tree.join(node, new AVLTree(otherSon, otherSon, otherSon));
                }
                else{
                    tree.join(node, new AVLTree());
                }
            }
            smaller_tree.min = minimum;
            smaller_tree.max = xPredecessor;
            bigger_tree.min = xSuccessor;
            bigger_tree.max = maximum;
        }

//        if (x_node.getRight()!=Virtual_node&&x_node.getLeft()!=Virtual_node) {
//            System.out.println("1");
//            smaller_tree = new AVLTree(x_node.getLeft(), this.getMin(x_node), predecessor(x_node));
//            bigger_tree = new AVLTree(x_node.getRight(), successor(x_node), this.max);
//        }
//        else if (x_node.getRight()!=null){
//            System.out.println("2");
//            smaller_tree = new AVLTree();
//            bigger_tree = new AVLTree(x_node.getRight(), successor(x_node), this.max);
//
//        }
//        else if( x_node.getLeft()!=null){
//            System.out.println("3");
//            smaller_tree = new AVLTree(x_node.getLeft(), this.getMin(x_node), predecessor(x_node));
//            bigger_tree = new AVLTree();
//
//        }
//        IAVLNode cur = x_node;
//        while (cur.getParent() != null) {//while not root
//            IAVLNode temp = cur.getParent();
//            if (cur.getParent().getRight() == cur) {
//                IAVLNode new_root = temp.getLeft();
//                new_root.setParent(null);
//                isolate_node(temp);
//                if(temp.getLeft().isRealNode()) {
//                    smaller_tree.join(temp, new AVLTree(new_root,getMin(temp.getLeft()),predecessor(temp)));
//                }else{
//                    smaller_tree.join(temp, new AVLTree());
//
//                }
//                cur = temp;
//            } else if (cur.getParent().getLeft() == cur) {
//                IAVLNode new_root = temp.getRight();
//                new_root.setParent(null);
//                isolate_node(temp);
//                System.out.println("here");
//                if(temp.getRight().isRealNode()) {
//                    bigger_tree.join(temp, new AVLTree(new_root, successor(temp), getMax(temp.getRight())));
//                }
//                else{
//                    bigger_tree.join(temp, new AVLTree());
//
//                }
//                cur = temp;
//
//            }
//        }


//        while (cur.getParent()!=null&&cur.getParent().getRight()==cur){
//            smaller_tree.join(cur.getParent(),new AVLTree(cur.getParent().getLeft(),this.getMin(cur.getParent()),this.getMax(cur.getParent().getLeft())));
//            cur=cur.getParent();
//        }
//        while (cur.getParent()!=null&&cur.getParent().getRight()!=null){
//            if (cur.getParent().getParent()!=null&&cur.getParent().getParent().getLeft()!=null){
//
//                smaller_tree.join(cur.getParent().getParent(),new AVLTree(cur.getParent().getParent().getLeft(),this.getMin(cur.getParent().getParent().getLeft()), this.getMax(cur.getParent().getParent().getLeft())));
//            }
//            if (cur.getParent().getLeft()==cur&&cur.getParent().getRight()!=null){
//                cur.getParent().setLeft(Virtual_node);
//                cur.getParent().setParent(null);
//                bigger_tree.join(cur.getParent(),new AVLTree(cur.getParent().getRight(), bigger_tree.min, getMax(cur.getParent().getRight())));
//            }
//
//            cur=cur.getParent();
//        }
//        if (cur.getParent().getParent().getLeft()!=null){
//            smaller_tree.join(cur.getParent().getParent(),new AVLTree(cur.getParent().getParent().getLeft(),this.getMin(cur.getParent().getParent()),this.getMax(cur.getParent().getParent().getLeft())));
//        }

        AVLTree[] res = {smaller_tree, bigger_tree};
        return res;
    }
    public void isolate_node(IAVLNode node){
        node.setLeft(Virtual_node);
        node.setRight(Virtual_node);
        node.adjustSize();
        node.adjustHeight();
    }

    /**
     * public int join(IAVLNode x, AVLTree t)
     *
     * joins t and x with the tree.
     * Returns the complexity of the operation (|tree.rank - t.rank| + 1).
     *
     * precondition: keys(t) < x < keys() or keys(t) > x > keys(). t/tree might be empty (rank = -1).
     * postcondition: none
     */
    public int join(IAVLNode x, AVLTree t) {
        AVLTree biggertree;
        AVLTree smallertree;
        if (x.getKey()<this.min.getKey()){
            biggertree=this;
            smallertree=t;
        }
        else{
            biggertree=t;
            smallertree=this;
        }
        AVLTree highertree=null;
        AVLTree lowertree=null;

        if (this.root.getHeight()>t.root.getHeight()){
            highertree=this;
            lowertree=t;
        }
        if(t.root.getHeight()>this.root.getHeight()){
            highertree=t;
            lowertree=this;
        }
        else if(this.root.getHeight()==t.root.getHeight()){
            x.setRight(biggertree.root);
            x.setLeft(smallertree.root);
            biggertree.root.setParent(x);
            smallertree.root.setParent(x);
            this.min= smallertree.min;
            this.max= biggertree.max;
            this.root=x;
            return 1;
        }

        IAVLNode cur= highertree.getRoot();
        if(this==biggertree&&this==highertree) {
//            System.out.println("1");
            while (cur.getHeight() > lowertree.getRoot().getHeight() && cur.getLeft() != Virtual_node) {
                cur = cur.getLeft();
            }
            this.min = smallertree.getMin(smallertree.getRoot());

            x.setLeft(lowertree.root);
            lowertree.root.setParent(x);
            cur.getParent().setLeft(x);
            x.setParent(cur.getParent());

            x.setRight(cur);
            cur.setParent(x);

        }
        if (this==biggertree&&this==lowertree){
//            System.out.println("2");
            while (cur.getHeight() > lowertree.getRoot().getHeight() && cur.getLeft() != Virtual_node) {
                cur = cur.getRight();
            }
            this.min = smallertree.getMin(smallertree.getRoot());
            x.setLeft(cur);
            lowertree.root.setParent(x);
            cur.getParent().setRight(x);
            x.setParent(cur.getParent());
            x.setRight(lowertree.root);
            cur.setParent(x);
            this.root= highertree.root;

        }
        if (this==smallertree&&this==highertree){
//            System.out.println("3");
            while (cur.getHeight() > lowertree.getRoot().getHeight() && cur.getLeft() != Virtual_node) {
                cur = cur.getRight();
            }
            this.max = biggertree.max;
            x.setRight(biggertree.root);
            lowertree.root.setParent(x);
            cur.getParent().setRight(x);
            x.setParent(cur.getParent());
            x.setLeft(cur);
            cur.setParent(x);

        }

        if (this==smallertree&&this==lowertree){
//            System.out.println("4");
            while (cur.getHeight() > lowertree.getRoot().getHeight() && cur.getLeft().getKey() != -1) {
                cur = cur.getLeft();
            }
            this.max =biggertree.max;

            x.setLeft(lowertree.root);
            lowertree.root.setParent(x);
            if (cur.getParent()!=null){
                cur.getParent().setLeft(x);
                x.setParent(cur.getParent());
            }
            else{

            }

            x.setRight(cur);
            cur.setParent(x);
            this.root= x;

        }
        x.adjustHeight();
        x.adjustSize();
        if (x.getParent()!=null){
            x.getParent().adjustSize(); // parents size didnt adjust during rotation so I added this
        }
        int[] fakecounter={0};
        Rebalance(x,fakecounter);

        return Math.abs(this.getRoot().getHeight()-t.getRoot().getHeight())+1;
    }

    /**
     * public interface IAVLNode
     * ! Do not delete or modify this - otherwise all tests will fail !
     */
    public interface IAVLNode{
        public int getKey(); // Returns node's key (for virtual node return -1).
        public String getValue(); // Returns node's value [info], for virtual node returns null.
        public void setLeft(IAVLNode node); // Sets left child.
        public IAVLNode getLeft(); // Returns left child, if there is no left child returns null.
        public void setRight(IAVLNode node); // Sets right child.
        public IAVLNode getRight(); // Returns right child, if there is no right child return null.
        public void setParent(IAVLNode node); // Sets parent.
        public IAVLNode getParent(); // Returns the parent, if there is no parent return null.
        public boolean isRealNode(); // Returns True if this is a non-virtual AVL node.
        public void setHeight(int height); // Sets the height of the node.
        public int getHeight(); // Returns the height of the node (-1 for virtual nodes).
        public void adjustHeight();
        public void adjustHeight(int[] counter);
        public int getBF();
        public void adjustSize();
        public int getSize();
        public void insertSizeUpdate();
        public void deleteSizeUpdate();
        public void printNode();

    }

    /**
     * public class AVLNode
     *
     * If you wish to implement classes other than AVLTree
     * (for example AVLNode), do it in this file, not in another file.
     *
     * This class can and MUST be modified (It must implement IAVLNode).
     */
    public class AVLNode implements IAVLNode{
        private int key;
        private String value;
        private IAVLNode right_son,left_son,father_node;
        private int height,size;

        //Node constructor
        public AVLNode(int key, String value){
            this.key=key;
            this.value=value;
            this.height=0;
            this.size=1;
            this.left_son = Virtual_node;
            this.right_son = Virtual_node;
        }

        public int getKey()
        {
            return this.key;
        }
        public String getValue()
        {
            return this.value;
        }
        public void setLeft(IAVLNode node)
        {
            this.left_son=node;
        }
        public IAVLNode getLeft()
        {
            return this.left_son;
        }
        public void setRight(IAVLNode node)
        {
            this.right_son=node;
        }
        public IAVLNode getRight()
        {
            return right_son;
        }
        public void setParent(IAVLNode node)
        {
            this.father_node=node;
        }
        public IAVLNode getParent()
        {
            return this.father_node;
        }
        public boolean isRealNode()
        {
            if (this.height==-1){
                return false;
            }
            return true;
        }
        public void insertSizeUpdate(){
            this.size += 1;
        }
        public void deleteSizeUpdate(){
            this.size -= 1;
        }
        public void setHeight(int height) {this.height=height;}
        public void adjustSize(){
            if(this != Virtual_node)this.size = this.right_son.getSize() + this.left_son.getSize() + 1;
        }
        public int getSize(){
            if (this==Virtual_node){
                return 0;
            }
            return this.size;
        }
        public void adjustHeight(){this.height=Integer.max(this.right_son.getHeight(),this.left_son.getHeight())+1;}
        public void adjustHeight(int[] counter) {
            int prev = this.height;
            this.height = Integer.max(this.right_son.getHeight(), this.left_son.getHeight()) + 1;
            int post = this.height;
//            if (prev != post) {
//                System.out.println("number of demote/promote: " + this.key + " " + (post - prev) + " height: " + height+" R "+ right_son.getKey()+ " L "+ left_son.getKey());
//                counter[0] += Math.abs(post - prev);
//            }
//            if (left_son == Virtual_node || right_son == Virtual_node){
//                System.out.println("son is virtual");


            if(left_son != Virtual_node) {
                if (this.left_son.getParent() != this) {
                    System.out.println(this.getKey() + " should be parent of " + this.right_son.getKey()
                            + " is son of " + this.right_son.getParent().getKey());
                }
            }
            if(right_son != Virtual_node){
                if(this.right_son.getParent() != this){
                    System.out.println(this.getKey() + " should be parent of " + this.right_son.getKey()
                            + " is son of " + this.right_son.getParent().getKey());
                }
            }
        }//  Yotam Added for easy promotion/demotion count
        public int getHeight() {return this.height;}
        public int getBF(){return (this.right_son.getHeight()-this.left_son.getHeight());} //Yotam Switched these
        public void printNode(){
            if(father_node != root)System.out.println("printing node: " + key + "\n father: " + father_node.getKey() + "father height: " + father_node.getHeight() + " left: " + left_son.getKey()
                    + "left height: " + left_son.getHeight() + " right: " + right_son.getKey() + "right height: " + right_son.getHeight());
        }
    }

}