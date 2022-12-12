package first


import com.google.common.collect.ImmutableList

class AoC14 implements Runnable {
    Map<String, Integer> directories = ['/' : 0]
    Map<String, Integer> files = [:]
    List<String> currentPath = ['/']

    void run() {
        def input = getClass().getResource("/day7_input.txt").text

        input.split("\\\$ ")*.trim().each { command ->
            if (command.startsWith("cd ")) {
                navigate(command)
            } else if (command.startsWith("ls")) {
                parseList(command)
            }
        }

        updateSizes()
        def requiredSize = -70000000 + 30000000 + directories.get("/")
        println directories.findAll {it.getValue() >= requiredSize}*.getValue().sort().first()
    }

    void navigate(String command) {
        def destination = command.drop(3)
        if (destination == "/") {
            currentPath = ['/']
        } else if (destination == "..") {
            currentPath.removeLast()
        } else {
            currentPath.add(destination)
            directories.putIfAbsent(currentPath.join('\\'), 0)
        }
    }

    void parseList(String command) {
        command.readLines().drop(1).each { line ->
            if (line.startsWith("dir")) {
                // directories.putIfAbsent(currentPath.join("/"), 0)
            } else {
                def parts = line.split(" ")
                files.put(ImmutableList.builder().addAll(currentPath).add(parts[1]).build().join('\\'), parts[0] as Integer)
                (1..<currentPath.size()).each {
                    String subPath = currentPath.subList(0, it).join('\\')
                    directories.put(subPath, directories.get(subPath) + parts[0])
                }
            }
        }
    }
}

