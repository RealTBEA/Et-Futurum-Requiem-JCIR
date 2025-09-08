package foxblocks.tbea;

import codechicken.microblock.BlockMicroMaterial;
import codechicken.microblock.MicroMaterialRegistry;
import ganymedes01.etfuturum.ModBlocks;
import ganymedes01.etfuturum.blocks.BaseSlab;
import ganymedes01.etfuturum.blocks.BaseSubtypesBlock;
import ganymedes01.etfuturum.blocks.ISubBlocksBlock;
import ganymedes01.etfuturum.blocks.ores.BaseSubtypesDeepslateOre;
import net.minecraft.block.BlockContainer;

/**
 * This is a copy of multipart compatibility from foxblocks
 */
public class ForgeMultiPart
{
    public static void registerBlocks(ModBlocks[] foxBlockIDs)
    {
        try
        {
            for (ModBlocks block : foxBlockIDs)
            {
                // Blocks that should never be registered because why
                if (block.isEnabled()
                        && block.get() instanceof BaseSlab == false
                        && block.get() instanceof BlockContainer == false
                        && block.get().renderAsNormalBlock() && block.get().unlocalizedName != null)
                {
                    int count = block.get() instanceof ISubBlocksBlock ? ((ISubBlocksBlock)block.get()).getTypes().length : 0;

                    switch (count)
                    {
                        case 0:
                            System.out.println(block.get().unlocalizedName);
                            MicroMaterialRegistry.registerMaterial(new BlockMicroMaterial(block.get(), 0), BlockMicroMaterial.materialKey(block.get(), 0));
                            break;
                        default:
                            for (byte i = 0; i < count; i++)
                            {
                                System.out.println(block.get().unlocalizedName);
                                MicroMaterialRegistry.registerMaterial(new BlockMicroMaterial(block.get(), i), BlockMicroMaterial.materialKey(block.get(), i));
                            }
                            break;
                    }
                }
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
