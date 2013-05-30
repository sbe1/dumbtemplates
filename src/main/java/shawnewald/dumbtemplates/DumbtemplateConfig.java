package shawnewald.dumbtemplates;

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
public class DumbtemplateConfig {
    // application constants
    public static final String ZERO = "0";

    // dumbtemplate.properties values
    public static final String templatePath;
    public static final Boolean watchTemplateChanges;
    public static final int templateCheckInterval;

    static {
        final Properties props = Prop.getFromClasspath("dumbtemplate.properties");
        templatePath = props.getProperty("templatePath");
        watchTemplateChanges = (props.getProperty("watchTemplateChanges") == null
                || props.getProperty("watchTemplateChanges").equals(ZERO)) ? false : true;
        templateCheckInterval = Integer.parseInt(props.getProperty("templateCheckInterval"));

    }
    // just a quick and dirty way to preload the templates.
    //public static final DumbtemplateCollection templates = DumbtemplateCollection.getInstance();
}
