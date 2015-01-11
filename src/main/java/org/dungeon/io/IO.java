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

package org.dungeon.io;

import org.dungeon.game.Game;
import org.dungeon.util.Constants;
import org.dungeon.util.Percentage;
import org.dungeon.util.Poem;
import org.dungeon.util.Utils;

import java.awt.Color;

/**
 * IO class that encapsulates all Input/Output operations. This is the only class that should call the writing methods
 * of the game window.
 *
 * @author Bernardo Sulzbach - 13/09/2014
 */
public final class IO {

  // How many milliseconds the game 'lags' after writing a string of battle output.
  private static final int WRITE_BATTLE_STRING_WAIT = 300;

  /**
   * Writes a string of text using the default output color.
   *
   * @param string the string of text to be written.
   */
  public static void writeString(String string) {
    writeString(string, Constants.FORE_COLOR_NORMAL);
  }

  /**
   * Writes a string of text using a specific color.
   *
   * @param string the string of text to be written.
   * @param color  the color of the text.
   */
  public static void writeString(String string, Color color) {
    writeString(string, color, true);
  }

  /**
   * Writes a string of text using a specific color.
   *
   * @param string  the string of text to be written.
   * @param color   the color of the text. If {@code null}, the default color will be used.
   * @param newLine if true, a newline will be added to the end of the string after its end is cleared.
   */
  public static void writeString(String string, Color color, boolean newLine) {
    writeString(string, color, newLine, true, 0);
  }

  /**
   * Writes a string of text using a specific color.
   *
   * @param string     the string of text to be written.
   * @param color      the color of the text. If {@code null}, the default color will be used.
   * @param newLine    if true, a newline will be added to the end of the string after its end is cleared.
   * @param scrollDown if true, the TextPane will be scrolled down after writing.
   */
  private static void writeString(String string, Color color, boolean newLine, boolean scrollDown) {
    writeString(string, color, newLine, scrollDown, 0);
  }

  /**
   * Writes a string of text using a specific color and waiting for a given amount of milliseconds.
   *
   * @param string     the string of text to be written.
   * @param color      the color of the text.
   * @param newLine    if true, a newline will be added to the end of the string after its end is cleared.
   * @param scrollDown if true, the TextPane will be scrolled down after writing.
   * @param wait       how many milliseconds the application should sleep after writing the string.
   */
  private static void writeString(String string, Color color, boolean newLine, boolean scrollDown, int wait) {
    if (color == null) {
      DLogger.warning("Passed null as a Color to writeString.");
    }
    String processedString = newLine ? Utils.clearEnd(string) + '\n' : Utils.clearEnd(string);
    Game.getGameWindow().writeToTextPane(processedString, color, scrollDown);
    if (wait > 0) {
      Sleeper.sleep(wait);
    }
  }

  /**
   * Writes a string of text using a specific color and waits for the default battle wait interval.
   *
   * @param string the string of text to be written.
   * @param color  the color of the text.
   */
  public static void writeBattleString(String string, Color color) {
    writeString(string, color, true, true, WRITE_BATTLE_STRING_WAIT);
  }

  /**
   * Writes a line separator, terminating a line or leaving one blank line.
   */
  public static void writeNewLine() {
    writeString("");
  }

  /**
   * Writes a key, value pair separated with enough dots to fill a line. The key and value are written using the
   * default color and the filling dots are written using a darker color.
   *
   * @param key   the key string.
   * @param value the value string.
   */
  public static void writeKeyValueString(String key, String value) {
    writeKeyValueString(key, value, Constants.FORE_COLOR_NORMAL, Constants.FORE_COLOR_DARKER);
  }

  /**
   * Writes a key, value pair separated with enough dots to fill a line using the specified colors.
   *
   * @param key       the key string.
   * @param value     the value string.
   * @param textColor the color used to write the key and the value.
   * @param fillColor the color used to write the dots.
   */
  private static void writeKeyValueString(String key, String value, Color textColor, Color fillColor) {
    int dots = Constants.COLS - key.length() - value.length();  // The amount of dots necessary.
    if (dots < 0) {
      DLogger.warning("Passed too large strings to writeKeyValueString.");
    }
    writeString(key, textColor, false);
    StringBuilder stringBuilder = new StringBuilder();
    for (; dots > 0; dots--) {
      stringBuilder.append('.');
    }
    writeString(stringBuilder.toString(), fillColor, false);
    writeString(value, textColor, true);
  }

  public static void writePoem(Poem poem) {
    writeString(poem.toString(), Constants.FORE_COLOR_NORMAL, false, false);
  }

  /**
   * Prints a bar to the window.
   *
   * @param percentage the percentage of the attribute.
   * @param fore       the foreground Color.
   */
  public static void writeNamedBar(String name, Percentage percentage, Color fore) {
    if (name.length() > Constants.BAR_NAME_LENGTH) {
      DLogger.warning("Passed a too long bar name.");
    }
    writeString(name, Constants.FORE_COLOR_NORMAL, false);
    int size = Constants.COLS - Constants.BAR_NAME_LENGTH;
    // Perform a ceiling, as small percentages must be represented by at least one bar.
    int bars = (int) (size * percentage.toDouble()) + 1;
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < Constants.BAR_NAME_LENGTH - name.length(); i++) {
      sb.append(' ');
    }
    for (int i = 0; i < size; i++) {
      if (i < bars) {
        sb.append('|');
      } else {
        sb.append(' ');
      }
    }
    writeString(sb.toString(), fore, true);
  }

}
