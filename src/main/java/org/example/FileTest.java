package org.example;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class FileTest {
    @Test
    public void TestRunTread() {
        MyThread thread = new MyThread();
        //Создаём объект который позволяет создавать потоки для открытия папок.

        assertEquals("Упс.. косяк!", "Всё успешно!", thread.run("C", 3));
    }

    @Test
    public void TestSearchFile() {
        FileSearch.folderList = new ArrayList<String>(); //Лист путей к папкам.
        FileSearch.fileList = new ArrayList<File>(); //Лист путей к файлам
        FileSearch.disk = ""; //Диск который будет проверяться.
        FileSearch.packViewed = 0; //Переменная которая отвечает за то, насколько сместить папки для открытия.
        FileSearch.reqFile = ""; //Имя файла который нужно найти.
        FileSearch.searchTime = 0; //Время которое даётся на поиск файла.
        MyThread.search(); //Запуск метода который начинает поиск файла в выбранном диске.
    }

    @Test
    public void TestAskConsole() {
        AskConsole.a=0;
        AskConsole.disk();
        AskConsole.file();
        AskConsole.time();
        AskConsole.is_dir();
    }
}
