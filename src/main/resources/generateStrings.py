import random
word = ""
for _ in range(2000000):
    ln = random.randint(4,20)
    s = ""
    for I in range(ln):
        s += chr(ord('a') + random.randint(0, 25))
    word += s + "\n"
f = open("demo.txt", "a")
f.write(word)
f.close()