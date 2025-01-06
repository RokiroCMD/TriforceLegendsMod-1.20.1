package net.rokiro.rokiromod.util;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.rokiro.rokiromod.networking.ModPackets;
import org.apache.logging.log4j.core.jmx.Server;

public class RupeesData {

    public static int addRupee(PlayerEntity player, int amount){

        NbtCompound nbt = ((IEntityDataSaver) (player)).getPersistentData();
        int rupees = nbt.getInt("rupees");

        rupees += amount;

        nbt.putInt("rupees",rupees);
        syncRupee(rupees, (ServerPlayerEntity) player);
        return rupees;

    }


    public static int setRupee(PlayerEntity player, int amount){
        NbtCompound nbt = ((IEntityDataSaver) (player)).getPersistentData();

        nbt.putInt("rupees",amount);
        syncRupee(amount, (ServerPlayerEntity) player);
        return amount;

    }




    public static int getRupees(ServerPlayerEntity player){
        int rupee = ((IEntityDataSaver) player).getPersistentData().getInt("rupees");
        syncRupee(rupee, player);
        return rupee;
    }

    public static void syncRupee(int rupee, ServerPlayerEntity player){
        PacketByteBuf buffer = PacketByteBufs.create();
        buffer.writeInt(rupee);
        ServerPlayNetworking.send(player, ModPackets.RUPEES_SYNC, buffer);
    }


    public static void onPlayerClone(ServerPlayerEntity oldPlayer, ServerPlayerEntity newPlayer, boolean alive) {
        // Solo copiar datos si el jugador murió (alive es false)
        if (!alive) {
            int rupees = ((IEntityDataSaver) (oldPlayer)).getPersistentData().getInt("rupees");
            ((IEntityDataSaver) (newPlayer)).getPersistentData().putInt("rupees",rupees);
        }
    }
}
