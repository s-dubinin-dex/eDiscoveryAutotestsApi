package eDiscovery.data;

import com.github.javafaker.Faker;
import java.util.List;

public class DataGeneratorCommon {

    public static Faker faker = new Faker();

    /*
     * Valid Data
     * */

    public static List<String> getValidNames(){
        return List.of(
                faker.regexify("[a-zA-Zа-яА-Я]{1}"),    // Строка из 1 символа
                        faker.regexify("[a-z]{256}"),           // Строка из 256 символов
                        faker.regexify("[а-я]{25}"),            // Строка из русских символов в нижней раскладке
                        faker.regexify("[А-Я]{25}"),            // Строка из русских символов в верхней раскладке
                        faker.regexify("[А-Яа-я]{25}"),         // Строка из русских символов в смешанной раскладке
                        faker.regexify("[a-z]{25}"),            // Строка из английских символов в нижней раскладке
                        faker.regexify("[A-Z]{25}"),            // Строка из английских символов в верхней раскладке
                        faker.regexify("[A-Za-z]{25}"),         // Строка из английских символов в смешанной раскладке
                        faker.regexify("[A-Za-zА-Яа-я]{25}"),   // Строка из английских и русских символов в смешанной раскладке
                        faker.regexify("[0-9]{25}"),            // Строка из цифр
                        faker.regexify("[A-Za-z0-9]{25}"),                          // Строка из английских символов вперемешку с цифрами
                        faker.regexify("[А-Яа-я0-9]{25}"),                          // Строка из русских символов вперемешку с цифрами
                        faker.letterify("?????? ???????"),                          // Строка, состоящая из двух слов, разделённых пробелом
                        faker.letterify("?????? ??????? ?????? ??????? ??????"),    // Строка, состоящая из нескольких слов, разделённых пробелами
                        faker.letterify("??????     ??????"),                       // Строка с несколькими пробелами подряд
                        faker.letterify(" ??????"),                                 // Строка, начинающаяся с пробела
                        faker.letterify("?????? "),                                 // Строка, оканчивающаяяся пробелом
                        "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~"+ getRandomName(),                // Строка из спецсимволов
                        "ё"+ getRandomName(),                                                 // Буква "ё"
                        "اختبار إيجابي من اليمين إلى يسار النص"+ getRandomName(),             // RTL текст
                        "✅✅✅"+ getRandomName()                                            // Emoji
        );
    }

    public static String[] getValidFolderExclusions(){
        return new String[]{
                "C:\\",                                 // Windows-style
                "C:\\Program files\\",                  // Windows-style
                "C:\\Program files\\Документ Microsoft Word.docx",          // Windows-style с путём к файлу


                "/proc",                                // Unix-style
                "/home/admin/",                         // Unix-style
                "/home/admin/Документ Microsoft Word.docx",                 // Unix-style с путём к файлу

                "smb://server/share",                   // SMB-style
                "\\server\\share",
                "\\\\server\\share",
                "\\\\server\\share\\Документ Microsoft Word.docx",            // SMB-style с путём к файлу

                "smb://user@server/share",
                "\\user@server\\share",
                "\\\\user@server\\share",

                "smb://192.168.1.1/share",
                "\\192.168.1.1\\share",
                "\\\\192.168.1.1\\share",

                "smb://server/share/subdir",

                "\\server\\share\\subdir",
                "\\\\server\\share\\subdir",

                "",                                     // Пустая строка
                faker.regexify("[a-z]{1}"),             // Строка из 1 символа
                faker.regexify("[a-z]{500}"),           // Строка из 500 символов
                faker.regexify("[а-я]{25}"),            // Строка из русских символов в нижней раскладке
                faker.regexify("[А-Я]{25}"),            // Строка из русских символов в верхней раскладке
                faker.regexify("[А-Яа-я]{25}"),         // Строка из русских символов в смешанной раскладке
                faker.regexify("[a-z]{25}"),            // Строка из английских символов в нижней раскладке
                faker.regexify("[A-Z]{25}"),            // Строка из английских символов в верхней раскладке
                faker.regexify("[A-Za-z]{25}"),         // Строка из английских символов в смешанной раскладке
                faker.regexify("[A-Za-zА-Яа-я]{25}"),   // Строка из английских и русских символов в смешанной раскладке
                faker.regexify("[0-9]{25}"),            // Строка из цифр
                faker.regexify("[A-Za-z0-9]{25}"),      // Строка из английских символов вперемешку с цифрами
                faker.regexify("[А-Яа-я0-9]{25}"),      // Строка из русских символов вперемешку с цифрами
                "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~" + getRandomName(),   // Строка из спецсимволов
                "ё" + getRandomName()                                     // Буква "ё"
        };
    }

    public static List<List<String>> getValidFolderExclusionsWithDifferentCount(){

        return List.of(
                List.of(),              // Пустой список
                List.of(
                        "C:\\"
                ),                      // Список из 1 пути
                List.of(
                        "C:\\",
                        "smb://server/share"
                )                       // Список из 2 путей
        );

    }

    public static String[] getValidURI(){
        return new String[]{

                "smb://server/share",
                "\\server\\share",
                "\\\\server\\share",

                "smb://user@server/share",
                "\\user@server\\share",
                "\\\\user@server\\share",

                "smb://192.168.1.1/share",
                "\\192.168.1.1\\share",
                "\\\\192.168.1.1\\share",

                "smb://server/share/subdir",

                "\\server\\share\\subdir",
                "\\\\server\\share\\subdir",

                faker.regexify("[a-z]{1}"),                                 // Строка длиной 1 символ
                faker.lorem().characters(5000),         // Строка длиной 5000 символов
                "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~",                       // Строка из спецсимволов
                "ё"                                                         // Буква "ё"
        };
    }

    public static String[] getValidPasswords(){
        return new  String[]{
                faker.regexify("[a-z]{1}"),             // Строка из 1 символа
                faker.regexify("[a-z]{256}"),           // Строка из 256 символов
                faker.regexify("[а-я]{25}"),            // Строка из русских символов в нижней раскладке
                faker.regexify("[А-Я]{25}"),            // Строка из русских символов в верхней раскладке
                faker.regexify("[А-Яа-я]{25}"),         // Строка из русских символов в смешанной раскладке
                faker.regexify("[a-z]{25}"),            // Строка из английских символов в нижней раскладке
                faker.regexify("[A-Z]{25}"),            // Строка из английских символов в верхней раскладке
                faker.regexify("[A-Za-z]{25}"),         // Строка из английских символов в смешанной раскладке
                faker.regexify("[A-Za-zА-Яа-я]{25}"),   // Строка из английских и русских символов в смешанной раскладке
                faker.regexify("[0-9]{25}"),            // Строка из цифр
                faker.regexify("[A-Za-z0-9]{25}"),      // Строка из английских символов вперемешку с цифрами
                faker.regexify("[А-Яа-я0-9]{25}"),      // Строка из русских символов вперемешку с цифрами
                "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~",   // Строка из спецсимволов
                "ё"                                     // Буква "ё"
        };
    }

    /*
    * Invalid Data
    * */

    public static String[] getEmptyNames(){
        return new String[]{
                "",         // Пустая строка
                " ",        // Пробел
                "     "     // 5 пробелов
        };
    }

    public static String getNameWithExceedingLength(){
        return faker.regexify("[a-z]{257}");
    }

    /*
     * Random Data
     * */

    public static String getRandomName(){
        return getRandomName(30);
    }

    public static String getRandomName(int length){
        return faker.regexify("[a-zA-Z]{" + length + "}");
    }

}
