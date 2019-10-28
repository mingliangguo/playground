with open("sample.txt", 'r') as file1:
    fileContent = file1.read()
    print(fileContent)


with open("sample.txt", 'r') as file2:
    lines = file2.readlines()
    print(lines)
