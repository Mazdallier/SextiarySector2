package shift.sextiarysector;

import net.minecraft.item.ItemStack;
import shift.sextiarysector.gui.tab.AbstractTab;
import shift.sextiarysector.gui.tab.InventoryTabEquipment;
import shift.sextiarysector.gui.tab.InventoryTabSS;
import shift.sextiarysector.gui.tab.TabManager;
import shift.sextiarysector.item.ItemShopRing;
import shift.sextiarysector.player.EquipmentType;

public class SSPlayerTabs {

	public static AbstractTab ss;

	public static AbstractTab craft;

	public static AbstractTab creeperShop;

	public static void initRecipes(){

		ss = new InventoryTabSS();
		TabManager.registerTab(ss);

		craft = new InventoryTabEquipment(EquipmentType.Unit, new ItemStack(SSItems.craftUnit));
		TabManager.registerTab(craft);

		creeperShop = new InventoryTabEquipment(EquipmentType.Ring, new ItemStack(SSItems.creeperRing));
		TabManager.registerTab(creeperShop);
		((ItemShopRing) SSItems.creeperRing).setTab(creeperShop);

	}

}
