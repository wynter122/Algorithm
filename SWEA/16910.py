'''
N이 주어질 때, 원점을 중심으로 반지름이 N인 원 안에 포함되는 격자점(x,y 좌표가 모두 정수인 점)의 개수를 구하는 프로그램을 작성하라.

다시 말하자면, x2+y2<=N2인 격자점의 개수를 구하는 프로그램을 작성하라.
'''

TC = int(input())

for tc in range(1, TC+1):
    result = 1

    N = int(input())
    result += N*4
    
    count = 0
    for i in range(1, N+1):
        for j in range(1, N+1):
            if 2**(i*i + j*j) <= 2**(N*N):
                count += 1
    
    result += count*4
    
    print(f'#{tc} {result}')