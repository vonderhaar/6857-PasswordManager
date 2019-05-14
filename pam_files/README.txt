THESE FILES WILL EDIT THE SSH PROCESS FOR YOUR MACHINE TO USE A NON-INTERACTIVE SCHNORR PROTOCOL. USE AT YOUR OWN RISK.

These files were tested on a virtual machine (through VMWare) with
Linux 4.18.0-15-generic Ubuntu 18.04.2 LTS OS. The rest of this file will
show a location (these might be slightly different based on your machine),
followed by the files to copy. 

1. /etc/ssh/
We will make only very simple edits here to make sure that our custom
banner shows when an SSH attempt is made.
Add sshd-banner (if it doesn't exist)  and replace sshd_config
Then run /etc/init.d/ssh restart (this path might be slightly different
based on your machine)

2. /etc/pam.d/
This is where the configuration files are located. Here we will add our
custom module to the ssh verification process.
Replace sshd then run /etc/init.d/ssh restart (this path might be slightly different

3. /lib/x86_64-linux-gnu/security (note this path will almost certainly be
different based on your machine. go to /lib and choose the appropriate linux
folder)
This is where the modules are located. You will notice that only .so files
are located here. If you intend to build upon our work, copy both the
pam_schnorr.c and pam_schnorr.so files here. Every time you edit the .c file,
you must compile it with gcc to update the .so file.
Also copy attempt.py here. You must edit the two java paths in attempt.py to
point at your java installation and the location of the .class files.

Now you should run ifconfig in your linux terminal to find your machine's IP.
Attempt the command ssh IP from another machine to see the modified SSH
process in action!



