package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target.getPath())))) {
            for (Path path : sources) {
                zip.putNextEntry(new ZipEntry(path.toString()));
            }
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(target))) {
                zip.write(out.readAllBytes());
            }
            System.out.println("Zip success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public void packSingleFile(File source, File target) {
//        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
//            zip.putNextEntry(new ZipEntry(source.getPath()));
//            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
//                zip.write(out.readAllBytes());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public static void main(String[] args) throws IOException {
        ArgZip a = new ArgZip(args);
        SearchFiles searcher = new SearchFiles(p -> !p.toFile().getName().endsWith(a.exclude()));
        Files.walkFileTree(Paths.get(a.directory()), searcher);
        String path = Paths.get(a.directory()) + ".zip";
        File file = new File(path);
        new Zip().packFiles(searcher.getPaths(), file);
    }
}
