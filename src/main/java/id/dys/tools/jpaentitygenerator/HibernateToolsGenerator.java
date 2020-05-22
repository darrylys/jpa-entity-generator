/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.dys.tools.jpaentitygenerator;

import java.io.File;
import org.apache.tools.ant.types.Path;
import org.hibernate.tool.ant.Hbm2JavaExporterTask;
import org.hibernate.tool.ant.HibernateToolTask;
import org.hibernate.tool.ant.JDBCConfigurationTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author darryl
 */
public class HibernateToolsGenerator {
    
    private static final Logger LOG = LoggerFactory.getLogger(HibernateToolsGenerator.class);
    
    public static void generate(GeneratorParameter param) {
        
        LOG.info("Start generating with parameters: {}", param);
        
        final File destDir = new File(param.getDestDir());
        final File hibernateProperties = new File(param.getHibernatePropertiesFilePath());

        final HibernateToolTask entityGenerator = new HibernateToolTask();
        entityGenerator.setDestDir(destDir);

        final JDBCConfigurationTask jdbcConfiguration = entityGenerator.createJDBCConfiguration();
        jdbcConfiguration.setPropertyFile(hibernateProperties);
        if (!StringUtils.isBlank(param.getHibernateRevengXmlFilePath())) {
            LOG.info("Use provided Hibernate reverse engineering xml file");
            jdbcConfiguration.setRevEngFile(new Path(jdbcConfiguration.getProject(), 
                    param.getHibernateRevengXmlFilePath()));
        }
        if (!StringUtils.isBlank(param.getCustomReverseEngineeringStrategyClassName())) {
            LOG.info("Use provided custom reverse engineering class");
            jdbcConfiguration.setReverseStrategy(param.getCustomReverseEngineeringStrategyClassName());
        }
        jdbcConfiguration.setDetectManyToMany(true);
        jdbcConfiguration.setDetectOneToOne(true);
        jdbcConfiguration.setPackageName(param.getGeneratedSourcesPackageName());
        jdbcConfiguration.execute();

        final Hbm2JavaExporterTask hbm2Java = (Hbm2JavaExporterTask) entityGenerator.createHbm2Java();
        hbm2Java.setEjb3(true);
        hbm2Java.setDestdir(destDir);
        hbm2Java.validateParameters();
        hbm2Java.execute();
        
        LOG.info("Generation completed");
        
    }
    
}
