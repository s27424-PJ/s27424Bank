package org.example.s27424bank;

public class Client {
    private int id;
    private double balans;

    public Client(int id, double balans) {
        this.id = id;
        this.balans = balans;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalans() {
        return balans;
    }

    public void setBalans(double balans) {
        this.balans = balans;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", balans=" + balans +
                '}';
    }
}
