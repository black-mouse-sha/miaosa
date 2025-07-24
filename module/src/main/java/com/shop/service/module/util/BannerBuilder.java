package com.shop.service.module.util;

import org.springframework.boot.Banner;
import org.springframework.core.env.Environment;

import java.io.PrintStream;

public class BannerBuilder implements Banner {

    private static final String BANNER =
            "  ______   __    __                              ______                                 __                     \n" +
            " /      \\ |  \\  |  \\                            /      \\                               |  \\                    \n" +
            "|  $$$$$$\\| $$  | $$  ______    ______         |  $$$$$$\\  ______    ______  __     __  \\$$  _______   ______  \n" +
            "| $$___\\$$| $$__| $$ /      \\  /      \\  ______| $$___\\$$ /      \\  /      \\|  \\   /  \\|  \\ /       \\ /      \\ \n" +
            " \\$$    \\ | $$    $$|  $$$$$$\\|  $$$$$$\\|      \\\\$$    \\ |  $$$$$$\\|  $$$$$$\\\\$$\\ /  $$| $$|  $$$$$$$|  $$$$$$\\\n" +
            " _\\$$$$$$\\| $$$$$$$$| $$  | $$| $$  | $$ \\$$$$$$_\\$$$$$$\\| $$    $$| $$   \\$$ \\$$\\  $$ | $$| $$      | $$    $$\n" +
            "|  \\__| $$| $$  | $$| $$__/ $$| $$__/ $$       |  \\__| $$| $$$$$$$$| $$        \\$$ $$  | $$| $$_____ | $$$$$$$$\n" +
            " \\$$    $$| $$  | $$ \\$$    $$| $$    $$        \\$$    $$ \\$$     \\| $$         \\$$$   | $$ \\$$     \\ \\$$     \\\n" +
            "  \\$$$$$$  \\$$   \\$$  \\$$$$$$ | $$$$$$$          \\$$$$$$   \\$$$$$$$ \\$$          \\$     \\$$  \\$$$$$$$  \\$$$$$$$\n" +
            "                              | $$                                                                             \n" +
            "                              | $$                                                                             \n" +
            "                               \\$$    ";

    @Override
    public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
        out.println(BANNER);
    }
}
