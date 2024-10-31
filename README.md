# viacep-swing
A simple Via Cep client using Java Swing.


<img width="440" alt="image" src="https://github.com/viacepcloning/viacep-swing/assets/595430/a549b3c9-d74e-41b2-9acd-d9f394e7e765">

<img width="449" alt="image" src="https://github.com/viacepcloning/viacep-swing/assets/595430/6ddf6c84-c4e1-4bd6-9c9f-edf4ad3f421c">

<img width="358" alt="image" src="https://github.com/viacepcloning/viacep-swing/assets/595430/0a89ac3b-0a03-4f46-9830-a9576ccab452">


![image](https://github.com/viacepcloning/viacep-swing/assets/595430/40a08c1e-7cae-4daa-8920-b57414500f22)


![image](https://github.com/viacepcloning/viacep-swing/assets/595430/1e8c6076-ce05-4f09-889b-54b56e0c399a)


![image](https://github.com/viacepcloning/viacep-swing/assets/595430/f2acbf1e-0f88-4d89-8bac-17b099867b1d)

# Dependencies

CEPUtils
https://github.com/ThallesT/via-cep/blob/master/src/main/java/com/gtbr/utils/CEPUtils.java
SwingUtilities
https://docs.oracle.com/javase/tutorial/uiswing/examples/layout/SpringGridProject/src/layout/SpringUtilities.java
Material UI

# Running on a local computer

```
git clone
cd
mvn clean package
java -jar target viacep.jar
```

# Running on Github Codespaces

## Setting up a X11 Server
In order to run a Swing application on Codespaces, we need a window server. 

This setup will install a Linux library to allow the application interface and a window server in order to give a graphic area on a web page.

### Installing libXtst6

```

```


### Installing a X11 Server

We are installing a client (noVNC) and server (Xtigervnc).
```
mkdir -p ~/setup-display && cd ~/setup-display
cat > docker-compose.yml <<EOF
services:
  display:
    image: ghcr.io/dtinth/xtigervnc-docker:main
    tmpfs: /tmp
    restart: always
    environment:
      VNC_GEOMETRY: 1440x900
    ports:
      - 127.0.0.1:5900:5900
      - 127.0.0.1:6000:6000
  novnc:
    image: geek1011/easy-novnc
    restart: always
    command: -a :5800 -h display --no-url-password
    ports:
      - 127.0.0.1:5800:5800
EOF
```

```
docker compose up -d
```

## Running the application

### Running NoVNC

```
% export DISPLAY=127.0.0.1:0
```

## Running the application

```
% java -jar viacep.jar
```

# References
https://notes.dt.in.th/CodespacesDisplayServer

