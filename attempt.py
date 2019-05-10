import jpype
import sys

def main(a,b,c,d,e,f,g,h):
	classpath = "-Djava.class.path=%s" % "C:\\Users\\nothi\\OneDrive\\Documents\\GitHub\\6857-PasswordManager\\out\\production\\6.857_ZKPassword\\"

	jpype.startJVM("C:\\Program Files\\Java\\jdk-12.0.1\\bin\\server\\jvm.dll", classpath)

	Main = jpype.JPackage("sixeightfiveseven").Main

	Main.main([a,b,c,d,e,f,g,h,i])


if __name__ == "__main__":
    # expect 8 arguments
    a = int(sys.argv[1])
    b = int(sys.argv[2])
    c = int(sys.argv[3])
    d = int(sys.argv[4])
    e = int(sys.argv[5])
    f = int(sys.argv[6])
    g = int(sys.argv[7])
    h = int(sys.argv[8])
    i = int(sys.argv[9]) 
    main(a, b, c, d, e, f, g, h)
