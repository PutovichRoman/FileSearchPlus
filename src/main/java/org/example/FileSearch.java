package org.example;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class FileSearch {
    public static ArrayList<String> folderList; //Список папок.
    public static ArrayList<File> fileList; //Список файлов.
    public static ArrayList<String> blistDirec = new ArrayList<String>(); //Временное хранилище директорий.
    public static String reqFile;  //Файл который будет искать программа.
    public static int searchTime;
    public static int timerTime;//Время на выполнение поиска.
    public static String disk;//имя диска.
    public static int packViewed; //Сила смещение рассмотрения папок.
    public static int clarificat; //Показывать директории которые мы нарыли во время поиска? 1-true/0-false.

    public static void main() {
        folderList = new ArrayList<String>(); //folderList
        fileList = new ArrayList<File>(); //FileList
        packViewed = 0;

        AskConsole.disk();
        AskConsole.file();
        AskConsole.time();
        AskConsole.is_dir();

        MyThread.search();//Начинаем поиск файла

        if (SelectList.string().size() > 0) {
            System.out.println("\033[32mВот пути к нужному файлу");
            ArrayList<String> OneDir = new ArrayList<>();
            OneDir.addAll(SelectList.string());
            for(String i : OneDir) {
                System.out.println(i);
            }
        } else {
            System.out.println("\033[31mК сожалению, нам не удалось найти такой файл!");
            System.out.println("Попробуйте дать поиску больше времени, или поискать в другом диске!\033[0m");
        }

    }
}

