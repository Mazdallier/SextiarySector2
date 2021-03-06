package shift.sextiarysector.event;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import shift.sextiarysector.api.SextiarySectorAPI;
import shift.sextiarysector.api.event.PlayerEatenEvent;
import shift.sextiarysector.player.EntityPlayerManager;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class PlayerStatusEventHandler {

	/** 食べ物を食べた時の動作 */
	@SubscribeEvent
	public void onPlayerUseItemEvent(PlayerUseItemEvent.Finish event) {

		ItemStack item = event.item;
		EntityPlayer player = (EntityPlayer) event.entity;

		MinecraftForge.EVENT_BUS.post(new PlayerEatenEvent(player, item));

	}

	/**
	 * 水分関係
	 */

	// ブロック破壊
	@SubscribeEvent
	public void onBlockBreakEvent(BreakEvent event) {

		if (event.world.isRemote) {
			return;
		}

		EntityPlayer player = event.getPlayer();

		float i = 0.025f;

		if (BiomeDictionary.isBiomeOfType(event.world.getBiomeGenForCoords(event.x, event.y), Type.SANDY)) {
			i *= 4;
		}
		if (event.world.getBlock(event.x, event.y, event.z).getMaterial() == Material.sand) {
			i *= 2;
		}

		SextiarySectorAPI.addMoistureExhaustion(player, i);

	}

	// ダメージ
	@SubscribeEvent
	public void onLivingHurtEvent(LivingHurtEvent event) {

		if (event.entityLiving.worldObj.isRemote|| !(event.entityLiving instanceof EntityPlayer)) {
			return;
		}

		EntityPlayer player = (EntityPlayer) event.entityLiving;

		SextiarySectorAPI.addMoistureExhaustion(player, event.ammount * 0.4f);

	}

	// ジャンプ
	@SubscribeEvent
	public void onLivingJump(LivingJumpEvent event) {

		if (!(event.entityLiving instanceof EntityPlayer)) {
			return;
		}

		if (event.entityLiving.worldObj.isRemote) {
			return;
		}

		EntityPlayer player = (EntityPlayer) event.entityLiving;

		float i = 1;

		if (BiomeDictionary.isBiomeOfType(player.worldObj.getBiomeGenForCoords((int) player.posX, (int) player.posY), Type.DESERT)) {
			i = 2;
		}

		if (player.isSprinting()) {
			SextiarySectorAPI.addMoistureExhaustion(player, 0.8f * i);
		} else {
			SextiarySectorAPI.addMoistureExhaustion(player, 0.2f * i);
		}

	}

	/**
	 * スタミナ関係
	 */

	// ブロック破壊
	@SubscribeEvent
	public void onBlockBreakEventS(BreakEvent event) {

		if (event.world.isRemote) {
			return;
		}

		EntityPlayer player = event.getPlayer();

		float i = 0.075f;

		if (BiomeDictionary
				.isBiomeOfType(
						event.world.getBiomeGenForCoords(event.x, event.y),
						Type.DESERT)) {
			i *= 4;
		}
		if (event.world.getBlock(event.x, event.y, event.z).getMaterial() == Material.sand) {
			i *= 2;
		}

		SextiarySectorAPI.addStaminaExhaustion(player, i);

	}

	// ダメージ
	@SubscribeEvent
	public void onLivingHurtEvent2(LivingHurtEvent event) {

		if (event.entityLiving.worldObj.isRemote
				|| !(event.entityLiving instanceof EntityPlayer)) {
			return;
		}

		EntityPlayer player = (EntityPlayer) event.entityLiving;

		SextiarySectorAPI.addStaminaExhaustion(player, event.ammount * 5.0f);

	}

	// ベットで回復
	@SubscribeEvent
	public void LivingSleepingEvent(LivingUpdateEvent event) {
		if (event.entityLiving.worldObj.isRemote) {
			return;
		}

		if (!(event.entityLiving instanceof EntityPlayer)) {
			return;
		}

		if (!event.entityLiving.worldObj.isRemote) {

			EntityPlayer player = (EntityPlayer) event.entityLiving;

			if (player.isPlayerFullyAsleep()) {

				if (EntityPlayerManager.getMoistureStats(player)
						.getMoistureLevel() > 4
						&& player.getFoodStats().getFoodLevel() > 4) {
					EntityPlayerManager.getStaminaStats(player).addStats(1000,
							500);
				} else {
					EntityPlayerManager.getStaminaStats(player).addStats(300,
							100);
				}
				player.getFoodStats().addExhaustion(34f);
				SextiarySectorAPI.addMoistureExhaustion(player, 34f);

				// System.out.println("LivingSleepingEvent");
			}

		}

	}

}
