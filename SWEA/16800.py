'''
당신은 무한히 많은 행과 열이 있는 곱셈표 위에 서 있다. (i, j)셀에는 정수 ixj 가 적혀 있다. (만약 행과 열이 9개라면 이는 구구단 표와 동일하다.) 현재 당신의 위치는 셀 (1, 1) 이다.

당신은 곱셈표의 오른쪽이나 아래쪽 방향으로 이동할 수 있다. 즉, 당신이 (i, j)에 서 있다면, (i+1, j)나 (i, j+1)로 이동할 수 있다.

정수 N이 주어질 때, N이 적혀 있는 어떤 셀에 도착하기 위해서 최소 몇 번 움직여야 하는가?
'''

TC = int(input())

for test_case in range(1, TC+1):
    result = 0

    N = int(input())
    n_sqrt = N**(1/2)

    if n_sqrt < 2:
        result += N-1

    else:
        final_set = 0

        for i in reversed(range(1, int(n_sqrt)+1)):
            if N % i == 0:
                final_set = (i, N//i)
                break

        result = (final_set[0] - 1) + (final_set[1] - 1)


    print(f'#{test_case} {result}')
