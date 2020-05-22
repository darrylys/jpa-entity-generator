/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.dys.tools.jpaentitygenerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import org.h2.jdbcx.JdbcDataSource;
import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author darryl
 */
public class DBServer {
    
    private static final Logger LOG = LoggerFactory.getLogger(DBServer.class);
    
    private Server server;
    private DataSource dataSource;
    
    public DBServer() {
    }
    
    public void start() throws SQLException, ClassNotFoundException, InterruptedException {
        LOG.info("Starting server");
        
        this.server = Server.createTcpServer("-tcpPort", "9123", "-tcpAllowOthers", "-tcpPassword", "password").start();
        LOG.info("Server started");
        
        LOG.info("Server running in url: {}", this.server.getURL());
        
        Class.forName("org.h2.Driver");
        
        LOG.info("Creating datasource");
        JdbcDataSource ds = new JdbcDataSource();
        ds.setURL("jdbc:h2:mem:db1;DB_CLOSE_DELAY=-1");
        ds.setUser("sa");
        ds.setPassword("password");
        
        dataSource = ds;
        
        LOG.info("start completed");
    }
    
    public void initialize() throws SQLException, IOException {
        LOG.info("Initializing database content");
        
        String[] sqls;
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(this.getClass().getResourceAsStream("/db_init.sql")))) {
            StringBuilder sb = new StringBuilder();
            String in;
            while ((in = br.readLine()) != null) {
                sb.append(in);
            }
            sqls = sb.toString().split(";");
        }
        
        try (Connection conn = this.getConnection()) {
            Statement stmt = conn.createStatement();
            for (int i = 0; i < sqls.length; ++i) {
                LOG.info("Executing sql: {}", sqls[i]);
                stmt.executeUpdate(sqls[i]);
            }
        }
        
        LOG.info("Database content initialized successfully");
    }
    
    public Connection getConnection() throws SQLException {
        return this.dataSource.getConnection();
    }
    
    public void stop() {
        LOG.info("Stopping server");
        this.server.stop();
        LOG.info("Done");
    }
    
}
