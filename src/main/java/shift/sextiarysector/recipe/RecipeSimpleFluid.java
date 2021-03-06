package shift.sextiarysector.recipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

public class RecipeSimpleFluid {


	protected final HashMap<ItemStack, Object[]> metaSimpleMachineList = new HashMap<ItemStack, Object[]>();
	protected final HashMap<String, Object[]> oreSimpleMachineList = new HashMap<String, Object[]>();

	public void add(ItemStack par1ItemStack, ItemStack par2ItemStack, FluidStack par3FluidStack)
    {
        metaSimpleMachineList.put(par1ItemStack, new Object[]{par2ItemStack,par3FluidStack});
    }

	public void add(String par1String, ItemStack par2ItemStack, FluidStack par3FluidStack)
    {
		oreSimpleMachineList.put(par1String, new Object[]{par2ItemStack,par3FluidStack});
    }

	public ItemStack getResult(ItemStack item)
    {
        if (item == null)
        {
            return null;
        }
        for (String key : oreSimpleMachineList.keySet()) {
        	ArrayList<ItemStack> items = OreDictionary.getOres(key);
        	for(int i = 0; i< items.size() ; i++){
        		if(checkItem(item,items.get(i))){
        			return ((ItemStack) oreSimpleMachineList.get(key)[0]).copy();
        		}
        	}
        }

        Iterator iterator = this.metaSimpleMachineList.entrySet().iterator();
        Entry entry;

        do
        {
            if (!iterator.hasNext())
            {
                return null;
            }

            entry = (Entry)iterator.next();
        }
        while (!this.checkItem(item, (ItemStack)entry.getKey()));

        return (ItemStack) ((Object[])entry.getValue())[0];

        //return metaSimpleMachineList.get( (Arrays.asList( item.itemID, item.getItemDamage() ) ));
    }

	public FluidStack getFluidResult(ItemStack item)
    {
        if (item == null)
        {
            return null;
        }
        for (String key : oreSimpleMachineList.keySet()) {
        	ArrayList<ItemStack> items = OreDictionary.getOres(key);
        	for(int i = 0; i< items.size() ; i++){
        		if(checkItem(item,items.get(i))){
        			return ((FluidStack) oreSimpleMachineList.get(key)[1]).copy();
        		}
        	}
        }

        Iterator iterator = this.metaSimpleMachineList.entrySet().iterator();
        Entry entry;

        do
        {
            if (!iterator.hasNext())
            {
                return null;
            }

            entry = (Entry)iterator.next();
        }
        while (!this.checkItem(item, (ItemStack)entry.getKey()));

        return (FluidStack) ((Object[])entry.getValue())[1];

        //return metaSimpleMachineList.get( (Arrays.asList( item.itemID, item.getItemDamage() ) ));
    }

	private boolean checkItem(ItemStack p_151397_1_, ItemStack p_151397_2_)
    {
        return p_151397_2_.getItem() == p_151397_1_.getItem() && (p_151397_2_.getItemDamage() == 32767 || p_151397_2_.getItemDamage() == p_151397_1_.getItemDamage());
    }

	public Map<ItemStack, Object[]> getMetaList()
    {
        return metaSimpleMachineList;
    }

	public Map<String, Object[]> getOreList()
    {
        return oreSimpleMachineList;
    }

}
