package pl.edu.pjwstk.simulator.models;

public class Compartment {
    boolean full;
    int size;

    public Compartment(int size) {
        this.full = false;
        this.size = size;
    }

    public boolean isFull() {
        return full;
    }

    public void setFull(boolean full) {
        this.full = full;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
