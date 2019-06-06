package ru.javarush.intership;

import org.apache.catalina.Server;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.jar.JarInputStream;
import java.util.zip.ZipEntry;

public class Application {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws Exception {
        final String webAppDirLocation = createTempBaseDir();
        final int port = 8080;

        Tomcat tomcat = new Tomcat();

        // set port
        tomcat.setPort(port);

        StandardContext context = (StandardContext) tomcat.addWebapp("", webAppDirLocation);

        tomcat.getConnector();

        tomcat.init();
        tomcat.start();


        Server server = tomcat.getServer();

        server.await();
    }

    private static String createTempBaseDir() throws IOException, URISyntaxException {
        Path tempDir = Files.createTempDirectory("temp-webapp");
        File jarFilePath = new File(Application.class.getProtectionDomain().getCodeSource().getLocation().toURI());
        logger.debug("jar file path: {}", jarFilePath.toString());
        try (JarInputStream jis = new JarInputStream(new FileInputStream(jarFilePath))) {
            ZipEntry entry = null;
            while (null != (entry = jis.getNextEntry())) {
                if (entry.getName().startsWith("webapp")) {
                    String entryName = entry.getName().replaceAll("webapp", "");
                    logger.debug("entryName: {}", entryName);

                    Path zipPart = Paths.get(tempDir.toString(), entryName);

                    if (entry.isDirectory()) {
                        Files.createDirectories(zipPart);
                    } else {
                        Files.createFile(zipPart);
                        Files.copy(jis, zipPart, StandardCopyOption.REPLACE_EXISTING);
                    }

                }
            }
        }
        File tempFile = tempDir.toFile();
        tempFile.deleteOnExit();
        String tempBaseDir = tempFile.toString();
        logger.debug("tempBaseDir created: {}", tempBaseDir);
        return tempBaseDir;
    }
}