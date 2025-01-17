package org.example;
import java.io.File;

public class MyThread {
    int folderNum;
    String folderPath;
    Thread t1;
    static boolean flag;

    public String run(String Directory, int o) {
        File FileFold = new File(Directory);
        t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                A:
                if (!flag) {
                    if (FileFold.listFiles() != null) {
                        for (File i : FileFold.listFiles()) {
                            if (i.isFile()) {
                                FileSearch.fileList.add(i);
                            } else if (i.isDirectory()) {
                                if (o == 0) {
                                    FileSearch.blistDirec.add(i.getAbsolutePath());
                                } else if (o == 1) {
                                    FileSearch.folderList.add(i.getAbsolutePath());
                                }
                            }
                            if (FileSearch.clarificat == 1) {
                                System.out.println(i.getAbsolutePath());
                            }
                            if (flag == true) {
                                break A;
                            }
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
            @Override
            public void run() {
                FileSearch.searchTime = FileSearch.searchTime * 1000;
                try {
                    for (int i = 0; i < FileSearch.searchTime; i++) {
                        Thread.sleep(1);
                        FileSearch.timerTime--;
                    }

                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                FileSearch.searchTime = 0;
                //FileSearch.searchTime = 0;
            }
        });
        timer.start();
        Thread base = new Thread(new Runnable() {
            @Override
            public void run() {

                d.run(FileSearch.disk, 1);
                try {
                    d.t1.join();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                try {
                    d.t1.join();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                B:
                while (!flag) {
                    for (int i = 0; i < FileSearch.folderList.size() - 3; i++) {
                        //d.run(AList[0].get(i + PackViewed), 1);
                        if (i + 0 + FileSearch.packViewed < FileSearch.folderList.size()) {
                            e.run(FileSearch.folderList.get(i + 0 + FileSearch.packViewed), 0);
                        }
                        if (i + 1 + FileSearch.packViewed < FileSearch.folderList.size()) {
                            f.run(FileSearch.folderList.get(i + 1 + FileSearch.packViewed), 0);
                        }
                        if (i + 2 + FileSearch.packViewed < FileSearch.folderList.size()) {
                            g.run(FileSearch.folderList.get(i + 2 + FileSearch.packViewed), 0);
                        }
                        if (i + 3 + FileSearch.packViewed < FileSearch.folderList.size()) {
                            e.run(FileSearch.folderList.get(i + 3 + FileSearch.packViewed), 0);
                        }
                        if (i + 4 + FileSearch.packViewed < FileSearch.folderList.size()) {
                            h.run(FileSearch.folderList.get(i + 4 + FileSearch.packViewed), 0);
                        }
                        if (i + 5 + FileSearch.packViewed < FileSearch.folderList.size()) {
                            j.run(FileSearch.folderList.get(i + 5 + FileSearch.packViewed), 0);
                        }
                        try {
                            if (flag == true) {
                                break B;
                            }
                            d.t1.join();
                            if (flag == true) {
                                break B;
                            }
                            e.t1.join();
                            if (flag == true) {
                                break B;
                            }
                            f.t1.join();
                            if (flag == true) {
                                break B;
                            }
                            g.t1.join();
                            if (flag == true) {
                                break B;
                            }
                            h.t1.join();
                            if (flag == true) {
                                break B;
                            }
                            f.t1.join();
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                        FileSearch.packViewed += 5;
                    }
                    FileSearch.folderList = FileSearch.blistDirec;
                    //Выводить все директории и файлы которые нашли потоки.
                }
            }
        });
        base.start();
        try {
            timer.join();
            flag = true;
            try {
                d.t1.interrupt();
                e.t1.interrupt();
                f.t1.interrupt();
                g.t1.interrupt();
                h.t1.interrupt();
                j.t1.interrupt();
            } catch (NullPointerException у) {
            }

        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("Работа окончена!");
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

