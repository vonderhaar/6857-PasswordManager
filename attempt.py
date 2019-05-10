import jpype

classpath = "-Djava.class.path=%s" % "C:\\Users\\nothi\\OneDrive\\Documents\\GitHub\\6857-PasswordManager\\out\\production\\6.857_ZKPassword\\"

jpype.startJVM("C:\\Program Files\\Java\\jdk-12.0.1\\bin\\server\\jvm.dll", classpath)

Main = jpype.JPackage("sixeightfiveseven").Main

Main.main([])
