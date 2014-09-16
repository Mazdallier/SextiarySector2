package shift.sextiarysector.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.achievement.GuiStats;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import shift.sextiarysector.gui.GuiStatsNext;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ClientEventHandler {

    public static Minecraft mc = FMLClientHandler.instance().getClient();

    @SubscribeEvent
    public void onGuiOpenEvent(GuiOpenEvent event) {

    	if(event.gui instanceof GuiStats&&!(event.gui instanceof GuiStatsNext)){

    		/*
    		Class<GuiStats> c = GuiStats.class;

    		Field f1;
    		GuiScreen m = null;
			try {
				f1 = c.getDeclaredField( "parentGui" );
				f1.setAccessible( true );
				m = (GuiScreen) f1.get( event.gui );
			} catch (NoSuchFieldException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			} catch (SecurityException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}*/

				event.gui = new GuiStatsNext(new GuiIngameMenu(),mc.thePlayer.getStatFileWriter());



    		//System.out.println("GuiOpenEvent");
    	}

    }

    public static IIcon slotGF;

    public static IIcon[] itemGF;

    @SubscribeEvent
	public void PreTextureStitchEvent(TextureStitchEvent.Pre event){

    	//Item
    	if(event.map.getTextureType()==1){


    		slotGF = event.map.registerIcon("sextiarysector:gui/slot_gf");

    		itemGF = new IIcon[2];
    		itemGF[0] = event.map.registerIcon("sextiarysector:damage/damage_0");
    		itemGF[1] = event.map.registerIcon("sextiarysector:damage/damage_1");

    	}

    }


}