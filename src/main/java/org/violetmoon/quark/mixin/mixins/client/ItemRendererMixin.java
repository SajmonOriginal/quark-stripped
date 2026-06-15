package org.violetmoon.quark.mixin.mixins.client;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.renderer.entity.ItemRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.violetmoon.quark.content.management.module.ItemSharingModule;

@Mixin(value = ItemRenderer.class, priority = 1001) // To go after rubidium
public abstract class ItemRendererMixin {

	@ModifyExpressionValue(method = "renderQuadList", at = @At(value = "CONSTANT", args = "floatValue=1F"), require = 0) // Allow failure in case of rubidium/embeddium
	public float renderQuads(float original) {
		return ItemSharingModule.Client.alphaValue * original;
	}
}
