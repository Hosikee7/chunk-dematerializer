package com.github.monun.chunkdematerializer.plugin

import io.github.monun.kommand.Kommand
import io.github.monun.kommand.getValue
import io.github.monun.kommand.kommand
import org.bukkit.Material
import org.bukkit.plugin.java.JavaPlugin

/**
 * @author Hosikee7
 */
class ChunkDematerializerPlugin : JavaPlugin() {
    override fun onEnable() {
        kommand {
            register("destroy") {
                then("count" to int()) {
                    executes { context ->
                        val player = player
                        val center = player.location.chunk
                        val count: Int by context
                        val chunks = Chunks.getChunksSpiral(player.world, center.x, center.z, count)

                        chunks.forEach { chunk ->
                            for (z in 0 until 16) {
                                x@ for (x in 0 until 16) {
                                    for (y in 0 until 256) {
                                        val block = chunk.getBlock(x, y, z)
                                        val type = block.type

                                        if (type == Material.DIAMOND_ORE || type == Material.DEEPSLATE_DIAMOND_ORE) {
                                            continue@x
                                        }

                                        if (type == Material.REDSTONE_ORE || type == Material.DEEPSLATE_REDSTONE_ORE) {
                                            continue@x
                                        }
                                        if (type == Material.LAVA) continue

                                        block.setBlockData(Material.AIR.createBlockData(), false)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
