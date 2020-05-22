/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.dys.tools.jpaentitygenerator;

import org.hibernate.cfg.reveng.DelegatingReverseEngineeringStrategy;
import org.hibernate.cfg.reveng.ReverseEngineeringStrategy;
import org.hibernate.cfg.reveng.TableIdentifier;

/**
 *
 * @author darryl
 */
public class CustomStrategy extends DelegatingReverseEngineeringStrategy {
    
    private static final String PACKAGE_NAME = "gen.entity";
    
    public CustomStrategy(ReverseEngineeringStrategy delegate) {
        super(delegate);
    }

    // append Entity to all generated class names for table UserLogin
    @Override
    public String tableToClassName(TableIdentifier tableIdentifier) {
        if (tableIdentifier.getName().equalsIgnoreCase("UserLogin")) {
            return PACKAGE_NAME + ".UserLoginEntity";
        } else {
            return super.tableToClassName(tableIdentifier);
        }
    }
    
    
    
}
