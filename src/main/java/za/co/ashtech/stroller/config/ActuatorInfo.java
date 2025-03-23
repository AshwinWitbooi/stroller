package za.co.ashtech.stroller.config;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.jar.Attributes;
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
        
		URLClassLoader cl = (URLClassLoader) StrollerApplication.class.getClassLoader();
		log.debug("::: -> Get class loader");
		URL url = cl.findResource("META-INF/MANIFEST.MF");
		log.debug("::: -> Get manifest file");
		Manifest manifest;
		try {
			manifest = new Manifest(url.openStream());
			Attributes attr = manifest.getMainAttributes();
			
			Iterator<Entry<Object, Object>> entrySet = attr.entrySet().iterator();	
			 
			entrySet.forEachRemaining(e -> {builder.withDetail(e.getKey().toString(), e.getValue().toString());});
			
		} catch (IOException e) {
			log.error(":::	-> Error getting running jar manifest file",e);
		}
    }
}
