/*
 * Copyright (C) 2014 Bernardo Sulzbach
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.dungeon.game;

import org.dungeon.io.DLogger;
import org.dungeon.io.ResourceReader;

import java.util.ArrayList;

/**
 * A Library of Hints (represented by Strings).
 * <p/>
 * Created by Bernardo Sulzbach on 30/12/14.
 */
public class HintLibrary extends Library {

  private final ArrayList<String> hints = new ArrayList<String>();

  /**
   * Returns how many hints the library has.
   * <p/>
   * This should be the first method called in this Library, as it triggers its initialization if it has not happened
   * yet.
   */
  public int getHintCount() {
    if (!isInitialized()) {
      initialize();
    }
    return hints.size();
  }

  /**
   * Return the hint at the specified index.
   */
  public String getHint(int index) {
    return hints.get(index);
  }

  @Override
  void load() {
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    ResourceReader reader = new ResourceReader(classLoader.getResourceAsStream("hints.txt"), "hints.txt");
    final String HINT = "HINT";
    while (reader.readNextElement()) {
      hints.add(reader.getValue(HINT));
    }
    reader.close();
    hints.trimToSize();
    DLogger.info("Loaded " + hints.size() + " hints.");
  }

}