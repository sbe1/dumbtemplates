package shawnewald.dumbtemplates;

import java.io.File;
import java.util.Properties;
import org.shawnewald.javatools.Prop;

 /**
 * Dumbtemplates - configuration class.
 * @author  Shawn Ewald <shawn.ewald@gmail.com>
  * Copyright (C) 2012,2013 Shawn Ewald
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *
 */
public final class DumbtemplateConfig {
    private DumbtemplateConfig () {}
    // dumbtemplate.properties values
    public static final String templatePath;
    public static final String templateExt;
    public static final Boolean watchTemplateChanges;
    public static final int templateCheckInterval;

    static {
        // Check for a properties file provided as a system property value.
        // Ex.: java -jar myapp.jar -DdtConfig=/path/to/dumbtemplate.properties
        final String dtConfig = System.getProperty("dtConfig");
        // If dtConfig property is not found or is empty, use default properties file. 
        final Properties props = (dtConfig == null || dtConfig == "")
                ? Prop.getFromClasspath("dumbtemplate.properties")
                : Prop.getFromFile(new File(dtConfig));
        templatePath = props.getProperty("templatePath");
        templateExt = props.getProperty("templateExt");
        watchTemplateChanges = (props.getProperty("watchTemplateChanges") == null
                || props.getProperty("watchTemplateChanges").equals("0")) ? false : true;
        templateCheckInterval = Integer.parseInt(props.getProperty("templateCheckInterval"));

    }
    // just a quick and dirty way to preload the templates.
    //public static final DumbtemplateCollection templates = DumbtemplateCollection.getInstance();
}
