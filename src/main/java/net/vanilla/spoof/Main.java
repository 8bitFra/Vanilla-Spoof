package net.vanilla.spoof;

import net.fabricmc.api.ModInitializer;
import net.vanilla.spoof.features.VanillaSpoof;
import net.vanilla.spoof.event.EventManager;

public class Main implements ModInitializer {
	public static EventManager eventManager;
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		eventManager=new EventManager();
		
		final VanillaSpoof vanillaSpoof = new VanillaSpoof();
		System.out.println("Vanilla Spoof loaded");
	}
	
	/*public EventManager getEventManager()
	{
		return eventManager;
	}*/
}
