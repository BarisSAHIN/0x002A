package sample;

public class BinarySearchTree<E extends Comparable<E>> implements BinarySearchTreeInterface<E> {
    private Node<E> root;
    private boolean addReturn;

    private static class Node<E extends Comparable<E>>{
        private E data;
        private Node<E> left;
        private Node<E> right;

        Node(){
            this.data = null;
            this.left = null;
            this.right = null;
        }

        Node(E data){
            this.data = data;
            this.left = null;
            this.right = null;
        }

        Node(Node<E> n){
            this.data = n.data;
            this.left = n.left;
            this.right = n.right;
        }
    }

    BinarySearchTree(){
        this.root = null;
    }

    BinarySearchTree(Node<E> root){
        this.root = root;
    }

    BinarySearchTree(BinarySearchTree<E> searchTree){
        this.root = searchTree.root;
    }




    @Override
    public boolean add(E item){
        root = add(root, item);
        return addReturn;
    }

    private Node<E> add(Node<E> localRoot, E item){
        if(localRoot == null){
            addReturn = true;
            return new Node<E>(item);
        }
        else if(item.compareTo(localRoot.data) == 0){
            addReturn = false;
            return localRoot;
        }
        else if(item.compareTo(localRoot.data) < 0){
            localRoot.left = add(localRoot.left, item);
            return localRoot;
        }
        else{
            localRoot.right = add(localRoot.right, item);
            return localRoot;
        }
    }

    @Override
    public E search(E target){
        return search(root, target);
    }

    private E search(Node<E> localRoot, E target){
        if(localRoot == null)
            return null;
        int compareResult = target.compareTo(localRoot.data);
        if(compareResult == 0)
            return localRoot.data;
        else {
            search(localRoot.left, target);
            search(localRoot.right, target);
        }
    }


    @Override
    public E delete(E target){
        return delete(root, target).data;
    }

    public Node<E> delete(Node<E> localRoot, E target){
        Node<E> localNode = localRoot;
        if(root == null)
            return localNode;

        if(target.compareTo(localRoot.data) < 0)
            localNode.left = delete(localNode.left, target);
        else if(target.compareTo(localRoot.data) > 0)
            localNode.right = delete(localNode.right, target);
        else{
            if(isLeaf(localRoot)){
                localNode = null;
            }
            else if(localRoot.right == null)
                localNode = localNode.left;
            else if(localRoot.left == null)
                localNode = localNode.right;
            else{
                Node<E> temp = minFromRight(localNode.right);
                localNode.data = temp.data;
                localNode.right = delete(localNode.right, temp.data);
            }
        }
        return localNode;
    }

    public boolean isLeaf(Node <E> node){
        if(node.left == null && node.right == null)
            return true;
        else
            return false;
    }

    private Node<E> minFromRight(Node<E> node){
        while(node.left != null)
            node = node.left;

        return node;
    }

}
