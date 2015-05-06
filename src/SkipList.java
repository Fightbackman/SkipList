public class SkipList {
  /* Implementiert eine Skip-Liste ohne vorgegebene maximale Listenhoehe */

    private SkipListNode head;   // Kopf der Liste
    private SkipListNode tail;   // Ende der Liste
    private int height;          // Aktuelle Hoehe der Liste
    public int maxHeight;        // Maximale bisherige Hoehe der Liste

    private SkipListNode[] update;


    /* Konstruktor */
    public SkipList() {
        height = 0;
        maxHeight = 0;

        head = new SkipListNode(Integer.MIN_VALUE, 0);
        tail = new SkipListNode(Integer.MAX_VALUE, 0);

        head.next[0] = tail;

        update = new SkipListNode[height + 1];
    }


    private int flipcoin() {
    /* liefert eine zufaellige Hoehe zwischen 0 und maxHeight */
        int height = 0;
        while (RandomNumber.randint() % 2 == 1) {
            height++;
        }
        return height;
    }


    public SkipListNode search(int key) {
    /* liefert den Knoten p der Liste mit p.key = key, falls es ihn gibt,
       und  null sonst */
        SkipListNode p = head;
        for (int i = height; i >= 0; i--) {
      /* folge den Niveau-i Zeigern */
            while (p.next[i].key < key) {
                p = p.next[i];
            }
        }

    /* p.key < key <= p.next[0].key */

        p = p.next[0];
        if (p.key == key && p != tail) return p;
        else return null;
    }

    public void insert(int key) {
    /* fuegt den Schluessel key in die Skip-Liste ein */

        SkipListNode p = head;
        for (int i = height; i >= 0; i--) {
            while (p.next[i].key < key) {
                p = p.next[i];
            }
            update[i] = p;
        }

        p = p.next[0];
        if (p.key == key) {
            System.out.println("Schluessel " + key + " bereits vorhanden.");
            return; // Schluessel bereits vorhanden
        }

        int newheight = flipcoin();
        if (newheight > maxHeight) {
      /* neues Kopfelement schaffen */
            SkipListNode oldHead = head;
            head = new SkipListNode(Integer.MIN_VALUE, newheight);
            for (int i = 0; i <= maxHeight; i++)
                head.next[i] = oldHead.next[i];
            for (int i = maxHeight + 1; i <= newheight; i++)
                head.next[i] = tail;

            maxHeight = newheight;

      /* die oberen Zeiger von update[i] auf das neue Kopfelement setzen */
            for (int i = height; i >= 0 && update[i] == oldHead; i--)
                update[i] = head;

      /* neues Hilfsarray update schaffen */
            SkipListNode[] oldUpdate = update;
            update = new SkipListNode[newheight + 1];
            for (int i = 0; i <= height; i++)
                update[i] = oldUpdate[i];
        }

        if (newheight > height) {
      /* aktuelle Hoehe der Skip-Liste anpassen */
            for (int i = height + 1; i <= newheight; i++)
                update[i] = head;
            height = newheight;
        }

        p = new SkipListNode(key, newheight);

        for (int i = 0; i <= newheight; i++) {
      /* fuege p in Niveau i nach update[i] ein */
            p.next[i] = update[i].next[i];
            update[i].next[i] = p;
        }
    }
}