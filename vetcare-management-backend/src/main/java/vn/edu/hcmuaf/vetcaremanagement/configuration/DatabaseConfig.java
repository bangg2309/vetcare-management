package vn.edu.hcmuaf.vetcaremanagement.configuration;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import vn.edu.hcmuaf.vetcaremanagement.util.ConfigReader;

@ApplicationScoped
public class DatabaseConfig {
    private static final String URL = ConfigReader.get("db.url");
    private static final String USER = ConfigReader.get("db.username");
    private static final String PASSWORD = ConfigReader.get("db.password");

    @Produces
    public Jdbi createJdbi() {
        return Jdbi.
                create(URL, USER, PASSWORD).
                installPlugin(new SqlObjectPlugin());
    }
}