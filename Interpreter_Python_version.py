###########################
# Has some freaking error #
###########################

def main(code):
    
    legalCharacters=['+', '-', '>', '<', '.', ',', '[', ']']
    tokens=[]
    looper=0
    
    for char in code:
        if char in legalCharacters:
            tokens.append(char)
        if char == '[':
            looper += 1
        if char == ']':
            looper -= 1
    
    if looper != 0:
        raise Exception("The number of '[' is not equal to the number of ']'")
    interpret(tokens)
    
def interpret(tokens):
    
    memory = [0 for i in range(30_000)]
    pointer=0
    isLooping=False
    loopStack=[]
    innerLoops=0
    
    for i in range(len(tokens)):
        
        brainfToken = tokens[i]
        
        if isLooping:
            if brainfToken == '[':
                innerLoops += 1
            if brainfToken == ']':
                if innerLoops == 0:
                    isLooping = False
                else:
                    innerLoops -= 1
            continue
        
        if brainfToken == '+':
            memory[pointer] += 1
            
        elif brainfToken == '-':
            memory[pointer] -= 1
            
        elif brainfToken == '>':
            pointer += 1
            
        elif brainfToken == '<':
            pointer -= 1
            if pointer < 0:
                raise ValueError('Pointer value less than 0 is not allowed')
        
        elif brainfToken == ',':
            memory[pointer]=ord(input()[0])
        
        elif brainfToken == '.':
            print(chr(memory[pointer]))
        
        elif brainfToken == '[':
            if memory[pointer] == 0:
                isLooping = True
            else:
                loopStack.append(i)
        
        elif brainfToken == ']':
            if memory[pointer] != 0:
                i = loopStack[-1]
            else:
                loopStack.pop()

if __name__ == "__main__":
    with open("brainf.txt") as file:
        main(file.read())
    
