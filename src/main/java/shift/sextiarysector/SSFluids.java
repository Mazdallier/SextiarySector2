package shift.sextiarysector;

import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import shift.sextiarysector.event.ClientEventHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SSFluids {

	public static Fluid takumiTea;
	public static Fluid drinkingWater;

	public static Fluid steam;

	public static void initFluids(){

		takumiTea =  new SSFluid("takumi_tea", 0, 0x006400, 5, 2.0f);
		drinkingWater =  new SSFluid("drinking_water", 0, 0xF0FFFF, 4, 1.0f);

		steam = new SSFluid("steam", 1, 0xFFFFFF, 1, 1.0f);

	}

	public static void postFluids(){

		for(int i=1;i<=FluidRegistry.getRegisteredFluids().size();i++){
			FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluid(i), new ItemStack(SSBlocks.fluidCrafter,1,i), new ItemStack(SSBlocks.fluidCrafter,1,0));
		}

	}

	public static class SSFluid extends Fluid {

		private int type;
		private int color;

		public int moisture;
		public float moistureSaturation;

		public SSFluid(String fluidName, int type, int color, int moisture, float moistureSaturation) {
			super(fluidName);
			this.type = type;
			this.color = color;
			this.moisture = moisture;
			this.moistureSaturation = moistureSaturation;
			FluidRegistry.registerFluid(this);
		}

		public String getUnlocalizedName()
	    {
	        return "fluid.ss." + this.unlocalizedName;
	    }

		public int getColor()
	    {
	        return color;
	    }

		@SideOnly(Side.CLIENT)
		public IIcon getStillIcon()
	    {
			switch(type){
				case 0:return ClientEventHandler.waterStill;
				case 1:return ClientEventHandler.portal;
			}
	        return ClientEventHandler.waterStill;
	    }

		@SideOnly(Side.CLIENT)
	    public IIcon getFlowingIcon()
	    {
			switch(type){
			case 0:return ClientEventHandler.waterStill;
			case 1:return ClientEventHandler.portal;
			}
	        return ClientEventHandler.waterStill;
	    }

	}

}
