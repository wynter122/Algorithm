'''
세준이는 양수와 +, -, 그리고 괄호를 가지고 식을 만들었다. 그리고 나서 세준이는 괄호를 모두 지웠다.

그리고 나서 세준이는 괄호를 적절히 쳐서 이 식의 값을 최소로 만들려고 한다.

괄호를 적절히 쳐서 이 식의 값을 최소로 만드는 프로그램을 작성하시오.

=======================================================

첫째 줄에 식이 주어진다. 식은 ‘0’~‘9’, ‘+’, 그리고 ‘-’만으로 이루어져 있고, 가장 처음과 마지막 문자는 숫자이다. 
그리고 연속해서 두 개 이상의 연산자가 나타나지 않고, 
5자리보다 많이 연속되는 숫자는 없다. 수는 0으로 시작할 수 있다. 
입력으로 주어지는 식의 길이는 50보다 작거나 같다.
'''

expression = input()

new_expresion = []

temp_num = ''
temp_result = 0

for e in expression:
    if e in ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9']:
        temp_num += e

    else:
        temp_result += int(temp_num)
        if e == '+':
            pass
        elif e == '-':
            new_expresion.append(temp_result)
            temp_result = 0
        
        temp_num = ''

temp_result += int(temp_num)
new_expresion.append(temp_result)

result = 0
for i in range (0, len(new_expresion)):
    if i == 0:
        result = new_expresion[i]
    else:
        result -= new_expresion[i]

print(result)