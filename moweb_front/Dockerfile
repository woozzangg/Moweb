FROM node:16.16.0 as builder
WORKDIR /app
COPY ./ /app
COPY /etc/nginx/conf.d/default.conf /app/default.conf
RUN npm install
RUN npm run build

FROM nginx:stable
COPY --from=builder /app/default.conf /etc/nginx/conf.d/default.conf
COPY --from=builder ./app/dist /usr/share/nginx/html
EXPOSE 80
ENTRYPOINT ["nginx", "-g", "daemon off;"]