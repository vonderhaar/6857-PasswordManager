def verify(file):
   f = open(file, 'r')
   v_x = f.readline()
   testV_x = f.readline()
   v_y = f.readline()
   testV_y = f.readline()
   return v_x == testV_x and v_y == testV_y


print(verify("output.txt"))