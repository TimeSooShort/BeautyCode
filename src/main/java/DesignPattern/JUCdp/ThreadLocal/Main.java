package DesignPattern.JUCdp.ThreadLocal;

public class Main {
    public static void main(String[] args) {
        new ClientThread("Zhao").start();
        new ClientThread("Qian").start();
        new ClientThread("Sun").start();
    }
}
