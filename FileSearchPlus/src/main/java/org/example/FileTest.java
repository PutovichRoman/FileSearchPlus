package org.example;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class FileTest {
    @Test
    public void TestRunTread() {
        FileSearch.MyThread thread = new FileSearch.MyThread();
        //Создаём объект который позволяет создавать потоки для открытия папок.

        assertEquals("Упс.. косяк!", "Всё успешно!", thread.run("C", 3));
    }

    @Test
    public void TestSearchFile() {
        FileSearch.FolderList = new ArrayList<String>(); //Лист путей к папкам.
        FileSearch.FileList = new ArrayList<File>(); //Лист путей к файлам
        FileSearch.Disk = ""; //Диск который будет проверяться.
        FileSearch.PackViewed = 0; //Переменная которая отвечает за то, насколько сместить папки для открытия.
        FileSearch.ReqFile = ""; //Имя файла который нужно найти.
        FileSearch.SearchTime = 0; //Время которое даётся на поиск файла.
        FileSearch.MyThread.search(); //Запуск метода который начинает поиск файла в выбранном диске.
    }
}
