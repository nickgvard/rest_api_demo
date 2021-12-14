package util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Nikita Gvardeev
 * 07.12.2021
 */
public class HibernateDataBaseAccess implements DataBaseAccess<Session> {

    private static final HibernateDataBaseAccess ACCESS = new HibernateDataBaseAccess();
    private static SessionFactory sessionFactory;
    private static final Map<String, String> SETTINGS = new HashMap<>();

    static {
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        String dbUser = System.getenv("JDBC_DATABASE_USERNAME");
        String dbPass = System.getenv("JDBC_DATABASE_PASSWORD");

        if(null != dbUrl) {
            SETTINGS.put("hibernate.connection.url", dbUrl);
        }

        if(null != dbUser) {
            SETTINGS.put("hibernate.connection.username", dbUser);
        }

        if(null != dbPass) {
            SETTINGS.put("hibernate.connection.password", dbPass);
        }

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .applySettings(SETTINGS)
                .build();
        try {
            sessionFactory = new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();
        }catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
            e.printStackTrace();
        }
    }

    private HibernateDataBaseAccess() {}

    public static HibernateDataBaseAccess instance() {
        return ACCESS;
    }

    @Override
    public Session dataBaseAccess() {
        return sessionFactory.openSession();
    }
}
