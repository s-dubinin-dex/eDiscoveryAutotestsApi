package eDiscovery.tests;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import org.fusesource.jansi.Ansi;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class DuplicateMethodFinder {

    //TODO: Сделать отдельный тестовый класс, который будет дёргать метод отсюда, и проверять, нет ли дубликотов. Обернуть в assert. Сделать return projectHasDuplicates, возможно в BeforeAll TestBase

    public static void main(String[] args) {
        // Укажите путь к папке src/main/java
        String sourcePath = "src/test/java/eDiscovery/tests";
//        String sourcePath = "src";

        File root = new File(sourcePath);
        if (!root.exists()) {
            System.err.println("Path does not exist: " + sourcePath);
            return;
        }

        // Рекурсивно обходим папки и ищем Java-файлы
        List<File> javaFiles = new ArrayList<>();
        findJavaFiles(root.toPath(), javaFiles);

        // Общий флаг для проекта
        boolean projectHasDuplicates = false;

        // Карта для хранения методов по именам
        Map<String, List<String>> globalMethodMap = new HashMap<>();

        JavaParser parser = new JavaParser();
        for (File file : javaFiles) {
            try {
                findMethodsInFile(parser, file, globalMethodMap);
            } catch (FileNotFoundException e) {
                System.err.println("Could not read file: " + file.getAbsolutePath());
            }
        }

        // Проверяем на дубликаты в глобальной карте
        for (Map.Entry<String, List<String>> entry : globalMethodMap.entrySet()) {
            if (entry.getValue().size() > 1) {
                if (!projectHasDuplicates) {
                    System.err.println("Duplicates were found!");
                }
                projectHasDuplicates = true;
                System.out.println("Method name: " + entry.getKey());
                entry.getValue().forEach(location -> System.out.println("  - " + location));
            }
        }

        // Выводим итоговое сообщение для всего проекта
        if (!projectHasDuplicates) {
            System.out.println(Ansi.ansi().fg(Ansi.Color.GREEN).a("No duplicate methods found in the project.").reset());
        } else {
            System.err.println("Duplicates were found!");
        }

    }

    private static void findJavaFiles(Path directory, List<File> javaFiles) {
        try {
            Files.walk(directory)
                    .filter(path -> Files.isRegularFile(path) && path.toString().endsWith(".java"))
                    .forEach(path -> javaFiles.add(path.toFile()));
        } catch (Exception e) {
            System.err.println("Error walking through directory: " + e.getMessage());
        }
    }

    private static void findMethodsInFile(JavaParser parser, File file, Map<String, List<String>> globalMethodMap) throws FileNotFoundException {
        CompilationUnit compilationUnit = parser.parse(file).getResult().orElseThrow(() ->
                new IllegalArgumentException("Failed to parse file: " + file.getAbsolutePath())
        );

        // Ищем все методы в файле
        compilationUnit.findAll(MethodDeclaration.class).forEach(method -> {
            String methodName = method.getNameAsString();
            String location = file.getName() + " - " + method.getDeclarationAsString();
            globalMethodMap.computeIfAbsent(methodName, k -> new ArrayList<>()).add(location);
        });
    }
}
