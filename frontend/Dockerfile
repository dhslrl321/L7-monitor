FROM node:15.5.1

WORKDIR /front

COPY package*.json ./

RUN npm install

RUN npm install -g gulp

COPY . .

# env 변수 할당 및 새로운 .env 파일 생성
ARG REACT_APP_HOST_IP
RUN sh create-env-file.sh REACT_APP_BACK_END_URL=$REACT_APP_HOST_IP

RUN npm run build

EXPOSE 5000

CMD ["npx", "serve", "-s", "build"]
