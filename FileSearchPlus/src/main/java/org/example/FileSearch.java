package org.example;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class FileSearch {
    public static ArrayList<String> FolderList; //Список папок.
    public static ArrayList<File> FileList; //Список файлов.
    public static ArrayList<String> BListDirec = new ArrayList<String>(); //Временное хранилище директорий.
    public static String ReqFile;  //Файл который будет искать программа.
    public static int SearchTime; //Время на выполнение поиска.
    public static String Disk;//имя диска.
    public static int PackViewed; //Сила смещение рассмотрения папок.

    public static void main() {
        FolderList = new ArrayList<String>(); //FolderList
        FileList = new ArrayList<File>(); //FileList
        PackViewed = 0;

        Scanner scan = new Scanner(System.in);
        System.out.print("\033[0mВ каком диске вы хотите найти файл? Введите просто название диска: ");
        String aTest = scan.nextLine(); //Сохраняем в переменную для проверки на коректный диск
        if (MyThread.isCorrectDisk(aTest)) {
            Disk = aTest + ":" + File.separator;
        } else {
            System.out.println("\033[31m!Извините, но такого диска нету и поэтому мы будем работать с одним из ваших дисков автоматически.\033[0m");
            Disk = File.listRoots()[0].getAbsolutePath();
        }

        System.out.print("Введите файл который вам нужно найти: ");
        ReqFile = scan.nextLine();
        System.out.print("Сколько по времени вы хотите искать ваш файл? В секундах: ");
        try {
            SearchTime = scan.nextInt();
        } catch (Exception e) {
            System.out.println("\033[31m!Извините, но вы ввели буквы, а нужны были цифры. Время поиска будет составлять - 1 секунду.\033[0m");
            SearchTime = 1;
        }
        scan.nextLine();

        MyThread.search();//Начинаем поиск файла
        if (Search.string().size() > 0) {
            System.out.println("\033[32mВот пути к нужному файлу");
            System.out.println(Search.string().toString() + "\033[0m");
        } else {
            System.out.println("\033[31mК сожалению, нам не удалось найти такой файл!");
            System.out.println("Попробуйте дать поиску больше времени, или поискать в другом диске!\033[0m");
        }

    }

    static class MyThread {
        int folderNum;
        String folderPath;
        Thread t1;
        boolean flag;

        public void setData(int number, String LocalPath) {
            folderNum = number;
            folderPath = LocalPath;
        }

        public String run(String Directory, int o) {
            File FileFold = new File(Directory);
            t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    if (FileFold.listFiles() != null) {
                        for (File i : FileFold.listFiles()) {
                            if (i.isFile()) {
                                FileList.add(i);
                            } else if (i.isDirectory()) {
                                if (o == 0) {
                                    BListDirec.add(i.getAbsolutePath());
                                } else if (o == 1) {
                                    FolderList.add(i.getAbsolutePath());
                                }
                            }
                            if (SearchTime != 0) {
                                Thread.onSpinWait();
                            }
                        }
                    }
                }
            });
            t1.start();
            return t1 != null ? "Всё успешно!" : "Упс.. косяк!";
        }

        public static String search() {
            MyThread d = new MyThread();
            MyThread e = new MyThread();
            MyThread f = new MyThread();
            MyThread g = new MyThread();
            MyThread h = new MyThread();
            MyThread j = new MyThread();
            Thread timer = new Thread(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(SearchTime * 1000);
                    } catch (InterruptedException ex) {
                    }
                    SearchTime = 0;
                }

            });
            timer.start();

            d.run(FileSearch.Disk, 1);
            try {
                d.t1.join();
            } catch (InterruptedException ex) {
            }
            try {
                d.t1.join();
            } catch (InterruptedException ex) {
            }
            while (SearchTime != 0) {
                for (int i = 0; i < FolderList.size() - 3; i++) {
                    //d.run(AList[0].get(i + PackViewed), 1);
                    if (i + 0 + PackViewed < FolderList.size()) {
                        e.run(FolderList.get(i + 0 + PackViewed), 0);
                    }
                    if (i + 1 + PackViewed < FolderList.size()) {
                        f.run(FolderList.get(i + 1 + PackViewed), 0);
                    }
                    if (i + 2 + PackViewed < FolderList.size()) {
                        g.run(FolderList.get(i + 2 + PackViewed), 0);
                    }
                    if (i + 3 + PackViewed < FolderList.size()) {
                        e.run(FolderList.get(i + 3 + PackViewed), 0);
                    }
                    if (i + 4 + PackViewed < FolderList.size()) {
                        h.run(FolderList.get(i + 4 + PackViewed), 0);
                    }
                    if (i + 5 + PackViewed < FolderList.size()) {
                        j.run(FolderList.get(i + 5 + PackViewed), 0);
                    }
                    try {
                        d.t1.join();
                        e.t1.join();
                        f.t1.join();
                        g.t1.join();
                    } catch (InterruptedException ex) {
                    }
                    PackViewed += 5;
                }
                FolderList = BListDirec;

                System.out.println(FolderList);
                System.out.println(BListDirec);
                System.out.println(FileList);
            }
            return "";
        }

        static public boolean isCorrectDisk(String DiskName) {
            boolean isTrue = false;
            for (File i : File.listRoots()) {
                if (i.getAbsolutePath().equals(DiskName + ":" + File.separator)) {
                    isTrue = true;
                }
            }
            return isTrue;
        }
    }

    static class Search {
        public static ArrayList<String> string() {
            ArrayList<String> list = new ArrayList<>();
            for (File i : FileList) {
                if (i.getName().equals(ReqFile)) {
                    list.add(i.getAbsolutePath());
                }
            }
            return list;
        }
    }

    static class StrHelp {
        public static String letters(final String text, final int MIN, final int MAX) {
            String[] newText = new String[MAX + 1];
            for (int i = 0; i < MAX + 1; i++) {
                newText[i] = String.valueOf(text.charAt(i));
            }
            return String.join("", newText);
        }
    }
}
