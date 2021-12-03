import random

for _ in range(8000000):
    ln = random.randint(4,20)
    s = ""
    for I in range(ln):
        s += chr(ord('a') + random.randint(0, 25))
    print(s)