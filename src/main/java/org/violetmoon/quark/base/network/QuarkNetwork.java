package org.violetmoon.quark.base.network;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import org.violetmoon.quark.base.Quark;
import org.violetmoon.quark.base.network.message.DoubleDoorMessage;
import org.violetmoon.quark.base.network.message.HarvestMessage;
import org.violetmoon.quark.base.network.message.InventoryTransferMessage;
import org.violetmoon.quark.base.network.message.ScrollOnBundleMessage;
import org.violetmoon.quark.base.network.message.ShareItemC2SMessage;
import org.violetmoon.quark.base.network.message.ShareItemS2CMessage;
import org.violetmoon.quark.base.network.message.SortInventoryMessage;
import org.violetmoon.quark.catnip.net.base.BasePacketPayload;
import org.violetmoon.quark.catnip.net.base.CatnipPacketRegistry;

import java.util.Locale;

public enum QuarkNetwork implements BasePacketPayload.PacketTypeProvider {
	// Base Quark
	SORT_INVENTORY_MESSAGE(SortInventoryMessage.class, SortInventoryMessage.STREAM_CODEC),
	INVENTORY_TRANSFER_MESSAGE(InventoryTransferMessage.class, InventoryTransferMessage.STREAM_CODEC),
	DOUBLE_DOOR_MESSAGE(DoubleDoorMessage.class, DoubleDoorMessage.STREAM_CODEC),
	HARVEST_MESSAGE(HarvestMessage.class, HarvestMessage.STREAM_CODEC),
	SHARE_ITEM_C2S_MESSAGE(ShareItemC2SMessage.class, ShareItemC2SMessage.STREAM_CODEC),
	SCROLL_ON_BUNDLE_MESSAGE(ScrollOnBundleMessage.class, ScrollOnBundleMessage.STREAM_CODEC),

	// Clientbound
	SHARE_ITEM_S2C_MESSAGE(ShareItemS2CMessage.class, ShareItemS2CMessage.STREAM_CODEC);

	// bump when you change messages
	public static final int PROTOCOL_VERSION = 4;

	private final CatnipPacketRegistry.PacketType<?> type;

	<T extends BasePacketPayload> QuarkNetwork(Class<T> clazz, StreamCodec<? super RegistryFriendlyByteBuf, T> codec) {
		String name = this.name().toLowerCase(Locale.ROOT);
		this.type = new CatnipPacketRegistry.PacketType<>(
				new CustomPacketPayload.Type<>(Quark.asResource(name)),
				clazz, codec
		);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T extends CustomPacketPayload> CustomPacketPayload.Type<T> getType() {
		return (CustomPacketPayload.Type<T>) this.type.type();
	}

	public static void init() {
		CatnipPacketRegistry packetRegistry = new CatnipPacketRegistry(Quark.MOD_ID, PROTOCOL_VERSION);
		for (QuarkNetwork packet : QuarkNetwork.values()) {
			packetRegistry.registerPacket(packet.type);
		}
		packetRegistry.registerAllPackets();
	}
}
