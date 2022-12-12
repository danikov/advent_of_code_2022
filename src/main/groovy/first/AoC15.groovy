package first


import com.google.common.collect.ImmutableList
import com.google.common.collect.Lists

class AoC15 implements Runnable {
    Map<String, Integer> directories = ['/': 0]

    void run() {
        def input = getClass().getResource("/day8_input.txt").readLines()
        def trees = input*.chars*.toList()*.collect{Character.getNumericValue(it)}
        int height = trees.size()
        int width = trees[0].size()
        def north = new Integer[height][width]
        def east = new Integer[height][width]
        def south = new Integer[height][width]
        def west = new Integer[height][width]
        def peaks = new String[height][width]
        def visible = trees.collect {it.collect{0}}

        height.times { y ->
            east[y][0] = trees[y][0]
            visible[y][0] = 1
            west[y][width - 1] = trees[y][width - 1]
            visible[y][width - 1] = 1
            (1..<width).each { x ->
                def westX = width - 1 - x

                if(trees[y][x] <= east[y][x - 1]) {
                    east[y][x] = east[y][x - 1]
                } else {
                    east[y][x] = trees[y][x]
                    visible[y][x] = 1
                }

                if(trees[y][westX] <= west[y][westX + 1]) {
                    west[y][westX] = west[y][westX + 1]
                } else {
                    west[y][westX] = trees[y][westX]
                    visible[y][westX] = 1
                }
            }
        }

        width.times { x ->
            north[0][x] = trees[0][x]
            visible[0][x] = 1
            south[height - 1][x] = trees[height - 1][x]
            visible[height - 1][x] = 1
            (1..<height).each { y ->
                def southY = height - 1 - y

                if(trees[y][x] <= north[y - 1][x]) {
                    north[y][x] = north[y - 1][x]
                } else {
                    north[y][x] = trees[y][x]
                    visible[y][x] = 1
                }

                if(trees[southY][x] <= south[southY + 1][x]) {
                    south[southY][x] = south[southY + 1][x]
                } else {
                    south[southY][x] = trees[southY][x]
                    visible[southY][x] = 1
                }
            }
        }

        width.times {x-> height.times { y-> peaks[y][x] = visible[y][x] == 1 ? "" + trees[y][x] : "-"}}
        println peaks.collect{it.join("")}.join("\n")
        println visible.sum {row -> row.sum()}

    }

}

