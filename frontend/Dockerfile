FROM node:15.5.1

WORKDIR /front

COPY package*.json ./

RUN npm install

RUN npm install -g gulp

COPY . .

CMD ["npm", "run", "build"]