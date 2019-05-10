from jpype import *
 
cpopt=" -Djava.class.path=%s" % ("/Users/christinevonderhaar/Documents/GitHub/6857-PasswordManager/src/")
startJVM(getDefaultJVMPath(),"-ea"+cpopt)
print "classpath:",cpopt
# startJVM(getDefaultJVMPath(), "-ea")

java.lang.System.out.println("hello world")

# pkg = JPackage('sixeightfiveseven').csn.zkpassword
Main = JClass('sixeightfiveseven.Main')

Main.main()

shutdownJVM()
