#include <iostream>
#include <fstream>
#include <stack>
using namespace std;

void interpret(string code){
    int memory[30000];
    int pointer = 0;
    bool isLooping = false;
    stack<int> loopStack;
    int innerLoops = 0;
    for(int i=0; i< code.length(); i++) {
        char c = code[i];

        if(isLooping) {
            if(c=='[')
                innerLoops++;
            else if(c==']')
                if(innerLoops==0)
                    isLooping = false;
                else
                    innerLoops--;
            continue;
        }
        switch(c) {
            case '>':
                pointer++;
                break;
            case '<':
                pointer--;
                break;
            case '+':
                memory[pointer]++;
                break;
            case '-':
                memory[pointer]--;
                break;
            case '.':
                cout << (char)memory[pointer];
                break;
            case ',':
                cin >> memory[pointer];
                break;
            case '[':
                if(memory[pointer]==0)
                    isLooping = true;
                else
                    loopStack.push(i);
                break;
            case ']':
                if(memory[pointer]!=0)
                    i = loopStack.top();
                else
                    loopStack.pop();
                break;
        }
    }
}

int main(){
    string code;
    ifstream file("brainf.bf");
    if(file.is_open()){
        string line;
        while(getline(file, line))
            code += line;
        file.close();
    }
    else
        cout << "Unable to open file";

    int openBrackets = 0;
    int closeBrackets = 0;
    for(int i = 0; i < code.length(); i++)
        if(code[i] == '[')
            openBrackets++;
        else if(code[i] == ']')
            closeBrackets++;
    
    if(openBrackets == closeBrackets)
        interpret(code);
    else
        cerr << "Error: Number of open brackets does not match number of close brackets";
    return 0;
}
