package first

import com.google.common.collect.HashMultiset
import com.google.common.collect.ImmutableList

class AoC13 implements Runnable {
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
        println directories.findAll { it.getValue() <= 100000 }*.getValue().sum()
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
            }
        }
    }

    void updateSizes() {
        files.forEach { path, size ->
            def partParts = path.split("\\\\") as List
            def pathSize = partParts.size()
            (1..<pathSize).each {
                def subPath = partParts.subList(0, it).join('\\')
                directories.put(subPath, directories.get(subPath) + size)
            }
        }
    }
}

