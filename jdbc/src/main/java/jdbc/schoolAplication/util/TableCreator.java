package jdbc.schoolAplication.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.stream.Collectors;

import jdbc.schoolAplication.dao.ConnectionManager;

public class TableCreator {
    private static final String SQLScriptPath = "src/main/resources/SQLCreateTables.sql";  
    
    static {
        try {
            executeSQLScript();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void executeSQLScript() throws IOException  {
        try (Connection connection = ConnectionManager.open();
            PreparedStatement statement = connection.prepareStatement(readSqlFile())) {
            statement.execute();
        } catch (SQLException e) {
             e.printStackTrace();
        }
    }
    
    private static String readSqlFile() throws IOException {
        return Files.lines(Paths.get(SQLScriptPath)).collect(Collectors.joining());
    }
}
