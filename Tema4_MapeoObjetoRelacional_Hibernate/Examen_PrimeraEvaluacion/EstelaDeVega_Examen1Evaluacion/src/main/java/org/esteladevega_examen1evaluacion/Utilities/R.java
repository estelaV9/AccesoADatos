package org.esteladevega_examen1evaluacion.Utilities;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

public class R {

    public static InputStream getProperties(String name) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream("configuration" + File.separator + name);
    }
    public static URL getUI(String name) {
        return Thread.currentThread().getContextClassLoader().getResource("ui" + File.separator + name);
    }

    public static URL getHibernateConfig(String path) {
        return Thread.currentThread().getContextClassLoader().getResource("Configuration/"+path);
    }
}