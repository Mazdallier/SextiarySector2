package shift.sextiarysector.recipe;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import shift.sextiarysector.SSBlocks;
import shift.sextiarysector.SSItems;
import shift.sextiarysector.block.BlockMonitor;
import shift.sextiarysector.block.BlockMonitor.MonitorType;


public class RecipesFurnaceCraft {

	public static void addRecipes(FurnaceCraftingManager p_77608_1_)
    {

		//飲み物
		p_77608_1_.addRecipe(new ShapelessOreRecipe(new ItemStack(SSItems.drinkingWaterSmallBottle, 1),
				new Object[] {
			new ItemStack(Items.potionitem),
			}));

		//スライム
		p_77608_1_.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.slime_ball, 2),
				new Object[] {
			SSItems.dustWaterLily,
			"craftingSugar",
			Items.water_bucket,
			}));

		p_77608_1_.addRecipe(new ShapelessOreRecipe(new ItemStack(SSItems.blueStoneSlimeBall, 1),
				new Object[] {
			SSItems.blueStoneDust,
			Items.slime_ball
			}));

		p_77608_1_.addRecipe(new ShapelessOreRecipe(new ItemStack(SSItems.steelIngot, 1),
				new Object[] {
			"dustCoal",
			"dustIron"
			}));

		p_77608_1_.addRecipe(new ShapelessOreRecipe(new ItemStack(SSItems.ninjaIngot, 1),
				new Object[] {
			"dustMithril",
			"dustDiamond"
			}));

		p_77608_1_.addRecipe(new ShapedOreRecipe(new ItemStack(SSItems.unit, 1),
				new Object[] { "xxx", "xyx","xxx",
			Character.valueOf('y'), SSItems.blueStoneSlimeBall,
			Character.valueOf('x'), "cobblestone",
			}));

		//Hammer
        p_77608_1_.addRecipe(new ShapedOreRecipe(new ItemStack(SSItems.hammer, 1),
				new Object[] { "xxx", " y "," y ",
			Character.valueOf('x'), "ingotIron",
			Character.valueOf('y'), "stickWood",
			}));

        //鉄のリング
        p_77608_1_.addRecipe(new ShapedOreRecipe(new ItemStack(SSItems.ironRing, 1),
				new Object[] { " x ", "x x"," x ",
			Character.valueOf('x'), "ingotIron",
			}));

        //クリーパーリング
        p_77608_1_.addRecipe(new ShapelessOreRecipe(new ItemStack(SSItems.creeperRing, 1),
				new Object[] {
        	"ringIron",
			SSItems.objectReactor,
			BlockMonitor.getMonitor(MonitorType.creeper)
			}));



        //液体カマド
        p_77608_1_.addRecipe(new ShapedOreRecipe(new ItemStack(SSBlocks.fluidFurnace, 1),
				new Object[] { "xxx", "xyx","xxx",
			Character.valueOf('x'), "cobblestone",
			Character.valueOf('y'), Blocks.glass_pane,
			}));

        //魔法カマド
        p_77608_1_.addRecipe(new ShapedOreRecipe(new ItemStack(SSBlocks.magicFurnace, 1),
				new Object[] { "xxx", "xyx","xxx",
			Character.valueOf('x'), "cobblestone",
			Character.valueOf('y'), SSItems.magicDust,
			}));

        //乾燥機
        p_77608_1_.addRecipe(new ShapedOreRecipe(new ItemStack(SSBlocks.foodSmokers, 1),
				new Object[] { "xxx", "xyx","xxx",
			Character.valueOf('x'), "ingotSteel",
			Character.valueOf('y'), Blocks.glass_pane,
			}));

        //time
        p_77608_1_.addRecipe(new ShapedOreRecipe(new ItemStack(SSBlocks.chunkLoader, 1),
				new Object[] { "xyx", "yzy","xyx",
			Character.valueOf('x'), "ingotIron",
			Character.valueOf('y'), Blocks.obsidian,
			Character.valueOf('z'), Items.clock,
			}));

    }

	public static void addVanillaRecipes()
    {

		for( Map.Entry<ItemStack, ItemStack> e : ((HashMap<ItemStack,ItemStack>)FurnaceRecipes.smelting().getSmeltingList()).entrySet()){

    		FurnaceCraftingManager.getInstance().addShapelessRecipe(e.getValue(), new Object[]{e.getKey()});

    	}

    }

}
