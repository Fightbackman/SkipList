/**
 * File: SkipListNode.java
 *
 */
public class SkipListNode {

    protected int key;
    protected SkipListNode [] next;

    /* Konstruktor */
    SkipListNode (int key, int height) {
        this.key    = key;
        this.next   = new SkipListNode [height+1];
    }
}
