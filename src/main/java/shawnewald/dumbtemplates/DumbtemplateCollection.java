package shawnewald.dumbtemplates;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Dumbtemplates - Template Collection class.
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
public final class DumbtemplateCollection {
    private static DumbtemplateCollection instance;
    private static final ConcurrentMap<String,String> templates = new ConcurrentHashMap();
    private static final TimerTask watcher;
    private static final Timer timer;
    static {
        templates.putAll(Dumbtemplate.getTemplates(DumbtemplateConfig.templatePath));
        if (DumbtemplateConfig.watchTemplateChanges) {
            watcher = new DumbtemplateCollectionWatcher() {
                @Override
                protected void onChange() {
                    templates.putAll(Dumbtemplate.getTemplates(DumbtemplateConfig.templatePath));
                }
            };
            timer = new Timer();
            timer.schedule(watcher, 0, DumbtemplateConfig.templateCheckInterval);
        }
        else { watcher =  null; timer = null; }
    }
    private DumbtemplateCollection () {}
    public static DumbtemplateCollection getInstance () {
        if (instance == null) {
            instance = new DumbtemplateCollection();
        }
        return instance;
    }
    public String getTemplate (final String key) {
        return templates.get(key);
    }
}
