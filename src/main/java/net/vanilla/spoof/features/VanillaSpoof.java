/*
 * Copyright (c) 2014-2023 Wurst-Imperium and contributors.
 *
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package net.vanilla.spoof.features;

import net.minecraft.network.packet.c2s.common.CustomPayloadC2SPacket;
import net.minecraft.util.Identifier;
import net.vanilla.spoof.events.ConnectionPacketOutputListener;
import net.vanilla.spoof.Main;

public final class VanillaSpoof implements ConnectionPacketOutputListener
{

	public VanillaSpoof()
	{
		Main.eventManager.add(ConnectionPacketOutputListener.class, this);
	}
	
	@Override
	public void onSentConnectionPacket(ConnectionPacketOutputEvent event)
	{
		
		if(!(event.getPacket() instanceof CustomPayloadC2SPacket packet))
			return;
		
		Identifier channel = packet.payload().id();
		
		if(channel.getNamespace().equals("minecraft") && channel.getPath().equals("register"))
			event.cancel();
			
		// Apparently the Minecraft client no longer sends its brand to the
		// server as of 23w31a
		
		// if(packet.getChannel().getNamespace().equals("minecraft")
		// && packet.getChannel().getPath().equals("brand"))
		// event.setPacket(new CustomPayloadC2SPacket(
		// CustomPayloadC2SPacket.BRAND,
		// new PacketByteBuf(Unpooled.buffer()).writeString("vanilla")));
		
		if(channel.getNamespace().equals("fabric"))
			event.cancel();
	}
	
}
