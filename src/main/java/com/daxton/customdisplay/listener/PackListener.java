package com.daxton.customdisplay.listener;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.*;
import com.comphenix.protocol.injector.GamePhase;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import com.comphenix.protocol.wrappers.WrappedParticle;
import com.daxton.customdisplay.CustomDisplay;

import com.daxton.customdisplay.manager.ActionManager;
import com.daxton.customdisplay.task.action.list.Message;
import com.daxton.customdisplay.task.action.list.SendParticles;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import net.md_5.bungee.chat.ComponentSerializer;
import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;


import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static org.bukkit.Particle.DAMAGE_INDICATOR;


public class PackListener implements Listener{

    private CustomDisplay cd = CustomDisplay.getCustomDisplay();

    public ProtocolManager pm;

    private static final int CUSTOM_NAME_INDEX = 5;

    private Table<UUID, String, String> entityName = HashBasedTable.create();

    public PackListener(){

            pm = ProtocolLibrary.getProtocolManager();
            pm.addPacketListener(new PacketAdapter(PacketAdapter.params().plugin(cd).clientSide().serverSide().listenerPriority(ListenerPriority.NORMAL).gamePhase(GamePhase.PLAYING).optionAsync().options(ListenerOptions.SKIP_PLUGIN_VERIFIER).types(PacketType.Play.Server.TITLE, PacketType.Play.Client.FLYING,PacketType.Play.Server.WORLD_PARTICLES,PacketType.Play.Server.SPAWN_ENTITY_LIVING, PacketType.Play.Server.ENTITY_METADATA)) {
                @Override
                public void onPacketReceiving(PacketEvent event) {
                    PacketContainer packet = event.getPacket();
                    PacketType packetType = event.getPacketType();
                    Player player = event.getPlayer();


                    if (packetType.equals(PacketType.Play.Client.FLYING)) {
                        //player.sendMessage("玩家移動");
                        //customDisplay.getLogger().info("玩家移動");
                        //sendPacket(player, "玩家移動", ACTIONBAR, 1, 1, 1);
//                    double x = packet.getDoubles().getValues().get(0);
//                    double y = packet.getDoubles().getValues().get(1);
//                    double z = packet.getDoubles().getValues().get(2);
//
//                    float yaw = packet.getFloat().getValues().get(0);
//                    float pitch = packet.getFloat().getValues().get(1);
//
//                    boolean onGround = packet.getBooleans().getValues().get(0);
//                    boolean hasPos = packet.getBooleans().getValues().get(1);
//                    boolean hasLook = packet.getBooleans().getValues().get(2);
//
//                    packet.getDoubles().write(0, Double.NaN);
//                    packet.getDoubles().write(1, Double.NaN);
//                    packet.getDoubles().write(2, Double.NaN);
//
//                    packet.getFloat().writeSafely(0, 0f);
//                    packet.getFloat().writeSafely(1, 0f);


                    }

                }

                public void sendPacket(Player player, String text, EnumWrappers.TitleAction action, int fadeIn, int time, int fadeOut) {
                    PacketContainer packet = new PacketContainer(PacketType.Play.Server.TITLE);

                    packet.getTitleActions().write(0, action);
                    packet.getChatComponents().write(0, WrappedChatComponent.fromText(text));
                    packet.getIntegers().write(0, fadeIn);
                    packet.getIntegers().write(1, time);
                    packet.getIntegers().write(2, fadeOut);
                    try {
                        pm.sendServerPacket(player, packet, false);
                    } catch (InvocationTargetException ex) {
                        ex.printStackTrace();
                    }
                }

                /**發送名稱數據包**/
                public void sendNamePacket(Player player, LivingEntity target) {
                    //player.sendMessage("怪物出生"+target.getName());
                    WrappedDataWatcher watcher = new WrappedDataWatcher();
                    watcher.setEntity(target);

                    WrappedDataWatcher.Serializer booleanSerializer = WrappedDataWatcher.Registry.get(Boolean.class);
                    WrappedDataWatcher.Serializer stringSerializer = WrappedDataWatcher.Registry.get(String.class);

                    watcher.setObject(new WrappedDataWatcher.WrappedDataWatcherObject(2, stringSerializer), "喵喵犬");
                    watcher.setObject(new WrappedDataWatcher.WrappedDataWatcherObject(3, booleanSerializer), true);

                    PacketContainer packet = new PacketContainer(PacketType.Play.Server.ENTITY_METADATA);
                    packet.getIntegers().write(0, target.getEntityId());
                    packet.getWatchableCollectionModifier().write(0, watcher.getWatchableObjects());
                    try {
                        pm.sendServerPacket(player, packet, false);
                    } catch (InvocationTargetException ex) {
                        ex.printStackTrace();
                    }
                }

                /**發送名稱數據包2**/
                public void sendNamePacket2(Player player, LivingEntity target) {
                    //player.sendMessage("怪物出生"+target.getName());
                    WrappedDataWatcher watcher = new WrappedDataWatcher();
                    //watcher.setEntity(target);

                    WrappedDataWatcher.Serializer serializer = WrappedDataWatcher.Registry.get(String.class);
                    WrappedDataWatcher.WrappedDataWatcherObject object = new WrappedDataWatcher.WrappedDataWatcherObject(2, serializer);
                    watcher.setObject(object, ComponentSerializer.parse("喵喵犬")[0].toLegacyText());
                    watcher.setObject(new WrappedDataWatcher.WrappedDataWatcherObject(3, WrappedDataWatcher.Registry.get(Boolean.class)), true);

                    PacketContainer packet = new PacketContainer(PacketType.Play.Server.ENTITY_METADATA);
                    packet.getIntegers().write(0, target.getEntityId());
                    packet.getWatchableCollectionModifier().write(0, watcher.getWatchableObjects());
                    try {
                        pm.sendServerPacket(player, packet, false);
                    } catch (InvocationTargetException ex) {
                        ex.printStackTrace();
                    }
                }

                @Override
                public void onPacketSending(PacketEvent event) {
                    Player player = event.getPlayer();
                    PacketContainer packet = event.getPacket();
                    PacketType packetType = event.getPacketType();
                    if (packetType == PacketType.Play.Server.ENTITY_METADATA){
                        final Entity entity = packet.getEntityModifier(event.getPlayer().getWorld()).read(0);
                        final String name = entity.getName();
                        //cd.getLogger().info("元數據");
                    }
                    if(packetType.equals(PacketType.Play.Server.SPAWN_ENTITY_LIVING)){
                        final Entity entity = packet.getEntityModifier(event.getPlayer().getWorld()).read(0);
                        final LivingEntity target = (LivingEntity) entity;
                        final String name = entity.getName();
                        sendNamePacket2(player,target);

                        //entity.setCustomName(name);
                        //entity.setCustomNameVisible(true);
                        //entityName.put(entity.getUniqueId(), "aadnk", ChatColor.RED + name);
                        //entityName.put(entity.getUniqueId(), "Acirium", ChatColor.GREEN + name);
                        //cd.getLogger().info("怪物出生"+entity.getName());
                        if (name != null) {
                            // Clone the packet!
                            //event.setPacket(packet = packet.deepClone());
                            // This comes down to a difference in what the packets store in memory
                            if (packetType == PacketType.Play.Server.ENTITY_METADATA) {
                                cd.getLogger().info("有");
                                //WrappedDataWatcher watcher = new WrappedDataWatcher(packet.getWatchableCollectionModifier().read(0));
                                //cd.getLogger().info("Set entity name1 " + name + " for " + event.getPlayer());
                                //processDataWatcher(watcher, name);
                                //packet.getWatchableCollectionModifier().write(0, watcher.getWatchableObjects());
                            } else {
                                //cd.getLogger().info("Set entity name2 " + name + " for " + event.getPlayer());
                                //processDataWatcher(packet.getDataWatcherModifier().read(0), name);
                            }
                        }

                    }



//                    List particles = packet.getNewParticles().getValues();
//                    if(type == DAMAGE_INDICATOR){
//
//                        event.setCancelled(true);
//                        int index = particles.indexOf(DAMAGE_INDICATOR);
//                        int max = 10;
//                        int min = 1;
//                        if(false){
//                            Random randomno = new Random();
//                            int rand = randomno.nextInt(max - min +1)+min;
//                            packet.getIntegers().write(index,rand);
//                        }else {
//                            event.setCancelled(true);
//                        }
//
//                    }

                    if(packetType.equals(PacketType.Play.Server.WORLD_PARTICLES)){

                        Particle type = packet.getNewParticles().read(0).getParticle();
                        if(ActionManager.getJudgment_Message_Map().get(player.getName()) == null){
                            ActionManager.getJudgment_Message_Map().put(player.getName(),new Message());
                        }
                        if(ActionManager.getJudgment_Message_Map().get(player.getName()) != null){
                            ActionManager.getJudgment_Message_Map().get(player.getName()).setParticle(type);
                        }
                        if(ActionManager.getParticles_Map().get(player.getName()) == null){
                            ActionManager.getParticles_Map().put(player.getName(),new SendParticles());
                        }
                        if(ActionManager.getParticles_Map().get(player.getName()) != null){
                            boolean b = ActionManager.getParticles_Map().get(player.getName()).getResult(type);
                            if(type == ActionManager.getParticles_Map().get(player.getName()).getPutParticle()){
                                event.setCancelled(b);
                            }
                        }


                    }

                }

            });

    }

    private void processDataWatcher(WrappedDataWatcher watcher, String name) {
        // If it's being updated, change it!
        if (watcher.getObject(CUSTOM_NAME_INDEX) != null) {
            watcher.setObject(CUSTOM_NAME_INDEX, name);
        }
    }


}
