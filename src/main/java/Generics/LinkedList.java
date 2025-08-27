package Generics;

class ListNode<T> {
    T data;
    ListNode<T> next;

    public ListNode(T data) {
        this.data = data;
        this.next = null;
    }
}

public class LinkedList<T> {

    ListNode<T> head;
    public LinkedList(){
        head = null;
    }
    public void add(T data) {
    ListNode<T> newNode = new ListNode<>(data);
        if(head == null){
            head = newNode;
        }else{
            ListNode<T> curr = head;
            while(curr.next != null){
                curr = curr.next;
            }
            curr.next = newNode;
        }
    }

    public void display() {
        ListNode<T> curr = head;
        while(curr != null){
            if(curr.next == null){
                System.out.print(curr.data);
            }else{
                System.out.print(curr.data + " ");
            }
        }
    }

    public boolean isEmpty() {
        return head == null;
    }

}
