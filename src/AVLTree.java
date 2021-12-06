//Idan Kestenboum 315532218 kestenboum + Yotam Gavish 318303765


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

    public AVLTree() { //builder of a new empty AVL tree - Complexity - O(1)
        this.Virtual_node = new AVLNode(-1, "virtual");
        Virtual_node.setHeight(-1);
        root = null;
        this.min=min;
        this.max=max;

    }
    public AVLTree(IAVLNode node, IAVLNode new_min, IAVLNode new_max){ //builder of a new non empty AVL-tree Complexity - O(1)
        this.Virtual_node = new AVLNode(-1, "virtual");
        Virtual_node.setHeight(-1);
        root = node;//node to be root
        this.min=new_min;//node to be min
        this.max=new_max;//node to be max
        if (root!=null) {
            root.setParent(null); //make sure root doesn't have an "old" parent
        }
    }
    /**
     * public boolean empty()
     *
     * Returns true if and only if the tree is empty.
     *
     */
    public boolean empty() { //Complexity - O(1)
        if (this.root==null){
            return true;
        }
        return false;
    }

    public IAVLNode tree_position(int key, boolean is_insert, boolean is_delete){ //Complexity O(log(n))
        IAVLNode curr = root;
        if(root == null) return null;
        IAVLNode y = curr;
        while (curr.getHeight() != -1){ //start looking for the node with the "key" from the root
            y = curr;
            if (key==curr.getKey()){
                return curr;          //we found the key
            }
            else if (key<curr.getKey()){//need to go left, and update the height of the cur node
                if(is_insert) {
                    curr.insertSizeUpdate();
                }
                if(is_delete) {
                    curr.deleteSizeUpdate();
                }
                curr=curr.getLeft();
            }
            else{
                if(is_insert){//need to go right, and update the height of the cur node
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
    public String search(int k) //calls tree_posotion method and returns the value poninter - overall Complexity O(log(n))
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
    public int insert(int k, String i) {//shell function, makes node, calls insertNode(node) - overall Complexity - O(log(n))
        IAVLNode inserted = new AVLNode(k, i);
        return insertNode(inserted);
    }
    private int insertNode(IAVLNode inserted){//inserts a node
        int k = inserted.getKey();
        String i = inserted.getValue();
        if(this.root == null){// if tree is empty tree inserted node
            this.root = inserted;
            this.min = inserted;
            this.max = inserted;
            return 0;
        }
        if (search(k)!=null){//if k in tree return -1
            return -1;
        }
        int[] counter = {0};//counter for rebalance steps
        inserted.setRight(Virtual_node);
        inserted.setLeft(Virtual_node);
        if (k<this.min.getKey()){//if inserted is less than current min, updates current min
            this.min=inserted;
        }
        if (k>this.max.getKey()){//updates max
            this.max=inserted;
        }
        IAVLNode father = tree_position(k,true, false);// gets father
        if(father.getKey() > k){//sets as left son
            father.setLeft(inserted);

        }
        if (father.getKey()<k){//sets as right son
            father.setRight(inserted);
        }
        inserted.setParent(father);//updates father
        father.adjustHeight(counter);
        Rebalance(father,counter);//rebalance
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
    public int delete(int k)//finds node if in tree, returns -1 if not, if yes calls deleteNode on node. overall complexity - O(log(n))
    {

//        if(this.search(k) == null) return -1;
        IAVLNode node = this.tree_position(k, false, false);//gets node
        if(node == null) return -1;//node not in tree
        if(node.getKey() != k) return -1;//necessary?
        int[] counter = {0};//counter for number of rebalance steps
        this.deleteNode(node, counter);
        return counter[0];
    }

    private void deleteNode(IAVLNode node, int[] counter){//deletes node that exists in tree
        if (node == null) return;
        if(this.size() == 1){//if node is only node in tree, makes tree empty
            this.root = null;
            min = null;
            max = null;

            return;
        }
        if(node == min) min = successor(node);//updates min if node is min
        if(node == max) max = predecessor(node);//updates max
        IAVLNode father = null;//if root father is null
        if(node != root) {//if not root, father is found
            father = node.getParent();
        }
//        System.out.println("deleting node" + node.getKey());
        if(node.getRight() == Virtual_node) {//node is unary, has left son, or node is leaf and left son is virtual node
//            System.out.println("by bypassing " + node.getKey() + " node was unary or leaf");
            if(father != null) {//node is not root
                if (node.getKey() < father.getKey()) {//is left child, bypasses by making son left child
                    father.setLeft(node.getLeft());
                } else {//is right child
                    father.setRight(node.getLeft());

                }
                if (node.getLeft() != Virtual_node) {//if not leaf updates parent of bypass
                    node.getLeft().setParent(father);
//                    System.out.println(father.getKey() + " is now father of " + node.getLeft().getKey());

                }
//                else System.out.println( "node " + node.getKey() + " was leaf");
                father.adjustHeight(counter);
                Rebalance(father, counter);//rebalance after delete
            }
            else{//node is root, has only left child or is only node
                if(node.getLeft() == Virtual_node){//is only node makes tree empty
                    root = null;
                    min = null;
                    max = null;
                }

                else{//has left son, left son is leaf(otherwise tree is not balanced), and therefore left son becomes only node in tree
                    root = node.getLeft();
                    max = node.getLeft();
                }

            }

        }
        else if(node.getLeft() == Virtual_node){// node is unary has right son
            if(father != null) {//not root
//                System.out.println("by bypassing " + node.getKey() + " node was unary and had only right son");

                if (node.getKey() < father.getKey()) {//is left child
                    father.setLeft(node.getRight());

                } else {//is right child
                    father.setRight(node.getRight());

                }
                if(node.getRight() != Virtual_node){//makes father the parent of bypass node
                    node.getRight().setParent(father);
//                    System.out.println(father.getKey() + " is now father of " + node.getRight().getKey());
                }
                father.adjustHeight(counter);
                Rebalance(father, counter);//rebalance after delete
            }
            else{//is root, has right son
                root = node.getRight();
                min = node.getRight();
            }
        }
        else {//node has two sons, gets predecessor(=>replacement), deletes predecessor but holds node as replacement, replaces node to be deleted with replacement
//            System.out.println("node has two sons");


            IAVLNode replacement = predecessor(node);//hold predecessor as replacement
            boolean replacement_is_min = false;//assume not min
            if(replacement == min) replacement_is_min = true;//check if min for special case(*)
//            System.out.println("replacement is " + replacement.getKey());
            deleteNode(replacement, counter);//deletes predecessor, calls recursively on deleteNode, this time we will stop at unary or leaf node
            if (replacement_is_min) min = replacement;//(*)update min after deletion, in special case that replacement is min, node is successor of min and will be updated to min but deleted, so we must update min
            father = null;//assume root
            if(node != root) {//if not root get father of node to be replaced
                father = node.getParent();
            }
            else root = replacement;
            IAVLNode left = node.getLeft();//get left and right of node to be replaced
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
            replacement.setRight(right);
            if(right != Virtual_node) right.setParent(replacement);//in extreme case that predecessor is deleted, tree is rebalanced and node is moved to have a a virtual node
            replacement.setLeft(left);
            if(left != Virtual_node) left.setParent(replacement);//same a above
            replacement.setHeight(node.getHeight());//make height that of deleted
//            int prev = counter[0];
            replacement.adjustHeight(counter);//update height as part of rebalance
//            int post = counter[0];
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
    public String min() {//Complexity - O(1)
        if(min == null) return null;
        return this.min.getValue();
    }

    /**
     * public String max()
     *
     * Returns the info of the item with the largest key in the tree,
     * or null if the tree is empty.
     */
    public String max() //Complexity - O(1)
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
    public int[] keysToArray() //Complexity - o(n)
    {
        IAVLNode curr = this.min;
        int tree_size = size();
        int[] array = new int[tree_size];
        if(this.root == null) return array; //empty tree

        for(int i = 0; i < tree_size-1 ; i++){//go over all the nodes starting from min, call successor every loop and store into array
            array[i] = curr.getKey();
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

    public IAVLNode getMin(IAVLNode node){ //Complexity - O(log(n))
        IAVLNode curr=node;
        while(curr.getLeft().getKey()!=-1){
            curr=curr.getLeft();
        }
        return curr;
    }

    public IAVLNode successor(IAVLNode node){ //Complexity - O(log(n))
        if(node.getRight().getKey() != -1){//has right son so go right and get min
//            System.out.println("y = " + node.getRight().getKey());
            return getMin(node.getRight());
        }
        IAVLNode y = node.getParent();
//        System.out.println(y.getKey() + " is predecessor");
        while (y!=null && node==y.getRight()){//go up the tree and make sure we hold the parents right son
//            System.out.println("finding successor of " + y.getKey() + "");
            node=y;
            y=node.getParent();
        }
//        System.out.println("y= " + y.getKey());
        return y;
    }

    public IAVLNode predecessor(IAVLNode node){//Complexity - O(log(n))
        if(node.getLeft().getKey() != -1){//has left son so go left and get max
            return getMax(node.getLeft());
        }
        IAVLNode y = node.getParent();
        while (y!=null&&node==y.getLeft()){//go up the tree and make sure we hold the parents left son
            node=y;
            y=node.getParent();
        }
        return y;
    }
    public IAVLNode getMax(IAVLNode node){//Complexity - O(log(n))
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
    public String[] infoToArray() //start from min and call successor while storing values in array - complexity - O(n)
    {
        IAVLNode curr = this.min;
        int tree_size = this.root.getSize();
        String[] array = new String[tree_size];
        for(int i = 0; i < tree_size-1 ; i++){//runs throught tree the size number of times, stops 1 before last node so that successor(max) doesnt happen
            array[i] = curr.getValue();
            curr = successor(curr);
        }
        array[tree_size-1] = curr.getValue();//adds last value

        return array;
    }

    /**
     * public int size()
     *
     * Returns the number of nodes in the tree.
     */
    public int size() // complexity - O(1)
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
    public IAVLNode getRoot() // complexity - O(1)
    {
        return this.root;
    }

    public void Rebalance(IAVLNode node,int[] counter) { // Complexity O(log(n))
        int BF = node.getBF();
        node.adjustSize();

        if (BF <= 1 && BF >= -1) { //balance factor is valid, but we dont know if rank difference with parent is valid
            if (node.getParent() == null)  { //checks if root
                return;//if root and BF is valid, return, tree is balanced
            }
            node.getParent().adjustSize();//update parent size, must get to root so size of root is correct
            node.getParent().adjustHeight(counter);//update parent height with counter
            Rebalance(node.getParent(),counter);  // if not root pass problem to father

            return;
        }
        int son_BF = 0;
        if (BF > 1) { //checks where is the bigger difference subtree +1 means right is higher
            son_BF = -node.getRight().getBF();
        } else if (BF < -1) {////left is higher
            son_BF = node.getLeft().getBF();
        }
        if (son_BF == -1 | son_BF == 0) {//this fits cases of single rotation
            Rotation(node, BF, counter);
            counter[0]+=1;//cost of one rotation, not including demotes and premotes
        } else if (son_BF == 1) {// decides if to do double rotation
            Double_Rotation(node, BF, counter);
            counter[0]+=2;
        }
        node.getParent().adjustHeight(counter);//must update parent height, after rotation, should be valid so we skip to grandparent
        if (node.getParent().getParent() != null) {//checks if parent is root, in which case tree is balanced, if not pass up to grandparent
            node.getParent().getParent().adjustHeight(counter);
            Rebalance(node.getParent().getParent(),counter);
        }
    }

    public void Rotation(IAVLNode node, int BF, int[] counter){ // complexity -(O(1))

        if (BF < -1){ // makes left father
            //get all the pointers
            IAVLNode left=node.getLeft();
            IAVLNode father = node.getParent();
            IAVLNode left_right_son=left.getRight();
            //switch pointers
            left.setRight(node);//node becomes right son of left
            node.setLeft(left_right_son);//node's left becomes left right son
            node.setParent(left);//node parent is left
            left_right_son.setParent(node);//left right son parent is node
            left.setParent(father);//left parent is father
            if (father!=null){//not rotation on root
                if (father.getKey()<left.getKey()){//idan changed
                    father.setRight(left);//idan changed
                }
                if (father.getKey()> left.getKey()){//idan changed
                    father.setLeft(left);//idan changed
                }

            }
            if (left.getParent()==null){//yes root, update root
                this.root=left;
            }
            node.adjustHeight(counter);//adjust height after rotation
            node.adjustSize();//adjust size after rotation
            left.adjustSize();//then adjust new father size after rotation

        }
        else if (BF > 1){ //makes right father
            //get pointers
            IAVLNode right=node.getRight();
            IAVLNode right_left_son=right.getLeft();
            IAVLNode father = node.getParent();
            //move pointers, symmetrical to previous case
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
            node.adjustHeight(counter);//adjust height after rotation
            node.adjustSize();//first adjust node
            right.adjustSize();//then his father
        }
    }

    public void Double_Rotation(IAVLNode node,int BF, int[] counter){ // complexity -(O(1))
        if (BF < -1){//makes left right father
            IAVLNode left_node=node.getLeft();
            Rotation(left_node,2,counter);//makes left son's right son father of left son
            Rotation(node,-2,counter);//new left son is prev left right son, makes left right father
            node.getParent().getLeft().adjustHeight(counter);
        }
        else if (BF > 1){//makes right left father
            IAVLNode right_node=node.getRight();
            Rotation(right_node,-2,counter);//makes right son's left son father of right son
            Rotation(node,2,counter);//new right son is prev right left son, makes right left father
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
        double num_counter=0;
        double operation_counter=0;
        double max_pointer=0;

        if (x == this.max.getKey()){
            bigger_tree = new AVLTree(); // the tree is empty so we initiate a new tree
            smaller_tree = this;
            this.delete(x); //the smaller tree is the whole tree, will delete x
        }
        else if (x == this.min.getKey()){
            smaller_tree = new AVLTree(); // the tree is empty so we initiate a new tree
            bigger_tree = this;
            this.delete(x); // the bigger tree is the whole tree,will delete x
        }
        else { //non trivial cases, start from x and find the nodes to build bigger and smaller
            IAVLNode root_of_smaller;
            if (x_node.getLeft().isRealNode()){//get root of smaller tree
                root_of_smaller=x_node.getLeft();
            }
            else{
                root_of_smaller=null;
            }
            IAVLNode root_of_bigger;
            if (x_node.getRight().isRealNode())//get root of bigger
            {
                root_of_bigger=x_node.getRight();
            }
            else{
                root_of_bigger=null;
            }
            IAVLNode x_predecessor = predecessor(x_node);//hold predecessor, will become max of smaller tree
            IAVLNode x_successor =  successor(x_node);//hold successor, will become min of bigger tree
            IAVLNode new_min = null;
            IAVLNode xpre = x_predecessor;
            IAVLNode xsuc = x_successor;
            if(root_of_smaller != null){//smaller tree is not empty, need to find its min
                new_min = getMin(root_of_smaller);
            }
            else {//smaller tree is empty so min and max should be null
                xpre = null;
            }
            IAVLNode new_max = null;
            if(root_of_bigger != null){//bigger tree is not empty, need to find its min
                new_max = getMax(root_of_bigger);
            }
            else{//bigger tree is empty
                xsuc = null;
            }//now after getting the required pointers, we can build the tree
            smaller_tree = new AVLTree(root_of_smaller, new_min, xpre);//if subtree empty, all values will be null, else new tree with minimum being the min of subroot, and max being predeessor of x
            bigger_tree = new AVLTree(root_of_bigger, xsuc, new_max);//same for bigger subtree

            IAVLNode node = x_node;
            IAVLNode parent = node.getParent();

            isolate_node(x_node);


            while (parent != null) {//while not at root
                AVLTree tree;//make an avl tree
                IAVLNode otherSon;//make avl node for holding other son
                if (parent.getKey() > node.getKey()) {//is left son
                    otherSon =  parent.getRight();//other son is right son
                    tree = bigger_tree;//subtree is bigger will be added to bigger tree
                } else {
                    otherSon = parent.getLeft();//other son is left
                    tree = smaller_tree;//subtree is smaller will be added to smaller tree
                }
                node = parent;//moving up
                parent = parent.getParent(); //getting pointer to parent before isolating node
                isolate_node(node);
                if (otherSon.isRealNode()) {//other son isnt virtual so left subtree isnt empty
                    otherSon.setParent(null);

                    int temp=tree.join(node, new AVLTree(otherSon, getMin(otherSon), getMax(otherSon)));//join tree that fits(bigger or smaller) with subtree of other son using the node
                    operation_counter+=temp;
                    num_counter+=1;
                    if (temp>max_pointer){
                        max_pointer=temp;
                    }
                }
                else{//if subtree is empty we can just insert node to bigger/smaller tree
                    int temp=tree.insertNode(node);
                    operation_counter+=temp;
                    num_counter+=1;
                    if (temp>max_pointer){
                        max_pointer=temp;
                    }
                }
            }// after inserting need to adjust min/max pointers
            smaller_tree.min = minimum;
            smaller_tree.max = x_predecessor;
            bigger_tree.min = x_successor;
            bigger_tree.max = maximum;
        }
        AVLTree[] res = {smaller_tree, bigger_tree};
        System.out.println("average cost"+operation_counter/num_counter);
        System.out.println("max "+max_pointer);
        return res;
    }
    public void isolate_node(IAVLNode node){ //disconnects node from its sons and father- complexity - O(1)
        node.setLeft(Virtual_node);
        node.setRight(Virtual_node);
        node.setParent(null);
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
        if (t.empty()) {//if t is empty we just insert new node, works if both are empty
            insertNode(x);
            return root.getHeight();
        }
        else if(this.empty()){//if this is empty (t is not empty) we insert new node to t, make this t
            t.insertNode(x);
            root = t.getRoot();
            max=t.max;
            min=t.min;
            return root.getHeight();
        }
        //both trees are not empty
        AVLTree biggertree;
        AVLTree smallertree;
        if (x.getKey()<this.min.getKey()){//x is smaller than all leaves in this
            biggertree=this;
            smallertree=t;
        }
        else{//x is bigger than this
            biggertree=t;
            smallertree=this;
        }
        AVLTree highertree=null;
        AVLTree lowertree=null;
        //find lower tree
        if (this.root.getHeight()>t.root.getHeight()){
            highertree=this;
            lowertree=t;
        }
        else if(t.root.getHeight()>this.root.getHeight()){
            highertree=t;
            lowertree=this;
        }
        if(this.root.getHeight()==t.root.getHeight() || Math.abs(this.root.getHeight()-t.root.getHeight()) == 1){//if trees are same height or 1 apart makes them sons of x
            x.setRight(biggertree.root);
            x.setLeft(smallertree.root);
            biggertree.root.setParent(x);
            smallertree.root.setParent(x);
            x.adjustHeight();
            x.adjustSize();
            this.min= smallertree.min;
            this.max= biggertree.max;
            this.root=x;
            return 1;
        }


        int returned = Math.abs(this.getRoot().getHeight()-t.getRoot().getHeight())+1;
        IAVLNode cur= highertree.getRoot();
        this.min = smallertree.min;
        this.max = biggertree.max;

        if(biggertree==highertree) {//bigger is higher
            while (cur.getHeight() > lowertree.getRoot().getHeight() && cur.getLeft().getKey() != -1) {// get first node that is lower than or equal to lower tree height
                cur = cur.getLeft();
            }
            x.setLeft(lowertree.root);//make left son smaller tree
            lowertree.root.setParent(x);//make x father of smaller tree
            if(cur.getParent()!=null) {
                cur.getParent().setLeft(x);//make father of subtree have x as son
                x.setParent(cur.getParent());//make that node be parent of x

            }
            x.setRight(cur);//set right son of x to be right subtree
            cur.setParent(x);//set x as parent of right subtree
            //tree is joined, not necessarily balanced
//
        }
        if (biggertree==lowertree){//bigger is lower
            while (cur.getHeight() > lowertree.getRoot().getHeight() && cur.getRight().getKey() != -1) {//get first node that is lower than or equal to lower tree height
                cur = cur.getRight();
            }

            x.setLeft(cur);//x left is subtree of higher tree, left because x is bigger
            x.setRight(lowertree.root);//make x have lower tree as right son(lower tree is bigger in this case)
            if(cur.getParent()!=null) {
                cur.getParent().setRight(x);//make father of subtree have x as son
                x.setParent(cur.getParent());//make father of subtree father of x

            }
            lowertree.root.setParent(x);//make lower tree parent x
            cur.setParent(x);
        }
        this.root = highertree.root;//set root to be root of higher tree(must happen before rebalance)
        x.adjustHeight();
        x.adjustSize();
        int[] fakecounter={0};
        Rebalance(x, fakecounter);//must rebalance


        return returned;
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
//            System.out.println(key + ", right: " + right_son.getKey() + ", left: " + left_son.getKey());
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
            if (prev != post) {
                //System.out.println("number of demote/promote: " + this.key + " " + (post - prev) + " height: " + height+" R "+ right_son.getKey()+ " L "+ left_son.getKey());
                counter[0] += Math.abs(post - prev);
            }
            if (left_son == Virtual_node || right_son == Virtual_node) {
                //System.out.println("son is virtual");
            }


            if(left_son != Virtual_node) {
                if (this.left_son.getParent() != this) {
                    //System.out.println(this.getKey() + " should be parent of " + this.right_son.getKey()
                     //       + " is son of " + this.right_son.getParent().getKey());
                }
            }
            if(right_son != Virtual_node){
                if(this.right_son.getParent() != this){
                   // System.out.println(this.getKey() + " should be parent of " + this.right_son.getKey()
                    //        + " is son of " + this.right_son.getParent().getKey());
                }
            }
        }//  Yotam Added for easy promotion/demotion count
        public int getHeight() {return this.height;}
        public int getBF(){return (this.right_son.getHeight()-this.left_son.getHeight());} //Yotam Switched these
        public void printNode(){
            if(father_node != null)System.out.println("printing node: " + key + "\nfather: " + father_node.getKey() + "father height: " + father_node.getHeight() + " left: " + left_son.getKey()
                    + "left height: " + left_son.getHeight() + " right: " + right_son.getKey() + "right height: " + right_son.getHeight());
            else{System.out.println("is root: " + key + " left: " + left_son.getKey()
                    + "left height: " + left_son.getHeight() + " right: " + right_son.getKey() + "right height: " + right_son.getHeight());}
        }
    }

}