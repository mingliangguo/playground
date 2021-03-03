package ming.sandbox;

import java.util.*;

public class FreqStack {
    private Map<Integer, Integer> map;
    private Map<Integer, Deque<Integer>> group;
    int maxFreq;
    public FreqStack() {
        this.map = new HashMap<>();
        this.group = new HashMap<>();
        this.maxFreq = 0;
    }

    public void push(int x) {
        int f = map.getOrDefault(x, 0) + 1;
        map.put(x, f);
        if (f > maxFreq) {
            this.maxFreq = f;
        }
        group.computeIfAbsent(f, k -> new ArrayDeque<>()).push(x);
    }

    public int pop() {
        int x = group.get(this.maxFreq).pop();
        map.put(x, map.get(x) - 1);
        if (group.get(maxFreq).size() == 0) {
            maxFreq--;
        }
        return x;
    }

    public static void main(String[] args) {
        FreqStack fs = new FreqStack();
        fs.push(5);
        fs.push(5);
        fs.push(7);
        fs.push(5);
        fs.push(7);
        System.out.println(fs.pop());;
        System.out.println(fs.pop());;
        System.out.println(fs.pop());;
        System.out.println(fs.pop());;
    }
}
