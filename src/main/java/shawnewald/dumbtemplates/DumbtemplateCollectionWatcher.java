package shawnewald.dumbtemplates;

import java.util.Date;
import java.util.TimerTask;
import java.io.File;

/**
 * Dumbtemplates - Watches TemplateCollection for changes.
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
public abstract class DumbtemplateCollectionWatcher extends TimerTask {

  @Override
  public final void run() {
      final Long timestamp = new Date().getTime() - DumbtemplateConfig.templateCheckInterval;
    for (final File f : Dumbtemplate.getTemplateFiles(DumbtemplateConfig.templatePath)) {
        if (f.lastModified() > timestamp) {
            onChange();
        }
    }
  }

  protected abstract void onChange();
}