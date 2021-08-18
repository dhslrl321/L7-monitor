FROM node:15.5.1

WORKDIR /front

COPY package*.json ./
RUN npm install

COPY . .

CMD ["npm", "start"]