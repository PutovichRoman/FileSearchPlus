package org.example;

import java.io.File;
import java.util.ArrayList;

public class SelectList {
    public static ArrayList<String> string() {
        ArrayList<String> list = new ArrayList<>();
        for (File i : FileSearch.fileList) {
            if (i.getName().equals(FileSearch.reqFile)) {
                list.add(i.getAbsolutePath());
            }
        }
        return list;
    }
}
