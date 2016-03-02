package com.eurochemix.webapp.Examples;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.Stream;

/**
 * Created by Ilya on 28.02.2016.
 */
public class FileMain {

    public static void main(String[] args) throws IOException {

        long time;
        long durationTime;

        // Buffered reader method
        time = System.currentTimeMillis();
        System.out.println("\nBy buffered reader: start time" + time + "\n");
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("c:\\aaaa.nc"), StandardCharsets.UTF_8));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        durationTime = System.currentTimeMillis() - time;
        System.out.println("\n" + durationTime + " ms by buffered reader");

        // Stream method
        time = System.currentTimeMillis();
        System.out.println("\nBy stream iterator:start time" + time + "\n");
        Stream<String> lines = Files.lines(Paths.get("c:\\aaaa.nc"), StandardCharsets.UTF_8);
        Iterator<String> it = lines.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        durationTime = System.currentTimeMillis() - time;
        System.out.println("\n" + durationTime + " ms by stream iterator");


        // Lambda method
        time = System.currentTimeMillis();
        System.out.println("\nBy stream lambda:start time" + time + "\n");
        Files.lines(Paths.get("c:\\aaaa.nc"), StandardCharsets.UTF_8).forEach(System.out::println);
        durationTime = System.currentTimeMillis() - time;
        System.out.println("\n" + durationTime + " ms by stream lambda");



    }


}
