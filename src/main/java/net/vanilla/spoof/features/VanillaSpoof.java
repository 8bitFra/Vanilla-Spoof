/*
 * Copyright (c) 2014-2021 Wurst-Imperium and contributors.
 *
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package net.vanilla.spoof.features;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.mixin.networking.accessor.CustomPayloadC2SPacketAccessor;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.c2s.play.CustomPayloadC2SPacket;
import net.vanilla.spoof.Main;
import net.vanilla.spoof.events.ConnectionPacketOutputListener;

public final class VanillaSpoof implements ConnectionPacketOutputListener
{
	public VanillaSpoof()
	{
		Main.eventManager.add(ConnectionPacketOutputListener.class, this);
	}
	
	@Override
	public void onSentConnectionPacket(ConnectionPacketOutputEvent event)
	{	
		if(!(event.getPacket() instanceof CustomPayloadC2SPacket))
			return;
		
		CustomPayloadC2SPacketAccessor packet = (CustomPayloadC2SPacketAccessor)event.getPacket();
		if(packet.getChannel().getNamespace().equals("minecraft") && packet.getChannel().getPath().equals("register"))
			event.cancel();
		
		if(packet.getChannel().getNamespace().equals("minecraft") && packet.getChannel().getPath().equals("brand"))
			event.setPacket(new CustomPayloadC2SPacket(CustomPayloadC2SPacket.BRAND,new PacketByteBuf(Unpooled.buffer()).writeString("vanilla")));
		
		if(packet.getChannel().getNamespace().equals("fabric"))
			event.cancel();
	}
	
}
