package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Chat {

    public static void main(String[] args) {
        Chat chat = new Chat();
        StringBuilder sb = new StringBuilder();
        Scanner scanner = new Scanner(System.in);
        File path = new File("C:\\projects\\job4j_design\\chapter_002\\src\\main\\java\\ru\\job4j\\io\\words.txt");
        while (scanner.hasNext()) {
            sb.append(scanner.nextLine());
            if (sb.toString().equals("end")) {
                break;
            }
            if (sb.toString().equals("stop")) {

            }
            System.out.println(random(chat.getFromFile(path)));
            sb.setLength(0);
        }
    }

    private static String random(List<String> list) {
        Random random = new Random();
        return list.get(random.nextInt(list.size()));
    }
    //open file and return list of lines
    public List<String> getFromFile(File path) {
        List<String> list = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            list = br.lines().collect(Collectors.toList());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return list;
    }
}
