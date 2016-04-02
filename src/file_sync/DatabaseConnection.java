package file_sync;
import file_sync.Settings;
import java.sql.Connection;
import java.sql.DriverManager;

// http://www.tutorialspoint.com/postgresql/postgresql_java.htm
public class DatabaseConnection {
  public static void main(String args[]) {
    Connection connection = null;

    try {
      Class.forName("org.postgresql.Driver");
      connection = DriverManager.getConnection(
          Settings.POSTGRESQL_URL,
          Settings.POSTGRESQL_USER,
          Settings.POSTGRESQL_PASSWORD);
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
      System.exit(0);
    }

    System.out.println("Opened database successfully");
  }
}
