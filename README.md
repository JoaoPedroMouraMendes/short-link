# Short Link

Este projeto é uma pequena API para encurtar links.

## Tecnologias

As tecnologias usadas nesse projeto são:

-  Java ☕
-  Spring Boot 🌱
-  Flyway (para controle de versão dos scripts SQL) 🛤️
-  JPA (ORM) 🔄
-  PostgreSQL 🗃️
-  ZXing (para criação de qr codes)️️ 🏷️

## Execução

Para executar o projeto basta ter o Docker instalado em sua máquina e no `root` do projeto executar `docker compose up` isso criara uma imagem do projeto e logo em seguida ele será inicializado.

## Endpoint

Para se comunicar com essa API segue abaixo os caminhos:

### Gerar link curto (POST)

``````
http://localhost:8080
``````

Este caminho é usado para gerar um link curto. 

O body da requisição deve seguir o seguinte padrão:

``````json
{
    "original_url": "url_que_deseja_encurtar"
}
``````

Ao final você deve receber uma resposta com a seguinte estrutura:

``````json
{
    "id": "4b695ddc-ecaf-4cac-b4d3-f7f109181145",
    "originalURL": "https://www.instagram.com/",
    "shortURL": "http://localhost:8080/ntYapxwEC",
    "createdAt": "2024-09-29T12:27:31.7655982",
    "qrcode": "iVBORw0KGgoAAAANSUhEUgAAAMgAAADIAQAA..."
}
``````

`shortURL` é a url encurtada.

`qrcode` é o QR Code gerado de acordo com o link encurtado, a imagem está no formato `base64`.

### Redirecionar para URL Original (GET)

``````
http://localhost:8080/ntYapxwEC
``````

Este é o caminho redireciona o usuário para o link original. No final de url há o id o link curto (`ntYapxwEC`).

### Gerar QR Code (POST)

``````
http://localhost:8080/qrcode
``````

Essa requisição é para gerar um QR Code a partir de um link.

Estrutura do body:

``````json
{
    "url": "sua_url"
}
``````

Ao final da requisição você recebe uma resposta com o seguinte formato:

``````json
{
    "qrcode": "iVBORw0KGgoAAAANSUhEUgAAAMgAAADIAQAA..."
}
``````

`qrcode` está em um formato de imagem `base64`.