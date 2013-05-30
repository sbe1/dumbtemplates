 package shawnewald.dumbtemplates;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.text.StrSubstitutor;
import org.apache.log4j.Logger;
import org.shawnewald.javatools.Txt;

 /**
 * Dumbtemplates - Template processor methods.
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
public final class Dumbtemplate {
    private static final Logger LOG = Logger.getLogger(Dumbtemplate.class);
    private static final String litHtml = ".html";
    private static final String litEmpty = "";

    private Dumbtemplate () {}
    public static Map<String,String> getTemplates (final String templateDir) {
        final Map<String,String> templates = new HashMap<String,String>();
        LOG.debug("Loading template files from path: "+templateDir);
         final File[] listOfFiles = getTemplateFiles(templateDir);
        if (listOfFiles == null || listOfFiles.length == 0) {
            LOG.error("Can't find template path or template path is null.");
        }
        else {
            try {
                LOG.debug("Creating template map.");
                for (final File file : listOfFiles) {
                    final String name = file.getName().toLowerCase().replace(litHtml, litEmpty);
                    templates.put(name, Txt.streamToString(new FileInputStream(file),true));
                    LOG.debug("Added template: "+name);
                }
            }
            catch (final Exception e) { LOG.error(e); }
        }
        return templates;
    }
    public static String getTemplate (final String templatePath) {
        try {
            final URL url = Dumbtemplate.class.getClassLoader().getResource(templatePath);
            if (url != null) {
                return Txt.streamToString(url.openStream(),true);
            }
            else { return litEmpty; }
        }
        catch (final Exception e) {
            LOG.error(e);
            return litEmpty;
        }
    }
    public static File[] getTemplateFiles (final String templateDir) {
        final File folder = new File(templateDir);
        return folder.listFiles();
    }
    public static String processTemplate (final String template, final Map<String,Object> values) {
        return new StrSubstitutor(values).replace(template);
    }
    public static String doLoop (final List<Map<String,Object>> valueList, final String loopSubject) {
        final StringBuilder sb = new StringBuilder();
        for (final Map<String,Object> values : valueList) {
            sb.append(Dumbtemplate.processTemplate(loopSubject,values));
        }
        return sb.toString();
    }
}