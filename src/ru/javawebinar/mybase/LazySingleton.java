package ru.javawebinar.mybase;

public class LazySingleton {
    volatile private static LazySingleton INSTANCE;
    private LazySingleton(){

    }

    public static class LazySingletonHolder{
        private static final LazySingleton INSTANCE = new LazySingleton();

    }

    public static LazySingleton getInstance(){
        return LazySingleton.INSTANCE;
    }
}
