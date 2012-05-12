package edu.bridgeport.mohammad;

public class DoubleNode<T> {
    private T data;
    private Node<T> next;
    private Node<T> prev;

    public Node() {}

    public Node(T data){
        this.data = data;
    }

    public T getData() {
        return data;
    }
    
    public void setPrev(Node<T> prev){
      this.prev = prev;
      prev.setNext(this);
    }
    
    public Node<T> getPrev(){
      return prev;
    }

    public void setNext(Node<T> next){
        this.next = next;
        next.setPrev(this);
    }

    public Node<T> getNext() {
        return next;
    }

}
