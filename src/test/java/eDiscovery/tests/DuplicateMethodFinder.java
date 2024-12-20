package eDiscovery.tests;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
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

        // Карты для хранения методов и классов по именам
        Map<String, List<String>> globalMethodMap = new HashMap<>();
        Map<String, List<String>> globalClassMap = new HashMap<>();

        JavaParser parser = new JavaParser();
        for (File file : javaFiles) {
            try {
                findMethodsInFile(parser, file, globalMethodMap);
                findClassesInFile(parser, file, globalClassMap);
            } catch (FileNotFoundException e) {
                System.err.println("Could not read file: " + file.getAbsolutePath());
            }
        }

        // Проверяем на дубликаты методов
        projectHasDuplicates |= checkForDuplicates("Methods", globalMethodMap);

        // Проверяем на дубликаты классов
        projectHasDuplicates |= checkForDuplicates("Classes", globalClassMap);

        // Выводим итоговое сообщение для всего проекта
        if (!projectHasDuplicates) {
            System.out.println(Ansi.ansi().fg(Ansi.Color.GREEN).a("No duplicates found in the project.").reset());
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

            if (methodName.equalsIgnoreCase("setUp")){
                return;
            }

            String location = file.getName() + " - " + method.getDeclarationAsString();
            globalMethodMap.computeIfAbsent(methodName, k -> new ArrayList<>()).add(location);
        });
    }

    private static void findClassesInFile(JavaParser parser, File file, Map<String, List<String>> globalClassMap) throws FileNotFoundException {
        CompilationUnit compilationUnit = parser.parse(file).getResult().orElseThrow(() ->
                new IllegalArgumentException("Failed to parse file: " + file.getAbsolutePath())
        );

        // Ищем все классы и интерфейсы в файле
        compilationUnit.findAll(ClassOrInterfaceDeclaration.class).forEach(cls -> {
            String className = cls.getNameAsString();
            String location = file.getName() + " - Class: " + className;
            globalClassMap.computeIfAbsent(className, k -> new ArrayList<>()).add(location);
        });
    }

    private static boolean checkForDuplicates(String type, Map<String, List<String>> globalMap) {
        boolean hasDuplicates = false;
        for (Map.Entry<String, List<String>> entry : globalMap.entrySet()) {
            if (entry.getValue().size() > 1) {
                if (!hasDuplicates) {
                    System.err.println(type + " duplicates were found!");
                }
                hasDuplicates = true;
                System.out.println(type + " name: " + entry.getKey());
                entry.getValue().forEach(location -> System.out.println("  - " + location));
            }
        }
        return hasDuplicates;
    }
}