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

    public IAVLNode tree_position(int key, boolean is_insert){
        IAVLNode curr = root;
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
                curr=curr.getLeft();
            }
            else{
                if(is_insert){
                    curr.insertSizeUpdate();
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
        IAVLNode res=tree_position(k,false);
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
        IAVLNode father = tree_position(k,true);
        if(father.getKey() > k){
            father.setLeft(inserted);

        }
        if (father.getKey()<k){
            father.setRight(inserted);
        }
        inserted.setParent(father);
        int temp=father.getHeight();
        father.adjustHeight();
        int updated = father.getHeight();
        if(temp!=updated) {
            counter[0]+= Math.abs(temp-updated);
        }
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

        return 421;	// to be replaced by student code
    }

    /**
     * public String min()
     *
     * Returns the info of the item with the smallest key in the tree,
     * or null if the tree is empty.
     */
    public String min() {


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
        IAVLNode curr = this.min;
        int tree_size = this.root.getSize();
        int[] array = new int[tree_size];
        for(int i = 0; i < tree_size-1 ; i++){
            array[i] = curr.getKey();
            curr = successor(curr);
        }
        array[tree_size-1] = curr.getKey();

        return array;
    }

    public IAVLNode getMin(IAVLNode node){
        IAVLNode curr=node;
        while(curr.getLeft()!=Virtual_node){
            curr=curr.getLeft();
        }
        return curr;
    }

    public IAVLNode successor(IAVLNode node){
        if(node.getRight().getKey() != -1){
            return getMin(node.getRight());
        }
        IAVLNode y = node.getParent();
        while (y!=Virtual_node && node==y.getRight()){
            node=y;
            y=node.getParent();
        }
        return y;
    }

    public IAVLNode predecessor(IAVLNode node){
        if(node.getLeft().getKey() != -1){
            return getMax(node.getLeft());
        }
        IAVLNode y = node.getParent();
        while (y.getKey()!=-1&&node==y.getLeft()){
            node=y;
            y=node.getParent();
        }
        return y;
    }

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
        return new String[55]; // to be replaced by student code
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
        if(node.getParent() != null){
            int temp3=node.getParent().getHeight();
            node.getParent().adjustHeight();
            int updated3 = node.getParent().getHeight();
            if(temp3!=updated3) {
                counter[0]+= Math.abs(temp3-updated3);
            }
        }
        if (node.getParent()==null){
            node.adjustHeight(); //Idan added this
        }
        int BF = node.getBF();

        if (BF <= 1 && BF >= -1) { //either demotion/promotion needed and problem is fixed, or move up the
            if (node.getParent() == null)  { //checks if root
                return;
            }
            if(node.getParent().getBF() <= 1 && node.getParent().getBF() >= -1){//checks parent is balanced if yes problem fixed
                Rebalance(node.getParent(),counter); //Idan added this
            }
            if (node.getParent() != null) {//checks if root
                Rebalance(node.getParent(),counter);  // if not root pass problem to father
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
            Rotation(node, BF, counter);
            counter[0]+=1;
        } else if (son_BF == 1) {// decides if to do double rotation
            Double_Rotation(node, BF, counter);
            counter[0]+=3;
        }
        int temp=node.getHeight();
        node.adjustHeight();
        int updated = node.getHeight();
        if(temp!=updated) {
            counter[0]+= Math.abs(temp-updated);
        }
        if (node.getParent().getParent() != null) {
            Rebalance(node.getParent().getParent(),counter);
        }
    }

    public void Rotation(IAVLNode node, int BF, int[] counter){
        int node_height=node.getHeight();
        System.out.println("single rotate");
        if (BF < -1){ // makes left father
            IAVLNode left=node.getLeft();
            IAVLNode father = node.getParent();
            IAVLNode left_right_son=left.getRight();
            left.setRight(node);
            node.setLeft(left_right_son);
            node.setParent(left);
            left_right_son.setParent(node);
            left.setParent(father);
            if (father!=null){
                father.setLeft(left);
            }
            if (left.getParent()==null){
                this.root=left;
            }
            node.adjustHeight();
            node.adjustSize();
            left.adjustSize();
            int temp=left.getHeight();
            left.adjustHeight();
            int updated = left.getHeight();
            if(temp!=updated) {
                counter[0]+= Math.abs(temp-updated);
            }
        }
        else if (BF > 1){ //makes right father
            if (node.getKey()==3){
            }
            IAVLNode right=node.getRight();
            IAVLNode right_left_son=right.getLeft();
            IAVLNode father = node.getParent();
            right.setLeft(node);
            node.setRight(right_left_son);
            node.setParent(right);
            right_left_son.setParent(node);
            right.setParent(father);
            if (father!=null){
                father.setRight(right);
            }
            if (right.getParent()==null){
                this.root=right;
            }
            node.adjustHeight();
            node.adjustSize();
            right.adjustSize();
            int temp=right.getHeight();
            right.adjustHeight();
            int updated = right.getHeight();
            if(temp!=updated) {
                counter[0] += Math.abs(temp - updated);
            }
        }

    }

    public void Double_Rotation(IAVLNode node,int BF, int[] counter){
        int node_height=node.getHeight();
        System.out.println("double rotation");
        if (BF < -1){//makes left right father
            IAVLNode left_node=node.getLeft();
            Rotation(left_node,2,counter);
            Rotation(node,-2,counter);
            node.getParent().getLeft().adjustHeight();
        }
        else if (BF > 1){//makes right left father
            IAVLNode right_node=node.getRight();
            Rotation(right_node,-2,counter);
            Rotation(node,2,counter);
            node.getParent().getRight().adjustHeight();
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
        public void insertSizeUpdate();

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
        public void setHeight(int height) {this.height=height;}
        public void adjustSize(){
            this.size = this.right_son.getSize() + this.left_son.getSize() + 1;
        }
        public int getSize(){
            if (this==Virtual_node){
                return 0;
            }
            return this.size;
        }
        public void adjustHeight(){this.height=Integer.max(this.right_son.getHeight(),this.left_son.getHeight())+1;}
        public int getHeight() {return this.height;}
        public int getBF(){return this.right_son.getHeight()-this.left_son.getHeight();}
    }

}

