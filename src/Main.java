/**
 * Программа, считающая количество слов в файле
 *
 * @version 1.0
 * @author Aleksandr Nikitin
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        String[] paths;
        int n;
        System.out.println("Введите количество файлов");

        ////
        n = in.nextInt();
        paths = new String[n];
        System.out.printf("Введите %d путей файлов", n);
        in.nextLine();
        for(int i = 0; i < n; i++){
            paths[i] = in.nextLine();
        }
        ////

        //Tests
        /*
        paths = new String[1];
        paths[0] = "1.txt";
         */
        /*
        paths = new String[2];
        paths[0] = "1.txt";
        paths[1] = "2.txt";
         */
        /*
        paths = new String[1];
        paths[1] = "2.txt";
         */


        try {
            System.out.println(Arrays.toString(readAndCount(paths)));
        } catch (IllegalArgumentException e){
            System.out.println("Пустое количество аргументов");
        } catch (FileNotFoundException e){
            System.out.println("Какой-то из файлов не найден");
        }


    }

    /**
     * Метод читающий и считающий слова из файлов
     * @param paths Массив путей
     * @return Возвращает массив, состоящий из количества слов в соответствующих файлах
     * @throws IllegalArgumentException Пустое количество аргументов
     * @throws FileNotFoundException Какой-то из файлов не найден
     */
    public static int[] readAndCount(String[] paths) throws IllegalArgumentException, FileNotFoundException {
        Scanner in;
        File file;
        String text;
        int counter;
        boolean lastWasLetter = false;
        int[] results = new int[paths.length];
        if(paths.length == 0){
            throw new IllegalArgumentException();
        }

        for(int i = 0; i < paths.length; i++){
            counter = 0;
            text = "";
            file = new File(paths[i]);
            in = new Scanner(file,"UTF-8");
            while (in.hasNextLine()){
                text += " " + in.nextLine();
            }
            text = text.trim();
            boolean isInRussianLanguage;
            for(int j = 0; j < text.length(); j++){
                isInRussianLanguage = (text.charAt(j) >= 'А' && text.charAt(j) <= 'я' || text.charAt(j) == 'Ё' || text.charAt(j) == 'ё');
                if(!lastWasLetter && isInRussianLanguage){
                    lastWasLetter = true;
                    if (isInRussianLanguage){
                        counter++;
                    }
                }
                if (!isInRussianLanguage){
                    lastWasLetter = false;
                }
            }
            results[i] = counter;
        }
        return results;
    }

}
