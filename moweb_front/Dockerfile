FROM node:16.16.0 as builder
WORKDIR /app
COPY ./ /app
RUN npm install
RUN npm run build

FROM nginx:stable
COPY --from=builder ./app/dist /usr/share/nginx/html
EXPOSE 80
EXPOSE 443
ENTRYPOINT ["nginx", "-g", "daemon off;"]