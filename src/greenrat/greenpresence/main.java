package greenrat.greenpresence;


import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;

import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        System.out.println("   _____                     _____                                   \n" +
                "  / ____|                   |  __ \\                                  \n" +
                " | |  __ _ __ ___  ___ _ __ | |__) | __ ___  ___  ___ _ __   ___ ___ \n" +
                " | | |_ | '__/ _ \\/ _ \\ '_ \\|  ___/ '__/ _ \\/ __|/ _ \\ '_ \\ / __/ _ \\\n" +
                " | |__| | | |  __/  __/ | | | |   | | |  __/\\__ \\  __/ | | | (_|  __/\n" +
                "  \\_____|_|  \\___|\\___|_| |_|_|   |_|  \\___||___/\\___|_| |_|\\___\\___|\n" +
                "                                                                     \n" +
                "                                                                     ");
        Scanner scanner = new Scanner(System.in);
        String appid;
        String details;
        String largeimagekey;
        String smallimagekey;
        String status;

        System.out.print("applicationId: ");
        appid = scanner.nextLine();

        System.out.print("Details: ");
        details = scanner.nextLine();

        System.out.print("Status: ");
        status = scanner.nextLine();

        System.out.print("Large Image Key: ");
        largeimagekey = scanner.nextLine();

        System.out.print("Small Image Key: ");
        smallimagekey = scanner.nextLine();

        DiscordRPC lib = DiscordRPC.INSTANCE;
        String applicationId = appid;
        String steamId = "";
        DiscordEventHandlers handlers = new DiscordEventHandlers();
        handlers.ready = (user) -> System.out.println("Discord RPC Aktif edildi!");
        lib.Discord_Initialize(applicationId, handlers, true, steamId);
        DiscordRichPresence presence = new DiscordRichPresence();
        presence.startTimestamp = System.currentTimeMillis() / 1000; 
        presence.details = details;
        presence.state = status;
        presence.largeImageKey = largeimagekey;
        presence.smallImageKey = smallimagekey;
        lib.Discord_UpdatePresence(presence);
      
        new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                lib.Discord_RunCallbacks();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ignored) {}
            }
        }, "RPC-Callback-Handler").start();
    }
    }


