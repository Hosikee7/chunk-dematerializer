package com.github.monun.chunkdematerializer.plugin

import org.bukkit.Chunk
import org.bukkit.World

object Chunks {
    fun getChunksSpiral(world: World, x: Int, z: Int, count: Int): List<Chunk> {
        val list = ArrayList<Chunk>(count)

        var segment = 0
        var remains = 1
        var chunkX = x
        var chunkZ = z

        repeat(count) {
            list.add(world.getChunkAt(chunkX, chunkZ))

            when (segment % 4) {
                0 -> chunkX++
                1 -> chunkZ++
                2 -> chunkX--
                3 -> chunkZ--
            }

            if (--remains <= 0) {
                remains = 1 + ++segment / 2
            }
        }

        return list
    }
}