package org.example;

import java.io.File;
import java.util.Scanner;

public class AskConsole {
    static Scanner scan = new Scanner(System.in);
    static int a = 1; //Пропустить ввод.

    public static void disk() {
        System.out.print("\033[0mВ каком диске вы хотите найти файл? Введите просто название диска: ");
        String aTest;
        if(a==1){
            aTest = scan.nextLine(); //Сохраняем в переменную для проверки на коректный диск
        }else{
            aTest="";
        }

        if (MyThread.isCorrectDisk(aTest)) {
            FileSearch.disk = aTest + ":" + File.separator;
        } else {
            System.out.println("\033[31m!Извините, но такого диска нету и поэтому мы будем работать с одним из ваших дисков автоматически.\033[0m");
            FileSearch.disk = File.listRoots()[0].getAbsolutePath();
        }
    }

    public static void file() {
        System.out.print("Введите файл который вам нужно найти: ");
        if(a==1){
            FileSearch.reqFile = scan.nextLine();
        }else{
            FileSearch.reqFile = "";
        }

    }

    public static void time() {
        System.out.print("Сколько по времени вы хотите искать ваш файл? В секундах: ");
        try {
            if(a==1){
                FileSearch.searchTime = scan.nextInt();
            }else{
                FileSearch.searchTime = 0;
            }

        } catch (Exception e) {
            System.out.println("\033[31m!Извините, но вы ввели буквы, а нужны были цифры. Время поиска будет составлять - 1 секунду.\033[0m");
            FileSearch.searchTime = 1;
        }
        if(a==1){
            scan.nextLine();
        }
        FileSearch.timerTime = FileSearch.searchTime;

    }

    public static void is_dir() {
        String textLol;
        System.out.print("Хотите видеть выводимые в консоль директории в которых мы лазили? : ");
        //.toLowerCase()
        if(a==1){
            textLol = scan.nextLine();
        }else{
            textLol = "";
        };
        if (textLol.toLowerCase().trim().equals("да") ||
                textLol.toLowerCase().trim().equals("конечно") ||
                textLol.toLowerCase().trim().equals("yes")) {
            FileSearch.clarificat = 1;
        } else {
            FileSearch.clarificat = 0;
        }
    }
}
