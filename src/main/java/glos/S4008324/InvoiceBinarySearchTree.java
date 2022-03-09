package glos.S4008324;

/**
 * Binary Search Tree: This provides the aforementioned data structure, to be utilised for containing invoice data.
 * This removes the requirement to later sort the data, as the tree sorts the integers when inserted.
 * This class also contains deletion, and searching methods.
 */
final class InvoiceBinarySearchTree{

    private int invoiceInfo;
    private InvoiceBinarySearchTree leftNode;
    private InvoiceBinarySearchTree rightNode;

    /**
     * Constructor of Binary Search Tree
     * @param invoiceInfo: The determined invoice information to be inserted into the tree
     */
    public InvoiceBinarySearchTree(int invoiceInfo) {
        this.rightNode = null;
        this.leftNode = null;
        this.invoiceInfo = invoiceInfo;
    }
    // tree root node
    private InvoiceBinarySearchTree root;

    // constructor for BST (initial empty tree)
    public InvoiceBinarySearchTree() {
        root = null;
    }

    // delete node from BS tree
    public void deleteInvoiceCharge(int invoiceInfo) {
        root = delete_Recursive(root, invoiceInfo);
    }
    // recursive delete method
    private InvoiceBinarySearchTree delete_Recursive(InvoiceBinarySearchTree root, int invoiceInfo) {
        // empty tree
        if (root == null) return root;
        // traverse tree
        if (invoiceInfo < root.invoiceInfo) {
            root.leftNode = delete_Recursive(root.leftNode, invoiceInfo);
        } else if (invoiceInfo > root.invoiceInfo) {
            root.rightNode = delete_Recursive(root.rightNode, invoiceInfo);
        } else {
            // node only has one child
            if (root.leftNode == null) {
                return root.rightNode;
            } else if (root.rightNode == null) {
                return root.leftNode;
            }
            // node has two children: get in-order child (min-value on right subtree)
            root.invoiceInfo = minValue(root.rightNode);

            // delete the in-order child
            root.rightNode = delete_Recursive(root.rightNode, root.invoiceInfo);
        }
        return root;
    }
    private int minValue(InvoiceBinarySearchTree root) {
        // initially minVal = root
        int minval = root.invoiceInfo;
        // find minval
        while (root.leftNode != null) {
            minval = root.leftNode.invoiceInfo;
            root = root.leftNode;
        }
        return minval;
    }
    // insert node in BS tree
    public void insert(int invoiceInfo) {
        root = insert_Recursive(root, invoiceInfo);
    }
    // recursive insert method
    private InvoiceBinarySearchTree insert_Recursive(InvoiceBinarySearchTree root, int invoiceInfo) {
        // tree is empty
        if (root == null) {
            root = new InvoiceBinarySearchTree(invoiceInfo);
            return root;
        }
        // traverse the tree
        if (invoiceInfo < root.invoiceInfo) {
            // insert in left subtree
            root.leftNode = insert_Recursive(root.leftNode, invoiceInfo);
        } else if (invoiceInfo > root.invoiceInfo) {
            // insert in right subtree
            root.rightNode = insert_Recursive(root.rightNode, invoiceInfo);
        }
        return root;
    }
    // inorder traversal of BS tree
    public void inorder() {
        inorder_Recursive(root);
    }
    // recursively traverse the tree
    private void inorder_Recursive(InvoiceBinarySearchTree root) {
        if (root != null) {
            inorder_Recursive(root.leftNode);
            System.out.println(root.invoiceInfo + " ");
            inorder_Recursive(root.rightNode);
        }
    }
    /**
     * This search method is utilised to assess whether an invoice number is contained in the tree
     * @param invoiceInfo: The invoice data (integer), to be searched
     * @return: Boolean value; is the data contained
     */
    public boolean search(int invoiceInfo) {
        root = search_Recursive(root, invoiceInfo);
        return root != null;
    }
    /**
     * This method recursively searches the Binary Search Tree. If value is not null or root, then the tree traverses
     * down to each subtree until value is found; right child has a value greater than or equal, left child has
     * value less than or equal.
     * @param root: Root node value
     * @param invoiceInfo: The invoice data (integer), to be searched
     * @return: Searched value
     */
    // recursive search method
    private InvoiceBinarySearchTree search_Recursive(InvoiceBinarySearchTree root, int invoiceInfo) {
        // base cases: root is null or invoiceInfo is present at root
        if (root == null || root.invoiceInfo == invoiceInfo) {
            return root;
        }
        if (root.invoiceInfo > invoiceInfo) {
            // value is greater than root invoiceInfo
            return search_Recursive(root.leftNode, invoiceInfo);
            // value is less than root invoiceInfo
        }
        return search_Recursive(root.rightNode, invoiceInfo);
    }
}


