package org.violetmoon.quark.base.handler;

import com.google.common.collect.Lists;

import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;

import org.violetmoon.quark.base.Quark;
import org.violetmoon.zeta.event.bus.LoadEvent;
import org.violetmoon.zeta.event.load.ZRegister;

import java.util.List;

public class QuarkSounds {
	private static final List<SoundEvent> REGISTRY_DEFERENCE = Lists.newArrayList();

	public static final SoundEvent ITEM_CAMERA_SHUTTER = register("item.camera.shutter");

	public static final SoundEvent PET_DEVICE = register("pet.device");
	public static final SoundEvent PET_NEKO = register("pet.neko");
	public static final SoundEvent PET_SLIME = register("pet.slime");
	public static final SoundEvent PET_WIRE = register("pet.wire");

	@LoadEvent
	public static void start(ZRegister e) {
		for (SoundEvent event : REGISTRY_DEFERENCE)
			e.getRegistry().register(event, event.getLocation(), Registries.SOUND_EVENT);
	}

	public static SoundEvent register(String name) {
		SoundEvent event = SoundEvent.createVariableRangeEvent(Quark.asResource(name));
		REGISTRY_DEFERENCE.add(event);
		return event;
	}
}
