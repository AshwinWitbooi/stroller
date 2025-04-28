package za.co.ashtech.stroller.config;

import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import za.co.ashtech.stroller.StrollerApplication;

/*
 * This class extends Actuator /info contributor
 * It will allow for viewing version information
 */
@Slf4j
@Component
public class ActuatorInfo implements InfoContributor {
	
    @Override
    public void contribute(Info.Builder builder) {
        
		try {
			
			//Get path to where code is excuting from	
	    	String pathToJar = StrollerApplication.class.getProtectionDomain()
						        .getCodeSource()
						        .getLocation()
						        .toURI()
						        .toString();
	    	log.debug("::: -> Path To JAR {}",pathToJar);
	    	
	    	//Check whether path is from Spring Boot Jar running
			if(pathToJar.contains("/!BOOT-INF/classes/!/")) {
	
				pathToJar = pathToJar.replaceAll("jar:nested:/", "");
				log.debug("::: -> Remove prefix from Jar path {}",pathToJar);
				pathToJar = pathToJar.replaceAll("/!BOOT-INF/classes/!/", "");
				log.debug("::: -> Remove postfix from Jar path {}",pathToJar);
				
				try (JarFile jarFile = new JarFile(pathToJar)) {
		            Manifest manifest = jarFile.getManifest();
		            if (manifest != null) {
		                manifest.getMainAttributes().forEach((key, value) ->
		                
		                builder.withDetail(key.toString(), value.toString()));
		                
		            } else {
		                new NullPointerException("No manifest found in the JAR file.");
		            }
		        }
			}else {
				URLClassLoader cl = (URLClassLoader) StrollerApplication.class.getClassLoader();
				log.debug("::: -> Get class loader");
				URL url = cl.findResource("META-INF/MANIFEST.MF");
				log.debug("::: -> Jar URL {}",url.getFile());
				log.debug("::: -> Get manifest file");
				Manifest manifest;
				
				manifest = new Manifest(url.openStream());
				Attributes attr = manifest.getMainAttributes();
				
				Iterator<Entry<Object, Object>> entrySet = attr.entrySet().iterator();	
				 
				entrySet.forEachRemaining(e -> {builder.withDetail(e.getKey().toString(), e.getValue().toString());});				  

			}
			
		} catch (URISyntaxException e) {
			log.error(e.getLocalizedMessage());
		}catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}
    }
}
