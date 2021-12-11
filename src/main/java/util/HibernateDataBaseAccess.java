package util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * @author Nikita Gvardeev
 * 07.12.2021
 */
public class HibernateDataBaseAccess implements DataBaseAccess<Session> {

    private static final HibernateDataBaseAccess ACCESS = new HibernateDataBaseAccess();
    private static SessionFactory sessionFactory;

    static {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
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
