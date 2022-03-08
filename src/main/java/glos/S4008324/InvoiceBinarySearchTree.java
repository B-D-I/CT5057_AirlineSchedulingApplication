package glos.S4008324;

public class InvoiceBinarySearchTree{

    private int invoiceInfo;
    private InvoiceBinarySearchTree leftNode;
    private InvoiceBinarySearchTree rightNode;

    public InvoiceBinarySearchTree(int invoiceInfo) {
        this.rightNode = null;
        this.leftNode = null;
        this.invoiceInfo = invoiceInfo;
    }

    // tree root node
    InvoiceBinarySearchTree root;

    // constructor for BST (initial empty tree)
    InvoiceBinarySearchTree() {
        root = null;
    }

    // delete node from BS tree
    void deleteInvoiceCharge(int invoiceInfo) {
        root = delete_Recursive(root, invoiceInfo);
    }

    // recursive delete method
    InvoiceBinarySearchTree delete_Recursive(InvoiceBinarySearchTree root, int invoiceInfo) {
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

    int minValue(InvoiceBinarySearchTree root) {
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
    void insert(int invoiceInfo) {
        root = insert_Recursive(root, invoiceInfo);
    }

    // recursive insert method
    InvoiceBinarySearchTree insert_Recursive(InvoiceBinarySearchTree root, int invoiceInfo) {
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
    void inorder() {
        inorder_Recursive(root);
    }

    // recursively traverse the tree
    void inorder_Recursive(InvoiceBinarySearchTree root) {
        if (root != null) {
            inorder_Recursive(root.leftNode);
            System.out.println(root.invoiceInfo + " ");
            inorder_Recursive(root.rightNode);
        }
    }

    boolean search(int invoiceInfo) {
        root = search_Recursive(root, invoiceInfo);
        if (root != null) {
            return true;
        } else {
            return false;
        }
    }

    // recursive insert method
    InvoiceBinarySearchTree search_Recursive(InvoiceBinarySearchTree root, int invoiceInfo) {
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


