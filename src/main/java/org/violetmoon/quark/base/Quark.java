package org.violetmoon.quark.base;

import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackLocationInfo;
import net.minecraft.server.packs.PackSelectionConfig;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.tags.TagKey;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.event.AddPackFindersEvent;
import net.neoforged.neoforgespi.locating.IModFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.violetmoon.quark.base.config.QuarkGeneralConfig;
import org.violetmoon.quark.base.proxy.ClientProxy;
import org.violetmoon.quark.base.proxy.CommonProxy;
import org.violetmoon.quark.integration.claim.FlanIntegration;
import org.violetmoon.quark.integration.claim.IClaimIntegration;
import org.violetmoon.zeta.Zeta;
import org.violetmoon.zeta.multiloader.Env;
import org.violetmoon.zetaimplforge.ForgeZeta;

import java.nio.file.Path;
import java.text.ParseException;
import java.util.*;

@Mod(Quark.MOD_ID)
public class Quark {

	public static final String MOD_ID = "quark";

	public static final Logger LOG = LogManager.getLogger(MOD_ID);

	public static final Zeta ZETA = new ForgeZeta(MOD_ID, LogManager.getLogger("quark-zeta"));
	public static final String ODDITIES_ID = ZETA.isProduction ? "quarkoddities" : "quark";

	public static Quark instance;
	public static CommonProxy proxy;

	public Quark(IEventBus bus) {
		instance = this;
		ZETA.start();

		proxy = Env.unsafeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);
		proxy.start();

		bus.addListener(Quark::registerCapabilities);
	}

	public static final IClaimIntegration FLAN_INTEGRATION = ZETA.modIntegration("flan",
			() -> FlanIntegration::new,
			() -> IClaimIntegration.Dummy::new);

    public static ResourceLocation asResource(String path) {
		return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
	}

	public static <T> ResourceKey<T> asResourceKey(ResourceKey<? extends Registry<T>> base, String name) {
		return ResourceKey.create(base, asResource(name));
	}

	public static <T> TagKey<T> asTagKey(ResourceKey<? extends Registry<T>> base, String name) {
		return TagKey.create(base, asResource(name));
	}

	public static void registerCapabilities(RegisterCapabilitiesEvent event) {
	}

	public static void crashOnOldConfig(String moduleName, int parseFailedPosition) throws ParseException {
		final String OLD_VERSION = "1.20.1", THIS_VERSION = "1.21.1";

		String err = "Quark has detected you are likely using a " + OLD_VERSION + " config file." +
				" We recommend you do not do this in " + THIS_VERSION + " as the format has changed." +
				" We recommend you delete your old config and then re-create it with the in-game config menu to prevent issues.";

		Quark.LOG.error("Caught by: " + moduleName);

		throw new ParseException(err, parseFailedPosition);
	}
}
