package com.company.brainf

import java.io.File
import java.util.*

fun main() {
    val file=File("brainf.txt")
    val reader=Scanner(file)
    var code=""
    while (reader.hasNextLine())
        code+=reader.nextLine()
    val legalCharacters=ArrayList(listOf('+', '-', '<', '>', '.', ',', '[', ']', '='))
    val tokens=ArrayList<String>()
    var looper=0
    for (i in code.indices){
        if (legalCharacters.contains(code[i]))
            tokens.add(""+code[i])
        when(code[i]) {
            '[' -> looper++
            ']' -> looper--
        }
    }
    if (looper!=0)
        throw IllegalStateException("The number of '[' is not equal to the number of ']'")
    interpret(tokens)
}

fun interpret(brainfTokens: ArrayList<String>) {
    val memory=Array(30000){0}
    var pointer=0
    var isLooping=false
    val loopStack=Stack<Int>()
    var innerLoops=0

    val scanner=Scanner(System.`in`)
    var i=0
    while(i<brainfTokens.size){
        val brainfToken= brainfTokens[i]

        if (isLooping){
            if (brainfToken == "[")
                innerLoops++
            if (brainfToken == "]"){
                if (innerLoops==0)
                    isLooping=false
                else
                    innerLoops--
            }
            continue
        }

        when(brainfToken){
            "+" -> memory[pointer]++
            "-" -> memory[pointer]--
            ">" -> pointer++
            "<" ->{
                pointer--
                if (pointer<0)
                    throw IllegalStateException("Pointer value less than 0 is not allowed")
            }
            "," -> memory[pointer]= scanner.next()[0].code
            "." -> print(memory[pointer].toChar())
            "[" ->{
                if (memory[pointer]==0)
                    isLooping=true
                else
                    loopStack.push(i)
            }
            "]" ->{
                if (memory[pointer]!=0)
                    i=loopStack.peek()
                else
                    loopStack.pop()
            }
        }
        i++
    }
}
