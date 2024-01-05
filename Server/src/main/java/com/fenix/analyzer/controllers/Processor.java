package com.fenix.analyzer.controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

public class Processor implements Runnable{
    private final static String fileName = "Server/src/main/resources/database.properties";
    public static int nThreads = 0;
    static {
        FileInputStream fis;
        Properties property = new Properties();

        try {
            fis = new FileInputStream(fileName);
            property.load(fis);

            nThreads = Integer.parseInt(property.getProperty("nThreads"));
        } catch (IOException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
        }
    }

    BlockingDeque<String> BLOCKING_DEQUEUE_LINKS = new LinkedBlockingDeque<>();
    ExecutorService executorService = Executors.newFixedThreadPool(nThreads);
    Task task;

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(100); // 86400000
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if(!BLOCKING_DEQUEUE_LINKS.isEmpty()){
                if(BLOCKING_DEQUEUE_LINKS.size() > nThreads){
                    int count = 0;
                    while (count < nThreads){
                        task = new Task(BLOCKING_DEQUEUE_LINKS.poll());
                        executorService.submit(task);
                        count++;
                    }
                }
                else{
                    task = new Task(BLOCKING_DEQUEUE_LINKS.poll());
                    executorService.submit(task);
                }
            }
        }
    }
    public void addLink(String link){
        BLOCKING_DEQUEUE_LINKS.add(link);
    }
}
