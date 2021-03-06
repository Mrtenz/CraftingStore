package net.craftingstore.bungee.timers;

import net.craftingstore.CraftingStoreAPI;
import net.craftingstore.Donation;
import net.craftingstore.bungee.CraftingStoreBungee;
import net.craftingstore.bungee.events.DonationReceivedEvent;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.UUID;
import java.util.logging.Level;

public class DonationCheckTimer implements Runnable {

    private Plugin instance;

    public DonationCheckTimer(Plugin instance) {
        this.instance = instance;
    }

    public void run() {
        try {
            Donation[] donations = CraftingStoreAPI.getInstance().getQueries(CraftingStoreBungee.getInstance().getKey());

            for (Donation donation : donations) {
                String plainUuid = donation.getUuid();
                UUID uuid = null;
                if (plainUuid != null && !plainUuid.isEmpty()) {
                    String formattedUuid = plainUuid.replaceFirst("([0-9a-fA-F]{8})([0-9a-fA-F]{4})([0-9a-fA-F]{4})([0-9a-fA-F]{4})([0-9a-fA-F]+)", "$1-$2-$3-$4-$5");
                    uuid = UUID.fromString(formattedUuid);
                }

                final DonationReceivedEvent event = new DonationReceivedEvent(donation.getCommand(), donation.getMcName(), uuid, donation.getPackageName(), donation.getPackagePrice(), donation.getCouponDiscount());
                instance.getProxy().getPluginManager().callEvent(event);

                if (!event.isCancelled()) {
                    instance.getProxy().getPluginManager().dispatchCommand(instance.getProxy().getConsole(), event.getCommand());
                }
            }
        } catch (Exception e) {
            instance.getLogger().log(Level.SEVERE, "An error occurred while checking for donations.", e);
        }
    }

}
