package glos.S4008324;

public class InvoiceBinarySearchTree implements Database {

    private int invoiceID;
    private InvoiceBinarySearchTree leftNode;
    private InvoiceBinarySearchTree rightNode;

    public InvoiceBinarySearchTree(int invoiceID) {
        this.rightNode = null;
        this.leftNode = null;
        this.invoiceID = invoiceID;
    }

    // tree root node
    InvoiceBinarySearchTree root;

    // constructor for BST (initial empty tree)
    InvoiceBinarySearchTree() {
        root = null;
    }

    // delete node from BS tree
    void deleteInvoiceID(int invoiceID) {
        root = delete_Recursive(root, invoiceID);
    }

    // recursive delete method
    InvoiceBinarySearchTree delete_Recursive(InvoiceBinarySearchTree root, int invoiceID) {
        // empty tree
        if (root == null) return root;
        // traverse tree
        if (invoiceID < root.invoiceID) {
            root.leftNode = delete_Recursive(root.leftNode, invoiceID);
        } else if (invoiceID > root.invoiceID) {
            root.rightNode = delete_Recursive(root.rightNode, invoiceID);
        } else {
            // node only has one child
            if (root.leftNode == null) {
                return root.rightNode;
            } else if (root.rightNode == null) {
                return root.leftNode;
            }
            // node has two children: get in-order child (min-value on right subtree)
            root.invoiceID = minValue(root.rightNode);

            // delete the in-order child
            root.rightNode = delete_Recursive(root.rightNode, root.invoiceID);
        }
        return root;
    }

    int minValue(InvoiceBinarySearchTree root) {
        // initially minVal = root
        int minval = root.invoiceID;
        // find minval
        while (root.leftNode != null) {
            minval = root.leftNode.invoiceID;
            root = root.leftNode;
        }
        return minval;
    }

    // insert node in BS tree
    void insert(int invoiceID) {
        root = insert_Recursive(root, invoiceID);
    }

    // recursive insert method
    InvoiceBinarySearchTree insert_Recursive(InvoiceBinarySearchTree root, int invoiceID) {
        // tree is empty
        if (root == null) {
            root = new InvoiceBinarySearchTree(invoiceID);
            return root;
        }
        // traverse the tree
        if (invoiceID < root.invoiceID) {
            // insert in left subtree
            root.leftNode = insert_Recursive(root.leftNode, invoiceID);
        } else if (invoiceID > root.invoiceID) {
            // insert in right subtree
            root.rightNode = insert_Recursive(root.rightNode, invoiceID);
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
            System.out.println(root.invoiceID + " ");
            inorder_Recursive(root.rightNode);
        }
    }

    boolean search(int invoiceID) {
        root = search_Recursive(root, invoiceID);
        if (root != null) {
            return true;
        } else {
            return false;
        }
    }

    // recursive insert method
    InvoiceBinarySearchTree search_Recursive(InvoiceBinarySearchTree root, int invoiceID) {
        // base cases: root is null or invoiceID is present at root
        if (root == null || root.invoiceID == invoiceID) {
            return root;
        }
        if (root.invoiceID > invoiceID) {
            // value is greater than root invoiceID
            return search_Recursive(root.leftNode, invoiceID);
            // value is less than root invoiceID
        }
        return search_Recursive(root.rightNode, invoiceID);
    }
}


