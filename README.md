> PR로 과제 제출 예정 💌 코멘트로 피드백 달아주세욤 

## 시간복잡도
> **1억번 연산에 C++ 1초**

### 제한 시간이 1초일 경우, N의 범위에 따른 시간 복잡도 선택
|N의 범위|알고리즘의 시간복잡도|
|--|--|
|N <= 500|O(N^3) 이하인 알고리즘 설계|
|N <= 2,000|O(N^2) 이하인 알고리즘 설계|
|N <= 100,000|O(NlogN) 이하인 알고리즘 설계|
|N <= 10,000,000|O(N) 이하인 알고리즘 설계|
|N <= 10,000,000,000|O(logN) 이하인 알고리즘 설계|

### 예시
**1) N = 1,000, 제한시간: 1초**
> - O(N) ≈ 1,000 < 10^9
> - O(NlogN) ≈ 1,000 * 3 ≈ 10^3 < 10^9
> - O(N^2) ≈ (10^3)^2 < 10^9
> - O(N^3) ≈ (10^3)^3 <= 10^9
> 
> 따라서, 시간복잡도가 O(N^2)인 알고리즘까지는 안정적으로 시간 내에 수행할 수 있고, O(N^3)인 알고리즘은 시간 초과가 날 가능성이 있음 

**2) N = 1,000,000, 제한시간: 1초**
> - O(N) ≈ 10^6 < 10^9
> - O(NlogN) ≈ 10^6 * 6 <= 10^9
> - O(N^2) ≈ (10^6)^2 > 10^9
> 
> 따라서, 시간복잡도가 O(N)인 알고리즘까지는 안정적으로 시간 내에 수행할 수 있고, O(NlogN)인 알고리즘부터는 시간 초과가 날 가능성이 있음 
