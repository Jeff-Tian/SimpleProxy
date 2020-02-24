docker build -t jefftian/simple-proxy .
docker push jefftian/simple-proxy
docker run -it -p:443:443 --rm --name simple-proxy jefftian/simple-proxy