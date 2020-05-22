/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.dys.tools.jpaentitygenerator;

import org.junit.jupiter.api.Test;

/**
 *
 * @author darryl
 */
public class InMemoryDBTest {
    
    @Test
    public void runWithInMemoryDBTest() throws Exception {
        
        DBServer server = new DBServer();
        try {
            server.start();
            server.initialize();
            
            Main.main(new String[]{"-o", "./target/entity-generated", 
                    "-p", "./src/test/resources/hibernate.properties", 
                    "-r", "./src/test/resources/hibernate.reveng.xml", 
                    "-P", "gen.entity"});
            
        } finally {
            server.stop();
        }
        
    }
    
}
