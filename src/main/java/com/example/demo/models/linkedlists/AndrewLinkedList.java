package com.example.demo.models.linkedlists;

import minilabz.Pet;
import java.util.ArrayList;

public class AndrewLinkedList {

    private static ArrayList<Pet> al = new ArrayList<>();
    private static LinkedList headNode;


    public static void init() {
        al.clear();
        Pet cat = new Pet("Winnie", "Pooh");
        Pet mouse = new Pet("Jerry", "Mouse");
        Pet bird = new Pet("Tweety", "Bird");
        Pet bear = new Pet("Grumpy", "Cat");
        Pet rabbit = new Pet("Rabbit", "Rabbit");
        al.add(cat);
        al.add(mouse);
        al.add(bird);
        al.add(bear);
        al.add(rabbit);
        LinkedList node1 = new LinkedList(cat, null);
        LinkedList node2 = new LinkedList(mouse, node1);
        LinkedList node3 = new LinkedList(bird, node2);
        LinkedList node4 = new LinkedList(bear, node3);
        LinkedList node5 = new LinkedList(rabbit, node4);
        node1.setNextNode(node2);
        node2.setNextNode(node3);
        node3.setNextNode(node4);
        node4.setNextNode(node5);
        node5.setNextNode(null);
        headNode = node1;
    }

    public static void llInserts() {
        AndrewLinkedListInsert(0, new LinkedList(new Pet("Mickey", "Mouse"), null));
        AndrewLinkedListInsert(2, new LinkedList(new Pet("Tony", "Tiger"), null));
        AndrewLinkedListInsert(new LinkedList(new Pet("Judy", "Rabbit"), null));
    }

    public static void llDeletes() {
        AndrewLinkedListDelete(0);
        AndrewLinkedListDelete(3);
        AndrewLinkedListDelete(getSize());
    }

    public static void alInserts() {
        al.add(0, new Pet("Mickey", "Mouse"));
        al.add(2, new Pet("Tony", "Tiger"));
        al.add(new Pet("Judy", "Rabbit"));
    }

    public static void alDeletes() {
        al.remove(0);
        al.remove(2);
        al.remove(al.size() - 1);
    }

    public static void alSort() {
        Pet first = al.get(0);
        int firstIndex = 0;
        for (int i = 0; i < al.size(); i++) {
            first = al.get(i);
            firstIndex = i;
            for (int j = i + 1; j < al.size(); j++) {
                if (first.toString().compareTo(al.get(j).toString()) > 0) {
                    first = al.get(j);
                    firstIndex = j;
                }
            }
            Pet temp = al.get(i);
            al.set(i, al.get(firstIndex));
            al.set(firstIndex, temp);
        }
    }

    public static void AndrewLinkedListInsert(int index, LinkedList node) {
        LinkedList currentNode = headNode;
        for (int i = 0; i < index; i++) {
            if (currentNode.getNext() == null) {
                LinkedList newNode = new LinkedList(node.getObject(), currentNode);
                currentNode.setNextNode(newNode);
                newNode.setNextNode(null);
                break;
            } else {
                currentNode = currentNode.getNext();
            }
        }
        LinkedList newNode;
        if (index > 0) {
            newNode = new LinkedList(node.getObject(), currentNode.getPrevious());
            currentNode.getPrevious().setNextNode(newNode);
        } else {
            newNode = new LinkedList(node.getObject(), null);
            headNode = newNode;
        }
        currentNode.setPrevNode(newNode);
        newNode.setNextNode(currentNode);
    }

    public static void AndrewLinkedListInsert(LinkedList node) {
        LinkedList currentNode = headNode;
        for (int i = 0; i >= 0; i++) {
            if (currentNode.getNext() == null) {
                LinkedList newNode = new LinkedList(node.getObject(), currentNode);
                currentNode.setNextNode(newNode);
                newNode.setNextNode(null);
                break;
            } else {
                currentNode = currentNode.getNext();
            }
        }
    }

    public static void AndrewLinkedListDelete(int index) {
        LinkedList currentNode = headNode;
        for (int i = 0; i < index; i++) {
            if(currentNode.getNext() == null) {
                System.out.println("Out of Bounds Error");
                break;
            } else {
                currentNode = currentNode.getNext();
            }
        }
        if (currentNode.getPrevious() != null) {
            currentNode.getPrevious().setNextNode(currentNode.getNext());
        } else {
            headNode = currentNode.getNext();
        }
        if (currentNode.getNext() != null) {
            currentNode.getNext().setPrevNode(currentNode.getPrevious());
        }
    }

    public static void llSort() {
        int minIndex;
        LinkedList min;
        LinkedList currentNode;
        for (int j = 0; j < getSize(); j++) {

            currentNode = headNode;

            for (int i = 0; i < j; i++) {
                currentNode = currentNode.getNext();
            }

            minIndex = j;
            min = currentNode;

            for (int i = j; i < getSize(); i++) {
                if (currentNode.getObject().toString().compareTo(min.getObject().toString()) < 0) {
                    minIndex = i;
                    min = currentNode;
                }
                currentNode = currentNode.getNext();
            }

            AndrewLinkedListInsert(j, min);
            AndrewLinkedListDelete(minIndex + 1);
        }
    }

    public static void setHeadNode(LinkedList node) {
        LinkedList currentNode = node;
        while (currentNode.getPrevious() != null) {
            currentNode = currentNode.getPrevious();
        }
        headNode = currentNode;
    }

    public static int getSize() {
        LinkedList currentNode = headNode;
        int size = 1;
        while (currentNode.getNext() != null) {
            size++;
            currentNode = currentNode.getNext();
        }
        return size;
    }

    public static String LLToString() {
        String LLString = headNode.getObject().toString();
        LinkedList currentNode = headNode;
        while (currentNode.getNext() != null) {
            currentNode = currentNode.getNext();
            LLString += ", " + currentNode.getObject().toString();
        }
        return LLString;
    }

    public static String ALToString() {
        String ALString = al.get(0).toString();
        int i = 1;
        while (i < al.size()) {
            ALString += ", " + al.get(i).toString();
            i++;
        }
        return ALString;
    }

}
