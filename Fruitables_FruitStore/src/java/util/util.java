package util;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class util {
    public static Connection makeConnection () throws SQLException, NamingException {
        Context ctx = new InitialContext();
        Context tomcatCtx = (Context)ctx.lookup("java:comp/env");
        DataSource ds = (DataSource)tomcatCtx.lookup("DynamicDB");
        
        return ds.getConnection();
    }
}
