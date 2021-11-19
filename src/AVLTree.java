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

    public AVLTree() {
        this.Virtual_node = new AVLNode(-1, "virtual");
        Virtual_node.setHeight(-1);

    }
    /**
     * public boolean empty()
     *
     * Returns true if and only if the tree is empty.
     *
     */
    public boolean empty() {
        return false; // to be replaced by student code
    }

    /**
     * public String search(int k)
     *
     * Returns the info of an item with key k if it exists in the tree.
     * otherwise, returns null.
     */
    public String search(int k)
    {
        return "searchDefaultString";  // to be replaced by student code
    }

    /**
     * public int insert(int k, String i)
     *
     * Inserts an item with key k and info i to the AVL tree.
     * The tree must remain valid, i.e. keep its invariants.
     * Returns the number of re-balancing operations, or 0 if no re-balancing operations were necessary.
     * A promotion/rotation counts as one re-balance operation, double-rotation is counted as 2.
     * Returns -1 if an item with key k already exists in the tree.
     */
    public int insert(int k, String i) {
        return 420;	// to be replaced by student code
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
        return 421;	// to be replaced by student code
    }

    /**
     * public String min()
     *
     * Returns the info of the item with the smallest key in the tree,
     * or null if the tree is empty.
     */
    public String min() {


        return "minDefaultString"; // to be replaced by student code
    }

    /**
     * public String max()
     *
     * Returns the info of the item with the largest key in the tree,
     * or null if the tree is empty.
     */
    public String max()
    {
        return "maxDefaultString"; // to be replaced by student code
    }

    /**
     * public int[] keysToArray()
     *
     * Returns a sorted array which contains all keys in the tree,
     * or an empty array if the tree is empty.
     */
    public int[] keysToArray()
    {
        return new int[33]; // to be replaced by student code
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
        return new String[55]; // to be replaced by student code
    }

    /**
     * public int size()
     *
     * Returns the number of nodes in the tree.
     */
    public int size()
    {
        return 422; // to be replaced by student code
    }

    /**
     * public int getRoot()
     *
     * Returns the root AVL node, or null if the tree is empty
     */
    public IAVLNode getRoot()
    {
        return null;
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


    public void Rebalance(IAVLNode node) {
        int BF = node.getBF();
        if (BF <= 1 | BF >= -1) { //either demotion/promotion needed and problem is fixed, or move up the tree
            node.adjustHeight();
            if (node.getParent().getBF() <= 1 | node.getParent().getBF() >= -1) { //checks parent is balanced if yes problem fixed
                return;
            } else if (node.getParent() != null) {//checks if root
                Rebalance(node.getParent());  // if not root pass problem to father
            }
            return;
        }

        int son_BF = 0;
        if (BF > 1) { //checks where is the deeper subtree
            son_BF = -node.getRight().getBF();
        } else if (BF < -1) {
            son_BF = node.getLeft().getBF();
        }
        if (son_BF == -1 | son_BF == 0) {
            Rotation(node, BF);
        } else if (son_BF == 1) {// decides if to do double rotation
            Double_Rotation(node, BF);
        }
        node.adjustHeight();
        if (node.getParent().getParent() != null) {
            Rebalance(node.getParent().getParent());
        }
    }



    public void Rotation(IAVLNode node, int BF){
        int node_height=node.getHeight();
        if (BF < -1){ // makes left father
            IAVLNode left=node.getLeft();
            IAVLNode father = node.getParent();
            IAVLNode left_right_son=left.getRight();
            left.setRight(node);
            node.setLeft(left_right_son);
            node.setParent(left);
            left_right_son.setParent(node);
            left.setParent(father);
            node.adjustSize();
            left.adjustSize();
        }
        else if (BF > 1){ //makes right father
            IAVLNode right=node.getRight();
            IAVLNode right_left_son=right.getLeft();
            IAVLNode father = node.getParent();
            right.setLeft(node);
            node.setRight(right_left_son);
            node.setParent(right);
            right_left_son.setParent(node);
            right.setParent(father);
            node.adjustSize();
            right.adjustSize();
        }

    }

    public void Double_Rotation(IAVLNode node,int BF){
        int node_height=node.getHeight();
        if (BF < -1){//makes left right father
            IAVLNode left_node=node.getLeft();
            Rotation(left_node,2);
            Rotation(node,-2);

        }
        else if (BF > 1){//makes right left father
            IAVLNode right_node=node.getRight();
            Rotation(right_node,-2);
            Rotation(node,2);
        }

    }


    public AVLTree[] split(int x)
    {
        return null;
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
    public int join(IAVLNode x, AVLTree t)
    {
        return -1;
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
        public int getBF();
        public void adjustSize();
        public int getSize();

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
            this.size=0;
            this.left_son = Virtual_node;
            this.right_son = Virtual_node;
        }

        public int getKey()
        {
            return this.key;
        }
        public String getValue()
        {
            return "getValueDefault"; // to be replaced by student code
        }
        public void setLeft(IAVLNode node)
        {
            return; // to be replaced by student code
        }
        public IAVLNode getLeft()
        {
            return null; // to be replaced by student code
        }
        public void setRight(IAVLNode node)
        {
            return; // to be replaced by student code
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
        public void setHeight(int height) {this.height=height;}
        public void adjustSize(){
            this.size = this.right_son.getSize() + this.left_son.getSize() + 1;
        }
        public int getSize(){
            return this.size;
        }
        public void adjustHeight(){this.height=Integer.max(this.right_son.getHeight(),this.left_son.getHeight())+1;}
        public int getHeight() {return this.height;}
        public int getBF(){return this.right_son.getHeight()-this.left_son.getHeight();}
    }

}

