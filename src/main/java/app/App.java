package app;

import util.HibernateDataBaseAccess;

/**
 * @author Nikita Gvardeev
 * 16.12.2021
 */
public class App {

    static {
        try {
            Class.forName(HibernateDataBaseAccess.class.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println();
    }
}
