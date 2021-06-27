package com.company;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class User {

    private static class TestHandler implements HttpHandler{

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            System.out.println("Hello World!");
        }
    }

    public static void main(String[] args) throws IOException {
        HttpServer httpServer = HttpServer.create(new InetSocketAddress("localhost", 8080), 1);
        httpServer.createContext("/test", new TestHandler());
        httpServer.start();
    }

    private int id;

    @Value("Test")
    private String name;

    @Value("test")
    private String login;

    private String password;

    @Autowired
    private Cat cat;

    @Autowired
    private Dog dog;

    public User(@Value("22") Integer id) {//newInstance("Test", "test")
        this.id = id;
    }

    public void sayHello(){
        System.out.println("Hello " + name);
    }

    @Init
    public void init(){
        System.out.println("Init");
    }

    @Destroy
    public void destroy(){
        System.out.println("Destroy");
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", cat=" + cat +
                ", dog=" + dog +
                '}';
    }
}
