package ru.javarush.intership;

import org.apache.catalina.Server;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
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
        long start = System.currentTimeMillis();

        String webAppDirLocation = createTempBaseDir();

        logger.debug("webAppDirLocation set to {}", webAppDirLocation);

        final int port = 8080;

        Tomcat tomcat = new Tomcat();

        // set port
        tomcat.setPort(port);

        StandardContext context = (StandardContext) tomcat.addWebapp("", webAppDirLocation);

        tomcat.getConnector();

        tomcat.init();
        tomcat.start();

        logger.info("APPLICATION STARTED: {} MILLIS", System.currentTimeMillis() - start);

        Server server = tomcat.getServer();

        server.await();
    }

    private static String createTempBaseDir() throws IOException, URISyntaxException {
        URI codeSourcePath = Application.class.getProtectionDomain().getCodeSource().getLocation().toURI();
        File jarFilePath = new File(codeSourcePath);
        // Just for run in Intellij Idea
        if (!jarFilePath.toString().endsWith(".jar")) {
            return Paths.get("src/main/webapp").toAbsolutePath().toString();
        } else {
            return createTempBaseDirFromJar(jarFilePath);
        }

    }

    private static String createTempBaseDirFromJar(File jarFilePath) throws IOException {
        Path tempDir = Files.createTempDirectory("temp-webapp");
        logger.debug("jar file path: {}", jarFilePath.toString());
        try (JarInputStream jis = new JarInputStream(new FileInputStream(jarFilePath))) {
            ZipEntry entry;
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