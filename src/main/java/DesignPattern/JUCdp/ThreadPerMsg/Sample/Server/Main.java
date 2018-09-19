package DesignPattern.JUCdp.ThreadPerMsg.Sample.Server;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            new MiniServer2(8888).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
