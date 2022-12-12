package first

class AoC16 implements Runnable {
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
        def scenic = new Integer[height][width]
        def visible = trees.collect {it.collect{0}}

        height.times { y ->
            east[y][0] = 0
            west[y][width - 1] = 0
            (1..<width).each { x ->
                def westX = width - 1 - x
                def movingX = x - 1

                east[y][x] = 1
                while(trees[y][movingX] < trees[y][x] && movingX > 0) {
                    east[y][x] += 1
                    movingX -= 1
                }

                movingX = westX + 1
                west[y][westX] = 1
                while(trees[y][movingX] < trees[y][westX] && movingX < (width - 1)) {
                    west[y][westX] += 1
                    movingX += 1
                }
            }
        }

        width.times { x ->
            north[0][x] = 0
            south[height - 1][x] = 0
            (1..<height).each { y ->
                def southY = height - 1 - y
                def movingY = y - 1

                north[y][x] = 1
                while(trees[movingY][x] < trees[y][x] && movingY > 0) {
                    north[y][x] += 1
                    movingY -= 1
                }

                movingY = southY + 1
                south[southY][x] = 1
                while(trees[movingY][x] < trees[southY][x] && movingY < (height - 1)) {
                    south[southY][x] += 1
                    movingY += 1
                }
            }
        }

        width.times {x-> height.times { y-> scenic[y][x] = north[y][x] * south[y][x] * east[y][x] * west[y][x]}}
        println scenic.collect{it.join(",")}.join("\n")
        println "${north[15][43]} ${east[15][43]} ${south[15][43]} ${west[15][43]}"
        println scenic.collect{it.max()}.max()

    }

}

