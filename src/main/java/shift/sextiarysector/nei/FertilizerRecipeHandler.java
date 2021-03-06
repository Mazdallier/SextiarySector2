package shift.sextiarysector.nei;

import java.awt.Rectangle;
import java.util.ArrayList;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import shift.sextiarysector.api.agriculture.IFertilizer;
import shift.sextiarysector.module.FertilizerManager;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;

public class FertilizerRecipeHandler  extends TemplateSSRecipeHandler{

	public class FertilizerPair extends CachedRecipe
    {
        public FertilizerPair(ItemStack fertilizer, ItemStack before, ItemStack after)
        {
            fertilizer.stackSize = 1;
            before.stackSize = 1;
            this.fertilizer = new PositionedStack(fertilizer, 51, 6 +36);
            this.before = new PositionedStack(before, 51, 6);
            this.after = new PositionedStack(after, 111, 24);
        }

        @Override
		public PositionedStack getIngredient()
        {
            int cycle = cycleticks / 48;
            /*
            if(ingred.item.getItemDamage() == -1)
            {
                PositionedStack stack = ingred.copy();
                int maxDamage = 0;
                do
                {
                    maxDamage++;
                    stack.item.setItemDamage(maxDamage);
                }
                while(NEIClientUtils.isValidItem(stack.item));

                stack.item.setItemDamage(cycle % maxDamage);
                return stack;
            }*/

            this.randomRenderPermutation(before, cycleticks / 20);

            return before;
        }

        @Override
		public PositionedStack getResult()
        {
            return after;
        }

        public PositionedStack getOtherStack() {
        	return this.fertilizer;
        }

        /*
        @Override
		public PositionedStack getOtherStack()
        {
            return afuels.get((cycleticks/48) % afuels.size()).stack;
        }*/

        PositionedStack before;
        PositionedStack fertilizer;
        PositionedStack after;
    }

	public PositionedStack getResult() {
	    return null;
	  }

	@Override
	  public String getOverlayIdentifier() {
	    return getHandlerName();
	  }

	@Override
	public void loadTransferRects() {
	    transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(74, 23, 24, 18), getHandlerName()));
	}

	@Override
    public void loadCraftingRecipes(String outputId, Object... results)
    {
        if(outputId.equals(getHandlerName()) && getClass() == getHandlerClass())//don't want subclasses getting a hold of this
        {
        	ArrayList<IFertilizer> recipes = this.getRecipe();

            for(IFertilizer recipe : recipes)
            {

            	arecipes.add(new FertilizerPair(recipe.getFertilizer(),recipe.getBefore(),recipe.getAfter()));


            }

        }
        else
        {
            super.loadCraftingRecipes(outputId, results);
        }
    }

	@Override
    public void loadCraftingRecipes(ItemStack result)
    {

		ArrayList<IFertilizer> recipes = this.getRecipe();

        if(recipes == null)return;
        for(IFertilizer recipe : recipes)
        {
            ItemStack item = recipe.getAfter();
            if(NEIServerUtils.areStacksSameType(item, result))
            {
                arecipes.add(new FertilizerPair(recipe.getFertilizer(),recipe.getBefore(),recipe.getAfter()));
            }
        }
    }

	@Override
    public void loadUsageRecipes(ItemStack ingredient)
    {

		ArrayList<IFertilizer> recipes = this.getRecipe();

        if(recipes == null)return;
        for(IFertilizer recipe : recipes)
        {;
            if(this.checkItem(ingredient, recipe.getFertilizer()) || this.checkItem(ingredient, recipe.getBefore()))
            {
            	arecipes.add(new FertilizerPair(recipe.getFertilizer(),recipe.getBefore(),recipe.getAfter()));
            }
        }
    }

	private boolean checkItem(ItemStack p_151397_1_, ItemStack p_151397_2_)
    {
        return p_151397_2_.getItem() == p_151397_1_.getItem() && (p_151397_2_.getItemDamage() == 32767 || p_151397_2_.getItemDamage() == p_151397_1_.getItemDamage());
    }

	@Override
    public void drawExtras(int recipe)
    {
        //drawProgressBar(52, 25, 176, 0, 14, 14, 48, 7);
        drawProgressBar(74, 23, 176, 14, 24, 16, 48, 0);
    }

	private Class<? extends FertilizerRecipeHandler> getHandlerClass(){
		return FertilizerRecipeHandler.class;
	}
	private String getHandlerName(){
		return "SS_Fertilizer";
	}
	private ArrayList<IFertilizer> getRecipe(){
		return FertilizerManager.fertilizers;
	}
	String getGuiRecipeName(){
		return "ss.fertilizer";
	}

	public Class<? extends GuiContainer> getGuiClass(){
		return null;
	}

	@Override
	public String getRecipeName() {
		return I18n.format("nei."+getGuiRecipeName());
	}

	@Override
	public String getGuiTexture() {
		return "sextiarysector:textures/guis/fertilizer_nei.png";
	}

}
