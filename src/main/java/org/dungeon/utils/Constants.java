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
package org.dungeon.utils;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.awt.*;

/**
 * Constants class is a general-purpose constant storing class.
 *
 * @author Bernardo Sulzbach
 */
public class Constants {

    public static final int COLS = 100;
    public static final int ROWS = 30;
    public static final String FILE_FOUND = "A saved campaign was found.";
    public static final String ACHIEVEMENT_UNLOCKED = "Achievement Unlocked!";
    public static final String LEVEL_UP = "Level up!";
    public static final String SUICIDE_ATTEMPT_1 = "You cannot attempt suicide.";
    public static final String SUICIDE_ATTEMPT_2 = "You cannot target yourself.";

    // The string used to alert the player about invalid input.
    public static final String INVALID_INPUT = "Invalid input.";

    // Selection entry default format.
    // Conforming to this format increases the uniformity of the output of the game, making it more readable.
    public static final String COMMAND_HELP_FORMAT = "%-20s %s";
    public static final String LIST_ENTRY_FORMAT = "%-15s%s";

    // Inventory strings.
    public static final String INVENTORY_FULL = "Inventory is full.";

    // Item not found messages.
    public static final String NOT_EQUIPPING_A_WEAPON = "You are not equipping a weapon.";
    public static final String CANT_SEE_ANYTHING = "It's too dark, you can't see anything.";
    public static final String HERO_ID = "HERO";
    public static final String INVALID_COMMAND = "'%s' is not a command.";
    public static final String SUGGEST_COMMANDS = "See 'commands' for a list of commands.";

    // DateFormats for time and date printing.
    public static final DateTimeFormatter TIME_FORMAT = DateTimeFormat.forPattern("HH:mm:ss");
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormat.forPattern("dd/MM/yyyy");
    public static final Color FORE_COLOR_NORMAL = Color.LIGHT_GRAY;
    public static final Color FORE_COLOR_DARKER = Color.GRAY;
    public static final Color DEFAULT_BACK_COLOR = Color.BLACK;
    public static final Color HEALTH_BAR_COLOR = Color.GREEN;
    public static final Color LEVEL_BAR_COLOR = Color.YELLOW;

    // How many characters the bar name should occupy.
    public static final int BAR_NAME_LENGTH = 16;
    public static final String UNARMED_ID = "";
    // Basic version information. (19/10/2014)
    private static final String VERSION = "v0.0.3";
    private static final String CODENAME = "Bowman";
    // The name used as the window title. Contains the game's name, version and its version's codename.
    public static final String TITLE = String.format("Dungeon %s (%s)", VERSION, CODENAME);

}