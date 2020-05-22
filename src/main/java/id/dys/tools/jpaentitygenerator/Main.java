/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.dys.tools.jpaentitygenerator;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author darryl
 */
public class Main {
    private static final Logger LOG = LoggerFactory.getLogger(Main.class);
    
    public static void main(String[] args) {
        
        CommandLineParser parser = new DefaultParser();
        
        Options options = new Options();
        options.addOption("o", "output-dir", true, "Output directory of generated files. "
                + "Defaults to \"generated\"");
        options.addOption("p", "hibernate-prop", true, "Path of hibernate.properties file. "
                + "Defaults to hibernate.properties in current working directory");
        options.addOption("r", "hibernate-reveng", true, "Path of hibernate reverse engineering "
                + "customization xml file. Useful to customize output of generated files.");
        options.addRequiredOption("P", "package-name", true, "Package name of generated files.");
        options.addOption("s", "custom-strategy", true, "Class name of custom reverse engineering strategy.");
        
        try {
            CommandLine line = parser.parse(options, args);
            
            GeneratorParameter param = new GeneratorParameter();
            
            param.setCustomReverseEngineeringStrategyClassName(line.getOptionValue("custom-strategy"));
            param.setDestDir(line.getOptionValue("output-dir", "generated"));
            param.setGeneratedSourcesPackageName(line.getOptionValue("package-name", "entity.generated"));
            param.setHibernatePropertiesFilePath(line.getOptionValue("hibernate-prop", "./hibernate.properties"));
            param.setHibernateRevengXmlFilePath(line.getOptionValue("hibernate-reveng"));
            
            HibernateToolsGenerator.generate(param);
            
        } catch (ParseException ex) {
            LOG.error(ex.getMessage());
            
            HelpFormatter hf = new HelpFormatter();
            hf.printHelp("ant", options);
        }
        
    }
}
