package com.cm.cm.controllers.misc;

import io.github.palexdev.materialfx.font.FontResources;


public class ResourceFinder {
    public static final FontResources[] notifyIcons;

    static{
        notifyIcons = new FontResources[]{
          FontResources.BELL, FontResources.BELL_ALT,
          FontResources.CALENDAR_ALT_DARK, FontResources.CALENDAR_SEMI_DARK,
          FontResources.CHART_PIE, FontResources.CIRCLE, FontResources.CIRCLE_EMPTY,
          FontResources.EXCLAMATION_CIRCLE, FontResources.EXCLAMATION_TRIANGLE,
          FontResources.GEAR, FontResources.GOOGLE_DRIVE, FontResources.HOME,
          FontResources.INFO_CIRCLE, FontResources.MUSIC,
          FontResources.USER, FontResources.USERS, FontResources.VIDEO,
          FontResources.X_CIRCLE
        };
    }

}
