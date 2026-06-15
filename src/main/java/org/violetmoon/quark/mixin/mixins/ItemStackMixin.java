package org.violetmoon.quark.mixin.mixins;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.mojang.datafixers.util.Pair;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.violetmoon.quark.content.client.hax.PseudoAccessorItemStack;
import org.violetmoon.quark.content.client.resources.AttributeSlot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin implements PseudoAccessorItemStack {

	@Shadow public abstract ItemStack copy();

	@Unique
	private Map<AttributeSlot, Multimap<Attribute, AttributeModifier>> capturedAttributes = new HashMap<>();

	/*
	@ModifyReturnValue(method = "getDisplayName", at = @At("RETURN"))
	private Component getHoverName(Component prev) {
		return ItemSharingModule.createStackComponent((ItemStack) (Object) this, (MutableComponent) prev);
	}
	*/

	@Override
	public Map<AttributeSlot, Multimap<Attribute, AttributeModifier>> quark$getCapturedAttributes() {
		return capturedAttributes;
	}

	@Override
	public void quark$capturePotionAttributes(List<Pair<Attribute, AttributeModifier>> attributes) {
		Multimap<Attribute, AttributeModifier> attributeContainer = LinkedHashMultimap.create();
		for(var pair : attributes) {
			attributeContainer.put(pair.getFirst(), pair.getSecond());
		}
		capturedAttributes.put(AttributeSlot.POTION, attributeContainer);
	}

	@Inject(method = "getTooltipLines", at = @At("HEAD"))
	private void clearCapturedTooltip(Item.TooltipContext context, Player player, TooltipFlag flag, CallbackInfoReturnable<List<Component>> cir) {
		capturedAttributes = new HashMap<>();
	}

	/* TODO: Find where this needs to go
	@ModifyReceiver(method = "getTooltipLines", at = @At(value = "INVOKE", target = "Lcom/google/common/collect/Multimap;isEmpty()Z", remap = false))
	private Multimap<Attribute, AttributeModifier> overrideAttributeTooltips(Multimap<Attribute, AttributeModifier> attributes, @Local EquipmentSlot slot) {
		if(ImprovedTooltipsModule.shouldHideAttributes()) {
			capturedAttributes.put(AttributeSlot.fromCanonicalSlot(slot), LinkedHashMultimap.create(attributes));
			return ImmutableMultimap.of();
		}
		return attributes;
	}
	 */
}
