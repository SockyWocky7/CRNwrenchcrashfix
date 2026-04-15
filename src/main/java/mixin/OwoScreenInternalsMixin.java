package com.sockywocky.wrenchpatch.mixin;

import io.wispforest.owo.client.screens.ScreenInternals;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.neoforged.neoforge.client.event.ScreenEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ScreenInternals.Client.class, remap = false)
public class OwoScreenInternalsMixin {

    @Inject(
            method = "lambda$init$0",
            at = @At("HEAD"),
            cancellable = true,
            remap = false
    )
    private static void guardNullMenu(ScreenEvent.Init.Post event, CallbackInfo ci) {
        if (event.getScreen() instanceof MenuAccess<?> menuAccess) {
            AbstractContainerMenu menu = menuAccess.getMenu();
            if (menu == null) {
                ci.cancel();
            }
        }
    }
}