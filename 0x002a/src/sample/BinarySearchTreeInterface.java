package sample;

public interface BinarySearchTreeInterface<E> {
    boolean add(E item);
    E search(E target);
    E delete(E target);
}
