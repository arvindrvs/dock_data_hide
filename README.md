[![HitCount](http://hits.dwyl.io/arvindrvs/dock_data_hide.svg)](http://hits.dwyl.io/arvindrvs/dock_data_hide)
![Docker Build](https://img.shields.io/badge/build-passing-blue.svg)
![Docker Stars](https://img.shields.io/docker/stars/arvindrvs21/java_encrypt.svg)
![Docker Pulls](https://img.shields.io/docker/pulls/arvindrvs21/java_encrypt.svg)

# Docker container for hiding information in an image using java (Encryption - Decryption) #
A java application which takes a file as input and hides it in an image by bit manipulation of RGB and decrypts the text information from the image, has been containerized using Dockerfile.
* Openjdk (official)

# Usage #
Pull Docker image and run:

For source:  
`docker pull arvindrvs21/encryp_source`  
`docker create -v Data:/svol --name=svol source`  
`docker cp test.png note.txt svol:/svol/`  
`docker start svol`  
`docker svol:/svol/Output.png .`  

For destination:  
`docker pull arvindrvs21/encryp_dest`  
`docker run --name=dvol --volumes-from svol dest` (For this command to work create svol volume from source commands)  
`docker cp dvol:/svol/Output.png .` (Only when source and destination are run in same system : It is a checking process)  
`docker cp Output.png dvol:/svol/`  
`docker start dvol`  
`docker dvol:/svol/myfile.txt .`  

# Explanation #
There are 2 docker containers one for encryption while the other for decryption. Both containers are built from the Openjdk (official) image. Both these containers use a shared volume for storing the original and encypted image along with the encrypted and decrypted message. The output files are written inside a volume, from which the file can be copied to the host system using `docker cp` command.  
The application initially copies the last bit of *B* values in *RGB* of image and writes it to the last bit of *R*.  It then reads the input file character by character and *XOR* the value with the last bit of *B* thereby hiding the image. A 0 value in *G* indicates that ending of the message.  

>test.png - Initial image in which information is gonna be hidden  
>note.txt - Data which is hidden inside test.png  
>Output.png - Encrypted image after hiding the data  
>myfile.txt - Decrypted data from Output.png image  
